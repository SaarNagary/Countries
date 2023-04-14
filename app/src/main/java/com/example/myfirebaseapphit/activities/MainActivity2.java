package com.example.myfirebaseapphit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myfirebaseapphit.R;
import com.example.myfirebaseapphit.databinding.ActivityMain2Binding;
import com.example.myfirebaseapphit.fragments.countriesFragment;
import com.example.myfirebaseapphit.models.Person;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;


public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();
    FirebaseDatabase firebaseDatabase;

    public Person getCurrentUser() {
        return CurrentUser;
    }

    private Person CurrentUser;

    @Override
    public void onBackPressed() {
        //Remove this if you want to stop back pressing to go back to login
        super.onBackPressed();
    }

    //for favorite
    FirebaseFirestore userRef = FirebaseFirestore.getInstance();


    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    ActivityMain2Binding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMain2Binding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        replaceFragment(new countriesFragment());

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer,new countriesFragment());
        fragmentTransaction.commit();

        }

    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer,fragment);
        fragmentTransaction.commit();
    }
}