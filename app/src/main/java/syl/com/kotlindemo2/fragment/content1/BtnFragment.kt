package syl.com.kotlindemo2.fragment.content1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.Main2Activity
import syl.com.kotlindemo2.activity.Main3Activity
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/19.
 * @Describe
 * @Called
 */
class BtnFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_btn, container, false).also {
            it.findViewById<Button>(R.id.btn1).setOnClickListener {
                startActivity(Intent(context,Main2Activity::class.java))
            }
            it.findViewById<Button>(R.id.btn2).setOnClickListener {
                startActivity(Intent(context, Main3Activity::class.java))
            }
        }
    }
}
