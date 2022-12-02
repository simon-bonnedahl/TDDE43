package com.example.projekt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TagsLister extends LinearLayout {


    ArrayList<String> availableTags;
    ArrayList<String> activeTags;

    public ArrayList<String> getActiveTags() {
        return activeTags;
    }

    ArrayList<String> matchingTags;

    EditText searchBox;
    ListPopupWindow listPopupWindow;
    ListView listedTags;

    public TagsLister(Context context) {
        super(context);
        init();
    }

    public TagsLister(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagsLister(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        activeTags = new ArrayList<>();
        setOrientation(VERTICAL);
        listedTags = new ListView(getContext());
        listedTags.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        listedTags.setBackgroundColor(Color.TRANSPARENT);
        listedTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                availableTags.add(activeTags.remove(i));
                listedTags.setAdapter(new TagsListerAdapter(getContext(), activeTags));

            }
        });
        addView(listedTags);

        searchBox = new EditText(getContext());
        searchBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        searchBox.setMinHeight(50);
        searchBox.setInputType(EditText.AUTOFILL_TYPE_TEXT);
        searchBox.setHint("Add Tags");
        searchBox.setTextColor(Color.WHITE);
        searchBox.setHintTextColor(Color.WHITE);
        searchBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#80987c")));
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0) {
                    listPopupWindow.dismiss();
                } else {
                    matchingTags = searchTags(editable.toString().toLowerCase().trim());
                    Log.i("=====", matchingTags.toString());
                    if(matchingTags.size() > 0) {
                        listPopupWindow.setAdapter(new TagsListerAdapter(getContext(), matchingTags));
                        listPopupWindow.show();
                    }
                }

            }
        });
        addView(searchBox);
        listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAnchorView(searchBox);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("=======", adapterView.getAdapter().getItem(i).toString());
                String tag = adapterView.getAdapter().getItem(i).toString();
                if(!activeTags.contains(tag)) {
                    activeTags.add(adapterView.getAdapter().getItem(i).toString());
                    availableTags.remove(tag);
                    searchBox.setText("");
                    listedTags.setAdapter(new TagsListerAdapter(getContext(), activeTags));
                }
            }
        });


    }


    private ArrayList<String> searchTags(String query){
        ArrayList<String> result = new ArrayList<>();
        for(String tag : availableTags){
            if(tag.startsWith(query)){
                result.add(tag);
            }
        }
        return result;

    }
    public ArrayList<String> getAvailableTags() {
        return availableTags;
    }

    public void setAvailableTags(ArrayList<String> availableTags) {
        this.availableTags = availableTags;
    }
}
