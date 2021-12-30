package com.abhishekmaurya.quizapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhishekmaurya.quizapp.MainActivity;
import com.abhishekmaurya.quizapp.databinding.FragmentLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInFragment extends Fragment {


    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentLogInBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Log In");
        progressDialog.setMessage("we are logging in...");

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            //   finish();
        }

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String email = binding.emailBox.getText().toString().trim();
                String pass = binding.passwordBox.getText().toString().trim();

                if (email.isEmpty()) {
                    binding.emailBox.setError("please enter email");
                    progressDialog.dismiss();
                    return;
                }
                if (pass.isEmpty()) {
                    binding.passwordBox.setError("please enter password");
                    progressDialog.dismiss();
                    return;
                }
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return binding.getRoot();
    }
}