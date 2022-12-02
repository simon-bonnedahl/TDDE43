package com.example.projekt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddRecipeForm extends LinearLayout {
    ImageView addRecipeImage;
    EditText title, description;
    SeekBar portionsBar, timeBar;



    TextView portionsText, timeText;

    public AddRecipeForm(Context context) {
        super(context);
        init();
    }

    private int toDps(int a){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (a * scale + 0.5f);
    }


    @SuppressLint("ResourceAsColor")
    private void init(){
        setBackgroundResource(R.color.lightgray);
        setOrientation(VERTICAL);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addRecipeImage = new ImageView(getContext());
        setAddRecipeImage(R.drawable.addimage2);
        addRecipeImage.setLayoutParams(new ViewGroup.LayoutParams(toDps(230), toDps(190)));
        addView(addRecipeImage);

        title = new EditText(getContext());
        title.setHint("Recipe Title");
        title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setBackgroundTintList(ColorStateList.valueOf(R.color.primary));
        title.setTextColor(Color.WHITE);
        addView(title);

        /* Button addButton = findViewById(R.id.addRecipeButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldsValid()){
                    uploadRecipe();
                    Intent myInt = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(myInt);
                }

            }
        });

        timeBar = findViewById(R.id.timeBar);
        timeText = findViewById(R.id.timeText);
        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                timeText.setText("Time: " + 10*i + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        portionsBar = findViewById(R.id.portionsBar);
        portionsText = findViewById(R.id.portionsText);
        portionsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                portionsText.setText("Portions: " + i*2 + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

    }

    public ImageView getAddRecipeImage() {
        return addRecipeImage;
    }

    public void setAddRecipeImage(int resId) {
        this.addRecipeImage.setImageResource(resId);
    }

    public EditText getTitle() {
        return title;
    }

    public void setTitle(EditText title) {
        this.title = title;
    }

    public EditText getDescription() {
        return description;
    }

    public void setDescription(EditText description) {
        this.description = description;
    }
    public SeekBar getPortionsBar() {
        return portionsBar;
    }

    public void setPortionsBar(SeekBar portionsBar) {
        this.portionsBar = portionsBar;
    }

    public SeekBar getTimeBar() {
        return timeBar;
    }

    public void setTimeBar(SeekBar timeBar) {
        this.timeBar = timeBar;
    }
}
