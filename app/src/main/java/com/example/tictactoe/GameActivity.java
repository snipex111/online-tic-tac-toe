package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameActivity extends AppCompatActivity {
    ListView lv_loginusers;
    ArrayList<String> list_loginusers = new ArrayList<String>();
    ArrayAdapter adpt;
    ListView lv_requestedusers;
    ArrayList<String> list_requestedusers = new ArrayList<String>();
    ArrayAdapter requseradpt;
    String Loginuserid, username, loginuid;
    TextView tv_userid, textView10, textView12;

    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener mauthlistener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        lv_loginusers = (ListView) findViewById(R.id.lv_loginusers);
        adpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_loginusers);
        lv_loginusers.setAdapter(adpt);

        lv_requestedusers = (ListView) findViewById(R.id.lv_requestedusers);
        requseradpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_requestedusers);
        lv_requestedusers.setAdapter(requseradpt);


        tv_userid = (TextView) findViewById(R.id.textView11);
        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                loginuid = user.getUid();
                Log.d("Auth", "onAuthStateChanged:signed_in" + loginuid);
                Loginuserid = user.getEmail();
                tv_userid.setText(Loginuserid);
                username= Convertemailtostring(Loginuserid);
                myref.child("users").child(username).child("request").setValue(loginuid);
                requseradpt.clear();
                Acceptincomingrequests();
            }
        };


    }

    void Acceptincomingrequests() {
        myref.child("users").child(username).child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    HashMap<String, Object> map = (HashMap<String, Object>) snapshot.getValue();
                    if (map != null) {
                        String value = "";
                        for (String key : map.keySet()) {
                            value = (String) map.get(key);
                            requseradpt.add(Convertemailtostring(value));
                            requseradpt.notifyDataSetChanged();
                            myref.child("users").child(username).child("request").setValue(loginuid);

                        }}}
                catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled (@NonNull DatabaseError error){

                    }
                });
                myref.getRoot().child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        updateLoginUsers(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                lv_loginusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String requestToUser = ((TextView)view).getText().toString();
                        confirmRequest(requestToUser, "To");
                    }
                });



                lv_requestedusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String requestFromUser = ((TextView)view).getText().toString();
                        confirmRequest(requestFromUser, "From");
                    }
                });
            }

            void confirmRequest(final String OtherPlayer, final String reqType){
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.connect_player_dialog, null);
                b.setView(dialogView);

                b.setTitle("Start Game?");
                b.setMessage("Connect with " + OtherPlayer);
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myref.child("users")
                                .child(OtherPlayer).child("request").push().setValue(Loginuserid);
                        if(reqType.equalsIgnoreCase("From")) {
                            StartGame(OtherPlayer + ":" + username, OtherPlayer, "From");
                        }else{
                            StartGame(username + ":" + OtherPlayer, OtherPlayer, "To");
                        }
                    }
                });
                b.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.show();



            }


            void StartGame(String PlayerGameID, String OtherPlayer, String requestType){
                myref.child("playing").child(PlayerGameID).removeValue();
                Intent intent = new Intent(getApplicationContext(), OnlineGameActivity.class);
                intent.putExtra("player_session", PlayerGameID);
                intent.putExtra("user_name", username);
                intent.putExtra("other_player", OtherPlayer);
                intent.putExtra("login_uid", loginuid);
                intent.putExtra("request_type", requestType);
                startActivity(intent);
            }


            public void updateLoginUsers(DataSnapshot dataSnapshot) {
                String key = "";
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    key = ((DataSnapshot) i.next()).getKey();
                    if (!key.equalsIgnoreCase(username)) {
                        set.add(key);
                    }
                }

                adpt.clear();
                adpt.addAll(set);
                adpt.notifyDataSetChanged();

            }

            private String Convertemailtostring(String email) {
                String value = email.substring(0, email.indexOf('@'));
                value = value.replace(".", "");
                return value;
            }
        }