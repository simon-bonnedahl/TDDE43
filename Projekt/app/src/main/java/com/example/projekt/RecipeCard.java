package com.example.projekt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.print.PrintAttributes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

public class RecipeCard extends CardView {
    private ImageView image;
    private TextView title, time, portions;
    private ImageButton likeButton;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;

    private int textColor = Color.WHITE;
    private int backgroundColor = R.color.gray;



    private boolean liked;

    public RecipeCard(@NonNull Context context) {
        super(context);
        init(context);
    }



    private int toDps(int a){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (a * scale + 0.5f);
    }

    private void init(Context context){
        setCardElevation(toDps(10));
        setRadius(toDps(10));
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toDps(250)));
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundResource(backgroundColor);

        image = new ImageView(context);
        image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toDps(160)));
        image.setImageResource(R.mipmap.ic_launcher);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        linearLayout.addView(image);

        title = new TextView(context);
        title.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toDps(40)));
        title.setTextColor(Color.WHITE);
        title.setTypeface(Typeface.DEFAULT_BOLD);

        linearLayout.addView(title);

        relativeLayout = new RelativeLayout(getContext());
        linearLayout.addView(relativeLayout);



        portions = new TextView(getContext());
        portions.setTextColor(textColor);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params1.leftMargin = toDps(20);
        portions.setLayoutParams(params1);
        portions.setId(generateViewId());
        relativeLayout.addView(portions);

        time = new TextView(getContext());
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.BELOW, portions.getId());

        params2.leftMargin = toDps(20);
        time.setTextColor(textColor);
        time.setLayoutParams(params2);
        relativeLayout.addView(time);

        likeButton = new ImageButton(getContext());
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(toDps(50), toDps(50));
        params3.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        likeButton.setLayoutParams(params3);
        likeButton.setBackgroundColor(Color.TRANSPARENT);
        likeButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        relativeLayout.addView(likeButton);
        likeButton.setImageResource(R.drawable.likebutton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeButton.setImageResource(R.drawable.likebuttonfilled);
                if(isLiked()){
                    setLiked(false);
                    likeButton.setImageResource(R.drawable.likebutton);

                }else{
                    setLiked(true);
                    likeButton.setImageResource(R.drawable.likebuttonfilled);
                }
            }
        });

        addView(linearLayout);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getTitle() {
        return title.getText().toString();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getTime() {
        return time.getText().toString();
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public String getPortions() {
        return portions.getText().toString();
    }

    public void setPortions(String portions) {
        this.portions.setText(portions);
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public ImageButton getLikeButton() {
        return likeButton;
    }

    public void setLikeButton(ImageButton likeButton) {
        this.likeButton = likeButton;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        title.setTextColor(textColor);
        portions.setTextColor(textColor);
        time.setTextColor(textColor);
    }

    @SuppressLint("ResourceType")
    public void setBackgroundColor(int backgroundColor){
        this.backgroundColor = backgroundColor;
        linearLayout.setBackgroundResource(backgroundColor);
    }
}
