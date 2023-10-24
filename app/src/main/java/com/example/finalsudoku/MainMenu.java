package com.example.finalsudoku;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.finalsudoku.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;


public class MainMenu extends AppCompatActivity{
    View shine;
    private ActivityMainBinding binding;
    RelativeLayout newGame;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();

        shine = findViewById(R.id.shine);
        shineAnimation();

        constraintLayout = findViewById(R.id.MainPage);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        newGame = findViewById(R.id.NewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSudoku();
            }
        });
    }
    private void initUI(){
        Party party =(
                float speed = 0,
                maxSpeed = 30f,
                damping = 0.9f;
                int spread = 360;
                int[] colors = [0xfce18a, 0xff726d, 0xf4306d, 0xb48def];
                Emitter emitter = (int duration = 100, TimeUnit.MILLISECONDS).max(100),
                position = Position.Relative(0.5, 0.3);
        );
        )
        viewKonfetti.start(party);
    }

    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_right);
        shine.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                shine.startAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    public void openSudoku(){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}
