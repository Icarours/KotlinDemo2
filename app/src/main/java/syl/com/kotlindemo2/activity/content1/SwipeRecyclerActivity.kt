package syl.com.kotlindemo2.activity.content1

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_swipe_recycler.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerExtras
import syl.com.kotlindemo2.adapter.RecyclerSwipeAdapter
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.RecyclerInfo
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.widget.SpacesItemDecoration

/**
 * author   Bright
 * date     2019/5/11 14:38
 * desc
 * 下拉刷新
 */
class SwipeRecyclerActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener, RecyclerExtras.OnItemClickListener,
    RecyclerExtras.OnItemLongClickListener, RecyclerExtras.OnItemDeleteClickListener {
    lateinit var adapter: RecyclerSwipeAdapter
    private var currents = RecyclerInfo.defaultList
    private var alls = RecyclerInfo.defaultList
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_recycler)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        //设置下拉刷新的监听器对象
        srl_dynamic.setOnRefreshListener(this)
        //设置刷新时转圈圈动画的渐变颜色列表
        srl_dynamic.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue)
        rv_dynamic.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerSwipeAdapter(this, currents)
        adapter.setOnItemClickListener(this)
        adapter.setOnItemLongClickListener(this)
        adapter.setOnItemDeleteClickListener(this)
        rv_dynamic.adapter = adapter
        rv_dynamic.itemAnimator = DefaultItemAnimator()
        rv_dynamic.addItemDecoration(SpacesItemDecoration(1))
    }

    //监听器接口OnRefreshListener需要实现onRefresh方法完成刷新的事务处理
    override fun onRefresh() {
        mHandler.postDelayed(mRefresh, 2000)
    }

    private val mHandler = Handler()
    private val mRefresh = Runnable {
        //下拉刷新结束，要把isRefreshing设置为false，以便从界面上去除转圈图标
        srl_dynamic.isRefreshing = false
        val position = (Math.random() * 100 % alls.size).toInt()
        val old_item = alls[position]
        val new_item = RecyclerInfo(old_item.pic_id, old_item.title, old_item.desc)
        //每次刷新之时，往循环视图列表顶部添加一条信息
        currents.add(0, new_item)
        //通知循环适配器在第0项发生了添加操作
        adapter.notifyItemInserted(0)
        //让循环视图滚动到第0项的位置
        rv_dynamic.scrollToPosition(0)
    }

    //实现单项的点击方法
    override fun onItemClick(view: View, position: Int) {
        val desc = "您点击了第${position+1}项，标题是${currents[position].title}"
        toast(desc)
    }

    //实现单项的长按方法
    override fun onItemLongClick(view: View, position: Int) {
        //长按时在该项右边弹出删除按钮
        currents[position].pressed = !(currents[position].pressed)
        //通知循环适配器在第position项发生了变更操作
        adapter.notifyItemChanged(position)
    }

    //实现单项内部删除按钮的点击方法
    override fun onItemDeleteClick(view: View, position: Int) {
        //移除当前项
        currents.removeAt(position)
        //通知循环适配器在第position项发生了移除操作
        adapter.notifyItemRemoved(position)
    }
}
