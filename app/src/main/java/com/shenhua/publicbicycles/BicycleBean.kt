package com.shenhua.publicbicycles

/**
 * Created by shenhua on 2018/5/23.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class BicycleBean {


    /**
     * count : 10
     * data : [{"address":"建业路511号","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23987,"lat":"30.194274","len":"94","lon":"120.187605","name":"建业路滨兴路口西南","number":"6251","rentcount":"21","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13486377970","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"滨安路建业路口东北","areaname":"城南","bikenum":"32","createTime":1527039868628,"guardType":"无人值守","id":24055,"lat":"30.19207","len":"211","lon":"120.189478","name":"滨安路建业路口东北","number":"6319","rentcount":"32","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13750870574","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"滨安路1186号唯新食品有限公司对面","areaname":"城南","bikenum":"32","createTime":1527039868628,"guardType":"无人值守","id":23767,"lat":"30.191325","len":"274","lon":"120.186724","name":"滨安路一一八六号","number":"6030","rentcount":"32","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13486377970","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"滨安路建业路口东南","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23884,"lat":"30.19182","len":"317","lon":"120.190699","name":"滨安路建业路东南","number":"6147","rentcount":"20","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13750870574","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"江南大道建业路口东南加油站对面","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23871,"lat":"30.196908","len":"380","lon":"120.187537","name":"江南大道建业路东南","number":"6134","rentcount":"2","restorecount":"19","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13750870574","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"江南大道江三村公交站西南","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23873,"lat":"30.195273","len":"437","lon":"120.183975","name":"公交江三村站二","number":"6136","rentcount":"0","restorecount":"20","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13486377970","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"东流路建业路东北40米","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23933,"lat":"30.189526","len":"462","lon":"120.18937","name":"东流路建业路口","number":"6196","rentcount":"20","restorecount":"1","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13750870574","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"仁苑南门","areaname":"城南","bikenum":"32","createTime":1527039868628,"guardType":"无人值守","id":23953,"lat":"30.197396","len":"504","lon":"120.185341","name":"平乐路建业路口西北","number":"6216","rentcount":"4","restorecount":"27","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13486377970","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"江南大道江三村公交站西北","areaname":"城南","bikenum":"21","createTime":1527039868628,"guardType":"无人值守","id":23872,"lat":"30.195242","len":"524","lon":"120.182969","name":"公交江三村站一","number":"6135","rentcount":"21","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13486377970","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"},{"address":"滨安路时代大道西北","areaname":"城南","bikenum":"32","createTime":1527039868628,"guardType":"无人值守","id":24021,"lat":"30.192382","len":"577","lon":"120.193897","name":"滨安路时代大道西北","number":"6285","rentcount":"0","restorecount":"0","serviceType":"05:30-24:00","sort":0,"stationPhone":"(7:00-19:00)|13750870574","stationPhone2":"(19:00-24:00)|13486377675","useflag":"正常"}]
     */

    var count: Int = 0
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * address : 建业路511号
         * areaname : 城南
         * bikenum : 21
         * createTime : 1527039868628
         * guardType : 无人值守
         * id : 23987
         * lat : 30.194274
         * len : 94
         * lon : 120.187605
         * name : 建业路滨兴路口西南
         * number : 6251
         * rentcount : 21
         * restorecount : 0
         * serviceType : 05:30-24:00
         * sort : 0
         * stationPhone : (7:00-19:00)|13486377970
         * stationPhone2 : (19:00-24:00)|13486377675
         * useflag : 正常
         */

        var address: String? = null
        var areaname: String? = null
        var bikenum: String? = null
        var createTime: Long = 0
        var guardType: String? = null
        var id: Int = 0
        var lat: String? = null
        var len: String? = null
        var lon: String? = null
        var name: String? = null
        var number: String? = null
        var rentcount: String? = null
        var restorecount: String? = null
        var serviceType: String? = null
        var sort: Int = 0
        var stationPhone: String? = null
        var stationPhone2: String? = null
        var useflag: String? = null
    }
}
