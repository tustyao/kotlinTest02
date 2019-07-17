import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

//fun main() = runBlocking {
//    //创建顶层的协程
////    val job = GlobalScope.launch {
////        //delay(1000L)
////        println("WORLD!")
////
////    }
////    println("HELLO")
////    //等待GlobalScope.launch 启动的协程执行结束
////    job.join()
//    //外部协程（runBlocking）需要等待其作用域内启动的协程结束后，它才结束
//    launch {
//        delay(1000L)
//        println("WORLD!")
//    }
//    println("HELLO")
//
//
//}