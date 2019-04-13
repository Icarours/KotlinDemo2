package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_kotlin_image_sacle.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/13.
 * @Describe ImageScale图片的缩放样式
 * @Called
 */
class KotlinImageScaleFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_image_sacle, container, false).apply {
            iv_scale.setImageResource(R.drawable.mm1)
            btn_center.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.CENTER }
            btn_fitCenter.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_CENTER }
            btn_centerCrop.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.CENTER_CROP }
            btn_centerInside.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.CENTER_INSIDE }
            btn_fitXY.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_XY }
            btn_fitStart.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_START }
            btn_fitEnd.setOnClickListener { iv_scale.scaleType = ImageView.ScaleType.FIT_END }
        }
    }
}