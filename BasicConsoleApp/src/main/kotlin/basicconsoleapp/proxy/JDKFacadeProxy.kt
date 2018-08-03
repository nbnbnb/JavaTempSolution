package basicconsoleapp.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Created by jinzhanga on 2018/8/3.
 */

class JDKFacadeProxy : InvocationHandler {

    // 这其实业务实现类对象，用来调用具体的业务方法
    private var target: Any? = null

    /**
     * 绑定业务对象并返回一个代理类
     */
    fun bind(target: Any): Any {

        // 接收业务实现类对象参数
        this.target = target

        // 通过反射机制，创建一个代理类对象实例并返回
        // 创建代理对象时，需要传递该业务类的类加载器（用来获取业务实现类的元数据，在包装方法是调用真正的业务方法）、接口、handler实现类
        return Proxy.newProxyInstance(target.javaClass.classLoader, target.javaClass.interfaces, this)
    }

    override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
        println("预处理操作——————")

        // 调用真正的业务方法
        // 注意 args 的解包操作
        val result = method.invoke(target, *(args ?: arrayOfNulls<Any>(0)))

        println("调用后处理——————")

        return result
    }

}