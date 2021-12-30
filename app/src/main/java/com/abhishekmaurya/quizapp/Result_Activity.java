package com.abhishekmaurya.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abhishekmaurya.quizapp.Models.User;
import com.abhishekmaurya.quizapp.databinding.ActivityResultBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class Result_Activity extends AppCompatActivity {

    ActivityResultBinding binding;
    int POINTS = 10;
    FirebaseFirestore database;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int correctAnswers = getIntent().getIntExtra("correct", 0);
        int totalQuestions = getIntent().getIntExtra("total", 0);

        long points = correctAnswers * POINTS;

        binding.recored.setText(String.format("%d/%d", correctAnswers, totalQuestions));
        binding.earnCons.setText(String.valueOf(points));

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));

        binding.restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result_Activity.this, MainActivity.class));
                finishAffinity();
            }
        });




        database = FirebaseFirestore.getInstance();
        // setSupportActionBar(binding.toolbar);
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                Glide.with(Result_Activity.this).load(user.getProfileImg()).into(binding.imageView9);
              //  binding.nametv.setText(user.getName());
            }
        });

    }

}