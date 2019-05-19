package syl.com.kotlindemo2.fragment.content2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_json_convert.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.UserInfo

/**
 * Created by Bright on 2019/5/18.
 * @Describe
 * Gson解析JSON
 * @Called
 */
class JsonConvertFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_json_convert,container,false).apply {
            val user = UserInfo(name="阿四", age=25, height=160L, weight=45.0f, married=false)
            //把数据类的对象直接转换成json格式
            val json = Gson().toJson(user)

            btn_origin_json.setOnClickListener { tv_json.text = "json串内容如下：\n$json" }
            btn_convert_json.setOnClickListener {
                //利用Gson包直接将json串解析为对应格式的数据类对象
                val newUser = Gson().fromJson(json, UserInfo::class.java)
                tv_json.text = "从json串解析而来的用户信息如下：" +
                        "\n\t姓名=${newUser.name}" +
                        "\n\t年龄=${newUser.age}" +
                        "\n\t身高=${newUser.height}" +
                        "\n\t体重=${newUser.weight}" +
                        "\n\t婚否=${newUser.married}"
            }
        }
    }
}