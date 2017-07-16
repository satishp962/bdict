package com.briliantech.bdict;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.Voice;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class voice_input extends Activity implements TextToSpeech.OnInitListener {

    //Button mic;
    RelativeLayout mic;
    TextView txt;
    private TextToSpeech t1;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = t1.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This language is not supported.");
            } else {
                String braille_select_voice = "You have selected Voice Input. Tap to start Voice Input.";
                t1.speak(braille_select_voice, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_input);

        //getActionBar().hide();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        t1 = new TextToSpeech(this, this);

        //mic = (Button)findViewById(R.id.mic_but);
        mic = (RelativeLayout)findViewById(R.id.voice_input_rl);

        /*mic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                promptInputSpeech();
            }
        });*/
        mic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Toast.makeText(getApplicationContext(), "hi i am touched.", Toast.LENGTH_SHORT).show();
                promptInputSpeech();
                return false;
            }
        });
    }

    public void promptInputSpeech(){
        Intent speech_intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speech_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speech_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speech_intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            startActivityForResult(speech_intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Speech not supported", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT: {
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String search_txt = result.get(0);
                    Toast.makeText(getApplicationContext(), search_txt, Toast.LENGTH_SHORT);

                    global_braille_word gw = (global_braille_word)this.getApplicationContext();
                    gw.flush_braille_word();
                    gw.setBraille_word(search_txt);

                    Intent i = new Intent(voice_input.this, output_screen.class);
                    startActivity(i);
                }
                break;
            }
        }
    }
}
