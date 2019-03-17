package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
interface Person:Named {
    val firstName:String
    val lastName:String
    override val name: String
        get() = "$firstName $lastName"
}