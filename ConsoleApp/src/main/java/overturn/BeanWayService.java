package overturn;

public class BeanWayService {
    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void init() {
        System.out.println("@Bean-init-method");
    }

    public void destory() {
        System.out.println("@Bean-destory-method");
    }

}
