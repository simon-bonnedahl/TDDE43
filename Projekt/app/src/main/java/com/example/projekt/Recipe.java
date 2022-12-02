package com.example.projekt;

public class Recipe {
    private String title, description, imageId, portions, time;
    private boolean isLiked;

    public Recipe(String title){
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortions() {
        return portions;
    }

    public void setPortions(String portions) {
        this.portions = portions;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTime(){
        return this.time;
    }
    public void setTime(String time){
        this.time = time;
    }

    public void setLiked(boolean b){
        isLiked = b;
    }

    public boolean isLiked(){
        return isLiked;
    }
}
