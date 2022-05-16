package com.example.recipeslist.ui;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.recipeslist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsFragment extends PreferenceFragmentCompat {
    SwitchPreferenceCompat switchPreferenceCompat;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        switchPreferenceCompat = findPreference("theme");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert switchPreferenceCompat != null;
        switchPreferenceCompat.setOnPreferenceClickListener(v->{
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            Toast.makeText(requireActivity().getApplicationContext(), "Dark Mode", Toast.LENGTH_SHORT ).show();}
            else {Toast.makeText(requireActivity().getApplicationContext(), "Light Mode", Toast.LENGTH_SHORT ).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);}
                return true;
        });
    }
}