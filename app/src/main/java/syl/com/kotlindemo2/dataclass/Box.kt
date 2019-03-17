package com.syl.kotlindemo.dataclass

/**
 * Created by Bright on 2019/2/9.
 * @Describe kotlin中的泛型
 * @Called
 */
class Box<T>(t: T) {
    var value = t
}

fun main(args: Array<String>) {
    val box: Box<Int> = Box<Int>(1)
    val box2 = Box<Int>(2)
    val box3 = Box(3)

    println("box=$box box.value=${box.value}")
    println("box2=$box2 box2.value=${box2.value}")
    println("box3=$box3 box3.value=${box3.value}")
}