package demos

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun test() {

}

class ResourceDelegate<out T> : ReadOnlyProperty<MyUI, T> {
    override fun getValue(thisRef: MyUI, property: KProperty<*>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class GGG<T> {

    operator fun provideDelegate(thisRef: MyUI, property: KProperty<*>): ReadOnlyProperty<MyUI, T> {
        // 创建委托
        return ResourceDelegate()
    }
}

class MyUI
