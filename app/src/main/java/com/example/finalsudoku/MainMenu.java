package com.example.finalsudoku;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenu extends AppCompatActivity{
    View shine;
    RelativeLayout newGame;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;
    FloatingActionButton button;
    public static boolean mute = false;

    Button dailyChallenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        shine = findViewById(R.id.shine);
        shineAnimation();

        constraintLayout = findViewById(R.id.Menu);

        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        newGame = findViewById(R.id.NewGame);
        newGame.setOnClickListener(view -> openSudoku());

        dailyChallenge = findViewById(R.id.DailyChallenge);
        dailyChallenge.setOnClickListener(view -> openSudoku());

        button = findViewById(R.id.Settings);
        button.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainMenu.this, button);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                Toast.makeText(MainMenu.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                menuItem.setChecked(true);
                mute = !mute;
                return true;
            });
            popupMenu.show();
        });
    }
    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_right);
        shine.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {
                shine.startAnimation(anim);
            }
            @Override public void onAnimationRepeat(Animation animation) {}
        });
    }
    public void openSudoku(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
