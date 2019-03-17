package com.syl.kotlindemo.extension

/**
 * Created by Bright on 2019/2/8.
 * @Describe
 * 扩展好神奇,扩展方法居然可以直接调用被扩展的类中的方法.扩展函数的用法:类对象名.扩展类方法名
 * 有点像Java中的静态方法
 * main函数1
 * @Called
 */
class ExtensionDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            C().caller(D()) // 输出 "D.foo in C"
            C1().caller(D()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
            C().caller(D1()) // 输出 "D.foo in C" —— 扩展接收者静态解析
        }
    }

    fun main() {
        C().caller(D()) // 输出 "D.foo in C"
        C1().caller(D()) // 输出 "D.foo in C1" —— 分发接收者虚拟解析
        C().caller(D1()) // 输出 "D.foo in C" —— 扩展接收者静态解析
    }
}