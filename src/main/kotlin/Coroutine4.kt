import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {

    GlobalScope.launch {
        repeat(1000) {
            delay(500L)
            println(it)
        }

    }
    //主线程延迟1200毫秒后退出，不会等GlobalScope.launch启动的协程执行完，它启动协程的就像java中的守护线程
    delay(1200L)
}
