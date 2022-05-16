package com.example.recipeslist.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipeslist.R;


public class HekpAndFeedbackFragment extends Fragment {

    TextView email;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.contact_email);
        email.setOnClickListener(v->sendEmail(v));
    }

    private void sendEmail(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Help and Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi Yoana\n");
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hekp_and_feedback, container, false);
    }
}