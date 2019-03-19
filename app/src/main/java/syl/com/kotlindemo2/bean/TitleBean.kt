package syl.com.kotlindemo2.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
class TitleBean() :Parcelable{
    var id: Int? = null
    var title: String? = null
    var description: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        description = parcel.readString()
    }

    constructor(id: Int?, title: String?, description: String?) : this() {
        this.id = id
        this.title = title
        this.description = description
    }

    override fun toString(): String {
        return "TitleBean(id=$id, title=$title, description=$description)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TitleBean> {
        override fun createFromParcel(parcel: Parcel): TitleBean {
            return TitleBean(parcel)
        }

        override fun newArray(size: Int): Array<TitleBean?> {
            return arrayOfNulls(size)
        }
    }
}