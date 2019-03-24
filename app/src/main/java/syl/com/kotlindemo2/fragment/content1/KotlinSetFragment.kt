package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/24.
 * @Describe set集合
 * Kotlin号称全面兼容Java，于是乎Java的容器类仍可在Kotlin中正常使用，包括大家熟悉的队列ArrayList、映射HashMap等等。不过Kotlin作为一门全新的语言，肯定还是要有自己的容器类，不然哪天Java跟Kotlin划清界限，那麻烦就大了。与Java类似，Kotlin也拥有三类基本的容器，分别是集合Set、队列List、映射Map，然后每类容器又分作只读与可变两种类型，这是为了判断该容器能否进行增删改等变更操作。Kotlin对修改操作很慎重，比如变量用val前缀表示不可修改，用var前缀表示允许修改；类默认是不允许继承的，只有添加open前缀才允许该类被继承；至于容器默认为只读容器，如果需要进行修改则需加上Mutable形成新的容器，比如MutableSet表示可变集合，MutableList表示可变队列，MutableMap表示可变映射。

既然Set/List/Map都属于容器，那么必定拥有相同的基本容器方法，具体说明如下：
isEmpty : 判断该容器是否为空。
isNotEmpty : 判断该容器是否非空。
clear : 清空该容器。
contains : 判断该容器是否包含指定元素。
iterator : 获取该容器的迭代器。
count : 获取该容器包含的元素个数，也可通过size属性获得元素数量。
初始化赋值 : Kotlin允许在声明容器变量之时进行初始赋值，这点很方便比Java先进，当然不同容器的初始化方法有所区别，具体的对应关系见下表：
只读集合Set    setOf
可变集合    mutableSetOf
只读队列List    listOf
可变队列MutableList    mutableListOf
只读映射Map    mapOf
可变映射MutableMap    mutableMapOf
以上是Kotlin容器的基本方法，更具体的增删改查等用法则有所不同，下面分别介绍这三类六种容器的详细用法。

只读集合Set/可变集合MutableSet
集合是一种简单的容器，它具有以下特性：
1、容器内部的元素不按顺序排列，因此无法按照下标进行访问；
2、容器内部的元素存在唯一性，通过哈希值校验是否存在相同的元素，如果存在则覆盖之；
因为Set是只读集合，初始化赋值后便不可更改，所以元素变更的方法只适用于可变集合MutableSet，但MutableSet的变更操作尚有以下限制：
1、MutableSet的add方法仅仅往集合中添加元素，由于集合是无序的，因此不知道添加的具体位置；
2、MutableSet没有修改元素值的方法，一个元素一旦被添加，就不可被修改；
3、MutableSet的remove方法用于删除指定对象，但无法删除某个位置的元素，这是因为集合内的元素不是按顺序排列的；

对于集合的遍历操作，Kotlin提供了好几种方式，有熟悉的for循环，有迭代器循环，还有新面孔forEach循环，三种循环遍历的用法说明如下：
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82699463
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinSetFragment : BaseFragment() {
    private var goodsMutSet: MutableSet<String> = mutableSetOf()
    private val goodsA: String = "iPhone8"
    private val goodsB: String = "Mate10"
    private val goodsC: String = "小米6"
    private val goodsD: String = "OPPO R11"
    private val goodsE: String = "vivo X9S"
    private val goodsF: String = "魅族Pro6S"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_set, container, false).apply {
            val tvResult = findViewById<TextView>(R.id.tv_set_result)
            findViewById<Button>(R.id.btn_set_add).setOnClickListener {
                goodsMutSet.add(goodsA)
                goodsMutSet.add(goodsB)
                goodsMutSet.add(goodsC)
                goodsMutSet.add(goodsD)
                goodsMutSet.add(goodsE)
                goodsMutSet.add(goodsF)
                tvResult.text = "畅销手机排行榜已添加,共有${goodsMutSet.size}款手机"
                toast("手机排行榜添加成功")
            }
            findViewById<Button>(R.id.btn_set_clear).setOnClickListener {
                //如果集合非空，则删除goodsF。注意集合不能删除指定下标的记录
                if (goodsMutSet.isNotEmpty())
                    goodsMutSet.clear()
                tvResult.text = "畅销手机排行榜已清空,共有${goodsMutSet.size}款手机"
                toast("手机排行榜已清空")
            }
            findViewById<Button>(R.id.btn_remove_tail).setOnClickListener {
                if (goodsMutSet.isNotEmpty()) {
                    goodsMutSet.remove(goodsA)
                }
                tvResult.text = "畅销手机排行榜已移除--$goodsA,共有--${goodsMutSet.size}款手机"
                toast("手机排行榜已移除$goodsA")
            }
            find<Button>(R.id.btn_set_for).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("打印手机排行榜(for in):\n")
                for (item in goodsMutSet) {
                    stringBuilder.append(item)
                    stringBuilder.append(",\n")
                }
                tvResult.text = stringBuilder.toString()
                toast("手机排行榜打印完成(for in)")
            }
            /**
             * 不管是for-in循环还是迭代器循环，其实都脱胎于Java已有的容器遍历操作，代码书写上不够精炼。为了将代码精简做到极致，Kotlin给容
             * 器创造了forEach方法，明确指定该方法就是要依次遍历容器。forEach方法在编码时采用匿名函数的形式，内部使用it代表每个元素的对象，
             */
            find<Button>(R.id.btn_set_foreach).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("打印手机排行榜(foreach):\n")
                goodsMutSet.forEach{
                    stringBuilder.append(it)
                    stringBuilder.append(",\n")
                }
                tvResult.text = stringBuilder.toString()
                toast("手机排行榜打印完成(foreach)")
            }
            /**
             * 迭代器与指针的概念有点接近，它自身并非具体的元素，而是指向元素的存放地址，所以迭代器循环其实是遍历所有元素的地址。迭代器通过
             * hasNext方法判断是否还存在下一个节点，如果不存在下一节点则表示已经遍历完毕；它通过next方法获得下一个节点的元素，同时迭代器自身改为指向该元素的地址
             */
            find<Button>(R.id.btn_set_iterator).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("打印手机排行榜(iterator):\n")
                for (item in goodsMutSet.iterator()) {
                    stringBuilder.append(item)
                    stringBuilder.append(",\n")
                }
                tvResult.text = stringBuilder.toString()
                toast("手机排行榜打印完成(iterator)")
            }
        }
    }
}