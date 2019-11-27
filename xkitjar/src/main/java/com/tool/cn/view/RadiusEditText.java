package com.tool.cn.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tool.cn.title.helper.delegate.RadiusEditTextDelegate;


/**
 * author 张海洋
 * create on   2019/11/20 14:42
 * description   用于需要圆角矩形框背景的EditText的情况,减少直接使用EditText时引入的shape资源文件
 */
@SuppressLint("AppCompatCustomView")
public class RadiusEditText extends EditText {
    /**
     * 是否设置完成光标标识
     */
    private boolean mSelectionEndDone;
    private RadiusEditTextDelegate delegate;

    public RadiusEditText(Context context) {
        this(context, null);
    }

    public RadiusEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(false);
        delegate = new RadiusEditTextDelegate(this, context, attrs);
        setFocusableInTouchMode(true);
    }

    /**
     * 获取代理类用于Java代码控制shape属性
     *
     * @return
     */
    public RadiusEditTextDelegate getDelegate() {
        return delegate;
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (!delegate.isSelectionEndEnable()) {
            return;
        }
        if (mSelectionEndDone) {
            return;
        }
        setSelection(text.length());
        mSelectionEndDone = delegate.isSelectionEndOnceEnable();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate != null && delegate.getWidthHeightEqualEnable() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            if (delegate != null) {
                delegate.init();
            }
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (delegate != null) {
            delegate.init();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate != null) {
            if (delegate.getRadiusHalfHeightEnable()) {
                delegate.setRadius(getHeight() / 2);
            }
            delegate.init();
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (delegate != null) {
            delegate.setSelected(selected);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (delegate != null) {
            delegate.init();
        }
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (delegate != null) {
            delegate.init();
        }
    }
}
