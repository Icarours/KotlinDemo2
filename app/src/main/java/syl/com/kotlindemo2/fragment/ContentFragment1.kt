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
import syl.com.kotlindemo2.activity.Content1Activity
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
        val rv = rootView.findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv.layoutManager = linearLayoutManager
        rv.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        val adapter = ContentAdapter(R.layout.rv_title, mList)
        rv.adapter = adapter
        adapter.setOnItemClickListener { adapter1, view, position ->
            startActivity(Intent(activity, Content1Activity::class.java).putExtra("title", mList?.get(position)))
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
    }
}