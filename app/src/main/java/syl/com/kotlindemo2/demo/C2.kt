package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
class C2:A2 {
    override fun foo() {
        super.foo()
        println("class C2 foo()")
    }

    override fun bar() {
        println("class C2 bar()")
    }
}