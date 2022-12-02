package com.example.projekt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    GridView feedView;
    Searcher searcher;
    FloatingActionButton addRecipeButton;

    String collectionReference;
    ArrayList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        collectionReference = "recipes";


        feedView = findViewById(R.id.feedView);
        tags = new ArrayList<>();
        tags.add("nyttigt");
        tags.add("billigt");
        tags.add("vegetariskt");
        tags.add("veganskt");
        tags.add("asiatiskt");
        tags.add("husmanskost");
        tags.add("grillat");
        tags.add("friterat");
        tags.add("festligt");
        tags.add("enkelt");
        feedView.setAdapter(new TagViewAdapter(getApplicationContext(), tags));
        searcher = new Searcher(this, collectionReference, feedView);
        feedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tag = adapterView.getAdapter().getItem(i).toString();
                Log.i("====", tag);
                searcher.search(tag);

            }
        });


        addRecipeButton = findViewById(R.id.addRecipeButton);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddRecipeActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Sök");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() == 0){
                    feedView.setAdapter(new TagViewAdapter(getApplicationContext(), tags));
                }else {
                    searcher.search(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.length() == 0){
                    feedView.setAdapter(new TagViewAdapter(getApplicationContext(), tags));
                }else {
                    searcher.search(newText);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}