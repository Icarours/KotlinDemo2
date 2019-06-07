package syl.com.kotlindemo2.base

import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.util.NotifyUtil
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Bright on 2019/6/1.
 * @Describe
 * @Called
 */
class MyApplication : Application() {
    var mInfoMap = mutableMapOf<String, String>()
    var mIconMap = mutableMapOf<Long, Bitmap>()
    var groupMap = mutableMapOf<String, Boolean>()

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            NotifyUtil.createNotifyChannel(this, getString(R.string.app_name))
        }
    }

    //单例化的第一种方式：声明一个简单的Application属性
//    companion object {
//        //情况一：声明可空的属性
//        private var instance: MainApplication? = null
//        fun instance() = instance!!
//        //情况二：声明延迟初始化属性
//        //private lateinit var instance: MainApplication
//        //fun instance() = instance
//    }

    //单例化的第二种方式：利用系统自带的Delegates生成委托属性
//    companion object {
//        private var instance: MainApplication by Delegates.notNull()
//        fun instance() = instance
//    }

    //单例化的第三种方式：自定义一个非空且只能一次性赋值的委托属性
    companion object {
        private var instance: MyApplication by NotNullSingleValueVar()
        fun instance() = instance
    }

    //定义一个属性管理类，进行非空和重复赋值的判断
    private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
        private var value: T? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value ?: throw IllegalStateException("application not initialized")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = if (this.value == null) value
            else throw IllegalStateException("application already initialized")
        }
    }
}