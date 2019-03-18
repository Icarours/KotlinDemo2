package syl.com.kotlindemo2.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import syl.com.kotlindemo2.R

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val iv = findViewById<ImageView>(R.id.iv)
        findViewById<View>(R.id.btn1).setOnClickListener {
            iv.setImageResource(R.drawable.mm1)
        }
        findViewById<View>(R.id.btn2).setOnClickListener {
            val intent = Intent(this, Main2Activity().javaClass)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Main3Activity", "onDestroy()..")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Main3Activity", "onStart()..")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Main3Activity", "onResume()..")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Main3Activity", "onRestart()..")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main3Activity", "onPause()..")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("Main3Activity", "onSaveInstanceState()..")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Main3Activity", "onActivityResult()..")
    }
}
