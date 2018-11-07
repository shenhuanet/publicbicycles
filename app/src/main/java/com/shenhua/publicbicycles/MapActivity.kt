package com.shenhua.publicbicycles

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapOptions
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.*
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private var aMap: AMap? = null
    private val converter = CoordinateConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val centerBJPoint = baiduConverter(intent.getParcelableExtra<LatLng>("current"))
        val mapOptions = AMapOptions()
        mapOptions.camera(CameraPosition(centerBJPoint, 16.5f, 0f, 0f))
        mapView = MapView(this, mapOptions)
        mapView.onCreate(savedInstanceState)
        aMap = mapView.map
        mapRoot.addView(mapView)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.strokeColor(ContextCompat.getColor(this, R.color.colorAccent))
        myLocationStyle.radiusFillColor(ContextCompat.getColor(this, R.color.colorPrimary30))
        myLocationStyle.interval(2000)
        aMap!!.setMyLocationStyle(myLocationStyle)
        aMap!!.uiSettings.isMyLocationButtonEnabled = true;
        aMap!!.isMyLocationEnabled = true

        val list: ArrayList<Points> = intent.getParcelableArrayListExtra("list")
        for (p in list) {
            val markerOptions = MarkerOptions()
            markerOptions.position(baiduConverter(p.latLng!!))
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.mipmap.ic_marker)))
            markerOptions.title(p.title)
            markerOptions.snippet(p.desc)
            aMap!!.addMarker(markerOptions)
        }
//        aMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun baiduConverter(src: LatLng): LatLng {
        converter.from(CoordinateConverter.CoordType.BAIDU)
        converter.coord(src)
        return converter.convert()
    }

}
