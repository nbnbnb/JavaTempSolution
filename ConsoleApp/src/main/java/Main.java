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
        Demo();
        log("-----  end  -----");
    }

    public static void Demo() {
        try {
            SpringDemo.aopDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




