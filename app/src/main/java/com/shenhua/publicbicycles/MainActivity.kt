package com.shenhua.publicbicycles

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), Callback {

    override fun onSuccess(latitude: Double, longitude: Double, city: String?, address: String?) {
        if (current != R.id.menuCurrent) {
            return
        }
        tvAddress.text = address
        mScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(1)
        mScheduledThreadPoolExecutor.submit({
            val http = BaseHttpApi.getInstance()
            val url = BASE_URL + "?lng=$longitude&lat=$latitude&_=${System.currentTimeMillis()}"
            val result = http.doGet(url)
            if (result != null) {
                val bean = Gson().fromJson(result, BicycleBean::class.java)
                mDatas = bean.data

                runOnUiThread {
                    pointAdapter!!.setData(mDatas!!)
                    hideLoading()
                }
            }
        })
    }

    override fun onError(msg: String?) {
        tvAddress.text = msg
        hideLoading()
    }

    private var geoLocation: GeoLocation? = null
    private var current: Int = R.id.menuCurrent
    private var pointAdapter: PointAdapter? = null
    private var mDatas: List<BicycleBean.DataBean>? = null
    private lateinit var mScheduledThreadPoolExecutor: ScheduledThreadPoolExecutor
    private val BASE_URL = "http://c.ggzxc.com.cn/wz/np_getBikes.do"
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geoLocation = GeoLocation(this)
        geoLocation!!.init(this);
        navigationView.setOnNavigationItemReselectedListener {
            if (current == R.id.menuCurrent) {
                showLoading()
                start()
            }
        }
        navigationView.setOnNavigationItemSelectedListener({ item ->
            mScheduledThreadPoolExecutor.shutdown()
            showLoading()
            if (item.itemId == R.id.menuPoint1) {
                tvAddress.text = "建业路滨兴路口西南"
                mScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(1)
                changeData(120.187605, 30.194274)
            }
            if (item.itemId == R.id.menuPoint2) {
                tvAddress.text = "西兴路迎春路东南"
                mScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(1)
                changeData(120.235624, 30.203307)
            }
            current = item.itemId
            true
        })
        pointAdapter = PointAdapter(this, mDatas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = pointAdapter
        mScheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(1)
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("请稍候")
    }

    private fun changeData(lng: Double, lat: Double) {
        val http = BaseHttpApi.getInstance()
        val url = BASE_URL + "?lng=$lng&lat=$lat&_=${System.currentTimeMillis()}"
        val gson = Gson()
        mScheduledThreadPoolExecutor.scheduleAtFixedRate({
            if (current != R.id.menuCurrent) {
                val result = http.doGet(url)
                if (result != null) {
                    val bean = gson.fromJson(result, BicycleBean::class.java)
                    mDatas = bean.data

                    runOnUiThread {
                        pointAdapter!!.setData(mDatas!!)
                        hideLoading()
                    }
                }
            }
        }, 0, 2000, TimeUnit.MILLISECONDS)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        start()
        showLoading()
    }

    private fun start() {
        geoLocation!!.location()
    }

    override fun onDestroy() {
        super.onDestroy()
        geoLocation!!.onDestroy()
    }

    private fun showLoading() {
        if (progressDialog!!.isShowing) {
            return
        }
        progressDialog!!.show()
    }

    private fun hideLoading() {
        if (progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }
}
