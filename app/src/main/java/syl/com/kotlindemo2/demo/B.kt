package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * 接⼝成员默认就是“open”的
 * @Called
 */
interface B {
    //接⼝成员默认就是“open”的
    fun f() {
        println("f() in interface B")
    }

    fun b() {
        println("b in interface B")
    }
}