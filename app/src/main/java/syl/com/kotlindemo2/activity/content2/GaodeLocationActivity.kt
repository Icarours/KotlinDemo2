package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.annotation.RequiresApi
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol
import com.amap.api.location.AMapLocationListener
import com.amap.api.location.AMapLocationQualityReport
import kotlinx.android.synthetic.main.activity_gaode_location.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil


/**
 * author   Bright
 * date     2019/5/18 15:55
 * desc
 * 高德地图定位
 */
class GaodeLocationActivity : BaseActivity() {
    private var TAG = "GaodeLocationActivity"
    //在kotlin中,对象初始化不要放在成员变量位置,会报错,原因未知
    private var locationClient: AMapLocationClient? = null
    private var locationOption: AMapLocationClientOption? = null
    /**
     * 定位监听
     */
    var locationListener: AMapLocationListener = AMapLocationListener { location ->
        if (null != location) {
            val sb = StringBuffer()
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (location.errorCode == 0) {
                sb.append("定位成功" + "\n")
                sb.append("定位类型: " + location.locationType + "\n")
                sb.append("经    度    : " + location.longitude + "\n")
                sb.append("纬    度    : " + location.latitude + "\n")
                sb.append("精    度    : " + location.accuracy + "米" + "\n")
                sb.append("提供者    : " + location.provider + "\n")

                sb.append("速    度    : " + location.speed + "米/秒" + "\n")
                sb.append("角    度    : " + location.bearing + "\n")
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : " + location.satellites + "\n")
                sb.append("国    家    : " + location.country + "\n")
                sb.append("省            : " + location.province + "\n")
                sb.append("市            : " + location.city + "\n")
                sb.append("城市编码 : " + location.cityCode + "\n")
                sb.append("区            : " + location.district + "\n")
                sb.append("区域 码   : " + location.adCode + "\n")
                sb.append("地    址    : " + location.address + "\n")
                sb.append("兴趣点    : " + location.poiName + "\n")
                //定位完成的时间
                sb.append("定位时间: " + DateUtil.nowDateTime + "\n")
            } else {
                //定位失败
                sb.append("定位失败" + "\n")
                sb.append("错误码:" + location.errorCode + "\n")
                sb.append("错误信息:" + location.errorInfo + "\n")
                sb.append("错误描述:" + location.locationDetail + "\n")
            }
            sb.append("***定位质量报告***").append("\n")
            sb.append("* WIFI开关：").append(if (location.locationQualityReport.isWifiAble) "开启" else "关闭").append("\n")
            sb.append("* GPS状态：").append(getGPSStatusString(location.locationQualityReport.gpsStatus)).append("\n")
            sb.append("* GPS星数：").append(location.locationQualityReport.gpsSatellites).append("\n")
            sb.append("* 网络类型：" + location.locationQualityReport.networkType).append("\n")
            sb.append("* 网络耗时：" + location.locationQualityReport.netUseTime).append("\n")
            sb.append("****************").append("\n")
            //定位之后的回调时间
            sb.append("回调时间: " + DateUtil.nowDateTime + "\n")

            //解析定位结果，
            val result = sb.toString()
            Log.d(TAG,"result=="+result)
            tv_content.setText(result)
        } else {
            tv_content.setText("定位失败，loc is null")
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(syl.com.kotlindemo2.R.layout.activity_gaode_location)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        tv_content.movementMethod = ScrollingMovementMethod.getInstance()
        //初始化定位
        initLocation()
    }

    /**
     * 初始化定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun initLocation() {
        //初始化client
        locationClient = AMapLocationClient(applicationContext)
        locationOption = getDefaultOption()
        //设置定位参数
        locationClient!!.setLocationOption(locationOption)
        // 设置定位监听
        locationClient!!.setLocationListener(locationListener)

        startLocation()
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun getDefaultOption(): AMapLocationClientOption {
        val mOption = AMapLocationClientOption()
        mOption.locationMode = AMapLocationMode.Hight_Accuracy//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.isGpsFirst = true//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.httpTimeOut = 30000//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.interval = 2000//可选，设置定位间隔。默认为2秒
        mOption.isNeedAddress = true//可选，设置是否返回逆地理地址信息。默认是true
        mOption.isOnceLocation = false//可选，设置是否单次定位。默认是false
        mOption.isOnceLocationLatest = false//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP)//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.isSensorEnable = false//可选，设置是否使用传感器。默认是false
        mOption.isWifiScan = true //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.isLocationCacheEnable = true //可选，设置是否使用缓存定位，默认为true
        mOption.geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption
    }

    /**
     * 获取GPS状态的字符串
     * @param statusCode GPS状态码
     * @return
     */
    private fun getGPSStatusString(statusCode: Int): String {
        var str = ""
        when (statusCode) {
            AMapLocationQualityReport.GPS_STATUS_OK -> str = "GPS状态正常"
            AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER -> str = "手机中没有GPS Provider，无法进行GPS定位"
            AMapLocationQualityReport.GPS_STATUS_OFF -> str = "GPS关闭，建议开启GPS，提高定位质量"
            AMapLocationQualityReport.GPS_STATUS_MODE_SAVING -> str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量"
            AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION -> str = "没有GPS定位权限，建议开启gps定位权限"
        }
        return str
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyLocation()
    }

    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun startLocation() {
        // 设置定位参数
        locationClient?.setLocationOption(locationOption)
        // 启动定位
        locationClient?.startLocation()
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun stopLocation() {
        // 停止定位
        locationClient?.stopLocation()
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     */
    private fun destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient!!.onDestroy()
            locationClient = null
            locationOption = null
        }
    }
}
