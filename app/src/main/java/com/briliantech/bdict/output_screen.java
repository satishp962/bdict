package com.briliantech.bdict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.Locale;


public class output_screen extends Activity {

    TextToSpeech t1;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(output_screen.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);

        getActionBar().hide();

        final global_braille_word gbw = (global_braille_word) this.getApplicationContext();

        final DictDbHandler dictDbHandler = DictDbHandler.getInstance(this);
        dictDbHandler.open();

        final TextView txt = (TextView) findViewById(R.id.textView2);
        final TextView txt2 = (TextView) findViewById(R.id.textView4);

        final String global_word = gbw.getBraille_word();

        txt.setText(global_word.toString());

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }

                if (!global_word.equals("")) {
                    String global_word_desc = "";
                    if(dictDbHandler.search_word(global_word)) {
                        t1.speak("This is the output for your entered word " + global_word, TextToSpeech.QUEUE_ADD, null, null);
                        global_word_desc = dictDbHandler.fetch_word_desc(global_word);
                        txt2.setText(global_word_desc.toString().replace("\n", ""));
                        t1.speak(global_word_desc, TextToSpeech.QUEUE_ADD, null, null);
                    }
                    else {
                        t1.speak("Sorry i don't have that word in my dictionary", TextToSpeech.QUEUE_ADD, null, null);
                    }
                } else {
                    t1.speak("Sorry i cannot help you with that word.", TextToSpeech.QUEUE_ADD, null, null);
                }
            }
        });

        final GestureDetector gd;
        gd = new GestureDetector(getApplicationContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (!global_word.equals("")) {
                    String global_word_desc = "";
                    if(dictDbHandler.search_word(global_word)) {
                        global_word_desc = dictDbHandler.fetch_word_desc(global_word);
                        t1.speak(global_word + " means: " + global_word_desc, TextToSpeech.QUEUE_ADD, null, null);
                    }
                    else {
                        t1.speak("Sorry i don't have that word in my dictionary", TextToSpeech.QUEUE_ADD, null, null);
                    }
                } else {
                    t1.speak("Sorry i cannot help you with that word.", TextToSpeech.QUEUE_ADD, null, null);
                }
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                gbw.flush_braille_word();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        LinearLayout rl = (LinearLayout) findViewById(R.id.output_rl);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Toast.makeText(getApplicationContext(), "hi i am touched.", Toast.LENGTH_SHORT).show();
                gd.onTouchEvent(motionEvent);
                return true;
            }
        });

        Button yes = (Button) findViewById(R.id.rpt);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!global_word.equals("")) {
                    String global_word_desc = "";
                    if(dictDbHandler.search_word(global_word)) {
                        global_word_desc = dictDbHandler.fetch_word_desc(global_word);
                        t1.speak(global_word + " means: " + global_word_desc, TextToSpeech.QUEUE_ADD, null, null);
                    }
                    else {
                        t1.speak("Sorry i don't have that word in my dictionary", TextToSpeech.QUEUE_ADD, null, null);
                    }
                } else {
                    t1.speak("Sorry i cannot help you with that word.", TextToSpeech.QUEUE_ADD, null, null);
                }
            }
        });

        Button no = (Button) findViewById(R.id.hm);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gbw.flush_braille_word();
                startActivity(new Intent(output_screen.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        final global_braille_word gbw = (global_braille_word) this.getApplicationContext();
        gbw.flush_braille_word();
        super.onPause();
        t1.stop();
    }
}
