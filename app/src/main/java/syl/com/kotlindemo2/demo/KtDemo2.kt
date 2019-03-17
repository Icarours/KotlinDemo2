package com.syl.kotlindemo.demo

import android.widget.LinearLayout
import java.lang.Integer.parseInt

/**
 * Created by Bright on 2019/2/1.
 * @Describe kotlin 练习
 * @Called
 */
class KtDemo2 {
/*------------------ 定义函数start -----------------*/
    /**
     * 带有两个 Int 参数、返回 Int 的函数：
     */
    fun sum1(a: Int, b: Int): Int {
        return a + b;
    }

    /**
     * 将表达式作为函数体、返回值类型⾃动推断的函数：
     */
    fun sum2(a: Int, b: Int) = a + b

    /**
     * 函数返回⽆意义的值：
     */
    fun printSum1(a: Int, b: Int): Unit {
        println("Sum of $a and $b is ${a + b}")
    }

    /**
     * Unit 返回类型可以省略：
     */
    fun printSum2(a: Int, b: Int) {
        println("Sum of $a and $b is ${a + b}")
    }

    /*------------------ 定义函数end -----------------*/
    /*------------------ 使⽤字符串模板start -----------------*/
    fun funStr1() {
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"
        println(s1)
        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        println(s2)
    }
    /*------------------ 使⽤字符串模板end -----------------*/
    /**
     * 使用条件表达式
     */
    fun maxOf1(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun maxOf2(a: Int, b: Int): Int = if (a > b) a else b
    /**
     * 使⽤可空值及 null 检测
     * 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引⽤可为空。
     * 如果 str 的内容不是数字返回 null：
     */
    /*fun parseInt(str: String): Int? {
        return Integer.parseInt(str)
    }*/

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)
        if (x != null && y != null) {
            println(x * y)
        } else {
            println("either '$arg1' or '$arg2' is not number")
        }
    }

    /**
     * 使⽤类型检测及⾃动类型转换
     * is 运算符检测⼀个表达式是否某类型的⼀个实例。 如果⼀个不可变的局部变量或属性已经判断出为某类型，那么检测后的分⽀中可以直接当作该类型
    使⽤，⽆需显式转换：
     */
    fun getStringLength1(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        return null
    }

    fun getStringLength2(obj: Any): Int? {
        if (obj !is String) {
            return null
        }
        return obj.length
    }

    fun getStringLength3(obj: Any): Int? {
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    /**
     * 使用for循环
     */
    fun for1() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items)
            println(item)
        println("-----")
    }

    fun for2() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (index in items.indices)
            println("items at $index is ${items[index]}")
        println("-----")
    }

    /**
     * while循环
     */
    fun while1() {
        val items = listOf("apple", "banana", "kiwifruit")
        var index = 0;
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    /**
     * when 表达式
     * 只会执行一个分支,类似if else或者switch语句
     */
    fun describe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }

    /**
     * 使用区间range
     * 使⽤ in 运算符来检测某个数字是否在指定区间内：
     */
    fun range1() {
        val x = 10
        val y = 9
        if (x in 0..y) {
            println("fits in range")
        } else {
            println("$x out of range ${0..y}")
        }
    }

    fun range2(x: Int, y: Int) {
        if (x in 0..y) {
            println("fits in range")
        } else {
            println("$x out of range ${0..y}")
        }
    }

    /**
     * 检测某个数字是否在指定区间外:
     */
    fun range3() {
        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is out of valid list indices range too")
        }
    }

    /**
     * 区间迭代
     */
    fun range4() {
        for (x in 1..10) {
            println(x)
        }
        println("--------------------")
    }

    fun range5() {
        for (x in 1..10 step 2) {
            println(x)
        }
        println("--------------------")
    }

    fun range6() {
        for (x in 10 downTo 1 step 3) {
            println(x)
        }
        println("--------------------")
    }

    /**
     * 使用集合
     * 对集合进⾏迭代:
     */
    fun list1() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }
    }

    /**
     * 使⽤ in 运算符来判断集合内是否包含某实例：
     */
    fun list2() {
        val items = listOf("apple", "banana", "kiwifruit")
        /**
         * when 表达式
         * 只会执行一个分支,类似if else或者switch语句
         */
        when {
            "apple" in items -> println("this is apple")
            "banana" in items -> println("this is banana")
            "kiwifruit" in items -> println("this is kiwifruit")
        }
    }

    /**
     * 使⽤ lambda 表达式来过滤（filter）与映射（map）集合：
     */
    fun list3() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }

    /**
     * 创建基本类及其实例：
     * 创建类不用"new" 关键字
     */
    fun new1(): Unit {
        var ll = LinearLayout(null)
    }

    /**
     * 闭区间：包含 10
     */
    fun for3(): Unit {
        for (x in 1..10) {
            println(x)
        }
        println("------------------------")
    }

    /**
     * 半开区间：不包含 10
     */
    fun for4() {
        for (x in 1 until 10) {
            println(x)
        }
        println("------------------------")
    }

    fun for5() {
        for (x in 1..10 step 2) {
            println(x)
        }
    }

    fun for6() {
        for (x in 10 downTo 1 step 2) {
            println(x)
        }
    }

    /**
     * 只读 list
     */
    fun list4(): Unit {
        val list = listOf("a", "b", "c")
        for (item in list) {
            println(item)
        }
    }

    /**
     * 只读 map
     * map的遍历
     */
    fun map1() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for (item in map) {
            println(item.key + "---" + item.value)
        }
    }

    fun map2() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for (item in map) {
            println(item.key + "---" + map[item.key])
        }
    }

    /**
     * 在 Java 平台数字是物理存储为 JVM 的原⽣类型，除⾮我们需要⼀个可空的引⽤（如 Int? ）或泛型。
     * 后者情况下会把数字装箱。    注意数字装箱不⼀定保留同⼀性:
     */
    fun int1() {
        val a: Int = 10000
        println(a === a) // 输出“true”
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA === anotherBoxedA) // ！！！输出“false”！！！因为这是两个对象,值一样,但是存储地址不一样
        println("-------------------")
    }

    /**
     * 另⼀⽅⾯，它保留了相等性:
     */
    fun int2() {
        val a: Int = 10000
        println(a == a) // 输出“true”
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA == anotherBoxedA) // 输出“true”,因为这是两个对象,值一样,但是存储地址不一样
        println("-------------------")
    }

    /**
     * 字符⽤ Char 类型表⽰。它们不能直接当作数字
     * 字符字⾯值⽤单引号括起来: '1' 。 特殊字符可以⽤反斜杠转义。 ⽀持这⼏个转义序列：\t 、 \b 、\n 、\r 、\' 、\" 、\\ 与 \$ 。 编码其他字符要
    ⽤ Unicode 转义序列语法：'\uFF00' 。
    我们可以显式把字符转换为 Int 数字：
    当需要可空引⽤时，像数字、字符会被装箱。装箱操作不会保留同⼀性。
     */
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'Z')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    /**
     * char字符对应的int值
     */
    fun char1(c: Char) {
        println("$c==" + c.toInt())
    }

    /**
     * 数组在 Kotlin 中使⽤ Array 类来表⽰，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 [] ）以及 size 属性，以及⼀些其他有⽤的成
    员函数：
    我们可以使⽤库函数 arrayOf() 来创建⼀个数组并传递元素值给它，这样 arrayOf(1, 2, 3) 创建了 array [1, 2, 3] 。 或者，库函数
    arrayOfNulls() 可以⽤于创建⼀个指定⼤⼩的、所有元素都为空的数组。
    另⼀个选项是⽤接受数组⼤⼩以及⼀个函数参数的 Array 构造函数，⽤作参数的函数能够返回给定索引的每个元素初始值：
     */
    fun array1() {
        // 创建⼀个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
        val asc = Array(5) { i -> (i * i).toString() }
        asc.forEach { println(it) }
    }

    /**
     * Kotlin 有两种类型的字符串字⾯值: 转义字符串可以有转义字符，以及原始字符串可以包含换⾏以及任意⽂本。转义字符串很像 Java 字符串:
     * 原始字符串 使⽤三个引号（ """ ）分界符括起来，内部没有转义并且可以包含换⾏以及任何其他字符:
     * 默认 | ⽤作边界前缀，但你可以选择其他字符并作为参数传⼊，⽐如 trimMargin(">") 。
     */
    fun str1() {
        val text = """
for (c in "foo")
print(c)
"""
        println(text)
    }

    fun str2() {
        val text = """
|Tell me and I forget.
|Teach me and I remember.
|Involve me and I learn.
|(Benjamin Franklin)
""".trimMargin()
        println(text)
    }

    fun str3() {
        val i = 10
        println("i = $i") // 输出“i = 10”
        val s = "abc"
        println("$s.length is ${s.length}") // 输出“abc.length is 3”
        val price = """
${'$'}9.99
"""
        println(price)
    }
    /*------------------ 控制流：if、when、for、while start -----------------*/
    /**
     * If 表达式
     * 在 Kotlin 中，if是⼀个表达式，即它会返回⼀个值。 因此就不需要三元运算符（条件 ? 然后 : 否则），因为普通的 if 就能胜任这个⻆⾊。
     * 如果你使⽤ if 作为表达式⽽不是语句（例如：返回它的值或者把它赋给变量），该表达式需要有 else 分⽀。
     */
    //传统用法
    fun if1(a: Int, b: Int) {
        var max = a
        if (a < b) max = b
        println("max=$max")
    }

    // With else
    fun if2(a: Int, b: Int) {
        var max: Int
        if (a > b) {
            max = a
        } else {
            max = b
        }
        println("max=$max")
    }

    // 作为表达式
    fun if3(a: Int, b: Int) {
        val max = if (a > b) a else b
        println("max=$max")
    }

    fun if4(a: Int, b: Int) {
        val max = if (a > b) {
            println("Choose a")
            a
        } else {
            println("Choose b")
            b
        }
        println("max=$max")
    }
    /*------------------ 控制流：if、when、for、while end -----------------*/
    /**
     * when 将它的参数与所有的分⽀条件顺序⽐较，直到某个分⽀满⾜条件。 when 既可以被当做表达式使⽤也可以被当做语句使⽤。如果它被当做表达式，
    符合条件的分⽀的值就是整个表达式的值，如果当做语句使⽤， 则忽略个别分⽀的值。（像 if ⼀样，每⼀个分⽀可以是⼀个代码块，它的值是块中最后的
    表达式的值。）
    如果其他分⽀都不满⾜条件将会求值 else 分⽀。 如果 when 作为⼀个表达式使⽤，则必须有 else 分⽀， 除⾮编译器能够检测出所有的可能情况都已
    经覆盖了［例如，对于 枚举（enum）类条⽬与密封（sealed）类⼦类型］。
    如果很多分⽀需要⽤相同的⽅式处理，则可以把多个分⽀条件放在⼀起，⽤逗号分隔：
     */
    fun when1(x: Int) {
        when (x) {
            0, 1 -> println("x == 0 or x == 1")
            else -> println("otherwise")
        }
    }

    /**
     * 我们可以⽤任意表达式（⽽不只是常量）作为分⽀条件
     */
    fun when2(x: Int, s: String) {
        when (x) {
            parseInt(s) -> println("s encodes x")
            else -> println("s does not encode x")
        }
    }

    /**
     * 我们也可以检测⼀个值在（in）或者不在（!in）⼀个区间或者集合中：
     */
    fun when3(x: Int) {
        when (x) {
            in 1..10 -> println("x is in the range")
            !in 10..20 -> println("x is outside the range")
            else -> println("none of the above")
        }
    }

    /**
     * Kotlin 有函数字⾯量、局部函数和对象表达式。因此 Kotlin 的函数可以被嵌套。 标签限制的 return 允许我们从外层函数返回。 最重要的⼀个⽤途就是
    从 lambda 表达式中返回。回想⼀下我们这么写的时候：
     */
    fun foo1() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // ⾮局部直接返回到 foo() 的调⽤者
            print(it)
        }
        println("this point is unreachable")
    }

    /**
     * 如果我们需要从
    lambda 表达式中返回，我们必须给它加标签并⽤以限制 return。
     */
    fun foo2() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调⽤者，即 forEach 循环
            print(it)
        }
        print(" done with explicit label")
    }

    /**
     * 通常情况下使⽤隐式标签更⽅便。 该标签与接受该 lambda 的函数同名。
     */
    fun foo3() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调⽤者，即 forEach 循环
            print(it)
        }
        print(" done with implicit label")
    }

    fun foo4() {
        listOf(1, 2, 3, 4, 5).forEach {
            println(it)
        }
    }
/*------------------ 类start -----------------*/
    /**
     * 好像不是main函数,不能直接运行
     */
    fun main1() {
        println("Constructing Derived(\"hello\", \"world\")")
        val d = Derived("hello", "world")
    }

    fun main2() {
        val foo = Foo()
        val x = foo.x
        println("x in Foo x=$x")
        val i = foo.x + 2
        println("i in Foo i=$i")
        foo.f()
        println("-------------------------")
        val bar = Bar()
        val x1 = bar.x
        println("x1 in Bar x = $x1")
        val i1 = bar.x + 2
        println("i1 in Bar i1 = $i1")
        bar.f()
        println("-------------------------")
        val baz = bar.Baz()
        baz.g()
    }

    fun main3() {
        val c = C()
        c.b()
        c.a()
        c.f()
    }

    /*------------------ 类end -----------------*/
    /*------------------ 接口start -----------------*/
    fun main4() {
        val employee = Employee("张三", "李四")
        println(employee.toString())
        println(employee.firstName)
        println(employee.lastName)
        println(employee.name)
        println(employee.javaClass)
    }

    fun main5() {
        val c2 = C2()
        c2.bar()
        c2.foo()
        println("------------------")
        val d2 = D2()
        d2.bar()
        d2.foo()
    }

    /*------------------ 接口end -----------------*/
    /**
     * 交换集合中某两个元素的位置
     */
    fun MutableList<Int>.swap1(index1: Int, index2: Int) {
        val tmp = this[index1] // “this”对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
        val tmp = this[index1] // “this”对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun swapDemo1() {
        val l = mutableListOf(1, 2, 3)
        for (i in l) {
            print("$i-----")
        }
        println()
        l.swap1(0, 2) // “swap()”内部的“this”得到“l”的值
        for (i in l) {
            print("$i-----")
        }
        println()
    }
}