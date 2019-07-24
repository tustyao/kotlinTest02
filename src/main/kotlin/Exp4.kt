import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/**
 * SupervisorJob 异常只能向下传播，不会向上传播
 *
 * supervisorScope 监督作用域可以用来替代coroutineScope
 * 它只会单向的传播并且当子作业自身执行失败的时候将它们全部取消。它也会在所有的子作业执行结束前等待， 就像 coroutineScope 所做的那样
 */
fun main() = runBlocking {
    val supervisor = SupervisorJob()
    val receiver = CoroutineScope(coroutineContext + supervisor)
    with(receiver) {
        // launch the first child -- its exception is ignored for this example (don't do this in practice!)
        val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }
        // launch the second child
        val secondChild = launch {
            firstChild.join()
            // Cancellation of the first child is not propagated to the second child
            println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                // But cancellation of the supervisor is propagated
                println("Second child is cancelled because supervisor is cancelled")
            }
        }
        // wait until the first child fails & completes
        firstChild.join()
        println("Cancelling supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}