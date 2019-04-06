package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_class.*
import kotlinx.android.synthetic.main.fragment_kotlin_class.view.*
import org.jetbrains.anko.support.v4.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.bean.animal.Animal
import syl.com.kotlindemo2.bean.animal.AnimalDefault
import syl.com.kotlindemo2.bean.animal.AnimalMain
import syl.com.kotlindemo2.bean.animal.AnimalSeparate

/**
 * Created by Bright on 2019/4/6.
 * @Describe
 * Kotlin对类的写法与Java之间有以下几点区别：
1、Kotlin省略了关键字public；
2、Kotlin用冒号“:”代替extends，也就是通过冒号表示继承关系；
3、Kotlin进行继承时，父类后面多了括号“()”；

跟Java的三点不同之处：
1、Kotlin初始化函数（看似构造函数？）的名字叫init，不像Java那样把类名作为构造函数的名称；
2、Kotlin打印日志使用了类似C语言的println方法，而非Java的System.out.println；
3、Kotlin创建实例时省略了关键字new；

二级构造函数和普通函数相比有两个区别：
1、二级构造函数没有函数名称，只用关键字constructor表示这是个构造函数。
2、二级构造函数需要调用主构造函数，“this(context, name)”这句代码在Java中要写在函数体内部，在Kotlin中则以冒号开头补充到输入参数后面，这意味着二级构造函数实际上是从主构造函数扩展而来，冒号表示前边属于后边的类型，犹如“var count:Int”一般。
由此看来，因为二级构造函数从属于主构造函数，于是如果使用二级构造函数声明该类的实例，则系统会先调用主构造函数的init代码，再调用二级构造函数的自身代码。现在若想声明AnimalMain类的实例，即可通过主构造函数声明，也可通过二级构造函数声明，

总结一下，Kotlin给类的构造函数引进了关键字constructor，并且区分了主构造函数和二级构造函数。主构造函数的入参在类名后面声明，函数体则位于init方法中；二级构造函数从属于主构造函数，它不但由主构造函数扩展而来，而且必定先调用主构造函数的实现代码。另外，Kotlin的构造函数也支持默认参数，从而避免了冗余的构造函数定义。
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/77084535
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinClassFragment : BaseFragment() {
    var count = 0
    var animalName = ""
    var animalSex = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_class, container, false).apply {

            tv_tips.text="Kotlin对类的写法与Java之间有以下几点区别：\n" +
                    "1、Kotlin省略了关键字public；\n" +
                    "2、Kotlin用冒号“:”代替extends，也就是通过冒号表示继承关系；\n" +
                    "3、Kotlin进行继承时，父类后面多了括号“()”；\n" +
                    "\n" +
                    "跟Java的三点不同之处：\n" +
                    "1、Kotlin初始化函数（看似构造函数？）的名字叫init，不像Java那样把类名作为构造函数的名称；\n" +
                    "2、Kotlin打印日志使用了类似C语言的println方法，而非Java的System.out.println；\n" +
                    "3、Kotlin创建实例时省略了关键字new；\n" +
                    "\n" +
                    "二级构造函数和普通函数相比有两个区别：\n" +
                    "1、二级构造函数没有函数名称，只用关键字constructor表示这是个构造函数。\n" +
                    "2、二级构造函数需要调用主构造函数，“this(context, name)”这句代码在Java中要写在函数体内部，在Kotlin中则以冒号开头" +
                    "补充到输入参数后面，这意味着二级构造函数实际上是从主构造函数扩展而来，冒号表示前边属于后边的类型，犹如“var count:Int”一般。\n" +
                    "由此看来，因为二级构造函数从属于主构造函数，于是如果使用二级构造函数声明该类的实例，则系统会先调用主构造函数的init代码，" +
                    "再调用二级构造函数的自身代码。现在若想声明AnimalMain类的实例，即可通过主构造函数声明，也可通过二级构造函数声明，\n" +
                    "\n" +
                    "总结一下，Kotlin给类的构造函数引进了关键字constructor，并且区分了主构造函数和二级构造函数。主构造函数的入参在类名" +
                    "后面声明，函数体则位于init方法中；二级构造函数从属于主构造函数，它不但由主构造函数扩展而来，而且必定先调用主构造函数的" +
                    "实现代码。另外，Kotlin的构造函数也支持默认参数，从而避免了冗余的构造函数定义。"

            btn_class_simple.setOnClickListener {
                //var animal: Animal = Animal()
                //因为根据等号后面的构造函数已经明确知道这是个Animal的实例
                //所以声明对象时可以不用指定它的类型
                var animal = Animal()
                tv_class_init.text = "简单类的初始化结果见日志"
            }

            btn_class_main.setOnClickListener {
                setAnimalInfo()
                when (count % 2) {
                    0 -> {
                        val animal = AnimalMain(context, animalName)
                        toast(animal.toString())
                    }
                    else -> {
                        val animal = AnimalMain(context, animalName, animalSex)
                        toast(animal.toString())
                    }
                }
            }

            btn_class_seperate.setOnClickListener {
                setAnimalInfo()
                when (count % 2) {
                    0 -> {
                        var animal = AnimalSeparate(context, animalName)
                        toast(animal.toString())
                    }
                    else -> {
                        var animal = AnimalSeparate(context, animalName, animalSex)
                        toast(animal.toString())
                    }
                }
            }

            btn_class_default.setOnClickListener {
                setAnimalInfo()
                when (count % 2) {
                    0 -> {
                        var animal = AnimalDefault(context, animalName)
                        toast(animal.toString())
                    }
                    else -> {
                        var animal = AnimalDefault(context, animalName, animalSex)
                        toast(animal.toString())
                    }
                }
            }
        }
    }

    fun setAnimalInfo() {
        count++
        animalName = if (count % 2 == 0) "老虎" else "斑马"
        animalSex = if (count % 3 == 0) 0 else 1
        tv_class_init.text = "这只${animalName}是${if (animalSex == 0) "公" else "母"}的"
    }
}