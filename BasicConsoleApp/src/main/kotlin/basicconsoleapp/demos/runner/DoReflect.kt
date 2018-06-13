package basicconsoleapp.demos.runner

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/**
 * Created by jinzhanga on 2018/6/13.
 */

object DoReflect {
    fun run() {
        printInfo(ABC::class, ABC::class.java)
    }
}

private fun <T> printInfo(kclazz: KClass<*>, clazz: Class<T>) {
    // address, name
    println(kclazz.memberProperties.joinToString { it.name })

    // equals, toString, hashCode, getAddress, getName, copy, component2, component1, copy$default, wait, wait, wait, getClass, notify, notifyAll
    println(clazz.methods.joinToString { it.name })

    // equals, toString, hashCode, getAddress, getName, copy, component2, component1, copy$default
    println(clazz.declaredMethods.joinToString { it.name })

    // empty
    println(clazz.fields.joinToString { it.name })

    // name, address
    println(clazz.declaredFields.joinToString { it.name })

    val firstField = clazz.declaredFields[0]
    // 私有属性需要通过设置为 true 才能读取
    firstField.isAccessible = true

    println(firstField.get(ABC("001", "002")))

}

private data class ABC(val name: String, val address: String)