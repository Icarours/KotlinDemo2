package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_radio_button.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/12.
 * @Describe RadioButton
 * @Called
 */
class KotlinRadioButtonFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_radio_button, container, false).apply {
            rg_sex.setOnCheckedChangeListener { group, checkedId ->
                tv_sex.text = when (checkedId) {
                    R.id.rb_male -> "哇哦，帅气的男孩子"
                    R.id.rb_female -> "哇哦，漂亮的女孩子"
                    else -> ""
                }
            }
        }
    }
}