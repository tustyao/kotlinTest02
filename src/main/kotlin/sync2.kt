import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/**
 * 协程修改共享数据
 *
 * 使用互斥锁 Mutex()   提供lock() unlock 和withLock
 */

//suspend fun massiveRun(action: suspend () -> Unit) {
//    val n = 100  // number of coroutines to launch
//    val k = 1000 // times an action is repeated by each coroutine
//    val time = measureTimeMillis {
//        coroutineScope { // scope for coroutines
//            repeat(n) {
//                launch {
//                    repeat(k) { action() }
//                }
//            }
//        }
//    }
//    println("Completed ${n * k} actions in $time ms")
//}
//
////sampleStart
//val mutex = Mutex()
//var counter = 0
//
//fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            // protect each increment with lock
//            mutex.withLock {
//                counter++
//            }
//        }
//    }
//    println("Counter = $counter")
//}
//sampleEnd