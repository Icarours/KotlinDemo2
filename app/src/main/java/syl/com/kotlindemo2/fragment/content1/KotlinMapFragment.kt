package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.find
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.Phone

/**
 * Created by Bright on 2019/3/24.
 * @Describe
 * @Called
 */
class KotlinMapFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_list,container,false).apply {
            val list = mutableListOf<Phone>()
            list.add(Phone("iPhone8",8000f,"iPhone8"))
            list.add(Phone("Mate10",8000f,"Mate10"))
            list.add(Phone("小米6",8000f,"小米6"))
            list.add(Phone("OPPO R11",8000f,"OPPO R11"))
            list.add( Phone("vivo X9S",8000f,"vivo X9S"))
            list.add(Phone("魅族Pro6S",8000f,"魅族Pro6S"))

            val tvResult = find<TextView>(R.id.tv_result)
            findViewById<Button>(R.id.btn_add).setOnClickListener {
                tvResult.text="手机排行榜已添加,共有${list.count()}款手机"
            }
            find<Button>(R.id.btn_clear).setOnClickListener {
                tvResult.text
            }
        }
    }
}