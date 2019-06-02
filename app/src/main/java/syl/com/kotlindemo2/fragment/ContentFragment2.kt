package syl.com.kotlindemo2.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_content1.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.Content2Activity
import syl.com.kotlindemo2.activity.content2.*
import syl.com.kotlindemo2.adapter.ContentAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.PermissionUtil

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
                when (position) {
                    3 -> {
                        startActivity(
                            Intent(activity, AsyncTaskActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    6 -> {
                        //context as Activity强转为Activity
                        if (PermissionUtil.checkPermission(
                                context as Activity,
                                Manifest.permission.ACCESS_FINE_LOCATION, 1100
                            )
                        ) {
                            startActivity(
                                Intent(activity, GaodeLocationActivity::class.java).putExtra(
                                    "title",
                                    mList?.get(position)
                                )
                            )
                        }
                    }
                    8 -> {
                        startActivity(
                            Intent(activity, DownloadApkActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    9 -> {
                        startActivity(
                            Intent(activity, DownloadImageActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    10 -> {
                        startActivity(
                            Intent(activity, ContentProviderActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    11 -> {
                        //context as Activity强转为Activity
                        if (PermissionUtil.checkMultiPermission(
                                context as Activity, arrayOf(
                                    Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.WRITE_CONTACTS
                                ), 1200
                            )
                        ) {
                            startActivity(
                                Intent(activity, ContentResolverActivity::class.java).putExtra(
                                    "title",
                                    mList?.get(position)
                                )
                            )
                        }
                    }
                    12 -> {
                        startActivity(
                            Intent(activity, SpannableActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    13 -> {
                        startActivity(
                            Intent(activity, ShareWriteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    14 -> {
                        startActivity(
                            Intent(activity, ShareReadActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    15 -> {
                        startActivity(
                            Intent(activity, LoginShareActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    16 -> {
                        startActivity(
                            Intent(activity, SQLiteWriteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    17 -> {
                        startActivity(
                            Intent(activity, SQLiteReadActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    18 -> {
                        startActivity(
                            Intent(activity, LoginSQLiteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    19 -> {
                        startActivity(
                            Intent(activity, FilePathActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    20 -> {
                        startActivity(
                            Intent(activity, TextWriteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    21 -> {
                        startActivity(
                            Intent(activity, TextReadActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    22 -> {
                        startActivity(
                            Intent(activity, ImageWriteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    23 -> {
                        startActivity(
                            Intent(activity, ImageReadActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    24 -> {
                        startActivity(
                            Intent(activity, AppWriteActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    25 -> {
                        startActivity(
                            Intent(activity, AppReadActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    26 -> {
                        startActivity(
                            Intent(activity, MenuOptionActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    27 -> {
                        startActivity(
                            Intent(activity, ShoppingCartActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    28 -> {
                        startActivity(
                            Intent(activity, CustomPropertyActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    29 -> {
                        startActivity(
                            Intent(activity, MeasureViewActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    30 -> {
                        startActivity(
                            Intent(activity, DrawRoundActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    31 -> {
                        startActivity(
                            Intent(activity, RunnableActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    32 -> {
                        startActivity(
                            Intent(activity, ProgressBarActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    33 -> {
                        startActivity(
                            Intent(activity, TextProgressActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    34 -> {
                        startActivity(
                            Intent(activity, ProgressAnimationActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    35 -> {
                        startActivity(
                            Intent(activity, NotifySimpleActivity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                    }
                    else -> {
                        //其他条目跳转到Content1Activity
                        startActivity(
                            Intent(activity, Content2Activity::class.java).putExtra(
                                "title",
                                mList?.get(position)
                            )
                        )
                        Toast.makeText(context, "clicked---$position", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun initData() {
        mList!!.add(TitleBean(0, "kotlin入门-handler消息传递", "handler消息传递,线程的用法"))
        mList!!.add(TitleBean(1, "kotlin入门-ProgressDialog", "圆形对话框进度条,水平对话框进度条"))
        mList!!.add(TitleBean(2, "kotlin入门-自定义圆形进度条-指定进度值", "自定义圆形进度条-指定进度值"))
        mList!!.add(TitleBean(3, "kotlin入门-异步任务", "AsyncTask"))
        mList!!.add(TitleBean(4, "kotlin入门-josn", "json数据的构造,解析,遍历"))
        mList!!.add(TitleBean(5, "kotlin入门-Gson解析JSON", "Gson解析JSON"))
        mList!!.add(TitleBean(6, "kotlin入门-高德地图定位", "高德地图定位"))
        mList!!.add(TitleBean(7, "kotlin入门-获取网络图片", "获取网络图片"))
        mList!!.add(TitleBean(8, "kotlin入门-DownloadManager", "下载apk安装包"))
        mList!!.add(TitleBean(9, "kotlin入门-DownloadManager2", "下载图片"))
        mList!!.add(TitleBean(10, "kotlin入门-ContentProvider", "内容提供者"))
        mList!!.add(TitleBean(11, "kotlin入门-ContentResolverActivity", "内容解析者"))
        mList!!.add(TitleBean(12, "kotlin入门-可变字符串", "可变字符串"))
        mList!!.add(TitleBean(13, "kotlin入门-SharedPreference", "SharedPreference 写入"))
        mList!!.add(TitleBean(14, "kotlin入门-SharedPreference", "SharedPreference 读取"))
        mList!!.add(TitleBean(15, "kotlin入门-SharedPreference", "SharedPreference-登录"))
        mList!!.add(TitleBean(16, "kotlin入门-数据库Sqlite", "数据库Sqlite-存储"))
        mList!!.add(TitleBean(17, "kotlin入门-数据库Sqlite", "数据库Sqlite-读取"))
        mList!!.add(TitleBean(18, "kotlin入门-数据库Sqlite-登录", "数据库Sqlite-登录"))
        mList!!.add(TitleBean(19, "kotlin入门-文件处理-文件路径", "文件处理-文件保存位置"))
        mList!!.add(TitleBean(20, "kotlin入门-文件处理-文本文件保存", "文件处理-文件保存-TextWrite"))
        mList!!.add(TitleBean(21, "kotlin入门-文件处理-文本文件读取", "文件处理-文件读取-TextWrite"))
        mList!!.add(TitleBean(22, "kotlin入门-文件处理-写入图片文件", "文件处理-写入图片文件"))
        mList!!.add(TitleBean(23, "kotlin入门-文件处理-读取图片文件", "文件处理-读取图片文件"))
        mList!!.add(TitleBean(24, "kotlin入门-保存信息到全局变量-MyApplication", "保存信息到全局变量-MyApplication"))
        mList!!.add(TitleBean(25, "kotlin入门-从全局变量MyApplication读取信息", "从全局变量MyApplication读取信息"))
        mList!!.add(TitleBean(26, "kotlin入门-ToolBar选项菜单", "ToolBar选项菜单"))
        mList!!.add(TitleBean(27, "kotlin入门-购物车", "购物车"))
        mList!!.add(TitleBean(28, "kotlin入门-自定义选项卡", "自定义选项卡"))
        mList!!.add(TitleBean(29, "kotlin入门-测量视图尺寸", "测量视图尺寸"))
        mList!!.add(TitleBean(30, "kotlin入门-绘制圆角边框", "绘制圆角边框"))
        mList!!.add(TitleBean(31, "kotlin入门-延迟执行", "线程-Handler"))
        mList!!.add(TitleBean(32, "kotlin入门-水平进度条", "水平进度条"))
        mList!!.add(TitleBean(33, "kotlin入门-水平进度条-文字", "水平进度条-文字"))
        mList!!.add(TitleBean(34, "kotlin入门-水平进度条动画", "水平进度条动画"))
        mList!!.add(TitleBean(35, "kotlin入门-发送简单通知", "发送简单通知"))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity<GaodeLocationActivity>()
            } else {
                toast("需要允许定位权限才能获取位置信息噢")
            }
        } else if (requestCode == 1200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity<ContentResolverActivity>()
            } else {
                toast("需要允许通讯录权限才能查看联系人噢")
            }
        }
    }
}