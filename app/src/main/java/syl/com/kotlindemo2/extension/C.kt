package com.syl.kotlindemo.extension

/**
 * Created by Bright on 2019/2/8.
 * @Describe
 * @Called
 */
open class C {
    open fun D.foo() {
        println("D.foo in C")
    }
    open fun D1.foo() {
        println("D1.foo in C")
    }
    fun caller(d: D) {
        d.foo() // 调⽤扩展函数
    }
}