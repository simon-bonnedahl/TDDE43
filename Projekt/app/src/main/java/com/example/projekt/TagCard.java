package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class TagCard extends CardView {

    RelativeLayout relativeLayout;
    TextView title;
    ImageView tagImage;
    public TagCard(@NonNull Context context) {
        super(context);
        init();
    }

    public TagCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setCardElevation(toDps(10));
        setRadius(toDps(10));
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toDps(150)));

        relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tagImage = new ImageView(getContext());
        tagImage.setImageResource(R.drawable.asiatiskt);
        tagImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tagImage.setScaleType(ImageView.ScaleType.FIT_XY);
        relativeLayout.addView(tagImage);

        title = new TextView(getContext());
        title.setTextColor(Color.WHITE);
        title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        title.bringToFront();
        title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextSize(22);
        title.setShadowLayer(30, 0, 0, Color.BLACK);


        relativeLayout.addView(title);
        addView(relativeLayout);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setTagImage(int resId){
        tagImage.setImageResource(resId);
    }

    private int toDps(int a){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (a * scale + 0.5f);
    }
}
