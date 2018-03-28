package com.example.babamalik.digitalassistant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;

public class MessageActivity extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static ImageView type_message_baba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        type_message_baba = (ImageView) findViewById(R.id.typemessagebaba);
        type_message_baba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ask, Baba is Listening?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String a=result.get(0);
                    //mVoiceInputTv.setText(a);

                    String[] arr=a.split(" ");
                    for(int i=0;i<arr.length;i++)
                    {
                        try
                        {
                            if((arr[i].equals("send") || arr[i].equals("Send")) || ((arr[i].equals("open") || arr[i].equals("Open"))))
                            {
                                if ((arr[i + 1].equals("message") || arr[i + 1].equals("Message")) && arr[i + 2].equals("to")) {
                                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                    smsIntent.setType("vnd.android-dir/mms-sms");
                                    Toast.makeText(this.getApplicationContext(),arr[i+3],Toast.LENGTH_LONG).show();
                                    smsIntent.putExtra("address", arr[i + 3]);
                                    smsIntent.putExtra("sms_body", "");
                                    startActivity(smsIntent);
                                }
                                else if (arr[i + 1].equals("whatsApp") || arr[i + 1].equals("WhatsApp")) {
                                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                                    startActivity(launchIntent);
                                }
                            }
                            else if (arr[i].equals("Search") || arr[i].equals("search"))
                            {
                                String Search=a.replace(arr[i],"");
                                String escapedQuery = URLEncoder.encode(Search, "UTF-8");
                                Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(this.getApplicationContext(),String.valueOf(e),Toast.LENGTH_SHORT);
                        }

                    }
                }
                break;
            }

        }
    }
}
