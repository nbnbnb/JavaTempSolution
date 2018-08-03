package basicconsoleapp.proxy.mock

import org.springframework.cglib.proxy.InvocationHandler
import org.springframework.cglib.proxy.Proxy
import java.lang.reflect.Method
import java.util.*

/**
 * Created by jinzhanga on 2018/8/3.
 */

class InterfaceProxy : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
        System.out.println("ObjectProxy execute:" + method.name)
        val methodName = method.name
        val param = Arrays.toString(args)
        return "pepsi is param=$param"
    }

    companion object {
        fun <T> newInstance(innerInterface: Class<T>): T {
            val classLoader = innerInterface.classLoader
            val interfaces = arrayOf<Class<*>>(innerInterface)
            val proxy = InterfaceProxy()
            return Proxy.newProxyInstance(classLoader, interfaces, proxy) as T
        }
    }

}