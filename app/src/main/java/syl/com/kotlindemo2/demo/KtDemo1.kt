package com.syl.kotlindemo.demo

import java.io.File

/**
 * Created by Bright on 2018/12/1.
 * @Describe kotlin 练习
 * @Called
 */
class KtDemo1 {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    fun printSum2(a: Int, b: Int) {
        println("sum of $a and $b is ${a + b}")
    }

    fun method1() {
        var a = 1
        //模板中的简单名称
        val s1 = "a is $a"
        a = 2
//        模板中的任意表达式
        val s2 = "${s1.replace("is", "was")},but nao is $a"
        println(s1)
        println(s2)
    }

    fun maxOf(a: Int, b: Int): Int {
        if (a > b) {
            println("$a 比 $b 大")
            return a
        } else {
            println("$b 比 $a 大")
            return b
        }
    }

    /*
    * is 运算符检测⼀个表达式是否某类型的⼀个实例。 如果⼀个不可变的局部变量或属性已经判断出为某类型，
    * 那么检测后的分⽀中可以直接当作该类型使⽤，⽆需显式转换：
    * */
    fun getStringLenth(obj: Any): Int? {
        if (obj is String) {
            // `obj` 在该条件分⽀内⾃动转换成 `String`
            return obj.length
        }
        // 在离开类型检测分⽀后，`obj` 仍然是 `Any` 类型
        return null
    }

    fun getStringLength2(str: Any): Int? {
        if (str !is String)
            return null
        // `obj` 在这⼀分⽀⾃动转换为 `String`
        return str.length
    }

    fun getStringLength3(obj: Any): Int? {
        // `obj` 在 `&&` 右边⾃动转换成 `String` 类型
        if (obj is String && obj.length > 0)
            return obj.length
        return null
    }

    /**
     * 使⽤ for 循环
     */
    fun for1() {
        val items = listOf<String>("apple", "android", "js")
        for (item in items)
            println(item)
    }

    fun for2() {
        val items = listOf<String>("apple", "android", "js")
        for (index in items.indices)
            println("items at $index is ${items[index]}")
    }

    /**
     * 使⽤ while 循环
     */
    fun while1() {
        val items = listOf<String>("apple", "android", "js")
        var index = 0
        while (items.size > index) {
            println("items at $index is ${items[index]}")
            index++
        }
    }

    /**
     * 使⽤ when 表达式
     */
    fun when1(obj: Any): String {
        var str = when (obj) {
            1 -> "one"
            "hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }
        return str
    }

    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

    /**
     * 使⽤区间（range）
     * 使⽤ in 运算符来检测某个数字是否在指定区间内：
     */
    fun range1() {
        val x = 10
        val y = 9
        if (x in 1..y + 1)
            println("fits in range")
    }

    /**
     * 检测某个数字是否在指定区间外:
     */
    fun range2() {
        val list = listOf<String>("aa", "bb", "cc")
        if (-1 !in 0..list.lastIndex)
            println("-1 is out of range")
        if (list.size !in 0..list.lastIndex)
            println("list size out of valid list indices range too")
    }

    /**
     * 区间迭代
     */
    fun rangeIterate2() {
        for (x in 1..5)
            println(x)
    }

    /**
     * 数列迭代
     */
    fun arrIterate() {
        for (x in 1..10 step 2)
            println(x)
        println("--------------")
        for (x in 12 downTo 0 step 3)
            println(x)
    }

    /**
     * 对集合进行迭代
     */
    fun listIterate() {
        val list = listOf<String>("aa", "bb", "cc", "dd")
        for (li in list) {
            if (list.get(list.size - 1) == li) {
                print(li)
            } else {
                print(li + "--")
            }
        }
    }

    /**
     * when是分支判断,只会返回一个分支
     */
    fun listIterate2() {
        val list = listOf<String>("aa", "bb", "cc", "dd")
        when {
            "aaq" in list -> println("aa in list")
            "bb" in list -> println("bb in list")
            "cc" in list -> println("cc in list")
            else -> print("others")
        }
    }

    /**
     * 使⽤ lambda 表达式来过滤（filter）与映射（map）集合：
     */
    fun listIterate3() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    }

    /**
     * 遍历map集合
     */
    fun mapIterate1() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for ((k, v) in map) {
            println("$k -> $v")
        }
    }

    fun mapIterate2() {
        var map = mapOf(
            Pair("aa", "aa"),
            Pair("2", "bb"),
            Pair("3", "cc"),
            Pair<Boolean, String>(true, "dd")
        )
        for ((k, v) in map) {
            println("$k -> $v")
        }
    }

    fun mapIterate3() {
        var map = mapOf(
            Pair("aa", "aa"),
            Pair("2", "bb"),
            Pair("3", "cc"),
            Pair<Boolean, String>(true, "dd")
        )
        println(map["aa"])
        println(map["2"])
        println(map["3"])
        println(map[true])
    }

    fun funa1() {
        val files = File("Test").listFiles()
        println(files?.size)
    }

    fun funa2() {
        val files = File("Test").listFiles()
        println(files?.size ?: "empty")
    }

    fun transform(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value") as Throwable
        }
    }

    fun foo(param: Int): String {
        val result = if (param == 1) {
            "one"
        } else if (param == 2) {
            "two"
        } else {
            "three"
        }
        return result
    }

    fun math1() {
        println(Math.round(-3.6))
        println(Math.round(3.6))
        println(Math.round(-3.5))
        println(Math.round(3.5))
    }

    /**
     * 单表达式函数
     */
    fun theAnswer() = 42

    /**
     * 等价于
     */
    fun theAnswer2(): Int {
        return 42
    }

    /**
     * 在 Java 平台数字是物理存储为 JVM 的原⽣类型，除⾮我们需要⼀个可空的引⽤（如 Int? ）或泛型。
     * 后者情况下会把数字装箱。
    注意数字装箱不⼀定保留同⼀性:
     */
    fun funaa1() {
        val a: Int = 10000
        println(a === a) // 输出“true”
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA === anotherBoxedA) // ！！！输出“false”！！！
        println("-------")
    }

    /**
     * 另⼀⽅⾯，它保留了相等性:
     */
    fun funaa2() {
        val a: Int = 10000
        println(a === a) // 输出“true”
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println(boxedA == anotherBoxedA) // ！！！输出“false”！！！
        println("-------")
    }
}

fun main() {
    val demo2 = KtDemo2()
        System.out.println(demo2.sum1(2, 4));
//        System.out.println(demo2.sum2(2, 4));
//        demo2.printSum1(2,8);
//        demo2.printSum2(22,8);
//        demo2.funStr1();
//        System.out.println(demo2.maxOf1(2, 3));
//        System.out.println(demo2.maxOf2(22, 3));
//        demo2.printProduct("3","4");
//        System.out.println(demo2.getStringLength1("skslll"));
//        System.out.println(demo2.getStringLength3("skslll"));
//        System.out.println(demo2.getStringLength1("skslll"));
//        demo2.for1();
//        demo2.for2();
//        demo2.while1();
//        System.out.println(demo2.describe(1));
//        demo2.range1();
//        demo2.range2(10, 20);
//        demo2.range3();
//        demo2.range4();
//        demo2.range5();
//        demo2.range6();
//        demo2.list1();
//        demo2.list2();
//        demo2.list3();
//        demo2.for3();
//        demo2.for4();
//        demo2.for5();
//        demo2.for6();
//        demo2.list4();
//        demo2.map1();
//        demo2.map2();
//        demo2.int1();
//        demo2.int2();
//        System.out.println(demo2.decimalDigitValue('A'));
//        demo2.char1('a');
//        demo2.char1('A');
//        demo2.char1('0');
//        demo2.char1(' ');
//        demo2.array1();
//        demo2.str1();
//        demo2.str2();
//        demo2.str3();
//        demo2.if1(2,3);
//        demo2.if2(2,3);
//        demo2.if3(2,3);
//        demo2.if4(2,3);
//        demo2.when1(2);
//        demo2.when2(3,"54");
//        demo2.when3(6);
//        demo2.foo1();
//        demo2.foo2();
//        demo2.foo3();
//        demo2.foo4();
//        demo2.main1();
//        demo2.main2();
//        demo2.main3();
//        demo2.main4();
//        demo2.main5();
//        swap1(demo2);
//    demo2.swapDemo1()
}