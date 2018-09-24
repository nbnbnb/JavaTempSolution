package basicconsoleapp.demos.runner;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

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

    public static void coldToHot() {
        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber2: " + aLong);
            }
        };

        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber3: " + aLong);
            }
        };

        // ConnectableObservable 是线程安全的
        // serializes the calls to the onSubscribe, onNext, onError and onComplete methods, making them thread-safe
        ConnectableObservable<Long> observable = Observable
                .create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        Observable
                                .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                                .take(Integer.MAX_VALUE)
                                .subscribe(emitter::onNext);
                    }
                })
                .observeOn(Schedulers.newThread())
                // 使用 publish 操作符，将 （Cold）Observable 转换为 （Hot）ConnectableObservable
                .publish();

        // 需要使用 connect 触发执行
        observable.connect();

        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // subscribe3 从当前流的位置开始订阅
        // 而不会重头开始
        observable.subscribe(subscribe3);

        try {
            Thread.sleep(105);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refCount01() {

        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber2: " + aLong);
            }
        };

        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber3: " + aLong);
            }
        };


        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable
                        .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();

        // refConut 操作符把一个 ConnectableObservable  的连接和断开过程自动化了
        // 它操作一个 ConnectableObservable，返回一个普通的 Observable
        Observable<Long> observable = connectableObservable.refCount();

        // 当第一个订阅者订阅这个 Observable 时，它连接到下层的 ConnectableObservable
        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();

        System.out.println("重新（重头）开始数据流");

        // 如果重新订阅，则重新开放数据流
        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refCount02() {

        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber2: " + aLong);
            }
        };

        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber3: " + aLong);
            }
        };

        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {

                Observable
                        .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(emitter::onNext);

            }
        }).observeOn(Schedulers.newThread()).publish();

        // refConut 操作符把一个 ConnectableObservable  的连接和断开过程自动化了
        // 它操作一个 ConnectableObservable，返回一个普通的 Observable
        Observable<Long> observable = connectableObservable.refCount();

        // 当第一个订阅者订阅这个 Observable 时，它连接到下层的 ConnectableObservable
        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        observable.subscribe(subscribe3);  // 这个订阅不会被取消

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();
        // subscribe3 不取消

        System.out.println("继续数据流");

        // 由于没有与原始 ConnectableObservable 的连接
        // 再次订阅的时候，继续从当前流的位置开始
        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void share() {

        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber2: " + aLong);
            }
        };

        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber3: " + aLong);
            }
        };

        Observable<Long> observable = Observable
                .create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {

                        Observable
                                .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                                .take(Integer.MAX_VALUE)
                                .subscribe(emitter::onNext);

                    }
                })
                .observeOn(Schedulers.newThread())
                // share 操作符封装了 publish().refCount() 调用
                .share();

        // 当第一个订阅者订阅这个 Observable 时，它连接到下层的 ConnectableObservable
        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        observable.subscribe(subscribe3);  // 这个订阅不会被取消

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();
        // subscribe3 不取消

        System.out.println("继续数据流");

        // 由于没有与原始 ConnectableObservable 的连接
        // 再次订阅的时候，继续从当前流的位置开始
        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Subject 和 Processor 的作用相同
     * Processor 是 RxJava 2.x 新增的类，继承自 Flowable，支持背压控制，而 Subject 则不支持背压控制
     */
    public static void coldToHot2() {
        Consumer<Long> subscribe1 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber1: " + aLong);
            }
        };

        Consumer<Long> subscribe2 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber2: " + aLong);
            }
        };

        Consumer<Long> subscribe3 = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscriber3: " + aLong);
            }
        };

        // ConnectableObservable 是线程安全的
        // serializes the calls to the onSubscribe, onNext, onError and onComplete methods, making them thread-safe
        Observable<Long> observable = Observable
                .create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                        Observable
                                .interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                                .take(Integer.MAX_VALUE)
                                .subscribe(emitter::onNext);
                    }
                })
                .observeOn(Schedulers.newThread());

        // PublishSubject 不是线程安全的
        // Subject 既是 Observable，又是 Observer

        // Subject 作为观察者，可以订阅目标 Cold Observable，是对方开始发送事件
        // 同时他又作为 Observable 转发或者发送新的事件，让 Cold Observable 借助 Subject 转换为 Hot Observable

        PublishSubject<Long> subject = PublishSubject.create();
        // Subject 不是线程安全的，如果需要线程安全，可以使用 toSerialized() 方法
        // Subject<Long> subject = PublishSubject.<Long>create().toSerialized();

        // subject 充当观察者
        observable.subscribe(subject);

        // subject 充当发布者
        subject.subscribe(subscribe1);
        subject.subscribe(subscribe2);

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // subscribe3 从当前流的位置开始订阅
        // 而不会重头开始
        subject.subscribe(subscribe3);

        try {
            Thread.sleep(105);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
