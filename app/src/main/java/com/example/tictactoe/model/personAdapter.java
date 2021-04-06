package com.example.tictactoe.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class personAdapter extends FirebaseRecyclerAdapter<
        Blog, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<Blog> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "Blog.xml") iwth data in
    // model class(here "Blog.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Blog model) {

        // Add firstname from model class (here
        // "Blog.class")to appropriate view in Card
        // view (here "Blog.xml")
        holder.firstname.setText(model.getBLOG());


    }

    // Function to tell the class about the Card view (here
    // "Blog.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person, parent, false);
        return new personAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "Blog.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView firstname;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            firstname
                    = itemView.findViewById(R.id.firstname);

        }
    }
}
