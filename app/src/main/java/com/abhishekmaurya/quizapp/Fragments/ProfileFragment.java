package com.abhishekmaurya.quizapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhishekmaurya.quizapp.Models.User;
import com.abhishekmaurya.quizapp.R;
import com.abhishekmaurya.quizapp.Result_Activity;
import com.abhishekmaurya.quizapp.databinding.FragmentProfileBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

import javax.xml.transform.Result;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    FirebaseAuth auth;
    FragmentProfileBinding binding;
    FirebaseFirestore database;
    User user;
    Uri selectedImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        database = FirebaseFirestore.getInstance();

//        binding.dot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             //   auth.signOut();
//            }
//        });

        binding.addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); //*
                startActivityForResult(intent, 33);
            }
        });

//        binding.updatebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FirebaseStorage storage  = FirebaseStorage.getInstance();
//                StorageReference upload = storage.getReference().child(auth.getUid());
//                upload.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        upload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                               String profileImg = uri.toString();
//
//                               User user = new User();
//                               database.collection("users").document().set(user);
//
////                               String name = auth.getUid();
////                               String email = auth.getCurrentUser().getEmail();
////                               String pass = auth.getUid();
////                               String refercode = auth.getUid();
//
//                            }
//                        });
//                    }
//                });
//            }
//        });



//        database.collection("users")
//                .document(FirebaseAuth.getInstance().getUid())
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                user = documentSnapshot.toObject(User.class);
//                Glide.with(getContext()).load(user.getProfileImg()).into(binding.profileImage);
//                binding.editTextTextPersonName.setText(user.getName());
//                binding.editTextTextEmailAddress.setText(user.getEmail());
//            }
//        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if(data != null){
            if (data.getData() != null){
                binding.profileImage.setImageURI(data.getData());
                selectedImage = data.getData();

            }
        }

    }
}