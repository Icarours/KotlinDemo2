package syl.com.kotlindemo2.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.*
import syl.com.kotlindemo2.adapter.ContentAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.TitleBean

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
class ContentFragment1 : BaseFragment() {
    var mList: MutableList<TitleBean>? = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_content1, container, false)
        val rv:RecyclerView = rootView.findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv.layoutManager = linearLayoutManager
        rv.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        val adapter = ContentAdapter(R.layout.rv_title, mList)
        rv.adapter = adapter
        adapter.setOnItemClickListener { adapter1, view, position ->
            if (22 == position) {//该条目直接跳转到一个新的Activity,ButtonClickActivity
                startActivity(
                    Intent(activity, ButtonClickActivity::class.java).putExtra(
                        "title",
                        TitleBean(0, "button", "button 在Activity中")
                    )
                )
            } else if (31 == position) {
                startActivity(
                    Intent(activity, ActFirstActivity::class.java).putExtra(
                        "title",
                        TitleBean(0, "Activity跳转", "无返回数据")
                    )
                )
            } else if (32 == position) {
                startActivity(
                    Intent(activity, ParcelableFirstActivity::class.java).putExtra(
                        "title",
                        TitleBean(0, "Activity跳转", "请求打包数据 Parcelable")
                    )
                )
            } else if (33 == position) {
                startActivity(
                    Intent(activity, ActRequestActivity::class.java).putExtra(
                        "title",
                        TitleBean(0, "Activity跳转", "有返回数据")
                    )
                )
            } else if (35 == position) {
                startActivity(
                    Intent(activity, LoginPageActivity::class.java).putExtra(
                        "title",
                        TitleBean(0, "登录页面LoginPageActivity", "登录页面LoginPage")
                    )
                )
            } else {//其他条目跳转到Content1Activity
                startActivity(Intent(activity, Content1Activity::class.java).putExtra("title", mList?.get(position)))
            }
            Toast.makeText(context, "clicked---$position", Toast.LENGTH_SHORT).show()
        }
        return rootView
    }

    override fun initData() {
        mList!!.add(TitleBean(0, "Activity", "kotlin 编写Activity"))
        mList!!.add(TitleBean(1, "PassValueFragment", "kotlin 编写 PassValueFragment"))
        mList!!.add(TitleBean(2, "PassValueFragment", "kotlin 编写 PassValueFragment--2"))
        mList!!.add(TitleBean(3, "Anko", "Anko 举例"))
        mList!!.add(TitleBean(4, "Kotlin入门-基本数据类型", "Kotlin入门(3)基本变量类型的用法"))
        mList!!.add(TitleBean(5, "Kotlin入门-数组", "Kotlin数组"))
        mList!!.add(TitleBean(6, "Kotlin入门-字符串", "Kotlin字符串"))
        mList!!.add(TitleBean(7, "Kotlin入门-存储容器", "set集合"))
        mList!!.add(TitleBean(8, "Kotlin入门-存储容器", "list队列"))
        mList!!.add(TitleBean(9, "Kotlin入门-存储容器", "map映射"))
        mList!!.add(TitleBean(10, "Kotlin入门-条件分支", "条件分支的实现"))
        mList!!.add(TitleBean(11, "Kotlin入门-循环语句", "循环语句的实现"))
        mList!!.add(TitleBean(12, "Kotlin入门-空值的判断和处理", "空值的判断和处理"))
        mList!!.add(TitleBean(13, "Kotlin入门-等式判断", "等式判断"))
        mList!!.add(TitleBean(14, "Kotlin入门-函数的基本用法", "函数的基本用法"))
        mList!!.add(TitleBean(15, "Kotlin入门-函数-可变输入参数", "函数-可变输入参数"))
        mList!!.add(TitleBean(16, "Kotlin入门-函数-特殊函数", "函数-特殊函数"))
        mList!!.add(TitleBean(17, "Kotlin入门-函数-日期函数", "函数-日期函数"))
        mList!!.add(TitleBean(18, "Kotlin入门-类", "kotlin的类"))
        mList!!.add(TitleBean(19, "Kotlin入门-类-伴生", "kotlin的类-伴生"))
        mList!!.add(TitleBean(20, "Kotlin入门-类-继承", "kotlin的类-继承"))
        mList!!.add(TitleBean(21, "Kotlin入门-类-特殊类", "kotlin的类-特殊类"))
        mList!!.add(TitleBean(22, "Kotlin入门-布局控件-Button", "kotlin的布局控件-Button"))
        mList!!.add(TitleBean(23, "Kotlin入门-布局控件-CheckBox", "kotlin的布局控件-CheckBox"))
        mList!!.add(TitleBean(24, "Kotlin入门-布局控件-RadioButton", "kotlin的布局控件-RadioButton"))
        mList!!.add(TitleBean(25, "Kotlin入门-布局控件-LinearLayout", "kotlin的布局控件-LinearLayout"))
        mList!!.add(TitleBean(26, "Kotlin入门-布局控件-RelativeLayout", "kotlin的布局控件-RelativeLayout"))
        mList!!.add(TitleBean(27, "Kotlin入门-布局控件-ConstraintLayout", "kotlin的布局控件-ConstraintLayout"))
        mList!!.add(TitleBean(28, "Kotlin入门-布局控件-TextView跑马灯效果", "kotlin的布局控件-TextView跑马灯效果"))
        mList!!.add(TitleBean(29, "Kotlin入门-布局控件-ImageView", "kotlin的布局控件-ImageView图片的缩放样式"))
        mList!!.add(TitleBean(30, "Kotlin入门-布局控件-EditText", "kotlin的布局控件-EditText"))
        mList!!.add(TitleBean(31, "Kotlin入门-Activity跳转", "Activity跳转-无数据返回"))
        mList!!.add(TitleBean(32, "Kotlin入门-Activity跳转", "Activity跳转-请求打包数据"))
        mList!!.add(TitleBean(33, "Kotlin入门-Activity跳转", "Activity跳转-有返回数据"))
        mList!!.add(TitleBean(34, "Kotlin入门-AlertDialog", "AlertDialog对话框"))
        mList!!.add(TitleBean(35, "Kotlin入门-登录页面", "LoginPage登录页面"))
        mList!!.add(TitleBean(36, "Kotlin入门-SpinnerDialog下拉列表框", "SpinnerDialog下拉列表框"))
        mList!!.add(TitleBean(37, "Kotlin入门-ListView", "ListView"))
        mList!!.add(TitleBean(38, "Kotlin入门-GridView", "GridView"))
        mList!!.add(TitleBean(39, "Kotlin入门-RecyclerView-RecyclerLinear", "RecyclerView-RecyclerLinear"))
        mList!!.add(TitleBean(40, "Kotlin入门-RecyclerView-RecyclerGrid", "RecyclerView-RecyclerGrid"))
        mList!!.add(TitleBean(41, "Kotlin入门-RecyclerView-RecyclerStaggered", "RecyclerView-RecyclerStaggered"))
        mList!!.add(TitleBean(42, "Kotlin入门-Coordinator", "Coordinator协调者布局"))
    }
}