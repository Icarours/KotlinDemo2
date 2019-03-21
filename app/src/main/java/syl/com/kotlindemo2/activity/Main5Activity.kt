package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import org.jetbrains.anko.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity

class Main5Activity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Anko可以直接在代码中编写布局,很牛
        verticalLayout {
            toolbar {
                title = "Main5Activity"
                subtitle = "subTitle"
                background = getDrawable(R.color.colorPrimaryDark)
                navigationIcon = getDrawable(R.drawable.ic_arrow_back_black_24dp)
                setNavigationOnClickListener { finish() }
            }
            verticalLayout {
                padding = dip(30)
                textView {
                    text = context.getString(R.string.tv_str)
                    textSize = 36f
                    textColor = resources.getColor(R.color.colorAccent)
                    background = getDrawable(R.color.colorPrimaryDark)
                }.lparams(width = wrapContent, height = dip(200))
                editText {
                    hint = "Name"
                    textSize = 24f
                }
                editText {
                    hint = "Password"
                    textSize = 24f
                }
                button("Login") {
                    textSize = 26f
                }
                imageView(R.drawable.mm1) {
                }
            }
        }
    }
}
