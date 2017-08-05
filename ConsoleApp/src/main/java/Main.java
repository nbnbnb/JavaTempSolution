import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

interface KKKing {

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test {
    public int id();

    public String description() default "no description";
}

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

        Supplier<Void> myVoid = () -> null;
        Void bb = myVoid.get();
        log(bb); // null
        log(bb == null);  // true
    }
}

class A implements KKKing {
    @Test(id = 123)
    int a = 123;
}

class B extends A {
}

class C extends B {
}
