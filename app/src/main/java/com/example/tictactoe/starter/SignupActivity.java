package com.example.tictactoe.starter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(SignupActivity.this, "fill up credentials", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 8) {
                    Toast.makeText(SignupActivity.this, "password too short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txt_email, txt_password);

                }
            }
        });
    }
//    private String Convertemailtostring(String s) {
//        String value = s.substring(0, s.indexOf('@'));
//        value = value.replace(".", "");
//        return value;
//    }


    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    //add email to users child
////                    String txt_email = email.getText().toString();
//                    String k=Convertemailtostring(txt_email);
//                    FirebaseDatabase.getInstance().getReference().child("users").;






                    Toast.makeText(SignupActivity.this, "registration succesfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));

                } else {
                    Toast.makeText(SignupActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}