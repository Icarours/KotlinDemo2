package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_kotlin_recycler_grid.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerGridAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.RecyclerInfo
import syl.com.kotlindemo2.widget.SpacesItemDecoration

/**
 * Created by Bright on 2019/4/16.
 * @Describe RecyclerView-RecyclerGrid 网格
 * @Called
 */
class KotlinRecyclerGridFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_recycler_grid,container,false).apply {
            rv_grid.layoutManager = GridLayoutManager(context, 5)
            val adapter = RecyclerGridAdapter(context, RecyclerInfo.defaultGrid)
            adapter.setOnItemClickListener(adapter)
            adapter.setOnItemLongClickListener(adapter)
            rv_grid.adapter = adapter
            rv_grid.itemAnimator = DefaultItemAnimator()
            rv_grid.addItemDecoration(SpacesItemDecoration(1))
        }
    }
}