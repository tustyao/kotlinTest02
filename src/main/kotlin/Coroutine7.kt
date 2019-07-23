import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * 协程调度器，一般采用默认的调度器Dispatcher.Default
 * -Dkotlinx.coroutines.debug配置，打印当前线程名时，可以打印协程信息[main @coroutine#1]
 * 显示指定协程名字Dispatchers.Default + CoroutineName("test")
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking<Unit> {
    //sampleStart
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    coroutineScope {  }
    log("The answer is ${a.await() * b.await()}")
    //Creates a coroutine execution context using a single thread with built-in [yield] support
    newSingleThreadContext("ctx1").use { ctx1 ->
        newSingleThreadContext("ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                //使用witchContext更换协程的上下文线程，而仍然驻留在相同的协程中
                withContext(ctx2) {
                    //coroutineContext[Job] 获取协程上下文中的job
                    val job = coroutineContext[Job]
                    log("My job is ${job}")
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
//sampleEnd
}