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


public class personAdapter extends FirebaseRecyclerAdapter<
        Blog, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<Blog> options) {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Blog model) {

        holder.firstname.setText(model.getBLOG());


    }


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
