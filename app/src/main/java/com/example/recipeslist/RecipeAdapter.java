package com.example.recipeslist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes;
    private OnClickListener onClickListener;

    RecipeAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.recipeTitle.setText(recipes.get(position).getTitle());
        viewHolder.recipeCals.setText(""+recipes.get(position).getCalories());
        viewHolder.recipeImage.setImageResource(recipes.get(position).getImage());
    }

    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeTitle;
        TextView recipeCals;
        ImageView recipeImage;

        ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeCals = itemView.findViewById(R.id.recipeCals);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(recipes.get(getAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(Recipe recipe);
    }
}
