package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TagsListerAdapter extends ArrayAdapter {
    public TagsListerAdapter(@NonNull Context context, ArrayList<String> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tw = new TextView(getContext());
        String tag = getItem(position).toString();
        tw.setText(tag.substring(0, 1).toUpperCase() + tag.substring(1));
        tw.setTextColor(Color.WHITE);
        tw.setGravity(Gravity.CENTER);
        tw.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
        tw.setBackgroundResource(R.color.gray);

        return tw;

    }
}
