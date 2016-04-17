package uk.co.amlcurran.radiallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class RadialLayout extends RelativeLayout {

    private int angleOffset;
    private final float radius;

    public RadialLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadialLayout);
        try {
            radius = typedArray.getDimension(R.styleable.RadialLayout_rl_radius, getResources().getDimension(R.dimen.defaultRadius));
            angleOffset = typedArray.getInteger(R.styleable.RadialLayout_rl_angleOffset, 0);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            float viewMidX = view.getMeasuredWidth() / 2;
            float viewMidY = view.getMeasuredHeight() / 2;
            float anngleRelativeTo90 = i * (360 / childCount) - 90 + angleOffset;
            double groupX = radius * Math.cos(Math.toRadians(anngleRelativeTo90)) + getWidth() / 2.0;
            double groupY = radius * Math.sin(Math.toRadians(anngleRelativeTo90)) + getHeight() / 2.0;
            view.layout(
                    (int) (groupX - viewMidX),
                    (int) (groupY - viewMidY),
                    (int) (groupX + viewMidX),
                    (int) (groupY + viewMidY)
            );
        }
    }

    public int getAngleOffset() {
        return angleOffset;
    }

    public void setAngleOffset(int angleOffset) {
        this.angleOffset = angleOffset;
        requestLayout();
    }
}
