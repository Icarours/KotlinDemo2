package com.syl.kotlindemo.dataclass

/**
 * Created by Bright on 2019/2/9.
 * @Describe
 * @Called
 */
data class User(val name: String = "", val age: Int = 0){
//    val jack = User(name = "Jack", age = 1)
//    val olderJack = jack.copy(age = 2)
}
fun main(args: Array<String>) {
//sampleStart
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
//sampleEnd
    println("jack == olderJack: ${jack == olderJack}")
    println("jack with age ${jack.age}: ${jack}")
    println("olderJack with age ${olderJack.age}: ${olderJack}")

    println("------------------------------")
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // 输出 "Jane, 35 years of age"
}
