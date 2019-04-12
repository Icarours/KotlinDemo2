package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_check_box.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/12.
 * @Describe kotlin中的CheckBox
 * @Called
 */
class KotlinCheckBoxFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_check_box, container, false).apply {
            ck_select.isChecked = false
            ck_select.setOnCheckedChangeListener { buttonView, isChecked ->
                tv_select.text = "您${ if (isChecked) "勾选" else "取消勾选"}了复选框"
            }
        }
    }
}

