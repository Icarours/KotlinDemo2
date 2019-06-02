package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_sqlite_read.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.db.UserDBHelper2

/**
 * author   Bright
 * date     2019/5/25 16:38
 * desc
 * 数据库Sqlite-读取
 */
class SQLiteReadActivity : BaseActivity() {
    private lateinit var mHelper: UserDBHelper2
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_read)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        mHelper = UserDBHelper2.getInstance(this)
        readSQLite()
        btn_delete.setOnClickListener {
            mHelper.deleteAll()
            //重新读取数据库
            readSQLite()
        }
    }

    private fun readSQLite() {
        val userArray = mHelper.queryAll()
        var desc = "数据库查询到${userArray.size}条记录，详情如下："
        for (i in userArray.indices) {
            val item = userArray[i]
            desc = "$desc\n第${i+1}条记录信息如下：" +
                    "\n　姓名为${item.name}" +
                    "\n　年龄为${item.age}" +
                    "\n　身高为${item.height}" +
                    "\n　体重为${item.weight}" +
                    "\n　婚否为${item.married}" +
                    "\n　更新时间为${item.update_time}"
        }
        if (userArray.isEmpty()) {
            desc = "数据库查询到的记录为空"
        }
        tv_sqlite.text = desc
    }
}
