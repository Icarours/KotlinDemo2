package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe
 * @Called
 */
data class Employee(
        // 不必实现“name”
        override val firstName: String,
        override val lastName: String
) : Person