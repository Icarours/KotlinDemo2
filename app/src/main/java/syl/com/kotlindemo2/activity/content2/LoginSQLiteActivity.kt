package syl.com.kotlindemo2.activity.content2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_login_sqlite.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.LoginForgetActivity
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.bean.UserInfo2
import syl.com.kotlindemo2.db.UserDBHelper2
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.ViewUtil

/**
 * author   Bright
 * date     2019/5/25 16:48
 * desc
 * 数据库Sqlite-登录
 */
class LoginSQLiteActivity : BaseActivity() {
    lateinit var mHelper: UserDBHelper2
    private val mRequestCode = 0
    private var bRemember = false
    private var mPassword = "111111"
    private var mVerifyCode: String? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sqlite)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        rg_login.setOnCheckedChangeListener { group, checkedId -> resetHint(checkedId) }
        ck_remember.setOnCheckedChangeListener { buttonView, isChecked -> bRemember = isChecked }
        et_phone.addTextChangedListener(HideTextWatcher(et_phone))
        et_password.addTextChangedListener(HideTextWatcher(et_password))
        btn_forget.setOnClickListener { doForget() }
        btn_login.setOnClickListener { doLogin() }

        mHelper = UserDBHelper2.getInstance(this)
        et_password.setOnFocusChangeListener { v, hasFocus ->
            val phone: String = et_phone.text.toString()
            if (phone.isNotEmpty() && hasFocus) {
                val info = mHelper.queryByPhone(phone)
                et_password.setText(info.password)
            }
        }
    }

    private fun resetHint(checkedId: Int) {
        if (checkedId == R.id.rb_password) {
            tv_password.text = "登录密码："
            et_password.hint = "请输入密码"
            btn_forget.text = "忘记密码"
            ck_remember.visibility = View.VISIBLE
        } else if (checkedId == R.id.rb_verifycode) {
            tv_password.text = "　验证码："
            et_password.hint = "请输入验证码"
            btn_forget.text = "获取验证码"
            ck_remember.visibility = View.INVISIBLE
        }
    }

    private inner class HideTextWatcher(private val mView: EditText) : TextWatcher {
        private val mMaxLength: Int = ViewUtil.getMaxLength(mView)
        private var mStr: CharSequence? = null

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mStr = s
        }

        override fun afterTextChanged(s: Editable) {
            if (mStr.isNullOrEmpty())
                return
            if (mStr!!.length == 11 && mMaxLength == 11 || mStr!!.length == 6 && mMaxLength == 6) {
                ViewUtil.hideOneInputMethod(this@LoginSQLiteActivity, mView)
            }
        }
    }

    private fun doForget() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            toast("请输入正确的手机号")
            return
        }
        if (rb_password.isChecked) {
            startActivityForResult<LoginForgetActivity>(mRequestCode, "phone" to phone,"title" to intent.getParcelableExtra("title") as TitleBean)
        } else if (rb_verifycode.isChecked) {
            mVerifyCode = String.format("%06d", (Math.random() * 1000000 % 1000000).toInt())
            alert("手机号$phone，本次验证码是$mVerifyCode，请输入验证码", "请记住验证码") {
                positiveButton("好的") { }
            }.show()
        }
    }

    private fun doLogin() {
        val phone = et_phone.text.toString()
        if (phone.isBlank() || phone.length < 11) {
            toast("请输入正确的手机号")
            return
        }
        if (rb_password.isChecked) {
            if (et_password.text.toString() != mPassword) {
                toast("请输入正确的密码")
                return
            } else {
                loginSuccess()
            }
        } else if (rb_verifycode.isChecked) {
            if (et_password.text.toString() != mVerifyCode) {
                toast("请输入正确的验证码")
                return
            } else {
                loginSuccess()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == mRequestCode && data != null) {
            //用户密码已改为新密码
            mPassword = data.getStringExtra("new_password")
        }
    }

    //从修改密码页面返回登录页面，要清空密码的输入框
    override fun onRestart() {
        et_password.setText("")
        super.onRestart()
    }

    private fun loginSuccess() {
        alert("您的手机号码是${et_phone.text}，恭喜你通过登录验证，点击“确定”按钮返回上个页面", "登录成功") {
            positiveButton("确定返回") { finish() }
            negativeButton("我再看看") { }
        }.show()
        if (bRemember) {
            val info = UserInfo2(
                phone = et_phone.text.toString(),
                password = et_password.text.toString(),
                update_time = DateUtil.nowDateTime
            )
            mHelper.insert(info)
            Log.d(TAG,"登录信息成功保存到数据库---${info}")
        }
    }
    companion object{
        private val TAG="LoginSQLiteActivity"
    }
}
