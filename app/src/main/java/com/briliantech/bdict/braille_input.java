package com.briliantech.bdict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.Locale;

public class braille_input extends Activity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    private TextToSpeech t1;
    private GestureDetector gesturedetector;

    public void flush_b_w() {
        final global_braille_word m = (global_braille_word) this.getApplication();
        m.flush_braille_word();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braille_input);

        getActionBar().hide();

        flush_b_w();

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
                String braille_select_voice = "You have selected Braille Input. Tap to start braille input".toString();
                t1.speak(braille_select_voice, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        LinearLayout rl = (LinearLayout)findViewById(R.id.braille_input_rl);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Toast.makeText(getApplicationContext(), "hi i am touched.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(braille_input.this, braille_input_screen.class));
                return false;
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

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

    @Override
    public void onResume(){
        super.onResume();
        flush_b_w();
    }
}
