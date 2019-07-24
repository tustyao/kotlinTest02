import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/**
 * 协程修改共享数据
 *
 * kotlin中volatile是个注解  例如
 * @Volatile
 * var a = 0
 * 1，对于简单的例如计数，可以使用原子类 例如AtomicInteger
 * 2，将修改操作限定到单线程上下文
 * withContext(Dispatchers.Default) {
 *        massiveRun {
 *            // confine each increment to a single-threaded context
 *            withContext(counterContext) {
 *                counter++
 *            }
 *        }
 *   }
 *    withContext(counterContext) {
 *         // confine each increment to a single-threaded context
 *         withContext(counterContext) {
 *             counter++
 *         }
 *   }
 *
 *
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
//val counterContext = newSingleThreadContext("CounterContext")
//var counter = 0
//
//fun main() = runBlocking {
//    withContext(counterContext) {
//        massiveRun {
//            counter++
//        }
//    }
//    println("Counter = $counter")
//}
////sampleEnd