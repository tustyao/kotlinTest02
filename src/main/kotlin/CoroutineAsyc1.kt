import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.system.measureTimeMillis

/**
 * launch函数启动协程返回一个后台job
 * async函数启动一个协程返回一个Deferred（一个轻量级非阻塞的future），它也是一个job，附带结果值
 * 在结构化并发中启动多个协程，当有异常发生，正在执行的协程会被父线程取消
 *
 * 注：
 * kotlin中强烈不建议使用Async-style functions，这样会导致非结构化并发，不能取消启动的后台协程，如下：
 *
 * // The result type of somethingUsefulOneAsync is Deferred<Int>
 *fun somethingUsefulOneAsync() = GlobalScope.async {
 *   doSomethingUsefulOne()
 *}

 * // The result type of somethingUsefulTwoAsync is Deferred<Int>
 *fun somethingUsefulTwoAsync() = GlobalScope.async {
 *  doSomethingUsefulTwo()
 *}
 * fun main() {
 *    val time = measureTimeMillis {
 *        // we can initiate async actions outside of a coroutine
 *        val one = somethingUsefulOneAsync()
 *        val two = somethingUsefulTwoAsync()
 *        // but waiting for a result must involve either suspending or blocking.
 *        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
 *        runBlocking {
 *            println("The answer is ${one.await() + two.await()}")
 *        }
 *    }
 *    println("Completed in $time ms")
 *}
 */
fun main() = runBlocking {
    var time = measureTimeMillis {
        //        var one = doSomethingUsefulOne()
//        var two = doSomethingUsefulTwo()
//        var one = async { doSomethingUsefulOne() }
//        var two = async { doSomethingUsefulTwo() }
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 执行一些计算
        //  one.start() // 启动第一个
        // two.start() // 启动第二个
        //当async函数的start参数为CoroutineStart.LAZY时，调用返回的Deferred的await获取结果也可以是协程启动（如果忘记调用start()）
        //此时协程将串行（一个协程await()启动并等待完成，然后继续）
        println("result=${one.await() + two.await()}")
    }

    println("cost time=$time")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(3000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

