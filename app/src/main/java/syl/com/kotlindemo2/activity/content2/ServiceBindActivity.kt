package syl.com.kotlindemo2.activity.content2

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_service_bind.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.service.BindService
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/6/5 22:29
 * desc
 * 绑定服务,启动服务
 */
class ServiceBindActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_bind)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        Bind.tv_bind = findViewById<TextView>(R.id.tv_bind)
        btn_start_bind.setOnClickListener {
            val intentBind = intentFor<BindService>("request_content" to et_request.text.toString())
            val bindFlag = bindService(intentBind, mFirstConn, Context.BIND_AUTO_CREATE)
            Log.d(TAG, "bindFlag=" + bindFlag)
            toast("服务已绑定启动")
        }
        btn_unbind.setOnClickListener {
            if (mBindService != null) {
                unbindService(mFirstConn)
                mBindService = null
                toast("服务已解除绑定")
            }
        }
    }

    private var mBindService: BindService? = null
    //new  接口
    private val mFirstConn = object : ServiceConnection {
        //获取服务对象时的操作
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            //如果服务运行于另外一个进程，则不能直接强制转换类型，
            //否则会报错“java.lang.ClassCastException: android.os.BinderProxy cannot be cast to...”
            mBindService = (service as BindService.LocalBinder).service
            Log.d(TAG, "onServiceConnected")
        }

        //无法获取到服务对象时的操作
        override fun onServiceDisconnected(name: ComponentName) {
            mBindService = null
            Log.d(TAG, "onServiceDisconnected")
        }
    }

    companion object Bind {
        private val TAG = "ServiceBindActivity"
        private var tv_bind: TextView? = null
        private var mDesc = ""

        fun showText(desc: String) {
            mDesc = "$mDesc${DateUtil.nowTime} $desc\n"
            tv_bind?.text = mDesc
        }
    }
}
