package com.library.classic;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Administrator on 2017/11/22.
 */

public class CanItemDecoration extends RecyclerView.ItemDecoration {


    private int height;
    private int width;
    private int rowSpan = 1;

    private boolean isReversed;
    private boolean isVertical;

    private boolean isHeader = true;
    private RecyclerView.LayoutManager layoutManager;

    public CanItemDecoration(RecyclerView.LayoutManager manager) {

        this.layoutManager = manager;
        initLayoutManager();

    }

    public CanItemDecoration setHeight(int height) {
        this.height = height;

        return this;
    }

    public CanItemDecoration setWidth(int width) {
        this.width = width;

        return this;
    }

    public CanItemDecoration setIsHeader(boolean isHeader) {


        this.isHeader = isHeader;

        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        boolean relatedPosition = false;

        initLayoutManager();

        if (isHeader) {

            relatedPosition = parent.getChildLayoutPosition(view) < rowSpan;

        } else {

            int lastSum = 1;


            int itemCount = layoutManager.getItemCount();

            if (itemCount > 0 && rowSpan > 1) {

                lastSum = itemCount % rowSpan;

                if (lastSum == 0) {
                    lastSum = rowSpan;
                }
            }

            int count = itemCount - lastSum;


            int lastPosition = parent.getChildLayoutPosition(view);


            relatedPosition = lastPosition >= count;

        }


        int heightOffset = relatedPosition && isVertical ? height : 0;
        int widthOffset = relatedPosition && !isVertical ? width : 0;

        if (isHeader) {

            if (isReversed) {
                outRect.bottom = heightOffset;
                outRect.right = widthOffset;
            } else {
                outRect.top = heightOffset;
                outRect.left = widthOffset;
            }
        } else {

            if (isReversed) {

                outRect.top = heightOffset;
                outRect.left = widthOffset;

            } else {


                outRect.bottom = heightOffset;
                outRect.right = widthOffset;
            }
        }


    }


    public void initLayoutManager() {

        if (layoutManager instanceof GridLayoutManager) {


            GridLayoutManager grid = (GridLayoutManager) layoutManager;


            this.rowSpan = grid.getSpanCount();

            this.isReversed = grid.getReverseLayout();

            this.isVertical = grid.getOrientation() == LinearLayoutManager.VERTICAL;

        } else if (layoutManager instanceof LinearLayoutManager) {

            LinearLayoutManager linear = (LinearLayoutManager) layoutManager;

            this.rowSpan = 1;
            this.isReversed = linear.getReverseLayout();

            this.isVertical = linear.getOrientation() == LinearLayoutManager.VERTICAL;

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

            StaggeredGridLayoutManager staggeredGrid = (StaggeredGridLayoutManager) layoutManager;

            this.rowSpan = staggeredGrid.getSpanCount();

            this.isReversed = staggeredGrid.getReverseLayout();

            this.isVertical = staggeredGrid.getOrientation() == LinearLayoutManager.VERTICAL;
        }


    }


}
