import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {
    //sampleStart
    //超时操作
    try {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("yichang")
    } finally {
    }
    //不会抛异常的超时操作
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
//sampleEnd
}
