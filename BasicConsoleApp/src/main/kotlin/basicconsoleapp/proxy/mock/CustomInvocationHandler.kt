package basicconsoleapp.proxy.mock

import org.springframework.cglib.proxy.InvocationHandler
import org.springframework.cglib.proxy.Proxy
import java.lang.reflect.Method
import java.util.*

/**
 * Created by jinzhanga on 2018/8/3.
 */

class CustomInvocationHandler : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {

        // 获取 method 信息
        // 动态执行并返回
        System.out.println("ObjectProxy execute:" + method.name)
        val param = Arrays.toString(args)
        return "pepsi is param=$param"
    }

    companion object {
        fun newInstance(innerInterface: Class<*>): Any {
            val classLoader = innerInterface.classLoader
            val interfaces = arrayOf(innerInterface)
            val proxy = CustomInvocationHandler()
            return Proxy.newProxyInstance(classLoader, interfaces, proxy)
        }
    }

}