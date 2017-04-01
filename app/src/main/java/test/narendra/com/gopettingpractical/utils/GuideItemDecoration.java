package test.narendra.com.gopettingpractical.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <h1>GuideItemDecoration, {@link android.support.v7.widget.RecyclerView.ItemDecoration} for guide recycler view</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */


public class GuideItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public GuideItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        outRect.top = mSpace;
    }
}