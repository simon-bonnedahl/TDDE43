package com.example.projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class TagViewAdapter extends ArrayAdapter<String>{



    public TagViewAdapter(@NonNull Context context, ArrayList<String> tagArray) {
        super(context, 0, tagArray);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String tag = getItem(position);
        TagCard card = new TagCard(getContext());


        int imageId = getContext().getResources().getIdentifier(tag, "drawable", getContext().getPackageName());
        card.setTagImage(imageId);




        //Set image
        //card.setTagImage(R.drawable.)
        //Set text
        card.setTitle(tag.substring(0, 1).toUpperCase() + tag.substring(1));

        return card;


    }
}