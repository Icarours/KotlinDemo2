package syl.com.kotlindemo2.fragment.content2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_progress_circle.view.*
import org.jetbrains.anko.support.v4.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/5/18.
 * @Describe 自定义圆形进度条
 * @Called
 */
class ProgressCircleFragment : BaseFragment() {
    private val progresses = listOf("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_progress_circle, container, false).apply {
            sp_progress.visibility = View.GONE
            tv_spinner.visibility = View.VISIBLE
            tv_spinner.text = progresses[0]
            tv_spinner.setOnClickListener {
                selector("请选择进度值", progresses) { dialogInterFace, i ->
                    tv_spinner.text = progresses[i]
                    tpc_progress.setProgress(progresses[i].toInt(), 40f)
                }
            }
        }
    }
}