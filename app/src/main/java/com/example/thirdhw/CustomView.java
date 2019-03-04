package com.example.thirdhw;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomView extends ViewGroup {

    private int childHeight;
    private int distance;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Attrs(context, attrs);
    }

    private void Attrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        this.childHeight = typedArray.getDimensionPixelSize(R.styleable.CustomView_height, 20);
        this.distance = typedArray.getDimensionPixelSize(R.styleable.CustomView_dist2, 20);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widht = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        int width1 = widht;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            measureChild(child, widthMeasureSpec, MeasureSpec.makeMeasureSpec(this.childHeight, MeasureSpec.EXACTLY));

            int childWidth = child.getMeasuredWidth() + this.distance;
            int childHeight = child.getMeasuredHeight();

            if (height == 0) height = childHeight;

            if (width1 >= childWidth) width1 -= childWidth;
            else {
                height += childHeight + this.distance;
                width1 = widht - childWidth;
            }
        }
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = this.getPaddingLeft();
        int childTop = this.getPaddingTop();
        int childRight = this.getMeasuredWidth() - this.getPaddingRight();

        int Width, Height, maxHeight = 0, Left2 = childLeft, Top2 = childTop;

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view.getVisibility() == GONE) continue;

            Width = view.getMeasuredWidth();
            Height = view.getMeasuredHeight();

                if (Left2 + Width >= childRight) {
                    Left2 = childLeft;
                    Top2 += maxHeight;
                }

                view.layout(Left2, Top2, Left2 + Width, Top2 + Height);

                if (maxHeight < Height)
                    maxHeight = Height + this.distance;
                Left2 += Width + this.distance;
        }
    }
}



