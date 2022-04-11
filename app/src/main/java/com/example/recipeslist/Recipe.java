package com.example.recipeslist;

public class Recipe {
    private String title;
    private String description;
    private int cals;
    private int image;

    public Recipe(String title, String description, int cals, int image)
    {
        setTitle(title);
        setCals(cals);
        setDescription(description);
        setImage(image);
    }

    public String getDescription() {
        return description;
    }

    public int getCals() {
        return cals;
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

    public void setCals(int cals) {
        this.cals = cals;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "/" + description + "/" + cals + "/" + image;
    }
    public static Recipe toObject(String recipe)
    {
        String[] data = recipe.split("/");
        return new Recipe(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
    }
}
