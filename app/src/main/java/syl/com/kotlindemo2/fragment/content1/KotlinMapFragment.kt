package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.find
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.Phone

/**
 * Created by Bright on 2019/3/24.
 * @Describe
 * 只读映射Map/可变映射MutableMap
映射内部保存的是一组键值对（Key-Value），也就是说，每个元素都由两部分构成，第一部分是元素的键，相当于元素的名字；第二部分是元素的值，存放着元素的详细信息。
元素的键与值是一一对应的关系，相同的键名指向的值对象是唯一的，所以映射中每个元素的键名各不相同，这个特性使得映射的变更操作与队列存在以下不同之处（注意增删操作必须由MutableMap来完成）：
1、映射的containsKey方法判断是否存在指定键名的元素，containsValue方法判断是否存在指定值对象的元素；
2、MutableMap的put方法不单单是添加元素，而是智能的数据存储；每次调用put方法，映射会先根据键名寻找同名元素，如果找不到就添加新元素，如果找得到就用新元素替换旧元素；
3、MutableMap的remove方法，是通过键名来删除元素的；
4、调用mapOf和mutableMapOf方法初始化映射之时，有两种方式可以表达单个键值对元素。其一是采取“键名 to 值对象”的形式，其二是采取Pair配对方式形如“Pair(键名, 值对象)”，
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82699463
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinMapFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_map, container, false).apply {
            val map = mutableMapOf<String, Phone>()

            val tvResult = find<TextView>(R.id.tv_result)
            findViewById<Button>(R.id.btn_add).setOnClickListener {
                //to方式初始化映射
//                var map = mapOf("苹果" to goodsA, "华为" to goodsB, "小米" to goodsC, "欧珀" to goodsD, "步步高" to goodsE, "魅族" to goodsF)
                //Pair方式初始化映射
//                var map = mutableMapOf(Pair("苹果", goodsA), Pair("华为", goodsB), Pair("小米", goodsC), Pair("欧珀", goodsD), Pair("步步高", goodsE), Pair("魅族", goodsF))

                map.put("苹果", Phone("iPhone8", 8000f, "iPhone8"))
                map.put("华为", Phone("Mate10", 7000f, "Mate10"))
                map.put("小米", Phone("小米6", 6000f, "小米6"))
                map.put("OPPO", Phone("OPPO R11", 7500f, "OPPO R11"))
                map.put("vivo", Phone("vivo X9S", 4000f, "vivo X9S"))
                map.put("魅族", Phone("魅族Pro6S", 5000f, "魅族Pro6S"))
                tvResult.text = "手机排行榜已添加,共有${map.count()}款手机"
            }
            find<Button>(R.id.btn_clear).setOnClickListener {
                map.clear()
                tvResult.text = "手机排行榜已清空,共有${map.count()}款手机"
            }
            find<Button>(R.id.btn_remove_item).setOnClickListener {
                if (map.containsKey("苹果")) {
                    tvResult.text = "手机排行榜已移除,共有${map.count()}款手机"
                    map.remove("苹果")
                }
            }
            find<Button>(R.id.btn_for).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("for in遍历").append("\n")
                for (item in map) {
                    stringBuilder.append(item.key).append("--").append(item.value.price).append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            find<Button>(R.id.btn_iterator).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("iterator遍历").append("\n")
                for (mutableEntry in map.iterator()) {
                    stringBuilder.append(mutableEntry.key).append("--").append(mutableEntry.value.price).append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            find<Button>(R.id.btn_foreach).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("forEach遍历").append("\n")
                map.forEach{
                    stringBuilder.append(it.key).append("--").append(it.value.price).append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
        }
    }
}