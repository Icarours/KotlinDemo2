package com.syl.kotlindemo.dataclass

/**
 * Created by Bright on 2019/2/9.
 * @Describe 数据类,
 * main函数的形式2
 * @Called
 */
data class Person(val name: String) {
    var age: Int = 0
}
fun main(args: Array<String>) {
//sampleStart
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20
//sampleEnd
    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: ${person1}")
    println("person2 with age ${person2.age}: ${person2}")
}