package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_kotlin_coordinator.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/16.
 * @Describe Coordinator协调者布局
 * @Called
 */
class KotlinCoordinatorFragment:BaseFragment() {
    private var floating_show = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_coordinator,container,false).apply {
            btn_snackbar.setOnClickListener {
                /**
                 * 导入com.google.android.material.snackbar.Snackbar下的Snackbar,其时长常量直接用Snackbar下的常量会有错误提示,要用
                 * com.google.android.material.snackbar.BaseTransientBottomBar中的时长常量.很费解,Google为什么不把FloatingActionButton
                 * 也放到androidx下面
                 */
                Snackbar.make(cl_main, "这是个提示条", BaseTransientBottomBar.LENGTH_LONG).show()
            }
            btn_floating.setOnClickListener {
                if (floating_show) {
                    fab_btn.hide()
                    btn_floating.text = "显示悬浮按钮"
                } else {
                    fab_btn.show()
                    btn_floating.text = "隐藏悬浮按钮"
                }
                floating_show = !floating_show
            }
        }
    }
}