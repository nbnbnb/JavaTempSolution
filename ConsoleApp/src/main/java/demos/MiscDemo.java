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
    public static void test() {
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


}
