package com.example.babamalik.digitalassistant;

import android.content.ActivityNotFoundException;
import android.speech.RecognizerIntent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static ImageView mImageView;
    private static Button mButton;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.Login);
        mImageView = (ImageView) findViewById(R.id.babaimage);
                        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatCount(Animation.INFINITE);

        mImageView.startAnimation(rotateAnimation);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MiddleActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                finish();
                //startVoiceInput();

//                Intent intent = new Intent(MainActivity.this, TextMessageActivity.class);
//                startActivity(intent);
            }
        });
    }

}
