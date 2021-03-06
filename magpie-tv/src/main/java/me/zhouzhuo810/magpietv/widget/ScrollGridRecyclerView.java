package me.zhouzhuo810.magpietv.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import me.zhouzhuo810.magpietv.R;
import me.zhouzhuo810.magpietv.adapter.RvBaseAdapter;
import me.zhouzhuo810.magpietv.widget.scroll.OnVerticalScrollListener;
import me.zhouzhuo810.magpietv.widget.scroll.ScrollGridLayoutManager;

/**
 * 滚动格子列表
 */
public class ScrollGridRecyclerView<T> extends RecyclerView {
    
    private ScrollGridLayoutManager mScrollGridLayoutManager;
    protected boolean mIsScrolling;
    protected boolean mScrollEnable;
    private RvBaseAdapter<T> mAdapter;
    private List<T> mData;
    private boolean mIsTouched;
    private int mTopBottomDelay = 3000;
    
    public ScrollGridRecyclerView(Context context, int spanCount) {
        super(context);
        init(context, null, spanCount);
    }
    
    public ScrollGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 1);
    }
    
    public ScrollGridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, 1);
    }
    
    
    private void init(Context context, AttributeSet attrs, int spanCount) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ScrollGridRecyclerView);
            mScrollEnable = t.getBoolean(R.styleable.ScrollGridRecyclerView_sgr_scrollEnable, true);
            int mSpanCount = t.getInteger(R.styleable.ScrollGridRecyclerView_sgr_spanCount, 1);
            int mScrollSpeed = t.getInteger(R.styleable.ScrollGridRecyclerView_sgr_scrollSpeed, 20);
            mTopBottomDelay = t.getInteger(R.styleable.ScrollGridRecyclerView_sgr_topBottomDelay, 3000);
            if (mTopBottomDelay < 0) {
                throw new RuntimeException("The 'sgr_topBottomDelay' value must >= 0");
            }
            if (mSpanCount > 0) {
                setLayoutManager(getScrollLayoutManager(context, mSpanCount));
            } else {
                throw new RuntimeException("you must set 'sgr_spanCount' attribute for your ScrollGridRecyclerView in layout file.");
            }
            setScrollSpeed(mScrollSpeed);
            t.recycle();
        } else {
            mScrollEnable = true;
            setLayoutManager(getScrollLayoutManager(context, spanCount));
            setScrollSpeed(20);
        }
        addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            protected void onScrolledToTop() {
                scrollTop();
            }
            
            @Override
            protected void onScrolledToBottom() {
                scrollBottom();
            }
            
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0)
                    mIsScrolling = false;
                else if (newState == 2)
                    mIsScrolling = true;
            }
        });
    }
    
    /**
     * 设置能否滚动
     *
     * @param enable 能否
     */
    public void setScrollEnable(boolean enable) {
        this.mScrollEnable = enable;
    }
    
    /**
     * 设置滚动速度
     *
     * @param millsPerPx 滚动1px需要多少毫秒
     */
    public void setScrollSpeed(int millsPerPx) {
        if (millsPerPx <= 0) {
            throw new RuntimeException("The speed value must > 0");
        }
        if (mScrollGridLayoutManager != null) {
            mScrollGridLayoutManager.setMillsPerPixel(millsPerPx);
        }
    }
    
    /**
     * 设置滚动到顶部和底部停留时间
     *
     * @param topBottomDelay 停留时间，毫秒
     */
    public void setTopBottomDelay(int topBottomDelay) {
        this.mTopBottomDelay = topBottomDelay;
    }
    
    /**
     * 刷新数据
     *
     * @param mData 数据
     */
    public void setNewData(List<T> mData) {
        if (mAdapter == null) {
            throw new RuntimeException("you must invoke ScrollGridRecyclerView#setAdapter() method first.");
        }
        setNewData(mData, true);
    }
    
    public void setNewData(List<T> mData, boolean forceUpdate) {
        if (mAdapter == null) {
            throw new RuntimeException("you must invoke ScrollGridRecyclerView#setAdapter() method first.");
        }
        setNewData(mData, forceUpdate, false);
    }
    
    private void setNewData(List<T> mData, boolean forceUpdate, boolean delay) {
        this.mData = mData;
        if (mAdapter != null) {
            if (forceUpdate) {
                stopScroll();
                if (delay) {
                    startScrollDelay(mTopBottomDelay);
                } else {
                    startScrollImmediately();
                }
            } else {
                if (!mIsScrolling) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.updateAll(ScrollGridRecyclerView.this.mData);
                        }
                    });
                }
            }
        }
    }
    
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof RvBaseAdapter) {
            mAdapter = (RvBaseAdapter<T>) adapter;
        } else {
            throw new RuntimeException("your adapter must extends RvBaseAdapter.");
        }
    }
    
    public ScrollGridLayoutManager getScrollLayoutManager(Context context, int spanCount) {
        if (mScrollGridLayoutManager == null) {
            mScrollGridLayoutManager = new ScrollGridLayoutManager(context, spanCount);
        }
        return mScrollGridLayoutManager;
    }
    
    
    public void scrollTop() {
        if (mScrollEnable && !mIsTouched) {
            try {
                setNewData(mData, true, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void scrollBottom() {
        if (mScrollEnable && !mIsTouched) {
            setNewData(mData, false);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        scrollToPosition(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, mTopBottomDelay);
        }
    }
    
    public void startScrollDelay(int mills) {
        if (mScrollEnable) {
            if (!mIsScrolling) {//滚动刷新
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mScrollEnable) {
                            try {
                                if (mData != null) {
                                    smoothScrollToPosition(mData.size());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, mills);
            }
        }
    }
    
    public void startScrollImmediately() {
        if (mScrollEnable) {
            try {
                if (mData != null) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollToPosition(mData.size());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsTouched = true;
                stopScroll();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsTouched = false;
                startScrollImmediately();
                break;
        }
        return super.onTouchEvent(e);
    }
    
    @Override
    public void stopScroll() {
        super.stopScroll();
        if (mAdapter != null) {
            post(new Runnable() {
                @Override
                public void run() {
                    if (mAdapter != null) {
                        mAdapter.updateAll(ScrollGridRecyclerView.this.mData);
                    }
                }
            });
        }
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }
    
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mTopBottomDelay = savedState.delay;
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.delay = mTopBottomDelay;
        return savedState;
    }
    
    
    static class SavedState extends View.BaseSavedState {
        int delay;
        
        public SavedState(Parcelable superState) {
            super(superState);
        }
        
        private SavedState(Parcel in) {
            super(in);
            delay = in.readInt();
        }
        
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(delay);
        }
        
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
            
            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
