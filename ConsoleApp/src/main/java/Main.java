import com.sun.javafx.image.IntPixelGetter;
import concert.Performance;
import config.AppConfig;
import demos.JdbcDemo;
import javafx.util.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            Performance performance= context.getBean(Performance.class);
            performance.perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




