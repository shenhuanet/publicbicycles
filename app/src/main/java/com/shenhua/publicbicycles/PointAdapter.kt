package com.shenhua.publicbicycles

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amap.api.maps2d.model.LatLng
import java.util.*

/**
 * Created by shenhua on 2018/5/23.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class PointAdapter(private val context: Context, private var datas: List<BicycleBean.DataBean>?) : RecyclerView.Adapter<PointAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataBean = datas!![position]
        (holder.itemView as CardView).setCardBackgroundColor(getColor(dataBean.useflag!!))
        holder.nameTv.text = dataBean.name + "（" + dataBean.number + "）"
        holder.nameTv.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(dataBean.guardType!!), 0, 0, 0)
        holder.addressTv.text = dataBean.address
        holder.serviceTypeTv.text = "租还时间：${dataBean.serviceType}"
        holder.bikeNumberTv.text = "总数：${dataBean.bikenum}"
        holder.restoreCountTv.text = "可还：${dataBean.restorecount}"
        holder.rentCountTv.text = "可租：${dataBean.rentcount}"
        holder.lengthTv.text = "${dataBean.len}米"
        holder.itemView.setOnLongClickListener {
            showDialog(dataBean.stationPhone!!, dataBean.stationPhone2!!)
            true
        }
        holder.lengthTv.setOnClickListener {
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra("current", LatLng(dataBean.lat!!.toDouble(), dataBean.lon!!.toDouble()))
            val list = ArrayList<Points>()
            list.add(Points(LatLng(dataBean.lat!!.toDouble(), dataBean.lon!!.toDouble()), dataBean.name!!, dataBean.number!!))
            intent.putParcelableArrayListExtra("list", list)
            context.startActivity(intent)
        }
    }

    fun setData(datas: List<BicycleBean.DataBean>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    private fun getDrawable(guardType: String): Int {
        return if (TextUtils.isEmpty(guardType) || "无人值守" == guardType) {
            R.mipmap.ic_wuren
        } else R.mipmap.ic_youren
    }

    private fun getColor(useflag: String): Int {
        return if (TextUtils.isEmpty(useflag) || "正常" != useflag) {
            ContextCompat.getColor(context, R.color.colorDisable)
        } else Color.WHITE
    }

    private fun getPhoneItems(vararg phones: String) {
        for (i in phones.indices) {
            val split = phones[i].split("|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }
    }

    private fun showDialog(vararg phones: String) {
        val builder = AlertDialog.Builder(context)
        builder.setItems(phones) { _, _ -> }
        builder.create().show()
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTv: TextView = itemView.findViewById(R.id.tvName)
        var addressTv: TextView = itemView.findViewById(R.id.tvAddress)
        var serviceTypeTv: TextView = itemView.findViewById(R.id.tvServiceType)
        var bikeNumberTv: TextView = itemView.findViewById(R.id.tvBikeNumber)
        var restoreCountTv: TextView = itemView.findViewById(R.id.tvRestoreCount)
        var rentCountTv: TextView = itemView.findViewById(R.id.tvRentCount)
        var lengthTv: TextView = itemView.findViewById(R.id.tvLength)

    }
}
