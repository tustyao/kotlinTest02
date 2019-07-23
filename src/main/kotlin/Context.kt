import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */

val threadLocal = ThreadLocal<String?>() // declare thread-local variable

fun main() = runBlocking<Unit> {
    //sampleStart
    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    //需要将threadLocal获取上下文传递给新协程，新协程中将获取设置的值
    val job = launch(Dispatchers.Default + threadLocal.asContextElement("lanch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    job.join()
    // 协程中更新的值丢失，不会传递给调用者中
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
//sampleEnd
}