package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
class D2:A2,B2 {
    override fun foo() {
        super<A2>.foo()
        super<B2>.foo()
        println("class D2 foo()")
    }

    override fun bar() {
        super.bar()
        println("class D2 bar()")
    }
}