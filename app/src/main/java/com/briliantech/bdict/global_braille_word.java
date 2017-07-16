package com.briliantech.bdict;

/**
 * Created by Satish on 30-11-2016.
 */

import android.app.Application;

public class global_braille_word extends Application {
    private StringBuffer braille_word = new StringBuffer();
    private char[] braille_word_chars;

    public void setBraille_word(String word){
        braille_word.append(word);
    }

    public String getBraille_word(){
        return braille_word.toString();
    }

    public void flush_braille_word(){
        braille_word = new StringBuffer("");
    }

    public char[] getBraille_word_chars() {
        braille_word.getChars(0, braille_word.length(), braille_word_chars, 0);
        return braille_word_chars;
    }

    public void setBraille_word_chars(char[] braille_word_chars) {
        this.braille_word_chars = braille_word_chars;
    }

    public void global_braille_word_arrayTostring(){
        String s = new String(braille_word_chars);
        braille_word = new StringBuffer(s);
    }
}
