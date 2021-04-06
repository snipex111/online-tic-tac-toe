package com.example.tictactoe.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tictactoe.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EditblogtextActivity extends AppCompatActivity {
    private Button button6;
    private Button button7;
    private TextInputLayout textInputLayout;
    private FirebaseAuth auth;
    private String UID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editblogtext);
        auth=FirebaseAuth.getInstance();
        UID=auth.getCurrentUser().getUid();


        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        button6 =findViewById(R.id.button6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name=textInputLayout.getEditText().getText().toString();
                if(txt_name.isEmpty())
                {
                    Toast.makeText(EditblogtextActivity.this, "Nothing entered", Toast.LENGTH_SHORT).show();
                }
                else
                { FirebaseDatabase.getInstance().getReference().child("Tictactoe").child("Blogs").child(UID).child("BLOG").setValue(txt_name);}


            }
        });
        button7=findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(EditblogtextActivity.this, BlogActivity.class));

            }
        });


    }
}