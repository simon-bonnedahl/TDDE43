package com.example.projekt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddRecipeActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageReference;
    ImageView addRecipeImage;
    String imageUrl;
    EditText title, description;
    TagsLister tagsLister;
    Uri filePath;
    final int PICK_IMAGE_REQUEST = 22;


    SeekBar portionsBar, timeBar;
    TextView portionsText, timeText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        title = findViewById(R.id.addRecipeTitle);
        description = findViewById(R.id.addRecipeDescription);


        addRecipeImage = findViewById(R.id.addRecipeImage);
        addRecipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        Button addButton = findViewById(R.id.addRecipeButton);
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
        });
        tagsLister = findViewById(R.id.tagsLister);
        ArrayList<String> tags = new ArrayList<>();
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


        tagsLister.setAvailableTags(tags);
    }
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                addRecipeImage.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private boolean fieldsValid(){
        boolean valid = true;
        if(description.getText().toString().length() == 0){
            valid = false;
        }
        if(title.getText().toString().length() == 0){
            valid = false;
        }


        if(valid){
            return true;
        }else{
            Toast.makeText(this,"Fill out all fields",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void uploadRecipe(){
        Map<String, Object> recipe = new HashMap<>();
        String imageId = UUID.randomUUID().toString();
        recipe.put("title", title.getText().toString().toLowerCase());
        recipe.put("ingridients", Arrays.asList());
        recipe.put("tags", tagsLister.getActiveTags());
        recipe.put("decription", description.getText().toString());
        recipe.put("time", String.valueOf(timeBar.getProgress()*10));
        recipe.put("portions", String.valueOf(portionsBar.getProgress()*2));
        recipe.put("imageId", imageId);

        db.collection("recipes").add(recipe);
        if (filePath != null) {
            Log.i("=========", filePath.getPath().toString());
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + imageId);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Log.i("==============", "SUCCESS");
                                    Toast
                                            .makeText(getApplicationContext(),
                                                    "Recipe Uploaded",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getApplicationContext(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    Log.i("======", String.valueOf((int) progress));
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

}
