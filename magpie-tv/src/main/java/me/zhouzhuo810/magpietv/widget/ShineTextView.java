package me.zhouzhuo810.magpietv.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.zhouzhuo810.magpietv.utils.RxHelper;

/**
 * 定时闪烁背景颜色的TextView
 */
public class ShineTextView extends AppCompatTextView {
    public static final boolean DEBUG = true;

    private boolean mAnimating = false;
    private int mDuration = 1;
    private int[] colors;
    private Disposable subscribe;

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setShiningColors(int... colors) {
        this.colors = colors;
    }

    @SuppressLint("CheckResult")
    public void startShining() {
        if (mAnimating)
            return;
        mAnimating = true;
        /*定时器闪烁*/
        if (mDuration == 0) {
            mDuration = 1;
        }
        subscribe = Observable.interval(0, mDuration, TimeUnit.SECONDS)
                .compose(RxHelper.<Long>io_main())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int i = (int) (aLong % colors.length);
                        setBackgroundColor(colors[i]);
                    }
                });
    }

    public void stopShining() {
        mAnimating = false;
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        setBackgroundColor(Color.TRANSPARENT);
    }

    public int getDurationSeconds() {
        return mDuration;
    }

    public void setDurationWithSeconds(int duration) {
        if (duration <= 0) {
            duration = 1;
        }
        mDuration = duration;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopShining();
    }
}
