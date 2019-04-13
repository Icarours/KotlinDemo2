package syl.com.kotlindemo2.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ouyangshen on 2017/9/7.
 */
//@Parcelize注解表示自动实现Parcelable接口的相关方法
data class MessageInfo(val content: String, val send_time: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeString(send_time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageInfo> {
        override fun createFromParcel(parcel: Parcel): MessageInfo {
            return MessageInfo(parcel)
        }

        override fun newArray(size: Int): Array<MessageInfo?> {
            return arrayOfNulls(size)
        }
    }
}
