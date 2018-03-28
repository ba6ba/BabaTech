package com.example.babamalik.digitalassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextMessageActivity extends AppCompatActivity {
    private static TextView MessageTextView;
    private static Button BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message);
        String getMessage = getIntent().getStringExtra("message");
        MessageTextView = (TextView) findViewById(R.id.MessageTextView);
        BackButton = (Button) findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TextMessageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MessageTextView.setText(getMessage);

    }
}
