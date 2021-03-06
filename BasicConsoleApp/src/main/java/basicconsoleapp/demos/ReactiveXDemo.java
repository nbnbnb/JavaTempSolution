package basicconsoleapp.demos;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.*;

import java.util.concurrent.TimeUnit;

public class ReactiveXDemo {
    // SubscribeOn：specify the Scheduler on which an Observable will operate
    // 指定一个 Observable(可观察对象) 将在其上操作的调度程序

    // ObserveOn：specify the Scheduler on which an observer will observe this Observable
    // 指定 Observer(观察者) 将在其上观察此 Observable(可观察对象) 的调度程序

    // Schedulers.io() = RxCachedThreadSchedule
    // Schedulers.computation() = RxComputationThreadPool
    // Schedulers.single() = RxSingleScheduler
    // Schedulers.trampoline() = RxComputationThreadPool
    // Schedulers.newThread() = RxNewThreadScheduler

    // 默认情况下，Observable（可观察对象） 和 Observer（观察者） 处于同一线程中
    // RxJava 的链式操作中，数据的处理是 “自下而上”的，这点与数据发射正好相反
    // 如果多次调用 subscribeOn，则最上面的线程切换最晚执行，所以就变成了只有第一次切换线程才有效

    // 执行 doOnSubscribe
    // 执行 create
    // 执行 map
    public static void threadSwitch() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    // 订阅时
                    // 执行这个方法
                    @Override
                    public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                        // 使用 Schedulers.single()
                        // 通过 subscribeOn(Schedulers.single()) 指定
                        System.out.println("create: " + Thread.currentThread().getName());
                        observableEmitter.onNext(1);
                        observableEmitter.onComplete();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // chedulers.single()
                        System.out.println("doOnSubscribe 06: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // chedulers.single()
                        System.out.println("doOnSubscribe 07: " + Thread.currentThread().getName());
                    }
                })
                // 再次将上面的代码切换到 Schedulers.computation() 运行
                // 由于这是最后一个，所以“事件发出者”最终在这个线程中运行
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Schedulers.newThread()
                        System.out.println("doOnSubscribe 04: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Schedulers.newThread()
                        System.out.println("doOnSubscribe 05: " + Thread.currentThread().getName());
                    }
                })
                // -------------------------------------------------------------------------------
                // 第一次执行 subscribeOn 的线程切换
                // 由于默认线程在 main 中，所以下面的 doOnSubscribe 都会在 main 中执行
                // 因为链式操作是由下往上执行的，所有整体的 doOnSubscribe 是由下往上
                // 但是在每个 doOnSubscribe（同一线程）范围内，顺序是从上到下的
                .subscribeOn(Schedulers.newThread())
                // doOnSubscribe 都会在主线程中执行
                .doOnSubscribe(new Consumer<Disposable>() {
                    // 使用 main，当前控制台的主线程
                    // 它在 subscribeOn(Schedulers.single()) 后指定
                    // 此时使用了当前控制台的线程

                    // 由于在主线程中，没有线程切换，01 一般是最先输出的
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("doOnSubscribe 01: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    // 使用 Schedulers.single()
                    // 通过 subscribeOn(Schedulers.single()) 指定
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("doOnSubscribe 02: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    // 使用 Schedulers.single()
                    // 通过 subscribeOn(Schedulers.single()) 指定
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("doOnSubscribe 03: " + Thread.currentThread().getName());
                    }
                })
                // -------------------------------------------------------------------------------
                .observeOn(Schedulers.io())  // 切换到 Schedulers.io()，后面的 map 使用这个线程处理
                // 消费回调
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        // Schedulers.io()
                        System.out.println("map:-1 " + Thread.currentThread().getName());
                        return integer + 10;
                    }
                })
                // -------------------------------------------------------------------------------
                .observeOn(Schedulers.computation())  // 切换 map2 到 Schedulers.computation()
                // 消费回调
                .map(new Function<Integer, Integer>() {
                    // 使用 Schedulers.computation()
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Schedulers.computation()
                        System.out.println("map:-2 " + Thread.currentThread().getName());
                        return integer + 100;
                    }
                })
                // -------------------------------------------------------------------------------
                // 切换消费者线程到 Schedulers.newThread()
                // 如果不指定，则使用最近一次的 observeOn 指定的调度器
                .observeOn(Schedulers.newThread())
                // 消费回调
                // 这个回调将会在 subscribe 方法发送数据前执行
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        // Schedulers.newThread()
                        System.out.println("subscribe: " + Thread.currentThread().getName() + " Result: " + integer);
                    }
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        // 由于是 Hot
        // subscribe3 从当前流的位置开始订阅
        // 而不会重头开始
        observable.subscribe(subscribe3);

        try {
            Thread.sleep(105);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
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

        ConnectableObservable<Long> connectableObservable = Observable
                // 当订阅的时候执行
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

        // 返回一个可观察对象，只要该可观察对象至少有一个订阅，该可观察对象就保持与该可观察对象的连接
        // refConut 操作符把一个 ConnectableObservable  的连接和断开过程自动化了
        // 它操作一个 ConnectableObservable，返回一个普通的 Observable
        Observable<Long> observable = connectableObservable.refCount();

        // 当第一个订阅者订阅这个 Observable 时，它连接到下层的 ConnectableObservable
        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        ConnectableObservable<Long> connectableObservable = Observable
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

        // 返回一个可观察对象，只要该可观察对象至少有一个订阅，该可观察对象就保持与该可观察对象的连接
        Observable<Long> observable = connectableObservable.refCount();

        // 当第一个订阅者订阅这个 Observable 时，它连接到下层的 ConnectableObservable
        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        // subscribe3 不取消
        observable.subscribe(subscribe3);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();
        // subscribe3 不取消

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("继续数据流");

        // 由于 subscribe3 没有与原始 ConnectableObservable 的连接
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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // RefCount 跟踪有多少个订阅者观察它，直到最后一个订阅者完成，才断开与下层 ConnectableObservable 的连接
        // 如果所有的订阅者都取消了订阅，则数据流停止
        disposable1.dispose();
        disposable2.dispose();
        // subscribe3 不取消

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        // Subject 既是 Observable（可观察的），又是 Observer（观察者）

        // Subject 作为 Observer（观察者），可以订阅目标 Cold Observable，使对方开始发送事件
        // Subject 作为 Observable（可观察的）转发或者发送新的事件，让 Cold Observable 借助 Subject 转换为 Hot Observable

        PublishSubject<Long> subject = PublishSubject.create();
        // Subject 不是线程安全的，如果需要线程安全，可以使用 toSerialized() 方法
        // Subject<Long> subject = PublishSubject.<Long>create().toSerialized();

        // subject 充当观察者
        observable.subscribe(subject);

        // subject 充当发布者
        subject.subscribe(subscribe1);
        subject.subscribe(subscribe2);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subscribe3 开始订阅");
        // subscribe3 从当前流的位置开始订阅
        // 而不会重头开始
        subject.subscribe(subscribe3);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 只有 onSuccess 和 onError，并没有 onComplete
     * Single 可以通过 toXXX 方法转换成 Observable、Flowable、Completable 及 Maybe
     */
    public static void single() {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                //emitter.onSuccess("success");
                emitter.onError(new IllegalArgumentException("error"));
            }
        }).subscribe(new Consumer<String>() {  // onSuccess
            @Override
            public void accept(String s) throws Exception {
                System.out.println("onSuccess: " + s);
            }
        }, new Consumer<Throwable>() {  // onError
            @Override
            public void accept(Throwable throwable) throws Exception {
                //throwable.printStackTrace();
                System.out.println("onError: " + throwable.getMessage());
            }
        });
    }

    /**
     * Completable 在创建后，不会发射任何数据
     * 只有 OnComplete 和 onError 事件
     */
    public static void completable() {

        RxJavaPlugins.setErrorHandler((err) -> {
            System.out.println("Custom ErrorHandler");
            err.printStackTrace();
        });

        System.out.println("Demo01 ------------");
        Completable
                .fromAction(new Action() {  // onComplete
                    @Override
                    public void run() throws Exception {
                        System.out.println("do Complete");

                        // 异常将会触发 RxJavaPlugins.onError
                        // 通过 RxJavaPlugins.setErrorHandler 自定义 onError
                        // throw new IllegalArgumentException("kkking");
                    }
                })
                // .onErrorComplete() // 可以捕获 Action 中的异常
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("do after Complete");
                    }
                });

        System.out.println("Demo02 ------------");

        Completable
                // 订阅的时候调用
                .create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(CompletableEmitter emitter) throws Exception {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("Ready to complete...");
                            emitter.onComplete();  // onComplete 调用后，执行 andThen
                        } catch (InterruptedException e) {
                            emitter.onError(e);
                        }
                    }
                })
                .andThen(Observable.range(1, 10))  // 结合 andThen 使用
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    /**
     * RxJava 2.x 之后才有的新类型
     * 可以看成是 Single 和 Completable 的结合
     * 没有 onNext 方法，需要通过 onSuccess 方法来发送数据
     */
    public static void maybe() {
        Maybe
                .create(new MaybeOnSubscribe<String>() {
                    @Override
                    public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                        // 只能发射 0 或 1 个数据
                        emitter.onSuccess("testA");
                        // 即使发送多个数据，后面发射的数据也不会处理
                        emitter.onSuccess("testB");

                        // emitter.onComplete();
                        // emitter.onError(e);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                });

        Maybe
                .create(new MaybeOnSubscribe<String>() {
                    @Override
                    public void subscribe(MaybeEmitter<String> emitter) throws Exception {

                        // 如果先调用了 onComplete，即使后面再调用 onSuccess，也不会发射任何数据
                        emitter.onComplete();

                        // 如果 Maybe 有数据发射或者调用了 onError，则不会执行 onComplete
                        emitter.onSuccess("testA");
                    }
                })
                .subscribe(new Consumer<String>() {   // onSuccess
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                }, new Consumer<Throwable>() {  // onError
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {  // onComplete
                    @Override
                    public void run() throws Exception {
                        System.out.println("Maybe onComplete");
                    }
                });
    }

    /**
     * Observer 会接收 AsyncSubject 的 OnComplete 之前的最后一个数据
     */
    public static void asyncSubject() {

        // A Subject that emits the very last value followed by a completion event or the received error to Observers.
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("test1");
        subject.onNext("test2");

        // OnComplete 之前的最后一个数据
        // 此处存储在 subject 中最后一个待处理数据是 test2
        // 在消费时，只会发射这一个数据

        // 如果不调用 onComplete，则什么都不会输出
        subject.onComplete();

        subject
                .subscribe(new Consumer<String>() {   // onNext
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {   // onError
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {   // onComplete
                    @Override
                    public void run() throws Exception {
                        System.out.println("asyncSubject:complete");
                    }
                });


        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // onNext 回调不会触发
        subject.onNext("test3");
        subject.onNext("test4");

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Observer 会先接收到 BehaviorSubject 被订阅之前的最后一个数据
     * 再接收订阅之后发射过来的数据
     */
    public static void behaviorSubject() {


        // 如果被订阅之前，没有写入数据
        // 则指定默认值
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("Default Value");

        subject.onNext("behaviorSubject 1");
        subject.onNext("behaviorSubject 2");

        // 默认设置
        // 订阅之前
        // 只缓存一条数据
        subject.onNext("behaviorSubject 3");

        subject.subscribe(new Consumer<String>() {  // onNext
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        }, new Consumer<Throwable>() {   // onError
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }, new Action() {  // onComplete
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject:complete");
            }
        });

        // 订阅之后的数据
        // 继续输出
        subject.onNext("behaviorSubject 4");
        subject.onNext("behaviorSubject 5");

        subject.onComplete();
    }

    /**
     * ReplaySubject 会发射所有来自原始 Observable 的数据给观察者
     * 无论他们是何时订阅的
     */
    public static void replaySubject() {

        // 缓存所有的数据（不指定容器大小）
        // 在 onComplete 时，全部发射出去
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.onNext("replaySubject 1");
        subject.onNext("replaySubject 2");

        subject
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("replaySubject:complete");
                    }
                });

        subject.onNext("replaySubject 3");
        subject.onNext("replaySubject 4");

        subject.onComplete();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置订阅前的缓存容量
     */
    public static void replaySubject2() {

        // 缓存指定大小的数据
        ReplaySubject<String> subject = ReplaySubject.createWithSize(2);

        subject.onNext("replaySubject 1");  // 超过容量，前面的丢弃
        subject.onNext("replaySubject 2");  // 缓存
        // 存储这个的时候，丢弃第一条数据
        subject.onNext("replaySubject 3");  // 缓存

        subject
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("replaySubject:complete");
                    }
                });

        subject.onNext("replaySubject 4");
        subject.onNext("replaySubject 5");

        subject.onComplete();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 只接收 PublishSubject 被订阅之后发送的数据
     */
    public static void publishSubject() {

        PublishSubject<String> subject = PublishSubject.create();

        subject.onNext("publishSubject 1");
        subject.onNext("publishSubject 2");

        // 订阅前端的数据都丢弃

        subject
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("publishSubject:complete");
                    }
                });

        subject.onNext("publishSubject 3");
        subject.onNext("publishSubject 4");

        subject.onComplete();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void publishSubjectHack() {

        PublishSubject<String> subject = PublishSubject.create();

        // 正常情况下
        // 订阅之后，发送给 subject 的内容就会被消费
        subject
                // 此处指定在 io 线程中消费
                // 如果注释掉后，可以消费 Foo
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("completed");
                    }
                });

        // subject 发射元素被指派到了 I/O 线程
        // 此时 I/O 线程正在初始化还没起来，subject 发射前，Foo 还在主线程中
        // 在从主线程往 I/O 线程转发的过程中，由于 I/O 线程还没有起来，所以就被丢弃了

        // 解决办法很简单，用 Observable.create() 替代 PublishSubject.create()
        // 或者不指定 io 线程

        // 由于 subject 中不存储数据
        // 发送后不管消费方有没有处理掉
        subject.onNext("Foo");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // io 线程起来后，可以正常的消费
        subject.onNext("Bar");

        subject.onComplete();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void temp() {

    }
}
