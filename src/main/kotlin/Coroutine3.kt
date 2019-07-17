import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/16
 */

fun main() = runBlocking {
    //coroutineScope创建协程作用域，其内部的子协程完成后，此作用域才结束
    coroutineScope {
        //启动1000个协程
        repeat(1000) {
            launch {
                println(it)
                delay(500L)
            }
        }
        test()
    }
    println("ddd")
}

//协程作用域（coroutineScope）提取的方法包含协程构建器时，仅仅加suspend，无法保证创建的协程在当前作用域
//CoroutineScope扩展函数是一种解决方案，如果函数属于类，则类扩展CoroutineScope或者CoroutineScope作为一个字段
private fun CoroutineScope.test() {
    launch {
        delay(20000L)
        println("uuu")
    }
    println("www")
}

fun aa() {

}

suspend fun test() {
    aa()
}