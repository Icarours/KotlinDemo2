package syl.com.kotlindemo2.bean

data class SmsContent(var address: String="", var person: String="",
                      var body: String="", var date: String="", var type: Int=0)
