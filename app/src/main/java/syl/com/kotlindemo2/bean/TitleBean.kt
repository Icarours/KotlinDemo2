package syl.com.kotlindemo2.bean

/**
 * Created by Bright on 2019/3/18.
 * @Describe
 * @Called
 */
class TitleBean {
    var id: Int? = null
    var title: String? = null
    var description: String? = null

    constructor(id: Int?, title: String?, description: String?) {
        this.id = id
        this.title = title
        this.description = description
    }

    override fun toString(): String {
        return "TitleBean(id=$id, title=$title, description=$description)"
    }
}