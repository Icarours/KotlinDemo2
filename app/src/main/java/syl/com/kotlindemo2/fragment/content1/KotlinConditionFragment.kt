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

/**
 * Created by Bright on 2019/3/25.
 * @Describe 条件分支
 * @Called
 */
class KotlinConditionFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_condition, container, false).apply {
            val tvResult = find<TextView>(R.id.tv_result)
            var isOdd = true
            find<Button>(R.id.btn_if_else).setOnClickListener {
                tvResult.text = if (isOdd) {
                    "Java"
                } else {
                    "kotlin"
                }
                isOdd = !isOdd
            }
            var count = 0
            find<Button>(R.id.btn_when).setOnClickListener {
                when (count) {
                    0 -> tvResult.text = "C"
                    1 -> tvResult.text = "C++"
                    2 -> tvResult.text = "java"
                    3 -> tvResult.text = "kotlin"
                    else -> tvResult.text = "其他"
                }
                count = (count + 1) % 4
            }
            find<Button>(R.id.btn_when_advance).setOnClickListener {
                when (count) {
                    0, 4, 5 -> tvResult.text = "C"
                    1, 6 -> tvResult.text = "C++"
                    2 -> tvResult.text = "java"
                    3 -> tvResult.text = "kotlin"
                    in 7..9 -> tvResult.text = "php"
                    else -> tvResult.text = "其他"
                }
                count = (count + 1) % 10
            }
            var numType: Number
            find<Button>(R.id.btn_type).setOnClickListener {
                count = (count + 1) % 3
                numType = when (count) {
                    0 -> count.toDouble()
                    1 -> count.toFloat()
                    else -> count.toLong()
                }
                when (numType) {
                    is Double -> tvResult.text = "Double"
                    is Float -> tvResult.text = "Float"
                    else -> tvResult.text = "Long"
                }
            }
        }
    }
}