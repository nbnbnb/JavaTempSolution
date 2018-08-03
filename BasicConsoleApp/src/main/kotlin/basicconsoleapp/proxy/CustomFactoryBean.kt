package basicconsoleapp.proxy

import org.springframework.beans.factory.FactoryBean

/**
 * Created by jinzhanga on 2018/8/3.
 */

class CustomFactoryBean(val clazz: Class<*>) : FactoryBean<Any> {

    override fun getObject(): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getObjectType(): Class<*>? {
        return clazz
    }

}