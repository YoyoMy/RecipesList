package com.example.recipeslist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class RecipeAdapter extends FirebaseRecyclerAdapter<Recipe, RecipeAdapter.ViewHolder> {

   // ArrayList<Recipe> recipes;
    private OnClickListener onClickListener;
    Recipe recipe;

    public RecipeAdapter(@NonNull FirebaseRecyclerOptions<Recipe> options) {
        super(options);
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

    /*public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.recipeTitle.setText(recipes.get(position).getTitle());
        viewHolder.recipeCals.setText(""+recipes.getCalories());
        viewHolder.recipeImage.setImageResource(recipes.get(position).getImage());
    }*/


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Recipe model) {
        holder.recipeTitle.setText(model.getTitle());
        holder.recipeCals.setText(""+model.getCalories());
        holder.recipeImage.setImageResource(model.getImage());
        holder.setRecipe(model);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeTitle;
        TextView recipeCals;
        ImageView recipeImage;
        Recipe recipe;

        ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeCals = itemView.findViewById(R.id.recipeCals);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(recipe);
            });
        }
        private void setRecipe(Recipe recipe)
        {
            this.recipe = recipe;
        }
    }

    public interface OnClickListener {
        void onClick(Recipe recipe);
    }
}
