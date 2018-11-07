package com.shenhua.publicbicycles

import android.content.Context
import android.util.Log
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener

/**
 * Created by shenhua on 2018/5/23.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class GeoLocation(private val context: Context) {
    private var mLocationClient: AMapLocationClient? = null

    /**
     * 初始化定位
     *
     * @param callback 定位回调
     */
    fun init(callback: Callback) {
        mLocationClient = AMapLocationClient(context.applicationContext)
        val mLocationListener = AMapLocationListener { aMapLocation ->
            if (aMapLocation.errorCode == 0) {
                callback.onSuccess(aMapLocation.latitude, aMapLocation.longitude,
                        aMapLocation.city, aMapLocation.address)
            } else {
                // 定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                val msg = ("定位失败:ErrCode:"
                        + aMapLocation.errorCode + ", errInfo:"
                        + aMapLocation.errorInfo)
                Log.e("onLocationChanged", msg)
                callback.onError(msg)
            }
        }
        mLocationClient!!.setLocationListener(mLocationListener)
        // 定位选项
        val mLocationOption = AMapLocationClientOption()
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationOption.httpTimeOut = 20000
        mLocationClient!!.setLocationOption(mLocationOption)
    }

    /**
     * 开始定位
     */
    fun location() {
        if (mLocationClient == null) {
            throw RuntimeException("必须先初始化")
        }
        mLocationClient!!.startLocation()
    }

    /**
     * 销毁
     */
    fun onDestroy() {
        if (mLocationClient != null) {
            mLocationClient!!.stopLocation()
            mLocationClient!!.onDestroy()
        }
    }

}
