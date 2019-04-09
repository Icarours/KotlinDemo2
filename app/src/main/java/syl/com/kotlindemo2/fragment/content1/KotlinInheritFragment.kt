package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_inherit.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.bird.*

/**
 * Created by Bright on 2019/4/9.
 * @Describe 类-继承
 * 现在有了基类框架，还得往里面补充成员属性和成员方法，然后给这些成员添加开放性修饰符。就像大家在Java和C++世界中熟知的几个关键字，包括public、protected、private，分别表示公开、只对子类开放、私有。那么Kotlin体系参照Java世界也给出了四个开放性修饰符，按开放程度从高到低分别是：
public : 对所有人开放。Kotlin的类、函数、变量不加开放性修饰符的话，默认就是public类型。
internal : 只对本模块内部开放，这是Kotlin新增的关键字。对于App开发来说，本模块便是指App自身。
protected : 只对自己和子类开放。
private : 只对自己开放，即私有。
注意到这几个修饰符与open一样都加在类和函数前面，并且都包含“开放”的意思，乍看过去还真有点扑朔迷离，到底open跟四个开放性修饰符是什么关系？其实也不复杂，open不控制某个对象的访问权限，只决定该对象能否繁衍开来，说白了，就是公告这个家伙有没有资格生儿育女。只有头戴open帽子的类，才允许作为基类派生出子类来；而头戴open帽子的函数，表示它允许在子类中进行重写。
至于那四个开放性修饰符，则是用来限定允许访问某对象的外部范围，通俗地说，就是哪里的男人可以娶这个美女。头戴public的，表示全世界的男人都能娶她；头戴internal的，表示本国的男人可以娶她；头戴protected的，表示本单位以及下属单位的男人可以娶她；头戴private的，表示肥水不流外人田，只有本单位的帅哥才能娶这个美女噢。
因为private的限制太严厉了，只对自己开放，甚至都不允许子类染指，所以它跟关键字open势同水火。open表示这个对象可以被继承，或者可以被重载，然而private却坚决斩断该对象与其子类的任何关系，因此二者不能并存。倘若在代码中强行给某个方法同时加上open和private，编译器只能无奈地报错“Modifier 'open' is incompatible with 'private'”，意思是open与private不兼容。
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/77287680
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinInheritFragment : BaseFragment() {
    var count = 0
    var birdName = ""
    var birdSex = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_inherit, container, false).apply {
            tv_tips.text="现在有了基类框架，还得往里面补充成员属性和成员方法，然后给这些成员添加开放性修饰符。就像大家在Java和C++世界中" +
                    "熟知的几个关键字，包括public、protected、private，分别表示公开、只对子类开放、私有。那么Kotlin体系参照Java世界也" +
                    "给出了四个开放性修饰符，按开放程度从高到低分别是：\n" +
                    "public : 对所有人开放。Kotlin的类、函数、变量不加开放性修饰符的话，默认就是public类型。\n" +
                    "internal : 只对本模块内部开放，这是Kotlin新增的关键字。对于App开发来说，本模块便是指App自身。\n" +
                    "protected : 只对自己和子类开放。\n" +
                    "private : 只对自己开放，即私有。\n" +
                    "注意到这几个修饰符与open一样都加在类和函数前面，并且都包含“开放”的意思，乍看过去还真有点扑朔迷离，到底open跟四个开放" +
                    "性修饰符是什么关系？其实也不复杂，open不控制某个对象的访问权限，只决定该对象能否繁衍开来，说白了，就是公告这个家伙有没有" +
                    "资格生儿育女。只有头戴open帽子的类，才允许作为基类派生出子类来；而头戴open帽子的函数，表示它允许在子类中进行重写。\n" +
                    "至于那四个开放性修饰符，则是用来限定允许访问某对象的外部范围，通俗地说，就是哪里的男人可以娶这个美女。头戴public的，表示" +
                    "全世界的男人都能娶她；头戴internal的，表示本国的男人可以娶她；头戴protected的，表示本单位以及下属单位的男人可以娶她；头" +
                    "戴private的，表示肥水不流外人田，只有本单位的帅哥才能娶这个美女噢。\n" +
                    "因为private的限制太严厉了，只对自己开放，甚至都不允许子类染指，所以它跟关键字open势同水火。open表示这个对象可以被继承，" +
                    "或者可以被重载，然而private却坚决斩断该对象与其子类的任何关系，因此二者不能并存。倘若在代码中强行给某个方法同时加上open和" +
                    "private，编译器只能无奈地报错“Modifier 'open' is incompatible with 'private'”，意思是open与private不兼容。"
            btn_class_bird.setOnClickListener {
                setbirdInfo()
                var bird = when (count % 2) {
                    0 -> Bird(birdName)
                    else -> Bird(birdName, birdSex)
                }
                tv_class_inherit.text = bird.getDesc("鸟语林")
                //外部无法访问private和protected的成员属性和成员方法
                //bird.getSexName(birdSex)
            }

            btn_class_duck.setOnClickListener {
                var sexBird = if (count++ % 3 == 0) Bird.MALE else Bird.FEMALE
                var duck = Duck(sex = sexBird)
                tv_class_inherit.text = duck.getDesc("鸟语林")
            }

            btn_class_ostrich.setOnClickListener {
                var sexBird = if (count++ % 3 == 0) Bird.MALE else Bird.FEMALE
                var ostrich = Ostrich(sex = sexBird)
                tv_class_inherit.text = ostrich.getDesc("鸟语林")
            }

            btn_abstract_cock.setOnClickListener {
                //调用公鸡类的叫唤方法
                tv_class_inherit.text = Cock().callOut(count++ % 10)
            }

            btn_abstract_hen.setOnClickListener {
                //调用母鸡类的叫唤方法
                tv_class_inherit.text = Hen().callOut(count++ % 10)
            }

            btn_interface_behavior.setOnClickListener {
                tv_class_inherit.text = when (count++ % 3) {
                    0 -> Goose().fly()
                    1 -> Goose().swim()
                    else -> Goose().run()
                }
            }

            btn_delegate_behavior.setOnClickListener {
                var fowl = when (count++ % 6) {
                    //把代理类作为输入参数来创建实例
                    0 -> WildFowl("老鹰", Bird.MALE, BehaviorFly())
                    //由于sex字段是个默认参数，因此可通过命名参数给behavior赋值
                    1 -> WildFowl("凤凰", behavior = BehaviorFly())
                    2 -> WildFowl("大雁", Bird.FEMALE, BehaviorSwim())
                    3 -> WildFowl("企鹅", behavior = BehaviorSwim())
                    4 -> WildFowl("鸵鸟", Bird.MALE, BehaviorRun())
                    else -> WildFowl("鸸鹋", behavior = BehaviorRun())
                }
                var action = when (count % 11) {
                    in 0..3 -> fowl.fly()
                    4, 7, 10 -> fowl.swim()
                    else -> fowl.run()
                }
                tv_class_inherit.text = "${fowl.name}：$action"
            }
        }
    }

    fun setbirdInfo() {
        count++
        birdName = if (count % 2 == 0) "孔雀" else "黄鹂"
        birdSex = if (count % 3 == 0) Bird.MALE else Bird.FEMALE
    }

}