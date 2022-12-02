package com.example.projekt;

import android.content.Context;
import android.graphics.Color;
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

import java.util.ArrayList;

public class FeedViewAdapter extends ArrayAdapter<Recipe> {
    StorageReference storageReference;
    String storageUrl = "gs://recipeapp-39918.appspot.com/images/";


    public FeedViewAdapter(@NonNull Context context, ArrayList<Recipe> recipeArray) {
        super(context, 0, recipeArray);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Recipe recipe = getItem(position);
        RecipeCard card = new RecipeCard(getContext());

        //Set image
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(storageUrl + recipe.getImageId());
        GlideApp.with(getContext())
                .load(storageReference)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(card.getImage());

        //Set text
        card.setTitle(recipe.getTitle().substring(0, 1).toUpperCase() + recipe.getTitle().substring(1));
        card.setTime(recipe.getTime() + " min");
        card.setPortions(recipe.getPortions() + " portioner");
        card.setTextColor(Color.WHITE);

        return card;


    }
}