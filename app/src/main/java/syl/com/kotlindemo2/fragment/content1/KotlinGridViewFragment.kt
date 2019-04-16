package syl.com.kotlindemo2.fragment.content1

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import kotlinx.android.synthetic.main.fragment_kotlin_grid_view.view.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.support.v4.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.PlanetGridAdapter
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.Planet

/**
 * Created by Bright on 2019/4/16.
 * @Describe GridView
 * @Called
 */
class KotlinGridViewFragment:BaseFragment() {
    private val dividers = listOf(
        "不显示分隔线",
        "只显示内部分隔线(NO_STRETCH)",
        "只显示内部分隔线(COLUMN_WIDTH)",
        "只显示内部分隔线(STRETCH_SPACING)",
        "只显示内部分隔线(SPACING_UNIFORM)",
        "显示全部分隔线(看我用padding大法)")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_grid_view,container,false).apply {

            val adapter = PlanetGridAdapter(context, Planet.defaultList, Color.WHITE)
            gv_planet.adapter = adapter
            gv_planet.onItemClickListener = adapter
            gv_planet.onItemLongClickListener = adapter

            val dividerPad = dip(2)
            sp_grid.visibility = View.GONE
            tv_spinner.visibility = View.VISIBLE
            tv_spinner.text = dividers[0]
            tv_spinner.setOnClickListener {
                selector("请选择分隔线显示方式", dividers) { dialogInterface,i ->
                    tv_spinner.text = dividers[i]
                    gv_planet.setBackgroundColor(Color.RED)
                    gv_planet.horizontalSpacing = dividerPad
                    gv_planet.verticalSpacing = dividerPad
                    gv_planet.stretchMode = GridView.STRETCH_COLUMN_WIDTH
                    gv_planet.columnWidth = 250
                    gv_planet.setPadding(0, 0, 0, 0)
                    when (i) {
                        0 -> {
                            gv_planet.setBackgroundColor(Color.WHITE)
                            gv_planet.horizontalSpacing = 0
                            gv_planet.verticalSpacing = 0
                        }
                        1 -> gv_planet.stretchMode = GridView.NO_STRETCH
                        2 -> gv_planet.stretchMode = GridView.STRETCH_COLUMN_WIDTH
                        3 -> gv_planet.stretchMode = GridView.STRETCH_SPACING
                        4 -> gv_planet.stretchMode = GridView.STRETCH_SPACING_UNIFORM
                        5 -> gv_planet.setPadding(dividerPad, dividerPad, dividerPad, dividerPad)
                    }
                }
            }
        }
    }
}