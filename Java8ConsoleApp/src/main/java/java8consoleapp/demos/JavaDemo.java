package java8consoleapp.demos;


import java.util.Random;

/**
 * Created by ZhangJin on 2017/11/23.
 */

public class JavaDemo {

    public static void test() {

    }
}


abstract class ABC {
    abstract Object getNumber();
}

class KKK extends ABC {

    @Override
    Number getNumber() {
        return null;
    }
}