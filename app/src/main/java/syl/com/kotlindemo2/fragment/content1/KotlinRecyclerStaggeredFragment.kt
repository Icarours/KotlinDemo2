package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_kotlin_recycler_stragered.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerStaggeredAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.RecyclerInfo
import syl.com.kotlindemo2.widget.SpacesItemDecoration

/**
 * Created by Bright on 2019/4/16.
 * @Describe RecyclerView-RecyclerStaggered 瀑布流
 * @Called
 */
class KotlinRecyclerStaggeredFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_recycler_stragered,container,false).apply {
            rv_staggered.layoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
            //第一种方式：使用采取了LayoutContainer的插件适配器
            val adapter = RecyclerStaggeredAdapter(context, RecyclerInfo.defaultStag)
            //第二种方式：使用把三类可变要素抽象出来的通用适配器
//        val adapter = RecyclerCommonAdapter(this, R.layout.item_recycler_staggered, RecyclerInfo.defaultStag,
//                {view, item ->
//                    val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
//                    val tv_title = view.findViewById<TextView>(R.id.tv_title)
//                    iv_pic.setImageResource(item.pic_id)
//                    tv_title.text = item.title
//                })
            rv_staggered.adapter = adapter
            rv_staggered.itemAnimator = DefaultItemAnimator()
            rv_staggered.addItemDecoration(SpacesItemDecoration(3))
        }
    }
}