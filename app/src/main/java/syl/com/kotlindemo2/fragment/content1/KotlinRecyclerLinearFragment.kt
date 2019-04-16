package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_kotlin_recycler_linear.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerLinearAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.RecyclerInfo
import syl.com.kotlindemo2.widget.SpacesItemDecoration

/**
 * Created by Bright on 2019/4/16.
 * @Describe RecyclerView-RecyclerLinear 列表
 * @Called
 */
class KotlinRecyclerLinearFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_recycler_linear,container,false).apply {
            rv_linear.layoutManager = LinearLayoutManager(context)
            val adapter = RecyclerLinearAdapter(context, RecyclerInfo.defaultList)
            adapter.setOnItemClickListener(adapter)
            adapter.setOnItemLongClickListener(adapter)
            rv_linear.adapter = adapter
            rv_linear.itemAnimator = DefaultItemAnimator()
            rv_linear.addItemDecoration(SpacesItemDecoration(1))
        }
    }
}