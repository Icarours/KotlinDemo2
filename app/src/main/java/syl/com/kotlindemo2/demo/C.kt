package com.syl.kotlindemo.demo

/**
 * Created by Bright on 2019/2/4.
 * @Describe kotlin继承或者实现接口的时候好像没有自动补全的快捷键,我只好一个一个方法,自己手动实现
 * @Called
 */
class C :A(),B {

    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}