package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.utils.GlideUtils;
import com.wildma.androidfastdevelop.utils.RoundedCornersTransformation;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/09
 * Desc	        ${Glide工具类使用demo}
 */
public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ImageView iv_img1 = (ImageView) findViewById(R.id.iv_img1);
        ImageView iv_img2 = (ImageView) findViewById(R.id.iv_img2);
        ImageView iv_img3 = (ImageView) findViewById(R.id.iv_img3);
        ImageView iv_img4 = (ImageView) findViewById(R.id.iv_img4);
        ImageView iv_img5 = (ImageView) findViewById(R.id.iv_img5);

        String imgUrl = "https://avatars0.githubusercontent.com/u/18508809?s=460&v=4";
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        GlideUtils.loadCustomImage(this, R.drawable.icon_avatar, iv_img1, requestOptions);
        GlideUtils.loaderCenterCropImage(this, imgUrl, iv_img2);
        GlideUtils.loadCircleImage(this, imgUrl, iv_img3);
        GlideUtils.loadRoundedCornersImage(this, imgUrl, iv_img4, 60);
        GlideUtils.loadRoundedCornersImage(this, imgUrl, iv_img5, 60, RoundedCornersTransformation.CornerType.TOP);
    }
}
