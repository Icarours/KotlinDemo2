package syl.com.kotlindemo2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.ContentAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.TitleBean

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
class ContentFragment2 : BaseFragment() {
    var mList: MutableList<TitleBean>? = mutableListOf<TitleBean>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_content1, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.rv)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv.layoutManager = linearLayoutManager
        rv.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        val adapter = ContentAdapter(R.layout.rv_title, mList)
        rv.adapter = adapter
        return rootView
    }

    override fun initData() {
        for (i in 0..20) {
            mList?.add(i, TitleBean(i, "title2--" + i, "description2--" + i))
        }
    }
}