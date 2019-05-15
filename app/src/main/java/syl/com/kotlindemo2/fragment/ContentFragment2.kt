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
import kotlinx.android.synthetic.main.fragment_content1.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.Content2Activity
import syl.com.kotlindemo2.adapter.ContentAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.TitleBean

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
class ContentFragment2 : BaseFragment() {
    var mList: MutableList<TitleBean>? = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content1, container, false).apply {

            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = RecyclerView.VERTICAL
            rv.layoutManager = linearLayoutManager
            rv.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

            val adapter = ContentAdapter(R.layout.rv_title, mList)
            rv.adapter = adapter

            adapter.setOnItemClickListener { adapter1, view, position ->
                //其他条目跳转到Content1Activity
                startActivity(Intent(activity, Content2Activity::class.java).putExtra("title", mList?.get(position)))
                Toast.makeText(context, "clicked---$position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initData() {
        mList!!.add(TitleBean(0, "kotlin入门-handler消息传递", "handler消息传递,线程的用法"))
        mList!!.add(TitleBean(1, "kotlin入门-ProgressDialog", "圆形对话框进度条,水平对话框进度条"))
    }
}