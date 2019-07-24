import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/**
 * SupervisorJob 异常只能向下传播，不会向上传播
 *
 * supervisorScope 监督作用域中 的子协程应该处理自身的异常，在于它不会传播到父作业中
 *
 */

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    supervisorScope {
        val child = launch(handler) {
            println("Child throws an exception")
            throw AssertionError()
        }
        val child2 = launch(handler) {
            println("Child2 throws an exception")
           delay(Long.MAX_VALUE)
        }
        println("Scope is completing")
    }
    println("Scope is completed")
}