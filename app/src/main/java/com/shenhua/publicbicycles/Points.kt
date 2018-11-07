package com.shenhua.publicbicycles

import android.os.Parcel
import android.os.Parcelable

import com.amap.api.maps2d.model.LatLng

/**
 * Created by shenhua on 2018/5/24.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class Points : Parcelable {

    var latLng: LatLng? = null
    var title: String? = null
    var desc: String? = null

    constructor(latLng: LatLng, title: String, desc: String) {
        this.latLng = latLng
        this.title = title
        this.desc = desc
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.latLng, flags)
        dest.writeString(this.title)
        dest.writeString(this.desc)
    }

    protected constructor(`in`: Parcel) {
        this.latLng = `in`.readParcelable(LatLng::class.java.classLoader)
        this.title = `in`.readString()
        this.desc = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Points> = object : Parcelable.Creator<Points> {
            override fun createFromParcel(source: Parcel): Points {
                return Points(source)
            }

            override fun newArray(size: Int): Array<Points?> {
                return arrayOfNulls(size)
            }
        }
    }
}
