import com.sun.applet2.preloader.CancelException
import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var i = 0;
        try {
            var nextPrintTime = startTime
            //手动检查是否取消了协程 方式一
//            while (isActive) { // computation loop, just wastes CPU
//                // print a message twice a second
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping  ...")
//                    nextPrintTime += 500L
//                }
//            }
            //方式二 yield() ,参数一必须是配置了线程池(Dispatchers.Default)，否则它不会起作用
            while (i < 20) { // computation loop, just wastes CPU
                // print a message twice a second
                yield()

                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping  ...${i++}")
                    nextPrintTime += 500L
                }
            }
        }catch (e: CancellationException){
            println("---异常")
        } finally {//finally释放资源
            // withContext(NonCancellable) 阻止suspend函数可取消
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    //协程取消操作，协程如果在计算，并且没有检查取消，则不会取消，直至完成
    //当然也可以取消，1检查是否取消(通过CoroutineScope的扩展属性isActive),取消就退出 ，
    // 2 周期性调用检查取消的yield()函数，必须设置调度程序的线程池
    //finally块中的代码通常非阻塞的，因为不会调用挂起函数，但是如果调用了挂起函数（它可以被取消），使用withContext(NonCancellable) {}阻止取消
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}
