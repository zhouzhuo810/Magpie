package me.zhouzhuo810.magpietv.widget.scroll;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class ScrollGridLayoutManager extends GridLayoutManager {
    
    private int MILLISECONDS_PER_INCH = 50;
    
    public ScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    public ScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
    
    public ScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
    
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
            new LinearSmoothScroller(recyclerView.getContext()) {
                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return ScrollGridLayoutManager.this
                        .computeScrollVectorForPosition(targetPosition);
                }
                
                //This returns the milliseconds it takes to
                //scroll one pixel.
                @Override
                protected float calculateSpeedPerPixel
                (DisplayMetrics displayMetrics) {
                    return MILLISECONDS_PER_INCH / displayMetrics.density;
                    //返回滑动一个pixel需要多少毫秒
                    
                }
                
            };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
