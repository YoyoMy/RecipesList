package com.example.recipeslist.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RecipeLiveData extends LiveData<Recipe> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Recipe recipe = snapshot.getValue(Recipe.class);
            setValue(recipe);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public RecipeLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
