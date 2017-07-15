import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by ZhangJin on 2017/7/15.
 */
public class Product implements HttpSessionBindingListener {

    private String id;
    private String name;
    private double price;

    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        String attributeName = httpSessionBindingEvent.getName();
        System.out.println(attributeName+" valueBound");
    }

    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        String attributeName = httpSessionBindingEvent.getName();
        System.out.println(attributeName+" valueUnbound");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
