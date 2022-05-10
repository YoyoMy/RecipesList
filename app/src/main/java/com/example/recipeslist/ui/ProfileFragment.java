package com.example.recipeslist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;


public class ProfileFragment extends Fragment {

    MainActivityViewModel recipeViewModel;
    TextView name;
    ImageView image;
    Button logout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recipeViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(v->recipeViewModel.signOut());
        name = view.findViewById(R.id.user_name);
        image = view.findViewById(R.id.user_photo);
        name.setText(recipeViewModel.getCurrentUser().getValue().getDisplayName());
        //image.setImageResource(recipeViewModel.getCurrentUser().getValue().getPhotoUrl().getPort());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}