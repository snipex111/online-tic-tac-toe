package com.example.tictactoe.blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tictactoe.R;
import com.example.tictactoe.model.Blog;
import com.example.tictactoe.model.personAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BlogActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private FirebaseAuth auth;
    personAdapter
            adapter;
    DatabaseReference mbase;
    private String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        auth = FirebaseAuth.getInstance();
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlogActivity.this, EditblogtextActivity.class));
            }
        });

        UID = auth.getCurrentUser().getUid();
        mbase
                = FirebaseDatabase.getInstance().getReference().child("Tictactoe").child("Blogs");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Blog> options
                = new FirebaseRecyclerOptions.Builder<Blog>()
                .setQuery(mbase, Blog.class)
                .build();

        adapter = new personAdapter(options);

        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}