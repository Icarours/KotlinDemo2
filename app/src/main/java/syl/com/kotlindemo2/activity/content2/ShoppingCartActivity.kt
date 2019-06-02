package syl.com.kotlindemo2.activity.content2

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.toolbar_custom.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerCartAdapter
import syl.com.kotlindemo2.adapter.RecyclerExtras
import syl.com.kotlindemo2.base.MyApplication
import syl.com.kotlindemo2.bean.CartInfo
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.db.CartDBHelper
import syl.com.kotlindemo2.db.GoodsDBHelper
import syl.com.kotlindemo2.util.FileUtil
import syl.com.kotlindemo2.util.Preference
import syl.com.kotlindemo2.widget.SpacesItemDecoration

/**
 * author   Bright
 * date     2019/6/1 17:31
 * desc
 * 购物车
 */
class ShoppingCartActivity : AppCompatActivity(), RecyclerExtras.OnItemClickListener,
    RecyclerExtras.OnItemLongClickListener {
    private var mCount: Int by Preference(this, "count", 0)
    private var mFirst: String by Preference(this, "first", "true")
    private lateinit var mGoodsHelper: GoodsDBHelper
    private lateinit var mCartHelper: CartDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_shopping_cart)
        tv_title.text = "购物车"
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        btn_shopping_channel.setOnClickListener {
            startActivity(intentFor<ShoppingChannelActivity>().clearTop())
        }
        btn_settle.setOnClickListener {
            alert("客官抱歉，支付功能尚未开通，请下次再来", "结算商品") {
                positiveButton("我知道了") { }
            }.show()
        }
        mGoodsHelper = GoodsDBHelper.getInstance(this)
        mCartHelper = CartDBHelper.getInstance(this)
        downloadGoods(this, mFirst, mGoodsHelper)
        mFirst = "false"
    }

    override fun onResume() {
        super.onResume()
        showCart()
        showCount(mCount)
    }

    //显示购物车图标中的商品数量
    private fun showCount(count: Int) {
        mCount = count
        tv_count.text = mCount.toString()
        ll_content.visibility = if (mCount == 0) View.GONE else View.VISIBLE
        ll_empty.visibility = if (mCount == 0) View.VISIBLE else View.GONE
    }

    private var mCartArray: MutableList<CartInfo> = mutableListOf()
    private fun showCart() {
        mCartArray = mCartHelper.queryAll()
        if (mCartArray.isEmpty()) {
            return
        }
        for (i in mCartArray.indices) {
            val info = mCartArray[i]
            info.goods = mGoodsHelper.queryByRowid(info.goods_id)
            mCartArray[i] = info
        }
        rv_cart.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerCartAdapter(this, mCartArray)
        adapter.setOnItemClickListener(this)
        adapter.setOnItemLongClickListener(this)
        rv_cart.adapter = adapter
        rv_cart.itemAnimator = DefaultItemAnimator()
        rv_cart.addItemDecoration(SpacesItemDecoration(2))
        //显示总金额
        var total_price = 0
        mCartArray.forEach { total_price += it.goods.price * it.count }
        tv_total_price.text = total_price.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_shopping -> startActivity(intentFor<ShoppingChannelActivity>().clearTop())
            R.id.menu_clear -> {
                mCartHelper.deleteAll() //清空购物车数据库
                showCount(0)
                toast("购物车已清空")
            }
            R.id.menu_return -> finish()
        }
        return true
    }

    override fun onItemLongClick(view: View, position: Int) {
        val cart = mCartArray[position]
        val message = "尊敬的用户，您是否不再购买${cart.goods.name}？"
        alert(message, "商品购买提示") {
            positiveButton("不再购买") {
                //从购物车删除商品的数据库操作
                mCartHelper.delete("goods_id=" + cart.goods_id)
                //更新购物车中的商品数量
                showCount(mCount - cart.count)
                toast("已从购物车删除${cart.goods.name}")
                //刷新购物车的商品列表
                showCart()
            }
            negativeButton("我再想想") { }
        }.show()
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = intentFor<ShoppingDetailActivity>(
            "goods_id" to mCartArray[position].goods_id
        )
        startActivity(intent.clearTop())
    }

    companion object {
        private val TAG = "ShoppingCartActivity"

        //模拟网络数据，初始化数据库中的商品信息
        fun downloadGoods(ctx: Context, isFirst: String, helper: GoodsDBHelper) {
            val path = MyApplication.instance().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
            Log.d(TAG, "path=$path")
            if (isFirst == "true") {
                val goodsList = GoodsInfo.defaultList
                for (i in goodsList.indices) {
                    val info = goodsList[i]
                    val rowid = helper.insert(info)
                    info.rowid = rowid
                    //往运行内存写入商品小图
                    val thumb = BitmapFactory.decodeResource(ctx.resources, info.thumb)
                    MyApplication.instance().mIconMap.put(rowid, thumb)
                    val thumb_path = "$path${rowid}_s.jpg"
                    FileUtil.saveImage(thumb_path, thumb)
                    info.thumb_path = thumb_path
                    //往SD卡保存商品大图
                    val pic = BitmapFactory.decodeResource(ctx.resources, info.pic)
                    val pic_path = "$path${rowid}.jpg"
                    FileUtil.saveImage(pic_path, pic)
                    pic.recycle()
                    info.pic_path = pic_path
                    helper.update(info)
                }
            } else {
                val goodsArray = helper.queryAll()
                for (item in goodsArray) {
                    Log.d(TAG, "rowid=${item.rowid}, thumb_path=${item.thumb_path}")
                    val thumb = BitmapFactory.decodeFile(item.thumb_path)
                    MyApplication.instance().mIconMap.put(item.rowid, thumb)
                }
            }
        }
    }

}
