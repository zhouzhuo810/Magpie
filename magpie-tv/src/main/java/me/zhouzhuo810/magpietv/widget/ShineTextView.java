package me.zhouzhuo810.magpietv.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ShineTextView extends AppCompatTextView {
    public static final boolean DEBUG = true;
    
    private boolean mAnimating = false;
    private int mDuration = 1000;
    private String mStartColor = "#FFFFFF";
    private int mStartColorInt = 0xffffff;
    private String mFinishColor = "#000000";
    private int mFinishColorInt = 0x000000;
    private ValueAnimator mAnimator;
    
    private boolean shiningTag = false;
    private Timer timer;
    private TimerTask timerTask;
    
    private int[] colors;
    
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
    
    public void setShiningColor(String startColor, String finishColor) {
        this.mStartColor = startColor;
        this.mFinishColor = finishColor;
        mStartColorInt = Color.parseColor(mStartColor);
        mFinishColorInt = Color.parseColor(mFinishColor);
    }
    
    public void setShiningColors(int... colors) {
        this.colors = colors;
    }
    
    public void startShinigs() {
        if (mAnimating)
            return;
        mAnimating = true;
        /*定时器闪烁*/
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                for (final int color : colors) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setBackgroundColor(color);
                        }
                    });
                }
            }
        };
        timer.schedule(timerTask, 10, colors.length * 1000);
    }
    
    public void stopShinings() {
        mAnimating = false;
        if (timerTask != null)
            timerTask.cancel();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        setBackgroundColor(Color.TRANSPARENT);
    }
    
    public void startShining() {
        if (mAnimating)
            return;
        mAnimating = true;
        /*定时器闪烁*/
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        shiningTag = !shiningTag;
                        setTextColor(shiningTag ? mStartColorInt : mFinishColorInt);
                    }
                });
            }
        };
        timer.schedule(timerTask, 10, 500);
        
    }
    
    public void stopShining() {
        mAnimating = false;
        if (timerTask != null)
            timerTask.cancel();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        setTextColor(Color.parseColor(mStartColor));
    }
    
    public int getDuration() {
        return mDuration;
    }
    
    public void setDuration(int duration) {
        mDuration = duration;
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopShining();
        stopShinings();
    }
}
