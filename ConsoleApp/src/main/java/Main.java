import demos.JdbcDemo;
import demos.MiscDemo;
import demos.SpringDemo;


/**
 * Created by ZhangJin on 2017/7/8.
 */
public class Main {
    private static void log(Object object) {
        System.out.println(object);
    }

    public static void main(String[] args) {
        log("----- start -----");
        demo();
        temp();
        log("-----  end  -----");
    }

    public static void demo() {
        MiscDemo.toJsonObjectTest();
        JdbcDemo.basicQueryForSQLServer();
        MiscDemo.toJsonStringTest();
        SpringDemo.aopDemo();
        SpringDemo.executionDemo();
    }

    public static void temp() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




