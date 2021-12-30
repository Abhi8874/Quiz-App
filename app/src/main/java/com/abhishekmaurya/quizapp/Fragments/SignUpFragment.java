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
import com.abhishekmaurya.quizapp.Models.User;
import com.abhishekmaurya.quizapp.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentSignUpBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Sign Up");
        progressDialog.setMessage("we are creating account...");

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            //  finish();
        }

        binding.createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.nameBox.getText().toString().trim();
                String email = binding.emailBox.getText().toString().trim();
                String pass = binding.passwordBox.getText().toString().trim();
                String refer = binding.referBox.getText().toString().trim();

                if (name.isEmpty()) {
                    binding.nameBox.setError("please enter name");
                    return;
                }
                if (email.isEmpty()) {
                    binding.emailBox.setError("please enter email");
                    return;
                }
                if (pass.isEmpty()) {
                    binding.passwordBox.setError("please enter password");
                    return;
                }
                User user = new User(name, email, pass, refer);

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressDialog.show();
                            String uid = task.getResult().getUser().getUid();

                            db.collection("users")
                                    .document(uid)
                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(getContext(), MainActivity.class);
                                        startActivity(intent);
                                        // finish();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}