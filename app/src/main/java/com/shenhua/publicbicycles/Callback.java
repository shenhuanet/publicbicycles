package com.shenhua.publicbicycles;

/**
 * Created by shenhua on 2018/5/23.
 *
 * @author shenhua
 *         Email shenhuanet@126.com
 */
public interface Callback {

    /**
     * 定位成功时回调
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @param city      城市
     * @param address   地址描述
     */
    void onSuccess(double latitude, double longitude, String city, String address);

    /**
     * 定位失败时回调
     *
     * @param msg 错误信息
     */
    void onError(String msg);
}
