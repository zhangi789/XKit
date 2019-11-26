package com.tool.cn.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.tool.cn.R;
import com.tool.cn.XKit;

/**
 * author 张海洋
 * create on   2019/11/20 14:42
 * description   android自定义圆角矩形背景按钮，避免创建大量不可复用的shape.xml
 */

public class ShapeTextView extends AppCompatTextView {

    public static final int TAG_FILL_TYPE = 1;//背景填充满

    public static final int TAG_STROKE_TYPE = 2;//背景空心带边缘

    public static final int TAG_NOBG_TYPE = 3;//无背景

    private GradientDrawable mDrawable;

    private GradientDrawable mMaskDrawable;

    private static final String DEFAULT_STROKE_COLOR = "#ff8c99";//默认背景边缘线颜色

    private static final String DEFAULT_BACKGROUND_COLOR = "#00000000";
    private static final String DEFAULT_BACKGROUND_WHITE = "#ffffff";

    private static final String DEFAULT_MASK_COLOR = "#26000000";//遮罩颜色，默认是20%黑色

    private static final int DEFAULT_STROKE_WIDTH = 1;

    private static final int HORIZONTAL_PADDING = 6;//dp 默认左右边padding

    private static final int VERTICAL_PADDING = 3;//dp 默认上下边padding

    private int mStrokeWidth;

    private float mRadius;//dp 默认圆角半径

    private boolean isRoundRect;//是否圆角

    private int mHozPadding;

    private int mVertPadding;

    private int mStrokeColor;

    /**
     * 按压颜色
     */
    private int mPressedColor;

    private boolean mGradientType;

    private int mStartTagColor;
    private int fillTextColor;

    private int mEndTagColor;

    private int mTagType;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ShapeTextView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);
        mRadius = a.getDimension(R.styleable.ShapeTextView_radius, 0);
        isRoundRect = a.getBoolean(R.styleable.ShapeTextView_isRoundRect, false);
        mGradientType = a.getBoolean(R.styleable.ShapeTextView_gradientType, false);
        mStrokeColor = a.getColor(R.styleable.ShapeTextView_tagColor, Color.parseColor(DEFAULT_STROKE_COLOR));
        fillTextColor = a.getColor(R.styleable.ShapeTextView_txtColor, Color.parseColor(DEFAULT_BACKGROUND_WHITE));
        mStartTagColor = a.getColor(R.styleable.ShapeTextView_startTagColor, Color.parseColor(DEFAULT_STROKE_COLOR));
        mEndTagColor = a.getColor(R.styleable.ShapeTextView_endTagColor, Color.parseColor(DEFAULT_STROKE_COLOR));
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.ShapeTextView_strokeWidth, XKit
                .dp2px(getContext(), DEFAULT_STROKE_WIDTH));
        mTagType = a.getInt(R.styleable.ShapeTextView_fillType, TAG_NOBG_TYPE);
        mHozPadding = a.getDimensionPixelSize(R.styleable.ShapeTextView_horizontalPadding, HORIZONTAL_PADDING);
        mVertPadding = a.getDimensionPixelSize(R.styleable.ShapeTextView_verticalPadding, VERTICAL_PADDING);
        mPressedColor = a.getColor(R.styleable.ShapeTextView_pressedColor, Color.parseColor(DEFAULT_MASK_COLOR));
        a.recycle();
        setPadding(mHozPadding, mVertPadding, mHozPadding, mVertPadding);
        setGravity(Gravity.CENTER);
        setColor(mStrokeColor);
    }

    private GradientDrawable getDrawable() {
        if (mDrawable == null) {
            mDrawable = new GradientDrawable();
        }
        mDrawable.setCornerRadius(mRadius);
        switch (mTagType) {
            case TAG_STROKE_TYPE:
                mDrawable.setStroke(mStrokeWidth, mStrokeColor);
                mDrawable.setColor(Color.parseColor(DEFAULT_BACKGROUND_COLOR));
                break;
            case TAG_FILL_TYPE:
                mDrawable.setStroke(mStrokeWidth, mStrokeColor);
                mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                if (mGradientType && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                    mDrawable.setColors(new int[]{mStartTagColor, mEndTagColor});
                } else {
                    mDrawable.setColor(mStrokeColor);
                }
                break;
            case TAG_NOBG_TYPE:
                mDrawable.setColor(Color.parseColor(DEFAULT_BACKGROUND_COLOR));
                break;
        }
        return mDrawable;
    }

    private Drawable getMaskDrawable() {
        if (mMaskDrawable == null) {
            mMaskDrawable = new GradientDrawable();
        }
        mMaskDrawable.setColor(mPressedColor);
        mMaskDrawable.setStroke(mStrokeWidth, mStrokeColor);
        mMaskDrawable.setCornerRadius(mRadius);
        return mMaskDrawable;
    }

    private Drawable getStateDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable normalDrawable = getDrawable();
        Drawable pressedDrawable = getMaskDrawable();
        if (pressedDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, pressedDrawable);
        }
        if (normalDrawable != null) {
            stateListDrawable.addState(new int[]{}, normalDrawable);
        }
        return stateListDrawable;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isRoundRect) {
            mRadius = getMeasuredHeight() / 2f;
            setColor(mStrokeColor);
        }
    }

    /**
     * 设置文字及背景颜色
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setColor(String color) {
        mStrokeColor = Color.parseColor(color);
        switch (mTagType) {
            case TAG_STROKE_TYPE:
                setTextColor(fillTextColor);
                if (Build.VERSION.SDK_INT < 16) {
                    getDrawable().setColor(Color.parseColor(DEFAULT_BACKGROUND_COLOR));
                }
                break;
            case TAG_FILL_TYPE:
                getDrawable().setColor(Color.parseColor(color));
                setTextColor(fillTextColor);
                break;
            case TAG_NOBG_TYPE:
                setTextColor(fillTextColor);
//                setTextColor(Color.parseColor(color));
                break;
        }
        getDrawable().setStroke(mStrokeWidth, Color.parseColor(color));
        setMyBackground();
    }

    /**
     * 设置文字及背景颜色
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setColor(int color) {
        mStrokeColor = color;
        switch (mTagType) {
            case TAG_STROKE_TYPE:
                setTextColor(fillTextColor);
                if (Build.VERSION.SDK_INT < 16) {
                    getDrawable().setColor(Color.parseColor(DEFAULT_BACKGROUND_COLOR));
                }
                break;
            case TAG_FILL_TYPE:
                getDrawable().setColor(color);
                setTextColor(fillTextColor);
                break;
            case TAG_NOBG_TYPE:
                setTextColor(fillTextColor);
                break;
        }
        getDrawable().setStroke(mStrokeWidth, color);
        setMyBackground();
    }

    private void setMyBackground() {
        Drawable drawable = getStateDrawable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void setPressedColor(int pressedColor) {
        mPressedColor = pressedColor;
    }

    public void setStrokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
    }

}
