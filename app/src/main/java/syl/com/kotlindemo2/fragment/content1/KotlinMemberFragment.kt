package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_member.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.animal.WildAnimalCompanion
import syl.com.kotlindemo2.bean.animal.WildAnimalConstant
import syl.com.kotlindemo2.bean.animal.WildAnimalFunction
import syl.com.kotlindemo2.bean.animal.WildAnimalMember

/**
 * Created by Bright on 2019/4/6.
 * @Describe 类-伴生
 *
 * Kotlin精简了大幅代码，包括：
1、冗余的同名属性声明；
2、冗余的同名属性赋值；
3、冗余的属性获取方法与设置方法；

Kotlin取消了关键字static，也就无法运用静态成员的相关手段。为了弥补这方面的功能缺陷，Kotlin引入了伴生对象的概念，可以把它理解为“影子”，伴生对象之于它所在的类，仿佛是如影随形。打个比方，类的实例犹如这个类的儿子，一个类可以拥有很多个儿子；而影子只有一个，并且儿子需要繁衍而来，但影子天生就有、无需繁衍。利用伴生对象的技术，即可间接实现静态成员的功能，前面有个代码从性别类型获得性别名称，反过来也可以从性别名称获得性别类型，这个功能便可以在伴生对象中定义一个judgeSex方法判断性别类型。
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/77152507
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinMemberFragment : BaseFragment() {
    var count = 0
    var animalName = ""
    var animalSex = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_member, container, false).apply {
            tv_tips.text = "Kotlin精简了大幅代码，包括：\n" +
                    "1、冗余的同名属性声明；\n" +
                    "2、冗余的同名属性赋值；\n" +
                    "3、冗余的属性获取方法与设置方法；\n" +
                    " \n" +
                    "Kotlin取消了关键字static，也就无法运用静态成员的相关手段。为了弥补这方面的功能缺陷，Kotlin引入了伴生对象的概念，" +
                    "可以把它理解为“影子”，伴生对象之于它所在的类，仿佛是如影随形。打个比方，类的实例犹如这个类的儿子，一个类可以拥有很多" +
                    "个儿子；而影子只有一个，并且儿子需要繁衍而来，但影子天生就有、无需繁衍。利用伴生对象的技术，即可间接实现静态成员的功能，" +
                    "前面有个代码从性别类型获得性别名称，反过来也可以从性别名称获得性别类型，这个功能便可以在伴生对象中定义一个judgeSex方法判断性别类型。\n"

            btn_member_default.setOnClickListener {
                setAnimalInfo()
                var animal = when (count % 2) {
                    0 -> WildAnimalConstant(animalName)
                    else -> WildAnimalConstant(animalName, animalSex)
                }
                tv_class_member.text = "这只${animal.name}是${if (animal.sex == 0) "公" else "母"}的"
            }

            btn_member_custom.setOnClickListener {
                setAnimalInfo()
                var animal = when (count % 2) {
                    0 -> WildAnimalMember(animalName)
                    else -> WildAnimalMember(animalName, animalSex)
                }
                tv_class_member.text = "这只${animal.name}是${animal.sexName}的"
            }

            btn_member_function.setOnClickListener {
                setAnimalInfo()
                var animal = when (count % 2) {
                    0 -> WildAnimalFunction(animalName)
                    else -> WildAnimalFunction(animalName, animalSex)
                }
                tv_class_member.text = animal.getDesc("动物园")
            }

            val sexArray: Array<String> = arrayOf("公", "母", "雄", "雌")
            btn_companion_object.setOnClickListener {
                var sexName: String = sexArray[count++ % 4]
                //伴生对象的WildAnimal名称可以省略掉
                //tv_class_member.text = "\"$sexName\"对应的类型是${WildAnimalCompanion.WildAnimal.judgeSex(sexName)}"
                tv_class_member.text = "\"$sexName\"对应的类型是${WildAnimalCompanion.judgeSex(sexName)}"
            }

            btn_property_constant.setOnClickListener {
                setAnimalInfo()
                var animal = when (count % 2) {
                    0 -> WildAnimalConstant(animalName)
                    else -> WildAnimalConstant(animalName, animalSex)
                }
                tv_class_member.text = "这只${animal.name}是${if (animal.sex == WildAnimalConstant.MALE) "公" else "母"}的"
            }
        }
    }

    fun setAnimalInfo() {
        count++
        animalName = if (count % 2 == 0) "老虎" else "斑马"
        animalSex = if (count % 3 == 0) 0 else 1
    }
}