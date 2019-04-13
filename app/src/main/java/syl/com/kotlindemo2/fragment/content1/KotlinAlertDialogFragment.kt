package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_alert_dialog.view.*
import org.jetbrains.anko.support.v4.alert
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/13.
 * @Describe AlertDialog对话框
 * @Called
 */
class KotlinAlertDialogFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_alert_dialog,container,false).apply {
            btn_alert.setOnClickListener {
                alert("你真的要卸载我吗？", "尊敬的用户") {
                    positiveButton("残忍卸载") { tv_alert.text = "虽然依依不舍，但是只能离开了" }
                    negativeButton("我再想想") { tv_alert.text = "让我再陪你三百六十五个日夜" }
                }.show()
            }
        }
    }
}