package syl.com.kotlindemo2.activity.content2

import android.content.ContentResolver
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_content_provider.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.bean.UserData
import syl.com.kotlindemo2.provider.UserInfoContent
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.ViewUtil
import java.util.*

/**
 * author   Bright
 * date     2019/5/19 21:34
 * desc
 * ContentProvider 内容提供者
 */
class ContentProviderActivity : BaseActivity() {
    private var userCount = ""
    private var userResult = ""
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        btn_add_user.setOnClickListener {
            ViewUtil.hideAllInputMethod(this)
            val user = UserData(name = et_user_name.text.toString(),
                age = et_user_age.text.toString().toInt(),
                height = et_user_height.text.toString().toLong(),
                weight = et_user_weight.text.toString().toFloat())
            addUser(contentResolver, user)
            showUserInfo()
            toast("成功写入用户信息")
        }
        tv_read_user.setOnClickListener {
            alert(userResult, userCount) {
                positiveButton("确定") {}
            }.show()
        }
        tv_read_user.setOnLongClickListener {
            contentResolver.delete(UserInfoContent.CONTENT_URI, "1==1", null)
            showUserInfo()
            toast("成功删除所有用户信息")
            true
        }
        showUserInfo()
    }

    private fun showUserInfo() {
        userResult = readAllUser(contentResolver)
        val list = userResult.split("\n")
        //val count = userResult.count({ it == '\n' })
        userCount = "当前共找到${list.size-1}位用户信息"
        tv_read_user.text = userCount+"\n"+userResult
    }

    private fun addUser(resolver: ContentResolver, user: UserData) {
        val name = ContentValues()
        name.put("name", user.name)
        name.put("age", user.age)
        name.put("height", user.height)
        name.put("weight", user.weight)
        name.put("married", false)
        name.put("update_time", DateUtil.getFormatTime())
        //UserInfoContent.CONTENT_URI指向的字符串就是provider在AndroidManifest.xml里的android:authorities属性值
        resolver.insert(UserInfoContent.CONTENT_URI, name)
    }

    private fun readAllUser(resolver: ContentResolver): String {
        val userArray = ArrayList<UserData>()
        val cursor = resolver.query(UserInfoContent.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val user = UserData()
                user.name = cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_NAME))
                user.age = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_AGE))
                user.height = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_HEIGHT)).toLong()
                user.weight = cursor.getFloat(cursor.getColumnIndex(UserInfoContent.USER_WEIGHT))
                userArray.add(user)
            }
            cursor.close()
        }
        var result = ""
        for (user in userArray) {
            result = "$result${user.name}	年龄${user.age}	身高${user.height}	体重${user.weight}\n"
        }
        return result
    }
}
