package demos

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object KotlinDemo {
    fun test() {
        val e = Example()
        e.p = "haha"
        println(e.p)

        val lazyValue: String by lazy {
            println("Computed!")
            "HEllo"

        }

        println(lazyValue)
        println(lazyValue)
    }
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {

        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef")
    }
}

class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

class Foo

fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

}

class ABC : ReadWriteProperty<String, Int> {
    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: String, property: KProperty<*>, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    override fun getValue(thisRef: String, property: KProperty<*>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}








