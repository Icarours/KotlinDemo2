package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_kotlin_start3.*
import kotlinx.android.synthetic.main.fragment_kotlin_start3.view.*
import org.jetbrains.anko.find
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/21.
 * @Describe kotlin基本数据类型,基本数据类型的转化
 * @Called
 */
class KotlinStart3Fragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_kotlin_start3, container, false).also {
            val original = 65
            it.tv_original.text = original.toString()
            it.tv_result.text = ""
            it.find<Button>(R.id.btn_int).setOnClickListener { tv_result.text = original.toString() }
            it.find<Button>(R.id.btn_int_long).setOnClickListener { tv_result.text = original.toLong().toString() }
            it.find<Button>(R.id.btn_float).setOnClickListener { tv_result.text = original.toFloat().toString() }
            it.find<Button>(R.id.btn_double).setOnClickListener { tv_result.text = original.toDouble().toString() }
            it.find<Button>(R.id.btn_boolean)
                .setOnClickListener { tv_result.text = original.toFloat().isNaN().toString() }
            it.find<Button>(R.id.btn_char).setOnClickListener { tv_result.text = original.toChar().toString() }
            //只有Float和Double可调用isNaN方法，其它基本类型都没有isNaN,是否数字
        }
    }
}