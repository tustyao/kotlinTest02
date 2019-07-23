import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/**
 * 协程构建器有两种风格：自动的传播异常（launch 以及 actor） 或者将它们暴露给用户（async 以及 produce）。
 * 前者对待异常是不处理的，类似于 Java 的 Thread.uncaughtExceptionHandler， 而后者依赖用户来最终消耗异常
 * 异常处理者CoroutineExceptionHandler设置在 runBlocking 主作用域内启动的协程中是没有意义,
 * 因为主协程中启动的子协程抛出非CancellationException，会取消父协程,
 * runBlocking协程它被用来提供一个稳定的协程层次结构来进行结构化并发而无需依赖 CoroutineExceptionHandler 的实现
 * 在这个例子中，CoroutineExceptionHandler 总是被设置在由 GlobalScope 启动的协程中
 * val handler = CoroutineExceptionHandler { _, exception ->
 *     println("Caught $exception")
 * }
 * val job = GlobalScope.launch(handler) {
 *    throw AssertionError()
 * }
 */
fun main() = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException() // 我们将在控制台打印 Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用等待
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}