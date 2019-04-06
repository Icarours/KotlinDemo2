package syl.com.kotlindemo2.bean.animal

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/8/8.
 */
//如果主构造函数没有带@符号的注解说明，则类名后面的constructor可以省略
//class AnimalMain (context:Context, name:String) {
class AnimalMain constructor(context: Context, name: String) {
    var name = ""
    var sex = 0

    init {
        context.toast("这是只$name")
    }

    /**
     * 二级构造函数
     */
    constructor(context: Context, name: String, sex: Int) : this(context, name) {
        var sexName: String = if (sex == 0) "公" else "母"
        context.toast("这只${name}是${sexName}的")
        this.name = name
        this.sex = sex
    }

    override fun toString(): String {
        return "AnimalMain(name='$name', sex=$sex)"
    }
}