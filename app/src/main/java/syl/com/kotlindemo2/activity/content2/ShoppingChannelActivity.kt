package syl.com.kotlindemo2.activity.content2

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_shopping_channel.*
import kotlinx.android.synthetic.main.toolbar_custom.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerGoodsAdapter
import syl.com.kotlindemo2.base.MyApplication
import syl.com.kotlindemo2.bean.CartInfo
import syl.com.kotlindemo2.db.CartDBHelper
import syl.com.kotlindemo2.db.GoodsDBHelper
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.Preference
import syl.com.kotlindemo2.widget.SpacesItemDecoration

class ShoppingChannelActivity : AppCompatActivity(), RecyclerGoodsAdapter.addCartListener {
    private var mCount: Int by Preference(this, "count", 0)
    private lateinit var mGoodsHelper: GoodsDBHelper
    private lateinit var mCartHelper: CartDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_shopping_channel)
        tv_title.text = "手机商场"
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        mGoodsHelper = GoodsDBHelper.getInstance(this)
        mCartHelper = CartDBHelper.getInstance(this)
        iv_cart.setOnClickListener {
            startActivity(intentFor<ShoppingCartActivity>().clearTop())
        }
    }

    override fun onResume() {
        super.onResume()
        showGoods() //展示商品列表
    }

    override fun addToCart(goods_id: Long) {
        mCount++
        tv_count.text = mCount.toString()
        var info = mCartHelper.queryByGoodsId(goods_id)
        if (info.goods_id > 0) {
            //数据库已有商品记录，那么数量加一。否则新增一条记录。
            info.count++
            info.update_time = DateUtil.getFormatTime()
            mCartHelper.update(info)
        } else {
            info = CartInfo(count=1, goods_id=goods_id, update_time=DateUtil.getFormatTime())
            mCartHelper.insert(info)
        }
    }

    private fun showGoods() {
        tv_count.text = mCount.toString()
        if (MyApplication.instance().mIconMap.isEmpty()) {
            ShoppingCartActivity.downloadGoods(this, "false", mGoodsHelper)
        }
        rv_channel.layoutManager = GridLayoutManager(this, 2)
        val goodsArray = mGoodsHelper.queryAll()
        val adapter = RecyclerGoodsAdapter(this, goodsArray, this)
        rv_channel.adapter = adapter
        adapter.setOnItemClickListener(adapter)
        rv_channel.itemAnimator = DefaultItemAnimator()
        rv_channel.addItemDecoration(SpacesItemDecoration(2))
    }

}

