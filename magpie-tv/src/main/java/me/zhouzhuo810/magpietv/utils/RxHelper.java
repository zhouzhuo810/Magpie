package me.zhouzhuo810.magpietv.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
     * @param count    时长（秒）
     * @param consumer 监听
     * @return 用于取消订阅
     */
    public static Disposable countDown(int count, Consumer<Long> consumer) {
        return Observable.intervalRange(0, count, 0, 1, TimeUnit.SECONDS)
                .compose(RxHelper.<Long>io_main())
                .subscribe(consumer);
    }



}
