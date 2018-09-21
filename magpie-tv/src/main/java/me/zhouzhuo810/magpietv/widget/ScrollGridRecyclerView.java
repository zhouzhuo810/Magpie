package me.zhouzhuo810.magpietv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;

import me.zhouzhuo810.magpietv.R;
import me.zhouzhuo810.magpietv.widget.scroll.ScrollGridLayoutManager;
import me.zhouzhuo810.magpietv.widget.scroll.ScrollLinearLayoutManager;

/**
 * 滚动格子列表
 */
public class ScrollGridRecyclerView extends RecyclerView {
    
    private ScrollGridLayoutManager mScrollGridLayoutManager;
    
    public ScrollGridRecyclerView(Context context, int spanCount) {
        super(context);
        init(context, null, spanCount);
    }
    
    public ScrollGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }
    
    public ScrollGridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, 0);
    }
    
    private void init(Context context, AttributeSet attrs, int spanCount) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ScrollGridRecyclerView);
            int mSpanCount = t.getInteger(R.styleable.ScrollGridRecyclerView_sgr_spanCount, 2);
            if (mSpanCount > 0) {
                setLayoutManager(getScrollLayoutManager(context, mSpanCount));
            } else {
                throw new RuntimeException("you must set 'sgr_spanCount' attribute for your ScrollGridRecyclerView in layout file.");
            }
            t.recycle();
        } else {
            setLayoutManager(getScrollLayoutManager(context, spanCount));
        }
        
    }
    
    public ScrollGridLayoutManager getScrollLayoutManager(Context context, int spanCount) {
        if (mScrollGridLayoutManager == null) {
            mScrollGridLayoutManager = new ScrollGridLayoutManager(context, spanCount);
        }
        return mScrollGridLayoutManager;
    }
    
    
}
