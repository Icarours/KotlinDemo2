package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity

class Main2Activity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tv = findViewById<TextView>(R.id.tv)
        tv.text = "hello kotlin"
        val btnChange = findViewById<Button>(R.id.btn_change)
        btnChange.setOnClickListener {
            btnChange.text = "点击改变文本文字"
            tv.text = "kotlin 的语法简易到让人很不习惯"
        }
        findViewById<Button>(R.id.btn_toast).setOnClickListener {
            Toast.makeText(this, "弹出toast", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_long_toast).setOnClickListener {
            Toast.makeText(this, "弹出btn_long_toast toast", Toast.LENGTH_SHORT).show()
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        initToolBar(toolbar)
    }
}
