package syl.com.kotlindemo2.bean

/**
 * Created by Bright on 2019/5/25.
 * @Describe
 * @Called
 */
data class UserInfo2(var rowid: Long=0, var xuhao: Int=0, var name: String="", var age: Int=0,
                    var height: Long=0, var weight: Float=0f, var married: Boolean=false,
                    var update_time: String="", var phone: String="", var password: String="")