package com.briliantech.bdict;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;


public class MainActivity extends Activity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private TextToSpeech t1;
    private GestureDetector gd;
    private boolean leaveCalled = false;
    private Vibrator vb;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onDestroy() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this.getApplicationContext(), "Home pressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().hide();
        vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = t1.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This language is not supported.");
                    } else {
                        //t1.setSpeechRate((float)0.85);
                        //t1.speak("Text to Speech Service started.", TextToSpeech.QUEUE_FLUSH, null, null);
                        welcome_msg();
                        home_instruct();
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        Button braille = (Button) findViewById(R.id.braille_button);
        Button voice = (Button) findViewById(R.id.voice_button);
        Button keyboard = (Button) findViewById(R.id.keyboard_button);
        Button exit = (Button) findViewById(R.id.exitbut);

        braille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, braille_input_screen.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.speak("You're exiting from the app.", TextToSpeech.QUEUE_FLUSH, null, null);
                //finish();
                //System.exit(0);
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, voice_input.class));
            }
        });

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, keyboard_input.class));
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        welcome_msg();
        super.onResume();
    }

    private void speak_data(String msg) {
        String toSpeak = msg.toString();
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void welcome_msg() {
        //t1.speak("Welcome to B.Dict, please select input method to continue...", TextToSpeech.QUEUE_ADD, null, null);
        vb.vibrate(500);
    }

    private void home_instruct() {
        //t1.speak("You're on the home screen.", TextToSpeech.QUEUE_ADD, null,null);
        //t1.speak("Three options are available.", TextToSpeech.QUEUE_ADD, null,null);
        //t1.speak("1. Braille input", TextToSpeech.QUEUE_ADD, null, null);
        //t1.speak("2. Voice input", TextToSpeech.QUEUE_ADD, null, null);
        //t1.speak("3. Keyboard input", TextToSpeech.QUEUE_ADD, null,null);
        //t1.speak("Move finger over the screen to select.", TextToSpeech.QUEUE_ADD, null, null);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Toast.makeText(this.getApplicationContext(), "Single Tap", Toast.LENGTH_SHORT);
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(this.getApplicationContext(), "Double Tap", Toast.LENGTH_SHORT);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Toast.makeText(this.getApplicationContext(), "Double Tap Event", Toast.LENGTH_SHORT);
        return false;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
