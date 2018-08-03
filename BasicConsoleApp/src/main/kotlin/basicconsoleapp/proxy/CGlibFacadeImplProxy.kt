package basicconsoleapp.proxy

import org.springframework.cglib.proxy.Enhancer
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

/**
 * Created by jinzhanga on 2018/8/3.
 */

/**
 *
Java 动态代理是利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用 InvokeHandler 来处理
而 cglib 动态代理是利用 asm 开源包，对代理对象类的 class 文件加载进来，通过修改其字节码生成子类来处理

1、如果目标对象实现了接口，默认情况下会采用 JDK 的动态代理实现 AOP
2、如果目标对象实现了接口，可以强制使用 CGLIB 实现 AOP
3、如果目标对象没有实现了接口，必须采用 CGLIB 库，Spring 会自动在 JDK 动态代理和 CGLIB 之间转换

 */
class CGlibFacadeImplProxy : MethodInterceptor {

    // 业务类对象，供代理方法中进行真正的业务方法调用
    private lateinit var target: Any

    // 相当于 JDK 动态代理中的绑定
    fun getInstance(target: Any): Any {
        // 给业务对象赋值
        this.target = target
        // 创建加强器，用来创建动态代理类
        val enhancer = Enhancer()
        // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target::class.java)
        // 设置回调：对于代理类上所有方法的调用，都会调用 CallBack，而 Callback 则需要实现 intercept() 方法进行拦
        enhancer.setCallback(this)
        // 创建动态代理类对象并返回
        return enhancer.create()
    }

    override fun intercept(obj: Any, method: Method, args: Array<Any>, proxy: MethodProxy): Any? {
        println("预处理操作——————")

        // 调用父类中的方法
        // 调用真正的业务方法
        // 注意 args 的解包操作
        val result = method.invoke(target, *(args ?: arrayOfNulls<Any>(0)))

        println("调用后处理——————")

        return result
    }
}