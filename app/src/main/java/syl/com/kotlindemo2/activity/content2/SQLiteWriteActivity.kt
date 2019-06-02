package syl.com.kotlindemo2.activity.content2

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_sqlite_write.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.UserInfo2
import syl.com.kotlindemo2.db.UserDBHelper2
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/5/25 14:20
 * desc
 * 数据库Sqlite
 */
class SQLiteWriteActivity : BaseActivity() {
    private val types = listOf("未婚", "已婚")
    private lateinit var mHelper: UserDBHelper2
    private var bMarried = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_write)

        mHelper = UserDBHelper2.getInstance(this)
        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状况", types) { dialog, i ->
                tv_spinner.text = types[i]
                bMarried = if (i == 0) false else true
            }
        }
        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> toast("请先填写姓名")
                et_age.text.isEmpty() -> toast("请先填写年龄")
                et_height.text.isEmpty() -> toast("请先填写身高")
                et_weight.text.isEmpty() -> toast("请先填写体重")
                else -> {
                    val info = UserInfo2(
                        name = et_name.text.toString(),
                        age = et_age.text.toString().toInt(),
                        height = et_height.text.toString().toLong(),
                        weight = et_weight.text.toString().toFloat(),
                        married = bMarried,
                        update_time = DateUtil.nowDateTime
                    )
                    mHelper.insert(info)
                    Log.d(TAG, info.toString())
                    toast("数据已写入SQLite数据库")
                }
            }
        }
    }

    companion object {
        private val TAG = "SQLiteWriteActivity"
    }
}
