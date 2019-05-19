package syl.com.kotlindemo2.fragment.content2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.util.DateUtil
import java.io.File
import java.net.URL

/**
 * Created by Bright on 2019/5/18.
 * @Describe 加载网络图片
 * @Called
 */
class HttpImageFragment : BaseFragment() {
    private val imageUrl = "http://yx12.fjjcjy.com/Public/Control/GetValidateCode?time="
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_http_image, container, false).apply {
            val ivImageCode = find<ImageView>(R.id.iv_image_code)
            ivImageCode.setOnClickListener { getImageCode(ivImageCode) }
            getImageCode(ivImageCode)
        }
    }

    //获取网络上的图片验证码
    private fun getImageCode(ivImageCode:ImageView) {
        ivImageCode.isEnabled = false
        doAsync {
            val url = "$imageUrl${DateUtil.getFormatTime()}"
            val bytes = URL(url).readBytes()
            //把字节数组解码为位图数据
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            //也可通过下面三行代码把字节数组写入文件，即生成一个图片文件
            val path = context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
            val file_path = "$path${DateUtil.getFormatTime()}.png"
            File(file_path).writeBytes(bytes)
            //获得验证码图片数据，回到主线程把验证码显示在界面上
            uiThread { finishGet(bitmap,ivImageCode) }
        }
    }

    //在主线程中显示获得到的验证码图片
    private fun finishGet(bitmap: Bitmap,ivImageCode:ImageView) {
        ivImageCode.setImageBitmap(bitmap)
        ivImageCode.isEnabled = true
    }
}