package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class PlaywithcomputerActivity extends AppCompatActivity {
    int gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playwithcomputeractivity);
        gameState = 1; //1-play 2-over 3-draw
    }

    public void GameBoardClick(View view) {
        ImageView selectedImage = (ImageView) view;
        int selectedblock = 0;
        switch (selectedImage.getId()) {
            case R.id.iv_11:
                selectedblock = 1;
                break;
            case R.id.iv_12:
                selectedblock = 2;
                break;
            case R.id.iv_13:
                selectedblock = 3;
                break;
            case R.id.iv_21:
                selectedblock = 4;
                break;
            case R.id.iv_22:
                selectedblock = 5;
                break;
            case R.id.iv_23:
                selectedblock = 6;
                break;
            case R.id.iv_31:
                selectedblock = 7;
                break;
            case R.id.iv_32:
                selectedblock = 8;
                break;
            case R.id.iv_33:
                selectedblock = 9;
                break;

        }
        PlayGame(selectedblock,selectedImage);

    }

    int activePlayer = 1;
    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();

    private void PlayGame(int selectedblock, ImageView selectedImage) {
        if (gameState == 1) {
            if (activePlayer == 1) {
                selectedImage.setImageResource(R.drawable.clipart856112);
                player1.add(selectedblock);
                activePlayer = 2;
                Autoplay();
            } else if (activePlayer == 2) {
                selectedImage.setImageResource(R.drawable.lettero);
                player2.add(selectedblock);
                activePlayer = 1;
            }
            selectedImage.setEnabled(false);
            CheckWinner();
        }
    }

    private void Autoplay() {
        ArrayList<Integer> emptyblocks = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            if (!(player1.contains(i) || player2.contains(i))) {
                emptyblocks.add(i);
            }
        }
        if (emptyblocks.size() == 0) {
            CheckWinner();
            if (gameState == 1) {
                AlertDialog.Builder b = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                showAlert("Draw");

            }
            gameState = 3;



        }
        else{
            Random r = new Random();
            int randomIndex = r.nextInt(emptyblocks.size());
            int selectedblock = emptyblocks.get(randomIndex);
            ImageView selectedImage = (ImageView) findViewById(R.id.iv_11);
            switch (selectedblock){
                case 1: selectedImage =(ImageView) findViewById(R.id.iv_11);break;
                case 2: selectedImage =(ImageView) findViewById(R.id.iv_12);break;
                case 3: selectedImage =(ImageView) findViewById(R.id.iv_13);break;
                case 4: selectedImage =(ImageView) findViewById(R.id.iv_21);break;
                case 5: selectedImage =(ImageView) findViewById(R.id.iv_22);break;
                case 6: selectedImage =(ImageView) findViewById(R.id.iv_23);break;
                case 7: selectedImage =(ImageView) findViewById(R.id.iv_31);break;
                case 8: selectedImage =(ImageView) findViewById(R.id.iv_32);break;
                case 9: selectedImage =(ImageView) findViewById(R.id.iv_33);break;
            }
            PlayGame(selectedblock,selectedImage);

        }

    }

    private void showAlert(String Title) {
        AlertDialog.Builder b=new AlertDialog.Builder(this,R.style.TransparentDailog);
        b.setTitle(Title).setMessage("start new game").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Resetgame();
            }
        }).setNegativeButton("main menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i=new Intent(getApplicationContext(),PlayActivity.class);
                startActivity(i);
                finish();
            }
        }).setIcon(android.R.drawable.ic_dialog_info).show();


    }
    void Resetgame(){
        gameState=1;
        activePlayer=1;
        player2.clear();
        player1.clear();
        ImageView iv;
        iv=(ImageView)findViewById(R.id.iv_11); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_12); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_13); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_21); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_22); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_23); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_31); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_32); iv.setImageResource(0);iv.setEnabled(true);
        iv=(ImageView)findViewById(R.id.iv_33); iv.setImageResource(0);iv.setEnabled(true);

    }


    private void CheckWinner() {
        int winner = 0;
        //player 1 win
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1;
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1;
        }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1;
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1;
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1;
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1;
        }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1;
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1;
        }

        //player 2 win
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2;
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2;
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2;
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2;
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2;
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2;
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2;
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2;
        }

        if (winner != 0 && gameState == 1) {
            if (winner == 1) {
                showAlert("you win the game");
            } else if (winner == 2) {
                showAlert("computer won");
            }
            gameState = 2;
        }


    }


}