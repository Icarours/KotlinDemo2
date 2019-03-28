package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_kotlin_repeat.view.*
import org.jetbrains.anko.find
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/28.
 * @Describe 循环语句
 * @Called
 */
class KotlinRepeatFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_repeat, container, false).apply {
            val poemArray: Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")
            find<Button>(R.id.btn_repeat_item).setOnClickListener {
                var poem = ""
                //循环取出数组中的元素
                for (item in poemArray) {
                    poem = "$poem$item，\n"
                }
                poem = "$poem\n(for循环)"
                tv_poem_content.text = poem
            }
            find<Button>(R.id.btn_repeat_subscript).setOnClickListener {
                var poem = ""
                for (i in poemArray.indices) {
                    if (i % 2 == 1) {
                        poem = "$poem${poemArray[i]},\n"
                    } else {
                        poem = "$poem${poemArray[i]}.\n"
                    }
                }
                poem = "$poem\n(下标for循环)"
                tv_poem_content.text = poem
            }
            find<Button>(R.id.btn_repeat_begin).setOnClickListener {
                var poem = ""
                var i = 0
                while (i < poemArray.size) {
                    if (i % 2 == 1) {
                        poem = "$poem${poemArray[i]},\n"
                    } else {
                        poem = "$poem${poemArray[i]}.\n"
                    }
                    i++
                }
                poem = "$poem 这首诗歌一共有${poemArray.size}句"
                poem = "$poem\n(while循环)"
                tv_poem_content.text = poem
            }
            find<Button>(R.id.btn_repeat_end).setOnClickListener {
                var poem = ""
                var i = 0
                do {
                    if (i % 2 == 1) {
                        poem = "$poem${poemArray[i]},\n"
                    } else {
                        poem = "$poem${poemArray[i]}.\n"
                    }
                    i++
                } while (i < poemArray.size)
                poem = "$poem 这首诗歌一共有${poemArray.size}句"
                poem = "$poem\n(do while循环)"
                tv_poem_content.text = poem
            }
            val poem2Array: Array<String?> =
                arrayOf("朝辞白帝彩云间", null, "千里江陵一日还", "", "两岸猿声啼不住", "   ", "轻舟已过万重山", "送孟浩然之广陵")
            find<Button>(R.id.btn_repeat_continue).setOnClickListener {
                var pos = -1
                var count = 0
                var poem = ""
                while (pos < poem2Array.size) {
                    pos++
                    //pos++ 必须放在判空这一句的前面,否则可能进入死循环
                    if (poem2Array[pos].isNullOrBlank()) continue
                    if (count % 2 == 1) {
                        poem = "${poem}${poem2Array[pos]},\n"
                    } else {
                        poem = "${poem}${poem2Array[pos]}.\n"
                    }
                    count++
                    //合法行数达到四行，则结束循环
                    if (4 == count) break
                }
                poem = "$poem 这首诗歌一共有${count}句"
                poem = "$poem\n(while循环,可能有空行)"
                tv_poem_content.text = poem
            }
            find<Button>(R.id.btn_repeat_break).setOnClickListener {
                var i = 0
                var isFound = false
                outside@ while (i < poemArray.size) {
                    var j = 0
                    var poemItem = poemArray[i]
                    while (j < poemItem.length) {
                        if ('一' == poemItem[j]) {
                            isFound = true
                            break@outside
                        }
                        j++
                    }
                    i++
                }
                tv_poem_content.text = if (isFound) "我找到'一'字啦" else "没有找到'一'字呀"
            }
        }
    }
}