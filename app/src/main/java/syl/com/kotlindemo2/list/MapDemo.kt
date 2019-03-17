package com.syl.kotlindemo.list

/**
 * Created by Bright on 2019/2/6.
 * @Describe
 * @Called
 */
class MapDemo {
    /**
     * 只读 map
     * map的遍历
     */
    fun map1() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for (item in map) {
            println(item.key + "---" + item.value)
        }
    }

    fun map2() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for (item in map) {
            println(item.key + "---" + map[item.key])
        }
    }

    fun map3() {
        val map = mapOf(Pair(1, "aa"), Pair(2, "bb"), Pair(3, "cc"))
        for (item in map) {
            println("${item.key}---${item.value}")
        }
    }

    /**
     * 创建map集合,遍历map集合,使用Pair创建Map集合中的元素
     */
    fun map4() {
        val map = mapOf(Pair(1, "aa"), Pair(2, "bb"), Pair(3, "cc"))
        for (item in map) {
            println("${item.key}---${map[item.key]}")
        }
    }
}

fun main() {
    val mapDemo = MapDemo()
    mapDemo.map3()
}