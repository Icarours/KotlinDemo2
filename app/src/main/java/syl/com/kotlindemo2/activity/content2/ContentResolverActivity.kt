package syl.com.kotlindemo2.activity.content2

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.method.ScrollingMovementMethod
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_content_resolver.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.Contact
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.CommunicationUtil
import syl.com.kotlindemo2.util.ViewUtil

/**
 * author   Bright
 * date     2019/5/19 22:01
 * desc
 * 内容解析者
 */
class ContentResolverActivity : BaseActivity() {
    private var contactCount = ""
    private var contactResult = ""
    private var dialog: ProgressDialog? = null
    private val ADD = 0
    private val QUERY = 1
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        btn_add_contact.setOnClickListener {
            ViewUtil.hideAllInputMethod(this)
            dialog = indeterminateProgressDialog("正在写入联系人信息", "请稍候")
            dialog?.show()
            Thread { addContactInfo() }.start()
        }
        tv_read_contact.setOnClickListener {
            alert(contactResult, contactCount) {
                positiveButton("确定") {}
                isCancelable= false//点击对话框外部不会消失
            }.show()
        }
        //TextView可以竖直滚动
        tv_read_contact.movementMethod = ScrollingMovementMethod.getInstance()
        showContactInfo()
    }

    private fun showContactInfo() {
        dialog = indeterminateProgressDialog("正在读取联系人信息", "请稍候")
        dialog?.show()
        //查询联系人操作可能较耗时，故需放在线程中处理
        Thread { queryContactInfo() }.start()
    }

    private fun addContactInfo() {
        val contact = Contact()
        contact.name = et_contact_name.text.toString()
        contact.phone = et_contact_phone.text.toString()
        contact.email = et_contact_email.text.toString()
        //方式一，使用ContentResolver多次写入，每次一个字段
        CommunicationUtil.addContacts(contentResolver, contact)
        //方式二，使用ContentProviderOperation一次写入，每次多个字段
        //CommunicationUtil.addFullContacts(getContentResolver(), contact);
        handler.sendEmptyMessage(ADD)
    }

    private fun queryContactInfo() {
        contactResult = CommunicationUtil.readAllContacts(contentResolver)
        val list = contactResult.split("\n")
        //val count = userResult.count({ it == '\n' })
        contactCount = "当前共找到${list.size - 1}位联系人"
        handler.sendEmptyMessage(QUERY)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            dialog?.dismiss()
            when (msg.what) {
                ADD -> {
                    toast("成功写入联系人信息")
                    showContactInfo()
                }
                else -> tv_read_contact.text = contactCount
//                else -> tv_read_contact.text = contactResult
            }
        }
    }
}
