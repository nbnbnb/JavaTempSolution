package basicconsoleapp.proxy.mock

import org.springframework.beans.factory.FactoryBean


/**
 * Created by jinzhanga on 2018/8/3.
 */


class CustomFactoryBean : FactoryBean<Any> {

    // 不能为 private
    var serviceClass: Class<*>? = null

    @Suppress("UNCHECKED_CAST")
    override fun getObject(): Any {

        // 此处返回动态创建的实现对象
        // 可以使用 JDK 或 CGlib 等动态技术进行对象的创建
        println("CustomFactoryBean.getObject")

        return CustomInvocationHandler.newInstance(serviceClass!!)
    }

    override fun getObjectType(): Class<*>? {
        return serviceClass
    }

    override fun isSingleton(): Boolean {
        return true
    }

}