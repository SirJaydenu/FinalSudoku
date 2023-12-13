package com.example.finalsudoku;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Game gameBoard;
    private Solver gameBoardSolver;
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    private int count = 0;
    MediaPlayer music;
    Boolean mute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        gameBoard = findViewById(R.id.board);

        gameBoardSolver = gameBoard.getSolver();


        music = MediaPlayer.create(MainActivity.this, R.raw.jazzaplaza);
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> openMenu());
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
        mute = MainMenu.mute;
        if (!this.mute) music.start();
        if(Solver.isSudokuSolved()) finished();
    }
    public void reveal(View view){
        Solver.revealHint();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    private void runTimer() {

        final TextView timeView = (TextView)findViewById(R.id.textView);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
        running = true;


    }
    public void onClickStop(View view)
    {
        count++;
        if(count%2==0){
            onResume();
        }
        else{
            onPause();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
        music.stop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
        music.start();
    }
    private void openMenu() {
        music.stop();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void BOnePress(View view) {
        gameBoardSolver.setNumberPos(1);
        gameBoard.invalidate();
    }
    public void BTwoPress(View view){
        gameBoardSolver.setNumberPos(2);
        gameBoard.invalidate();
    }
    public void BThreePress(View view){
        gameBoardSolver.setNumberPos(3);
        gameBoard.invalidate();
    }
    public void BFourPress(View view){
        gameBoardSolver.setNumberPos(4);
        gameBoard.invalidate();
    }
    public void BFivePress(View view){
        gameBoardSolver.setNumberPos(5);
        gameBoard.invalidate();
    }
    public void BSixPress(View view){
        gameBoardSolver.setNumberPos(6);
        gameBoard.invalidate();
    }
    public void BSevenPress(View view){
        gameBoardSolver.setNumberPos(7);
        gameBoard.invalidate();
    }
    public void BEightPress(View view){
        gameBoardSolver.setNumberPos(8);
        gameBoard.invalidate();
    }
    public void BNinePress(View view){
        gameBoardSolver.setNumberPos(9);
        gameBoard.invalidate();
    }
    public void finished(View view){
        running = false;
        Intent intent = new Intent(this, Congratulations.class);
        startActivity(intent);

    }
    public void finished(){
        running = false;
        Intent intent = new Intent(this, Congratulations.class);
        startActivity(intent);

    }
}
