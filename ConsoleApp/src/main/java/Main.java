import com.sun.javafx.image.IntPixelGetter;
import javafx.util.Pair;

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
        try {

            Arrays.sort(null, String.CASE_INSENSITIVE_ORDER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



