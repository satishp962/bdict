package com.briliantech.bdict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class keyboard_input extends Activity implements TextToSpeech.OnInitListener {
    private TextToSpeech t1;

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = t1.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This language is not supported.");
            } else {
                String braille_select_voice = "You have selected Keyboard Input.";
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
        setContentView(R.layout.activity_keyboard_input);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        t1 = new TextToSpeech(this, this);

        Button search_button = (Button)findViewById(R.id.search_but);
        final TextView search_text = (TextView)findViewById(R.id.search_text);

        final global_braille_word gw = (global_braille_word)this.getApplicationContext();

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(keyboard_input.this, search_text.getText().toString(), Toast.LENGTH_SHORT).show();
                //t1.speak("You have written " + search_text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);

                if(!search_text.getText().toString().equals(null)){
                    gw.flush_braille_word();
                    gw.setBraille_word(search_text.getText().toString());
                    Intent output = new Intent(keyboard_input.this, output_screen.class);
                    startActivity(output);
                }
            }
        });
    }

}
