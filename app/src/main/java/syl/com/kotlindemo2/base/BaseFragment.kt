package syl.com.kotlindemo2.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    open fun initData() {
    }
}