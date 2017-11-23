package demos;


import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * Created by ZhangJin on 2017/11/23.
 */
public class JavaDemo {

    public void a(@Nonnull(when = When.NEVER) String name) {

    }

    public void b() {
        a(null);
    }
}
