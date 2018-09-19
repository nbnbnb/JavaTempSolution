package basicconsoleapp.demos;


import basicconsoleapp.demos.runner.ReactiveXDemo;
import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by ZhangJin on 2017/11/23.
 */

public class JavaDemo {


    public static void test() {

        ReactiveXDemo.threadSwitch();

        try {
            Thread.sleep(123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


