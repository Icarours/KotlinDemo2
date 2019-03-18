package syl.com.kotlindemo2.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.bean.TitleBean

/**
 * Created by Bright on 2019/3/19.
 * @Describe
 * @Called
 */
class ContentAdapter(layoutResId: Int, data: List<TitleBean>?) :
    BaseQuickAdapter<TitleBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: TitleBean) {
        helper.setText(R.id.tv_title, item.title)
            .setText(R.id.tv_desc, item.description)
    }
}