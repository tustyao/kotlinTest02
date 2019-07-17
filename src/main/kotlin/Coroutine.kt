import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

//fun main() {
//    //启动一个协程，GlobalScope.launch启动的协程生命周期受应用的生命周期限制
//    GlobalScope.launch {
//        //suspend function：  只能在协程中或者suspend function中调用
//        delay(1000L)
//        print("World!")
//    }
//
//    println("Hello,")
//    // Thread.sleep(2000L)
//    //runBlocking启动的协程 阻塞主线程直到协程内部代码执行完毕
//    runBlocking {
//        delay(2000L)
//        // println("lll")
//    }
//    // println("yao")
//}