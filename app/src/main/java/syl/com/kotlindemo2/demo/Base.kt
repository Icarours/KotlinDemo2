package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
open class Base(val name: String) {
    init { println("Initializing Base") }
    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}