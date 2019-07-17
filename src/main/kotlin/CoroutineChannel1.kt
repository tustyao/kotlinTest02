import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {
    //sampleStart
    //channel通道提供可挂起的send和receive函数
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        //数据发送完毕后，可关闭通道
        channel.close()
    }
    // repeat(5) { println(channel.receive()) }
    //for循环从通道取数据，取出关闭前的所有数据
    for (y in channel) {
        println(y)
    }
    //CoroutineScope.produce 生产ReceiveChannel
    val rc = produce {
        for (x in 1..5) send(x * x)
    }
    rc.consumeEach {
        println(it)
    }
    println("Done!")

    //sampleEnd
}
