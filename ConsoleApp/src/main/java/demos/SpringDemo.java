package demos;

import SpringDemo.concert.*;
import config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDemo {

    public static void aopDemo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Performance performance = context.getBean(Performance.class);
        performance.doIt();

        // 由于实现了“引入新功能”
        // 此处转型为新功能接口
        Encoreable encoreable = (Encoreable) performance;
        encoreable.performEncore();
    }

    public static void executionDemo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IExecution myExecution = context.getBean("myExecution", IExecution.class);
        myExecution.doIt();

        IExecution sheExecution = context.getBean("sheExecution", IExecution.class);
        sheExecution.doIt();
    }

}
