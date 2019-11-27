package com.tool.cn.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.tool.cn.banner.transformer.AccordionTransformer;
import com.tool.cn.banner.transformer.BackgroundToForegroundTransformer;
import com.tool.cn.banner.transformer.CubeInTransformer;
import com.tool.cn.banner.transformer.CubeOutTransformer;
import com.tool.cn.banner.transformer.DefaultTransformer;
import com.tool.cn.banner.transformer.DepthPageTransformer;
import com.tool.cn.banner.transformer.FlipHorizontalTransformer;
import com.tool.cn.banner.transformer.FlipVerticalTransformer;
import com.tool.cn.banner.transformer.ForegroundToBackgroundTransformer;
import com.tool.cn.banner.transformer.RotateDownTransformer;
import com.tool.cn.banner.transformer.RotateUpTransformer;
import com.tool.cn.banner.transformer.ScaleInOutTransformer;
import com.tool.cn.banner.transformer.StackTransformer;
import com.tool.cn.banner.transformer.TabletTransformer;
import com.tool.cn.banner.transformer.ZoomInTransformer;
import com.tool.cn.banner.transformer.ZoomOutSlideTransformer;
import com.tool.cn.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
