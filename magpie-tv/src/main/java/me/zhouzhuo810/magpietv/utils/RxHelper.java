package me.zhouzhuo810.magpietv.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {
    
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> tObservable) {
                return tObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    
    public static <T> ObservableTransformer<T, T> io_io() {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> tObservable) {
                return tObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
            }
        };
    }
    
    /**
     * 倒计时
     *
     * @param count  时长（秒）
     * @param onNext 监听
     * @return 用于取消订阅
     */
    public static Disposable countDown(int count, Consumer<Long> onNext, Action onComplete) {
        return countDown(count, null, onComplete, onNext);
    }
    
    /**
     * 倒计时
     *
     * @param count      时长（秒）
     * @param onStart    开始监听
     * @param onComplete 结束监听
     * @param onNext     过程监听
     * @return 用于取消订阅
     */
    public static Disposable countDown(int count, Consumer<Disposable> onStart, Action onComplete, Consumer<Long> onNext) {
        Observable<Long> longObservable = Observable.intervalRange(0, count, 0, 1, TimeUnit.SECONDS)
            .compose(RxHelper.<Long>io_main());
        if (onStart != null) {
            longObservable = longObservable.doOnSubscribe(onStart);
        }
        if (onComplete != null) {
            longObservable = longObservable.doOnComplete(onComplete);
        }
        return longObservable.subscribe(onNext);
    }
    
    /**
     * 定时器
     *
     * @param period   时长
     * @param timeUnit 单位
     * @param consumer 订阅
     * @return 用户取消订阅
     */
    public static Disposable timer(int period, TimeUnit timeUnit, Consumer<Long> consumer) {
        return Observable.timer(period, timeUnit)
            .compose(RxHelper.<Long>io_main())
            .subscribe(consumer);
    }
    
    /**
     * 无限循环定时器
     *
     * @param period   时长
     * @param timeUnit 单位
     * @param consumer 订阅
     * @return 用户取消订阅
     */
    public static Disposable interval(int period, TimeUnit timeUnit, Consumer<Long> consumer) {
        return Observable.interval(0, period, timeUnit)
            .compose(RxHelper.<Long>io_main())
            .subscribe(consumer);
    }
    
    /**
     * 无限循环定时器
     *
     * @param initialDelay 首次执行延时时长
     * @param period       时长
     * @param timeUnit     单位
     * @param consumer     订阅
     * @return 用户取消订阅
     */
    public static Disposable interval(int initialDelay, int period, TimeUnit timeUnit, Consumer<Long> consumer) {
        return Observable.interval(initialDelay, period, timeUnit)
            .compose(RxHelper.<Long>io_main())
            .subscribe(consumer);
    }
    
    
}
