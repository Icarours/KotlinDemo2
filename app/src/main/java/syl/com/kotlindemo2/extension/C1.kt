package com.syl.kotlindemo.extension

/**
 * Created by Bright on 2019/2/8.
 * @Describe
 * @Called
 */
class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }
    override fun D1.foo() {
        println("D1.foo in C1")
    }
}