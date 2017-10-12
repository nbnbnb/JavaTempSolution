import demos.MiscDemo;


/**
 * Created by ZhangJin on 2017/7/8.
 */
public class Main {
    private static void log(Object object) {
        System.out.println(object);
    }

    public static void main(String[] args) {
        log("----- start -----");
        Demo();
        log("-----  end  -----");
    }

    public static void Demo() {
        try {
            MiscDemo.toJsonObjectTest();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




