package syl.com.kotlindemo2.bean

/**
 * Created by Bright on 2019/3/24.
 * @Describe
 * @Called
 */
open class Phone(val name: String, val price: Float, val desc: String){
    override fun toString(): String {
        return "Phone(name='$name', price=$price, desc='$desc')"
    }
}