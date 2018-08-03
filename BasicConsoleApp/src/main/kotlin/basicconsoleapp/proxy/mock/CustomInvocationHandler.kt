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
        println("CustomInvocationHandler.invoke:")
        println("MethodName ${method.name}")
        args.forEach {
            println("Arg: it.javaClass $it ")
        }
        println("ReturnType ${method.returnType}")

        // *(args ?: arrayOfNulls<Any>(0))
        // HACK
        // 此处使用了方法的参数用来构造返回都对象
        return method.returnType.constructors[0].newInstance(*args)

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