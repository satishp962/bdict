package com.briliantech.bdict;

import com.briliantech.bdict.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
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
import android.widget.Toast;
import java.util.Locale;

public class braille_input_screen extends Activity {

    TextToSpeech t1;
    boolean chk_unchk_spk = true;
    boolean num_input = false;
    boolean shw_err = true;
    Integer input[] = {0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_braille_input_screen);

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }

                t1.speak("You are on the braille input screen. Enter the first character", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        getActionBar().hide();

        final ToggleButton a = (ToggleButton)findViewById(R.id.toggleButton);
        final ToggleButton b = (ToggleButton)findViewById(R.id.toggleButton2);
        final ToggleButton c = (ToggleButton)findViewById(R.id.toggleButton3);
        final ToggleButton d = (ToggleButton)findViewById(R.id.toggleButton4);
        final ToggleButton e = (ToggleButton)findViewById(R.id.toggleButton5);
        final ToggleButton f = (ToggleButton)findViewById(R.id.toggleButton6);

        final Button b1 = (Button)findViewById(R.id.button);
        final Button b2 = (Button)findViewById(R.id.button2);

        final Vibrator vb = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        a.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    if(input[0] == 0)
                        input[0] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked first row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if(input[0] == 1)
                        input[0] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked first row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        b.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    if(input[1] == 0)
                        input[1] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked first row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if(input[1] == 1)
                        input[1] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked first row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        c.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (input[2] == 0)
                        input[2] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked second row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if (input[2] == 1)
                        input[2] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked second row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        d.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (input[3] == 0)
                        input[3] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked second row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if (input[3] == 1)
                        input[3] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked second row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        e.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (input[4] == 0)
                        input[4] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked third row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if (input[4] == 1)
                        input[4] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked third row, first column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        f.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (input[5] == 0)
                        input[5] = 1;

                    if(chk_unchk_spk)
                        t1.speak("You have checked third row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else {
                    if (input[5] == 1)
                        input[5] = 0;

                    if(chk_unchk_spk)
                        t1.speak("You have unchecked third row, second column dot", TextToSpeech.QUEUE_FLUSH, null, null);
                }

                vb.vibrate(300);
            }
        });

        //((global_braille_word)this.getApplication()).setBraille_word("Hello World");
        final global_braille_word m = (global_braille_word)this.getApplication();

        //final global_braille_word global_word = global_braille_word.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuffer s = new StringBuffer();
                s.append(input[0].toString());
                s.append(input[1].toString());
                s.append(input[2].toString());
                s.append(input[3].toString());
                s.append(input[4].toString());
                s.append(input[5].toString());

                String out = new String(s);
                String in_char = "";

                if(!num_input){
                    if(out.equals("100000")){
                        in_char = "a";
                    }
                    else if(out.equals("101000")){
                        in_char = "b";
                    }
                    else if(out.equals("110000")){
                        in_char = "c";
                    }
                    else if(out.equals("110100")){
                        in_char = "d";
                    }
                    else if(out.equals("100100")){
                        in_char = "e";
                    }
                    else if(out.equals("111000")){
                        in_char = "f";
                    }
                    else if(out.equals("111100")){
                        in_char = "g";
                    }
                    else if(out.equals("101100")){
                        in_char = "h";
                    }
                    else if(out.equals("011000")){
                        in_char = "i";
                    }
                    else if(out.equals("011100")){
                        in_char = "j";
                    }
                    else if(out.equals("100010")){
                        in_char = "k";
                    }
                    else if(out.equals("101010")){
                        in_char = "l";
                    }
                    else if(out.equals("110010")){
                        in_char = "m";
                    }
                    else if(out.equals("110110")){
                        in_char = "n";
                    }
                    else if(out.equals("100110")){
                        in_char = "o";
                    }
                    else if(out.equals("111010")){
                        in_char = "p";
                    }
                    else if(out.equals("111101")){
                        in_char = "q";
                    }
                    else if(out.equals("101110")){
                        in_char = "r";
                    }
                    else if(out.equals("011010")){
                        in_char = "s";
                    }
                    else if(out.equals("011110")){
                        in_char = "t";
                    }
                    else if(out.equals("100011")){
                        in_char = "u";
                    }
                    else if(out.equals("101011")){
                        in_char = "v";
                    }
                    else if(out.equals("011101")){
                        in_char = "w";
                    }
                    else if(out.equals("110011")){
                        in_char = "x";
                    }
                    else if(out.equals("110111")){
                        in_char = "y";
                    }
                    else if(out.equals("100111")){
                        in_char = "z";
                    }
                    else if(out.equals("010111")){
                        t1.speak("Enter the number", TextToSpeech.QUEUE_FLUSH, null, null);

                        chk_unchk_spk = false;
                        a.setChecked(false);
                        b.setChecked(false);
                        c.setChecked(false);
                        d.setChecked(false);
                        e.setChecked(false);
                        f.setChecked(false);
                        chk_unchk_spk = true;

                        num_input = true;
                        shw_err = false;
                    }
                }
                else {
                    if(out.equals("100000")){
                        in_char = "1";
                    }
                    else if(out.equals("101000")){
                        in_char = "2";
                    }
                    else if(out.equals("110000")){
                        in_char = "3";
                    }
                    else if(out.equals("110100")){
                        in_char = "4";
                    }
                    else if(out.equals("100100")){
                        in_char = "5";
                    }
                    else if(out.equals("111000")){
                        in_char = "6";
                    }
                    else if(out.equals("111100")){
                        in_char = "7";
                    }
                    else if(out.equals("101100")){
                        in_char = "8";
                    }
                    else if(out.equals("011000")){
                        in_char = "9";
                    }
                    else if(out.equals("011100")){
                        in_char = "0";
                    }

                    num_input = false;
                    shw_err = true;
                }

                if(!in_char.equals("")){
                    m.setBraille_word(in_char);

                    String braille_char_voice = ("You have entered a character " + in_char + ". Enter the next character.").toString();

                    Toast.makeText(braille_input_screen.this, m.getBraille_word(), Toast.LENGTH_SHORT).show();

                    chk_unchk_spk = false;
                    a.setChecked(false);
                    b.setChecked(false);
                    c.setChecked(false);
                    d.setChecked(false);
                    e.setChecked(false);
                    f.setChecked(false);
                    chk_unchk_spk = true;

                    t1.speak(braille_char_voice, TextToSpeech.QUEUE_FLUSH, null, null);
                }
                else if(shw_err) {
                    t1.speak("You have entered an invalid character, please enter again.", TextToSpeech.QUEUE_FLUSH, null, null);
                    //t1.speak("All dots are cleared", TextToSpeech.QUEUE_FLUSH, null, null);
                    chk_unchk_spk = false;
                    a.setChecked(false);
                    b.setChecked(false);
                    c.setChecked(false);
                    d.setChecked(false);
                    e.setChecked(false);
                    f.setChecked(false);
                    chk_unchk_spk = true;
                }
            }
        });

        GestureDetector gd;
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
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(m.getBraille_word().length() > 0)
                    startActivity(new Intent(getApplicationContext(), confirm_word.class));
                else
                    t1.speak("You've not entered any character.", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
