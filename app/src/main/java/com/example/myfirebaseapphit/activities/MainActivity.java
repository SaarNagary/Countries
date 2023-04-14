package com.example.myfirebaseapphit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirebaseapphit.models.Person;
import com.example.myfirebaseapphit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText User;
    EditText Password;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }

    public void funcButtonLoginLoginPage(EditText emailText, EditText passwordText, View view) {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Navigation.findNavController(view).navigate(R.id.action_logingFragment_to_mainActivity2);

                        } else {
                            Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void funcButtonRegPage(EditText emailText, EditText passwordText, View view)
    {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //Toast.makeText(MainActivity.this, "reg ok", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.action_regFragment_to_mainActivity2);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "reg fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void write(EditText emailText, EditText phoneText, EditText addressText, EditText idText) {
        Person p = new Person(emailText.getText().toString(), phoneText.getText().toString(), addressText.getText().toString());

// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(idText.getText().toString()); // איפה רוצים לשבץ את  הדבר החדש שינינו מ מסג' שהיה כתוב לפני ליוסרס והוספנו את הצ'ילד

        myRef.setValue(p);
    }

    public void read(TextView email, TextView phone, TextView address, EditText search)
    {
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(search.getText().toString()); // איפה רוצים לשבץ את  הדבר החדש שינינו מ מסג' שהיה כתוב לפני ליוסרס והוספנו את הצ'ילד
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                email.setText(value.email);
                phone.setText(value.phone);
                address.setText(value.address);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
    public void funcButtonRegisterLoginPage(View view) {
        String email = User.getText().toString().trim();
        String password = Password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "register ok", Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(MainActivity.this, "register fail", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}