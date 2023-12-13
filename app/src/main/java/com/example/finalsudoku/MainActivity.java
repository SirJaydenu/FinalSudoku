package com.example.finalsudoku;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;

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
//        if (!this.mute) music.start();
        finished();
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
        Intent intent = getIntent();
        String name = intent.getStringExtra("time");
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

    public void BOnePress(View view){
        gameBoardSolver.setNumberPos(1);
        gameBoard.invalidate();
    }
    public void undo(View view){
        Solver.undoNumber();
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
    public void finished(){
        if(Solver.isSudokuSolved()){
            EmitterConfig emitterConfig = new Emitter(5, TimeUnit.SECONDS).perSecond(30);
            KonfettiView konfettiView = null;

            final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart);
            assert drawable != null;
            Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true, true);
            konfettiView.start(
                    new PartyFactory(emitterConfig)
                            .angle(Angle.RIGHT - 45)
                            .spread(Spread.SMALL)
                            .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                            .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                            .setSpeedBetween(10f, 30f)
                            .position(new Position.Relative(0.0, 0.5))
                            .build(),
                    new PartyFactory(emitterConfig)
                            .angle(Angle.LEFT + 45)
                            .spread(Spread.SMALL)
                            .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                            .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                            .setSpeedBetween(10f, 30f)
                            .position(new Position.Relative(1.0, 0.5))
                            .build()
            );

            Intent intent = new Intent(this, Congratulations.class);
            startActivity(intent);

        }
    }


}
