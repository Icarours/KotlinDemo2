package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.TitleBean

/**
 * Created by Bright on 2019/3/19.
 * @Describe
 * @Called
 */
class PassValueFragment : BaseFragment() {
    var mTv1: TextView? = null
    var mTv2: TextView? = null
    var mTv3: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pass_value, container, false).also {
            mTv1 = it.findViewById(R.id.tv1)
            mTv2 = it.findViewById(R.id.tv2)
            mTv3 = it.findViewById(R.id.tv3)
            /**
             * 注意:savedInstanceState和我们自己传值的bundle不是同一个bundle
             */

            val bundle = arguments
            val titleBean = bundle!!.getParcelable("title") as TitleBean
            val key1 = bundle!!.getString("key1")
            val key2 = bundle!!.getString("key2")
            mTv1!!.text = titleBean!!.toString()
            mTv2!!.text = key1
            mTv3!!.text = key2
        }
    }
}