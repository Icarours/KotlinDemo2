package syl.com.kotlindemo2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        Log.d("MainActivity", "onDestroy()..")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()..")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume()..")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()..")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause()..")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("MainActivity", "onSaveInstanceState()..")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "onActivityResult()..")
    }
}
