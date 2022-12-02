package com.example.projekt;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Searcher{
    CollectionReference reference;
    GridView feedView;
    Context context;
    String collectionRef;

    ArrayList<Recipe> searchResults;
    Set<String> searchResultIds;
    public Searcher(Context context, String collectionRef, GridView feedView){
        this.context = context;
        this.feedView = feedView;
        this.collectionRef = collectionRef;
        this.reference = FirebaseFirestore.getInstance().collection(collectionRef);


    }

    private void searchTitle(String query){
        reference.whereGreaterThanOrEqualTo("title", query)
                .whereLessThanOrEqualTo("title", query + "\uF7FF")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("===>", document.getId() + " => " + document.getData());

                                if(!searchResultIds.contains(document.getId())){
                                    searchResultIds.add(document.getId());
                                    Recipe r = new Recipe(document.getString("title"));
                                    r.setImageId(document.getString("imageId"));
                                    r.setDescription(document.getString("description"));
                                    r.setPortions(document.getString("portions"));
                                    r.setTime(document.getString("time"));
                                    searchResults.add(r);
                                }
                            }
                        } else {
                            Log.d("===>", "Error getting documents: ", task.getException());

                        }
                        feedView.setAdapter(new FeedViewAdapter(context, searchResults));
                    }
                });
    }
    private void searchIngredients(String query){
        reference.whereArrayContains("ingredients", query)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("===>", document.getId() + " => " + document.getData());
                                if(!searchResultIds.contains(document.getId())){
                                    searchResultIds.add(document.getId());
                                    Recipe r = new Recipe(document.getString("title"));
                                    r.setImageId(document.getString("imageId"));
                                    r.setDescription(document.getString("description"));
                                    r.setPortions(document.getString("portions"));
                                    r.setTime(document.getString("time"));
                                    searchResults.add(r);
                                }
                            }
                        } else {
                            Log.d("===>", "Error getting documents: ", task.getException());
                        }
                        feedView.setAdapter(new FeedViewAdapter(context, searchResults));

                    }
                });
    }
    private void searchTags(String query){
        reference.whereArrayContains("tags", query)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("===>", document.getId() + " => " + document.getData());
                                if(!searchResultIds.contains(document.getId())){
                                    searchResultIds.add(document.getId());
                                    Recipe r = new Recipe(document.getString("title"));
                                    r.setImageId(document.getString("imageId"));
                                    r.setDescription(document.getString("description"));
                                    r.setPortions(document.getString("portions"));
                                    r.setTime(document.getString("time"));
                                    searchResults.add(r);
                                }
                            }

                        } else {
                            Log.d("===>", "Error getting documents: ", task.getException());
                        }
                        feedView.setAdapter(new FeedViewAdapter(context, searchResults));
                    }
                });
    }

    public void search(String query){
        query = query.toLowerCase().trim();
        reference = FirebaseFirestore.getInstance().collection(collectionRef);
        searchResults = new ArrayList<>();
        searchResultIds = new HashSet<>();
        searchTitle(query);             //Async
        //searchIngredients(query);       //Async
        searchTags(query);              //Async
    }
}
