package com.wildma.androidfastdevelop.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/9/2
 * Desc	        ${自定义跑马灯（单行文本滚动）}
 */
@SuppressLint("AppCompatCustomView")
public class MarqueeView extends TextView implements OnClickListener {

    public static final int     TOLEFT       = 0;//向左滚动
    public static final int     TORIGHT      = 1;//向右滚动
    private             boolean mIsStarting  = false;//是否开始滚动
    private             boolean mIsClickable = false;//是否可以点击
    private float        mTextLength;//文本长度
    private float        mViewWidth;//view的宽度
    private float        mX;//文本的横坐标
    private float        mY;//文本的纵坐标
    private float        mViewWidthPlusTextLength;//view的宽度+文本长度
    private float        mViewWidthPlusTwoTextLength;//view的宽度+文本长度*2
    private String       mText;//文本内容集合中的单个文本
    private List<String> mStringList;//文本内容集合
    private int   mTextPos     = 0;//文本内容集合索引
    private float mStep        = 4;//文本滚动间隔，越大越快
    private int   mOrientation = 0;//滚动方向
    private int   textColor    = 0xffffffff;//文本颜色
    private Paint mPaint;
    private boolean isFirst = true;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSingleLine();
        setEllipsize(null);
    }

    public void switchText() {
        if (mStringList == null || mStringList.size() <= 0) {
            return;
        }

        mText = mStringList.get(mTextPos).trim();
        mTextLength = mPaint.measureText(mText);
        mViewWidth = getWidth();
        mViewWidthPlusTextLength = mViewWidth + mTextLength;
        mViewWidthPlusTwoTextLength = mViewWidth + mTextLength * 2;

        if (mOrientation == TOLEFT) {
            mX = mTextLength;
        } else if (mOrientation == TORIGHT) {
            mX = -mTextLength;
        }

        mY = getTextSize() + getPaddingTop();

        if (mTextPos >= mStringList.size() - 1)
            mTextPos = 0;
        else
            mTextPos++;
    }

    @Override
    public boolean onPreDraw() {
        if (isFirst) {
            isFirst = false;
            switchText();
        }
        return super.onPreDraw();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(mText)) {
            return;
        }

        switch (mOrientation) {
            case TOLEFT:
                canvas.drawText(mText, mViewWidthPlusTextLength - mX, mY, mPaint);
                break;
            case TORIGHT:
                canvas.drawText(mText, mX, mY, mPaint);
                break;
        }

        if (!mIsStarting) {
            return;
        }

        switch (mOrientation) {
            case TOLEFT:
                mX += mStep;
                if (mX > mViewWidthPlusTwoTextLength) {
                    switchText();
                }
                break;
            case TORIGHT:
                mX += mStep;
                if (mX > mViewWidthPlusTwoTextLength) {
                    switchText();
                }
                break;
        }

        invalidate();
    }

    @Override
    public void onClick(View v) {
        if (!mIsClickable) {
            return;
        }

        if (mIsStarting) {
            stopScroll();
        } else {
            startScroll();
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.step = mX;
        ss.isStarting = mIsStarting;
        ss.textPos = mTextPos;
        ss.text = mText;
        ss.temp_view_plus_text_length = mViewWidthPlusTextLength;
        ss.temp_view_plus_two_text_length = mViewWidthPlusTwoTextLength;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mX = ss.step;
        mIsStarting = ss.isStarting;
        mTextPos = ss.textPos;
        mText = ss.text;
        mViewWidthPlusTextLength = ss.temp_view_plus_text_length;
        mViewWidthPlusTwoTextLength = ss.temp_view_plus_two_text_length;
    }

    @IntDef({TOLEFT, TORIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Orientation {
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }
        };
        boolean isStarting;
        float   step;
        int     textPos;
        String  text;
        float   temp_view_plus_text_length;
        float   temp_view_plus_two_text_length;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            isStarting = in.readByte() != 0;
            step = in.readFloat();
            textPos = in.readInt();
            text = in.readString();
            temp_view_plus_text_length = in.readFloat();
            temp_view_plus_two_text_length = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte((byte) (isStarting ? 1 : 0));
            out.writeFloat(step);
            out.writeInt(textPos);
            out.writeString(text);
            out.writeFloat(temp_view_plus_text_length);
            out.writeFloat(temp_view_plus_two_text_length);
        }
    }

    /**
     * 设置文本
     */
    public MarqueeView setText(@NonNull String[] strings) {
        mStringList = Arrays.asList(strings);
        return this;
    }

    /**
     * 设置是否允许点击，点击会 停止/开始 滚动
     */
    public MarqueeView setOnClickable(boolean clickable) {
        this.mIsClickable = clickable;
        if (clickable) {
            setOnClickListener(this);
        }
        return this;
    }

    /**
     * 设置滚动间隔，越大滚动的越快
     */
    public MarqueeView setStep(float step) {
        this.mStep = step;
        return this;
    }

    /**
     * 设置滚动方向
     */
    public MarqueeView setOrientation(@Orientation int orientation) {
        this.mOrientation = orientation;
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param color
     * @return
     */
    public MarqueeView setScrollTextColor(int color) {
        this.textColor = color;
        return this;
    }

    /**
     * 创建
     *
     * @return
     */
    public MarqueeView create() {
        this.mPaint = getPaint();
        mPaint.setColor(textColor);
        //        switchText();
        return this;
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        mIsStarting = true;
        invalidate();
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        mIsStarting = false;
        invalidate();
    }
}
