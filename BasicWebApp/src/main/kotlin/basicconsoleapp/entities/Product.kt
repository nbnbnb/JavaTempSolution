package basicconsoleapp.entities

import javax.servlet.http.HttpSessionBindingEvent
import javax.servlet.http.HttpSessionBindingListener

/**
 * Created by ZhangJin on 2017/7/15.
 */
class Product : HttpSessionBindingListener {

    var id: String? = null
    var name: String? = null
    var price: Double = 0.toDouble()

    override fun valueBound(httpSessionBindingEvent: HttpSessionBindingEvent) {
        val attributeName = httpSessionBindingEvent.name
        println(attributeName + " valueBound")
    }

    override fun valueUnbound(httpSessionBindingEvent: HttpSessionBindingEvent) {
        val attributeName = httpSessionBindingEvent.name
        println(attributeName + " valueUnbound")
    }
}
