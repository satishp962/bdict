package com.briliantech.bdict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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

import java.util.Locale;


public class confirm_word extends Activity {

    TextToSpeech t1;
    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_word);

        getActionBar().hide();

        final global_braille_word gw = (global_braille_word) this.getApplicationContext();

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }

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
                        startActivity(new Intent(getApplicationContext(), output_screen.class));
                        return false;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                        return false;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        startActivity(new Intent(getApplicationContext(), output_screen.class));
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        return false;
                    }
                });

                LinearLayout rl = (LinearLayout) findViewById(R.id.confirm_word_rl);
                rl.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        //Toast.makeText(getApplicationContext(), "hi i am touched.", Toast.LENGTH_SHORT).show();
                        gd.onTouchEvent(motionEvent);
                        return true;
                    }
                });

                Button yes = (Button) findViewById(R.id.button3);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), output_screen.class));
                    }
                });

                Button no = (Button) findViewById(R.id.button4);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gw.flush_braille_word();
                        startActivity(new Intent(confirm_word.this, braille_input_screen.class));
                    }
                });

                TextView txt = (TextView) findViewById(R.id.textView);
                //Button con = (Button) findViewById(R.id.button8);
               // Button back = (Button) findViewById(R.id.button9);

                txt.setText(gw.getBraille_word());
                final String global_word = gw.getBraille_word();

                if (!gw.getBraille_word().equals("")) {
                    t1.speak("You have entered the word" + global_word, TextToSpeech.QUEUE_FLUSH, null, null);
                    t1.speak("The entered characters are: ", TextToSpeech.QUEUE_ADD, null, null);
                    //Toast.makeText(getApplicationContext(), gw_arr.toString(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i <= global_word.length(); i++) {
                        if (i < global_word.length())
                            t1.speak(global_word.substring(i, i + 1) + ".......", TextToSpeech.QUEUE_ADD, null, null);
                        else
                            t1.speak(global_word.substring(i, i), TextToSpeech.QUEUE_ADD, null, null);
                    }

                    t1.speak("Do you want to continue with this word.", TextToSpeech.QUEUE_ADD, null, null);
                }

                /*bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        t1.speak("The entered characters are: ", TextToSpeech.QUEUE_ADD, null, null);
                        //Toast.makeText(getApplicationContext(), gw_arr.toString(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i <= global_word.length(); i++) {
                            if (i < global_word.length())
                                t1.speak(global_word.substring(i, i + 1) + ".......", TextToSpeech.QUEUE_ADD, null, null);
                            else
                                t1.speak(global_word.substring(i, i), TextToSpeech.QUEUE_ADD, null, null);
                        }
                    }
                });*/

                /*con.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(confirm_word.this, output_screen.class);
                        startActivity(i);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gw.flush_braille_word();
                        Intent i = new Intent(confirm_word.this, braille_input_screen.class);
                        startActivity(i);
                    }
                });*/
            }
        });
    }
}
