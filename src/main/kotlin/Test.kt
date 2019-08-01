import kotlinx.coroutines.*

/**
 * @author yaoqianfa@lakala.com
 * @date 2019/7/25
 */
/**
 * lambda表达式可以赋值给变量
 * 例如
 *  val a = {int: Int -> print(int)}
 *
 * 如果使用fun关键字定义lambda
 * 例如：
 *   fun foo(int: Int)={print(int)} // 表示int: Int -> () -> Unit
 * 则调用时候则是foo(int)()才可以
 */
fun foo(int: Int) = {
    print(int)
}

fun main() = runBlocking {
    //  listOf(1, 2, 3).forEach { foo(it)() }
    val launch = launch(Dispatchers.Unconfined) {
        println(Thread.currentThread().name);
        while (isActive) {
            println("iii")
        }
        println(Thread.currentThread().name)
    }
    launch.cancelAndJoin()
    println("end")

    val co = Comparator<String> { o1, o2 ->
        if (o1 == null)
            return@Comparator -1
        else if (o2 == null)
            return@Comparator 1
        o1.compareTo(o2)
    }

}