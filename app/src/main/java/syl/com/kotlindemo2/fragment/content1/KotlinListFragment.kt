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
 * 只读队列List/可变队列MutableList
队列是一种元素之间按照顺序排列的容器，它与集合的最大区别，便是多了个次序管理。不要小看这个有序性，正因为队列建立了秩序规则，所以它比集合多提供了如下功能（注意凡是涉及到增删改的，都必须由MutableList来完成）：
1、队列的get方法能够获取指定位置的元素，也可直接通过下标获得该位置的元素。
2、MutableList的add方法每次都是把元素添加到队列末尾，也可指定添加的位置；
3、MutableList的set方法允许替换或者修改指定位置的元素；
4、MutableList的removeAt方法允许删除指定位置的元素；
5、MutableList提供了sort系列方法用于给队列中的元素重新排序，其中sortBy方法表示按照升序排列，sortByDescending方法表示按照降序排列；
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82699463
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinListFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_list, container, false).apply {
            val list = mutableListOf<Phone>()

            val tvResult = find<TextView>(R.id.tv_result)
            findViewById<Button>(R.id.btn_add).setOnClickListener {
                list.add(Phone("iPhone8", 8000f, "iPhone8"))
                list.add(Phone("Mate10", 6000f, "Mate10"))
                list.add(Phone("小米6", 4000f, "小米6"))
                list.add(Phone("小米6", 4000f, "小米6"))
                list.add(Phone("OPPO R11", 2000f, "OPPO R11"))
                list.add(Phone("vivo X9S", 7000f, "vivo X9S"))
                list.add(Phone("魅族Pro6S", 1800f, "魅族Pro6S"))
                tvResult.text = "手机排行榜已添加,共有${list.count()}款手机"
            }
            find<Button>(R.id.btn_clear).setOnClickListener {
                list.clear()
                tvResult.text = "手机排行榜已清空,共有${list.count()}款手机"
            }
            find<Button>(R.id.btn_remove_item).setOnClickListener {
                tvResult.text = "手机数据集中的${list[0]}已被移除"
                list.removeAt(0)
            }
            find<Button>(R.id.btn_for).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("for in遍历").append("\n")
                for (item in list) {
                    stringBuilder.append(item).append(",").append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            find<Button>(R.id.btn_iterator).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("iterator 遍历").append("\n")
                for (phone in list.iterator()) {
                    stringBuilder.append(phone).append(",").append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            find<Button>(R.id.btn_foreach).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("forEach 遍历").append("\n")
                list.forEach {
                    stringBuilder.append(it).append(",").append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            find<Button>(R.id.btn_index).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("forEach 遍历").append("\n")
                for (i in list.indices) {
                    stringBuilder.append(list[i]).append(",").append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
            var sortAsc = true
            find<Button>(R.id.btn_sort_price).setOnClickListener {
                val stringBuilder = StringBuilder()
                stringBuilder.append("forEach 遍历").append("\n")
                if (sortAsc) {//升序排列
                    list.sortBy {
                        it.price
                    }
                } else {
                    list.sortByDescending {
                        it.price
                    }
                }
                for (phone in list.iterator()) {
                    stringBuilder.append(phone).append(",").append("\n")
                }
                tvResult.text = stringBuilder.toString()
            }
        }
    }
}