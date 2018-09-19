package basicconsoleapp.demos.runner;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ReactiveXDemo {
    // SubscribeOn：specify the Scheduler on which an Observable will operate
    // 指定 Observable 自身在哪个调度器上执行

    // ObserveOn：specify the Scheduler on which an observer will observe this Observable
    // 指定一个观察者在哪个调度器上观察这个 Observable

    // Schedulers.io() = RxCachedThreadSchedule
    // Schedulers.computation() = RxComputationThreadPool
    // Schedulers.single() = RxSingleScheduler
    // Schedulers.trampoline() = RxComputationThreadPool
    // Schedulers.newThread() = RxNewThreadScheduler

    // 默认情况下，Observable 和 Observer 处于同一线程中
    // RxJava 的链式操作中，数据的处理是 “自下而上”的，这点与数据发射正好相反
    // 如果多次调用 subscribeOn，则最上面的线程切换最晚执行，所以就变成了只有第一次切换线程才有效

    public static void threadSwitch() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                // 使用 Schedulers.single()
                // 通过 subscribeOn(Schedulers.single()) 指定
                System.out.println("create:" + Thread.currentThread().getName());
                observableEmitter.onNext(1);
                observableEmitter.onComplete();
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            // 使用 Schedulers.single()
            // 通过 subscribeOn(Schedulers.single()) 指定
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                System.out.println("doOnSubscribe 02:" + Thread.currentThread().getName());
            }
        });

        observable
                .doOnSubscribe(new Consumer<Disposable>() {
                    // 使用 Schedulers.single()
                    // 通过 subscribeOn(Schedulers.single()) 指定
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        System.out.println("doOnSubscribe 03:" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.single())  // 设置 Observable.create 的线程，第一次有效，影响他上面的 create 和 doOnSubscribe
                .doOnSubscribe(new Consumer<Disposable>() {
                    // 使用 main，当前控制台的主线程
                    // 它在 subscribeOn(Schedulers.single()) 后指定
                    // 此时使用了当前控制台的默认线程

                    // 因为数据处理是“自下而上”的，所以此次最先执行
                    // 然后 subscribeOn 才进行线程切换

                    // 由于在主线程中，没有线程切换，01 一般是最先输出的
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        System.out.println("doOnSubscribe 01:" + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())  // 切换到 Schedulers.io()，后面的 map 使用这个线程处理
                .map(new Function<Integer, Integer>() {
                    // 使用 Schedulers.io()
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        System.out.println("map:-1" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .observeOn(Schedulers.computation())  // 切换 map2 到 Schedulers.computation()
                .map(new Function<Integer, Integer>() {
                    // 使用 Schedulers.computation()
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        System.out.println("map:-2" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                // 切换 subscribe 到 Schedulers.newThread()
                // 如果不指定，则使用最近一次的 observeOn
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        System.out.println("subscribe:" + Thread.currentThread().getName());
                    }
                });
    }
}
