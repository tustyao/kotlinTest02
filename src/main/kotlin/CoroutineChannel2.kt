import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {
    //sampleStart
    val numbers = produceNumbers() // produces integers from 1 and on
    val squares = square(numbers) // squares integers
    for (i in 1..5) println(squares.receive()) // print first five
    println("Done!") // we are done
    // 协程上下文coroutineContext取消所有在其结构化中创建的所有子协程
    coroutineContext.cancelChildren() // cancel children coroutines
//sampleEnd
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // infinite stream of integers starting from 1
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}