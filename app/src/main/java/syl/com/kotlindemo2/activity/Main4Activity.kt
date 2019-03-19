
package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.find
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity

class Main4Activity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val toolbar = find<Toolbar>(R.id.toolbar)
        initToolBar(toolbar)
        val key1 = intent.extras.getString("key1")
        val key2 = intent.extras.getString("key2")
        find<TextView>(R.id.tv1).text = key1
        find<TextView>(R.id.tv2).text = key2
    }
}
