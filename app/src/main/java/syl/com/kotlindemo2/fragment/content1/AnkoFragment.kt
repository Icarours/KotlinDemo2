package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.jetbrains.anko.support.v4.startActivity
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.Main4Activity
import syl.com.kotlindemo2.activity.Main5Activity
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/19.
 * @Describe
 * @Called
 */
class AnkoFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_anko, container, false).also {
            it.findViewById<Button>(R.id.btn1).setOnClickListener {
                startActivity<Main4Activity>(Pair("key1", "value"), Pair("key2", "value2"))
            }
            it.findViewById<Button>(R.id.btn2).setOnClickListener {
                startActivity<Main5Activity>(Pair("key1", "value"), Pair("key2", "value2"))
            }
        }
    }
}