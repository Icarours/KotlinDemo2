package syl.com.kotlindemo2.bean.animal

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/8/8.
 */
class AnimalSeparate {
    var name: String = ""
    var sex = 0

    constructor(context: Context, name: String) {
        context.toast("这是只$name")
        this.name = name
    }

    constructor(context: Context, name: String, sex: Int) {
        var sexName: String = if (sex == 0) "公" else "母"
        context.toast("这只${name}是${sexName}的")
        this.name = name
        this.sex = sex
    }

    override fun toString(): String {
        return "AnimalSeparate(name='$name', sex=$sex)"
    }
}