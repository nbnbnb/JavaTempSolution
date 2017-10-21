package demos;

/**
 * 声明协变性和逆变性
 */
public class Generic {
    public void setApple(ABC<? super Apple> abc) {
        // 逆变性
        // 原理：如果只能向集合中放入项目，就可以用 Object 集合向其中放入 String

        // 对于 <? super Apple>
        // 你只能使用 Apple 及其子类作为参数
        // 并且返回得到的并非是一个 Apple，而是一个 Object

        // 作为参数
        abc.setTheType(new Apple());
        abc.setTheType(new RedApple());

        // 作为返回值
        //Apple myApple = abc.getTheType();  // 返回得到的并非是一个 Apple，而是一个 Object
        Object myApple = abc.getTheType();
    }

    public void getApple(ABC<? extends Apple> abc) {
        // 协变性
        // 原理：如果只能从集合中获取项目，那么使用 String 的集合，并且从其中读取 Object 也没问题

        // 对于 <? extends Apple>
        // 表示接受 Apple，及其一些子类型
        // 所以，读取为 Apple，Fruid，Object 都是没有问题的
        // 由于不知道到底为那个子类型，所以，只能写入为 null（可以当作一个标记使用）


        // 作为返回值
        Object myObject = abc.getTheType();
        Fruit myFruid = abc.getTheType();
        Apple myApple = abc.getTheType();

        // 作为参数
        abc.setTheType(null);
    }
}

class ABC<T> {
    private T theT = null;

    public T getTheType() {
        return theT;
    }

    public void setTheType(T t) {
        theT = t;
    }
}

class Fruit {

}

class Apple extends Fruit {

}

class RedApple extends Apple {

}
