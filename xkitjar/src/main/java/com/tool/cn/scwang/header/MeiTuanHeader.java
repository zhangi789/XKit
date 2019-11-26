package com.tool.cn.scwang.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tool.cn.R;
import com.tool.cn.scwang.api.RefreshHeader;
import com.tool.cn.scwang.api.RefreshKernel;
import com.tool.cn.scwang.api.RefreshLayout;
import com.tool.cn.scwang.constant.RefreshState;
import com.tool.cn.scwang.constant.SpinnerStyle;

/**
 * author 张海洋
 * create on   2019/11/22 10:31
 * description   TODO
 */

public class MeiTuanHeader extends LinearLayout implements RefreshHeader {


    ImageView mImage;
    private AnimationDrawable pullDownAnim;
    private AnimationDrawable refreshingAnim;
    private boolean hasSetPullDownAnim = false;

    public MeiTuanHeader(Context context) {
        this(context, null);
    }

    public MeiTuanHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeiTuanHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.srl_meituan_header, this);
        mImage = (ImageView) view.findViewById(R.id.iv_refresh_header);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    //平行移动
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }
    /**
     * 下拉过程中不断调用此方法。第一阶段从小变大的小人头动画，和第二阶段翻跟头动画都在这里设置
     */
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        if (percent < 1) {
            mImage.setScaleX(percent);
            mImage.setScaleY(percent);

            //是否执行过翻跟头动画的标记
            if (hasSetPullDownAnim) {
                hasSetPullDownAnim = false;
            }
        }

        //当下拉的高度达到Header高度100%时，开始加载正在下拉的初始动画，即翻跟头
        if (percent >= 1.0) {
            //因为这个方法是不停调用的，防止重复
            if (!hasSetPullDownAnim) {
                mImage.setImageResource(R.drawable.srl_anim_pull_end);
                pullDownAnim = (AnimationDrawable) mImage.getDrawable();
                pullDownAnim.start();

                hasSetPullDownAnim = true;
            }
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    /**
     * 动画结束后调用
     */
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (pullDownAnim != null && pullDownAnim.isRunning()) {
            pullDownAnim.stop();
        }
        if (refreshingAnim != null && refreshingAnim.isRunning()) {
            refreshingAnim.stop();
        }
        //重置状态
        hasSetPullDownAnim = false;
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
    /**
     * 状态改变时调用。在这里切换第三阶段的动画卖萌小人
     * @param refreshLayout
     * @param oldState
     * @param newState
     */
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {


        switch (newState) {
            case PullDownToRefresh: //下拉刷新开始。正在下拉还没松手时调用
                //每次重新下拉时，将图片资源重置为小人的大脑袋
                mImage.setImageResource(R.drawable.commonui_pull_image);
                break;
            case Refreshing: //正在刷新。只调用一次
                //状态切换为正在刷新状态时，设置图片资源为小人卖萌的动画并开始执行
                mImage.setImageResource(R.drawable.srl_anim_pull_refreshing);
                refreshingAnim = (AnimationDrawable) mImage.getDrawable();
                refreshingAnim.start();
                break;
            case ReleaseToRefresh:

                break;
        }


    }
}
