package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_spinner_dialog.view.*
import org.jetbrains.anko.support.v4.selector
import org.jetbrains.anko.support.v4.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/16.
 * @Describe SpinnerDialog下拉列表框
 * @Called
 */
class KotlinSpinnerDialogFragment:BaseFragment() {
    private val satellites = listOf("水星", "金星", "地球", "火星", "木星", "土星")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_spinner_dialog,container,false).apply {
            sp_dialog.visibility = View.GONE
            tv_spinner.visibility = View.VISIBLE
            tv_spinner.text = satellites[0]
            tv_spinner.setOnClickListener {
                //选择器selector,扩展函数
                selector("请选择行星", satellites) { dialogInterface,i ->
                    tv_spinner.text = satellites[i]
                    toast("你选择的行星是${tv_spinner.text}")
                }
            }
        }
    }
}