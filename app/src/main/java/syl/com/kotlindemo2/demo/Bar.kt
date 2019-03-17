package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
class Bar : Foo() {
    override fun f() {
        super.f()
        println("Bar.f()")
    }

    override val x: Int
        get() = super.x + 1

    /**
     * 可以调用超类/父类中的方法和成员变量
     */
    inner class Baz {
        fun g() {
            super@Bar.f() // 调⽤ Foo 实现的 f()
            println(super@Bar.x) // 使⽤ Foo 实现的 x 的 getter
        }
    }
}