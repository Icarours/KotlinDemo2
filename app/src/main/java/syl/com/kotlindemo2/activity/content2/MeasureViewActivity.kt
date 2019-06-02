package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_measure_view.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.PlanetAdapter
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.Planet
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/6/2 11:44
 * desc
 * 测量视图尺寸
 */
class MeasureViewActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_view)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        //lv_planet是系统自带的ListView，被ScrollView嵌套只能显示一行
        val adapter1 = PlanetAdapter(this, Planet.defaultList)
        lv_planet.adapter = adapter1
        lv_planet.onItemClickListener = adapter1
        lv_planet.onItemLongClickListener = adapter1
        //nslv_planet是自定义控件NoScrollListView，会显示所有行
        val adapter2 = PlanetAdapter(this, Planet.defaultList)
        nslv_planet.adapter = adapter2
        nslv_planet.onItemClickListener = adapter2
        nslv_planet.onItemLongClickListener = adapter2
    }
}
