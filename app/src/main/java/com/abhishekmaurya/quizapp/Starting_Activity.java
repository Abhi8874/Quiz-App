package com.abhishekmaurya.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.abhishekmaurya.quizapp.Fragments.LogInFragment;
import com.abhishekmaurya.quizapp.Fragments.SignUpFragment;
import com.abhishekmaurya.quizapp.databinding.ActivityStartingBinding;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class Starting_Activity extends AppCompatActivity {

    ActivityStartingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new LogInFragment());
        transaction.commit();

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case 0:
                        transaction.replace(R.id.content, new LogInFragment());
                        transaction.commit();
                        break;
                    case 1:
                        transaction.replace(R.id.content, new SignUpFragment());
                        transaction.commit();
                        break;
                    default:
                }

                return false;
            }
        });

    }
}