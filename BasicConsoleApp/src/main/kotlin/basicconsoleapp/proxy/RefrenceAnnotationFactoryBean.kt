package basicconsoleapp.proxy

import org.springframework.beans.factory.FactoryBean


/**
 * Created by jinzhanga on 2018/8/3.
 */


class RefrenceAnnotationFactoryBean<T> : FactoryBean<T> {

    @Suppress("UNCHECKED_CAST")
    override fun getObject(): T {
        return InterfaceProxy.newInstance(BizService::class.java) as T
    }

    override fun getObjectType(): Class<*>? {
        return BizService::class.java
    }

    override fun isSingleton(): Boolean {
        return true
    }

}