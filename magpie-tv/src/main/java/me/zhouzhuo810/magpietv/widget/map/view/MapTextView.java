package me.zhouzhuo810.magpietv.widget.map.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;

import me.zhouzhuo810.magpietv.widget.ShineTextView;


/**
 *
 */
@SuppressLint("ViewConstructor")
public class MapTextView extends ViewGroup {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int EMPTY = 2;
    
    private int mWidth, mHigh, mType;
    
    private ShineTextView mTextView;
    
    private float textSize;
    
    public MapTextView(Context context, int width, int high, float textSize) {
        super(context);
        this.mWidth = width;
        this.mHigh = high;
        this.textSize = textSize;
        this.mType = EMPTY;
        initEmptyChildView();
    }
    
    public MapTextView(Context context, int width, int high, int type, float textSize) {
        super(context);
        this.mWidth = width;
        this.mHigh = high;
        this.mType = type;
        this.textSize = textSize;
        initChildView();
    }
    
    private void initChildView() {
        mTextView = new ShineTextView(getContext(), null);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setGravity(Gravity.CENTER);
        //// FIXME: 2017/6/13 by zz 文字居中问题
        mTextView.measure(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(mHigh, MeasureSpec.EXACTLY));
        addView(mTextView, 0);
    }
    
    private void initEmptyChildView() {
        mTextView = new ShineTextView(getContext(), null);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mTextView.setGravity(Gravity.CENTER);
        //// FIXME: 2017/6/13 by zz 文字居中问题
        mTextView.measure(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(mHigh, MeasureSpec.EXACTLY));
        addView(mTextView, 0);
    }
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = Math.abs(r - l);
        int high = Math.abs(b - t);
        if (mType == VERTICAL) {
            mTextView.layout(0, 0, width, high);
        } else if (mType == HORIZONTAL) {
            mTextView.layout(0, 0, width, high);
        } else {
            mTextView.layout(0, 0, width, high);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHigh);
    }
    
    public void setMachineName(String name) {
        mTextView.setText(name);
        mTextView.setGravity(Gravity.CENTER);
        //mTextView.setBackgroundColor(Color.WHITE);
    }
}
