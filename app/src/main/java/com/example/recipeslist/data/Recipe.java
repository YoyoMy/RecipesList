package com.example.recipeslist.data;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    //private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("instructions")
    private String description;
    private int calories;
    private int image;
    @SerializedName("servings")
    private int servings;

    public Recipe()
    {

    }

    public Recipe(String title, String description, int calories, int image, int servings)
    {
        setTitle(title);
        setCalories(calories);
        setDescription(description);
        setImage(image);
        setServings(servings);
    }

    public String getDescription() {
        return description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getCalories() {
        return calories;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
