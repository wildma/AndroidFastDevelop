package com.wildma.androidfastdevelop.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/09
 * Desc	        ${Glide工具类}
 */
public class GlideUtils {

    /**
     * 加载自定义类型图片
     *
     * @param context
     * @param obj            图片url、resourceId等等
     * @param imageView      ImageView
     * @param requestOptions RequestOptions
     */
    public static void loadCustomImage(Context context, Object obj, ImageView imageView, RequestOptions requestOptions) {
        Glide.with(context).load(obj).apply(requestOptions).into(imageView);
    }

    /**
     * 加载centerCrop类型图片
     *
     * @param context
     * @param obj       图片url、resourceId等等
     * @param imageView ImageView
     */
    public static void loaderCenterCropImage(Context context, Object obj, ImageView imageView) {
        loaderCenterCropImage(context, obj, imageView, R.drawable.image_loading, R.drawable.image_load_err);
    }

    /**
     * 加载centerCrop类型图片
     *
     * @param context
     * @param obj               图片url、resourceId等等
     * @param imageView         ImageView
     * @param defaultImageResId 默认显示的图片
     * @param errorImageResId   错误时显示的图片
     */
    public static void loaderCenterCropImage(Context context, Object obj, ImageView imageView, @DrawableRes int defaultImageResId, @DrawableRes int errorImageResId) {
        RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(defaultImageResId).error(errorImageResId);
        Glide.with(context).load(obj).apply(requestOptions).into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param obj     图片url、resourceId等等
     * @param view    ImageView
     */
    public static void loadCircleImage(Context context, Object obj, ImageView view) {
        loadCircleImage(context, obj, view, R.drawable.image_loading, R.drawable.image_load_err);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param obj               图片url、resourceId等等
     * @param imageView         ImageView
     * @param defaultImageResId 默认显示的图片
     * @param errorImageResId   错误时显示的图片
     */
    public static void loadCircleImage(Context context, Object obj, ImageView imageView, @DrawableRes int defaultImageResId, @DrawableRes int errorImageResId) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform().placeholder(defaultImageResId).error(errorImageResId);
        Glide.with(context).load(obj).apply(requestOptions).into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param obj       图片url、resourceId等等
     * @param imageView ImageView
     * @param radius    圆角半径（px）
     */
    public static void loadRoundedCornersImage(Context context, Object obj, ImageView imageView, int radius) {
        loadRoundedCornersImage(context, obj, imageView, radius, RoundedCornersTransformation.CornerType.ALL, R.drawable.image_loading, R.drawable.image_load_err);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param obj        图片url、resourceId等等
     * @param imageView  ImageView
     * @param radius     圆角半径（px）
     * @param cornerType 圆角类型（可单独设置某个角为圆角）
     */
    public static void loadRoundedCornersImage(Context context, Object obj, ImageView imageView, int radius, RoundedCornersTransformation.CornerType cornerType) {
        loadRoundedCornersImage(context, obj, imageView, radius, cornerType, R.drawable.image_loading, R.drawable.image_load_err);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param obj               图片url、resourceId等等
     * @param imageView         ImageView
     * @param radius            圆角半径（px）
     * @param cornerType        圆角类型（可单独设置某个角为圆角）
     * @param defaultImageResId 默认显示的图片
     * @param errorImageResId   错误时显示的图片
     */
    public static void loadRoundedCornersImage(Context context, Object obj, ImageView imageView, int radius, RoundedCornersTransformation.CornerType cornerType, @DrawableRes int defaultImageResId, @DrawableRes int errorImageResId) {
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(radius, cornerType));
        RequestOptions requestOptions = RequestOptions
                .bitmapTransform(multiTransformation)
                .placeholder(defaultImageResId)
                .error(errorImageResId);
        Glide.with(context).load(obj).apply(requestOptions).into(imageView);
    }
}
