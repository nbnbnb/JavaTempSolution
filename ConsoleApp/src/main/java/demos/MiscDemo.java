package demos;

import entities.DateTimeClass;
import helper.DateHelper;
import helper.SerializerHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jinzhanga on 2017/10/12.
 */
public class MiscDemo {
    public static void toJsonStringTest() {
        DateTimeClass times = new DateTimeClass() {
            {
                long now = 1507790279021L;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(now);

                A = new Date(now);
                B = calendar;
                C = DateHelper.dateToLocalDate(A);
                D = DateHelper.dateToLocalDateTime(A);
            }
        };
        System.out.println(SerializerHelper.toJsonString(times));
    }

    public static void toJsonObjectTest() {
        String jsonString = "{\"A\":\"2017-10-12 14:41:17\",\"B\":\"2017-10-12 14:41:17\",\"C\":\"2017-10-12\",\"D\":\"2017-10-12T14:41:17.068\"}";
        DateTimeClass times = SerializerHelper.toJsonObject(jsonString, DateTimeClass.class);
        System.out.println(times.A);
        System.out.println(times.B.getTime());
        System.out.println(times.C);
        System.out.println(times.D);

        System.out.println();

        // Date 同样也支持 Timestamp 格式序列化
        jsonString = "{\"A\":1507790279021,\"B\":\"2017-10-12 14:41:17\",\"C\":\"2017-10-12\",\"D\":\"2017-10-12T14:41:17.068\"}";
        times = SerializerHelper.toJsonObject(jsonString, DateTimeClass.class);
        System.out.println(times.A);
        System.out.println(times.B.getTime());
        System.out.println(times.C);
        System.out.println(times.D);
    }

}
