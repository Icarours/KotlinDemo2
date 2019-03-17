package com.syl.kotlindemo.list

/**
 * Created by Bright on 2019/2/6.
 * @Describe kotlin中集合的使用:1.创建,2.遍历
 * @Called
 */
class ListDemo {
    /**
     * 1.创建list集合
     * 2.抽取变量,遍历打印,分成两部进行
     */
    fun list1() {
        val items = listOf<String>("aa", "as", "sddd")
        for (item in items)
            println(item)
    }

    /**
     * 创建集合和遍历打印放在一起处理
     * listOf<String>后面的泛型可以去掉
     */
    fun list2() {
        for (item in listOf("aa", "as", "sddd")) {
            println(item)
        }
    }

    /**
     * 1.arrayListOf是不可变的,但是不明白"不可变"指的是什么意思,好像可以向list集合中再次添加新的元素
     * 2.可以向集合中添加不同基本数据类型的元素,这点很强大
     *
     *
     * 优先使⽤不可变（⽽不是可变）数据。初始化后未修改的局部变量与属性，总是将其声明为 val ⽽不是 var 。
    总是使⽤不可变集合接⼝（ Collection , List , Set , Map ）来声明⽆需改变的集合。使⽤⼯⼚函数创建集合实例时，尽可能选⽤返回不可变集合类
    型的函数：
     */
    fun list3() {
        val list = arrayListOf("aa", "as", "sddd", false)
        list.add(true)
        for (item in list) {
            println(item)
        }
    }

    /**
     *1.可以向集合中添加不同基本数据类型的元素,这点很强大
     */
    fun list4() {
        for (s in mutableListOf("aa", "as", "sddd", 11, false, 'd')) {
            println(s)
        }
    }

    /**
     * 扩展函数
     * 交换集合中某两个元素的位置
     */
    fun MutableList<Int>.swap1(index1: Int, index2: Int) {
        val tmp = this[index1] // “this”对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    /**
     * 可以使用泛型的扩展函数
     */
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

    /**
     *可以使用泛型的扩展函数
     */
    fun swapDemo2() {
        val l = mutableListOf(1, 2, "啊啊")
        for (i in l) {
            print("$i-----")
        }
        println()
        l.swap2(0, 2) // “swap()”内部的“this”得到“l”的值
        for (i in l) {
            print("$i-----")
        }
        println()
    }
}