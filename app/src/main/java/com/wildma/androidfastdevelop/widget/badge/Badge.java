package com.wildma.androidfastdevelop.widget.badge;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author chqiu
 *         Email:qstumn@163.com
 */

public interface Badge {

    /**
     * 设置Badge数字
     */
    Badge setBadgeNumber(int badgeNum);

    int getBadgeNumber();

    /**
     * 设置Badge文本
     */
    Badge setBadgeText(String badgeText);

    String getBadgeText();

    /**
     * 设置是否显示精确模式数值
     */
    Badge setExactMode(boolean isExact);

    boolean isExactMode();

    /**
     * 设置是否显示阴影
     */
    Badge setShowShadow(boolean showShadow);

    boolean isShowShadow();

    /**
     * 设置背景色
     */
    Badge setBadgeBackgroundColor(int color);

    /**
     * 描边
     */
    Badge stroke(int color, float width, boolean isDpValue);

    int getBadgeBackgroundColor();

    /**
     * 设置背景图片
     */
    Badge setBadgeBackground(Drawable drawable);

    Badge setBadgeBackground(Drawable drawable, boolean clip);

    Drawable getBadgeBackground();

    /**
     * 设置文本颜色
     */
    Badge setBadgeTextColor(int color);

    int getBadgeTextColor();

    /**
     * 设置文本字体大小
     */
    Badge setBadgeTextSize(float size, boolean isSpValue);

    float getBadgeTextSize(boolean isSpValue);

    /**
     * 设置内边距
     */
    Badge setBadgePadding(float padding, boolean isDpValue);

    float getBadgePadding(boolean isDpValue);

    boolean isDraggable();

    /**
     * 设置Badge相对于TargetView的位置
     */
    Badge setBadgeGravity(int gravity);

    int getBadgeGravity();

    /**
     * 设置外边距
     */
    Badge setGravityOffset(float offset, boolean isDpValue);

    Badge setGravityOffset(float offsetX, float offsetY, boolean isDpValue);

    float getGravityOffsetX(boolean isDpValue);

    float getGravityOffsetY(boolean isDpValue);

    /**
     * 打开拖拽消除模式并设置监听
     */
    Badge setOnDragStateChangedListener(OnDragStateChangedListener l);

    PointF getDragCenter();

    Badge bindTarget(View view);

    View getTargetView();

    /**
     * 隐藏Badge
     */
    void hide(boolean animate);

    interface OnDragStateChangedListener {
        int STATE_START                 = 1;
        int STATE_DRAGGING              = 2;
        int STATE_DRAGGING_OUT_OF_RANGE = 3;
        int STATE_CANCELED              = 4;
        int STATE_SUCCEED               = 5;

        void onDragStateChanged(int dragState, Badge badge, View targetView);
    }
}
