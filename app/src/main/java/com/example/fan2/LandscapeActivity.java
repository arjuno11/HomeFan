package com.example.fan2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class LandscapeActivity extends AppCompatActivity {

    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    Button next;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);

        toggeButton =(ToggleButton) findViewById(R.id.toggleButton);
        switchButton = (Switch)findViewById(R.id.switch1);
        imageView = (ImageView)findViewById(R.id.imageView2);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        next = (Button)findViewById(R.id.button2);

        rotateAnimator=ObjectAnimator. ofFloat (imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator. INFINITE );
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable. RECTANGLE );
        gd.setGradientType(GradientDrawable. RADIAL_GRADIENT );
        gd.setGradientRadius(300);

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchButton.isChecked()) {
                    gd.setColors(new int[]{  Color. YELLOW
                            , Color. TRANSPARENT
                    });
                    imageView.setBackground(gd);
                }else {
                    imageView.setBackgroundColor(Color. TRANSPARENT );
                }
            }
        });

        toggeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggeButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[1]);
                    rotateAnimator.start();
                }else {
                    rotateAnimator.end();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //rotate the fan based on progress parameter
                if (toggeButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[progress]);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (toggeButton.isChecked()) {
                    rotateAnimator.start();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToNextActivity);
            }
        });
    }
}
