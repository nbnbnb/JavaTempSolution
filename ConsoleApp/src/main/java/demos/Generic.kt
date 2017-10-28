package demos

/**
 * 声明协变性和逆变性
 */
internal class Generic {
    fun setApple(abc: ABC<in Apple>) {
        // 逆变性
        // 原理：如果只能向集合中放入项目，就可以用 Object 集合向其中放入 String

        // 对于 <? super Apple>
        // 你只能使用 Apple 及其子类作为参数
        // 并且返回得到的并非是一个 Apple，而是一个 Object

        // 作为参数
        abc.theType = Apple()
        abc.theType = RedApple()

        // 作为返回值
        //Apple myApple = abc.getTheType();  // 返回得到的并非是一个 Apple，而是一个 Object
        val myApple = abc.theType
    }

    fun getApple(abc: ABC<out Apple>) {
        // 协变性
        // 原理：如果只能从集合中获取项目，那么使用 String 的集合，并且从其中读取 Object 也没问题

        // 对于 <? extends Apple>
        // 表示接受 Apple，及其一些子类型
        // 所以，读取为 Apple，Fruid，Object 都是没有问题的
        // 由于不知道到底为那个子类型，所以，只能写入为 null（可以当作一个标记使用）


        // 作为返回值
        val myObject = abc.theType
        val myFruid = abc.theType
        val myApple = abc.theType

        // 作为参数
        abc.theType = null
    }
}

internal class ABC<T> {
    var theType: T? = null
}

internal open class Fruit

internal open class Apple : Fruit()

internal class RedApple : Apple()
