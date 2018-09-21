package me.zhouzhuo810.magpietv.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import me.zhouzhuo810.magpietv.widget.scroll.ScrollLinearLayoutManager;

/**
 * 滚动列表
 */
public class ScrollListRecyclerView extends RecyclerView {
    
    private ScrollLinearLayoutManager mScrollLinearLayoutManager;
    
    public ScrollListRecyclerView(Context context) {
        super(context);
        setLayoutManager(getScrollLayoutManager(context));
    }
    
    public ScrollListRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(getScrollLayoutManager(context));
    }
    
    public ScrollListRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManager(getScrollLayoutManager(context));
    }
    
    
    public ScrollLinearLayoutManager getScrollLayoutManager(Context context) {
        if (mScrollLinearLayoutManager == null) {
            mScrollLinearLayoutManager = new ScrollLinearLayoutManager(context);
        }
        return mScrollLinearLayoutManager;
    }
    
    rv.addOnScrollListener(new OnVerticalScrollListener() {
        @Override
        public void onScrolledUp() {
        }
        
        @Override
        public void onScrolledDown() {
        }
        
        @Override
        public void onScrolledToTop() {
            scrollTop();
        }
        
        @Override
        public void onScrolledToBottom() {
            scrollBottom();
        }
        
        @Override
        public void onScrollStateChanged(int state) {
            if (state == 0)
                isScrolling = false;
            else if (state == 2)
                isScrolling = true;
        }
    });
    
    private void scrollTop() {
        if (canScroll && refreshMode == 3) {
            refreshHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mAdapter.updateAll(datas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            startScroll();
        }
    }
    
    private void scrollBottom() {
        if (canScroll && refreshMode == 3) {
            rv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        mAdapter.updateAll(datas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rv.scrollToPosition(0);
                }
            }, 3000);
        }
    }
    
    private void stopScroll() {
        rv.stopScroll();
        try {
            mAdapter.updateAll(datas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void startScroll() {
        if (canScroll) {
            if (refreshMode == 3 && !isScrolling) {//滚动刷新
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshMode == 3 && !isScrolling) {
                            try {
                                rv.smoothScrollToPosition(datas.size());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, 3000);
            }
        }
    }
    
}
