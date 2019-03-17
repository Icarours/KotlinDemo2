package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
class Test {
    /**
     * 好像不是main函数,不能直接运行
     */
    fun main(args: Array<String>) {
        println("Constructing Derived(\"hello\", \"world\")")
        val d = Derived("hello", "world")
    }
}