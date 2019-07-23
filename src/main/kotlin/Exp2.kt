import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/23
 */
/*
  如果协程遇到除 CancellationException 以外的异常，它将取消具有该异常的父协程
 */
fun main() = runBlocking {
    val launch1 = launch {
        try {
            println("launch 1")
           delay(1000)
        }finally {
            throw  ArrayIndexOutOfBoundsException("pao 1")
        }

    }
    val launch2 = launch {
        println("launch 2")
        throw ArithmeticException("pao 2")
    }
    launch2.join()
    launch1.join()
    //后续代码不能执行
    delay(2000)
    println("main end")

}