package com.briliantech.bdict;

public class dict_items {

    private int _id;
    private String _word;
    private String _wordDescription;

    public dict_items(String _word, String _wordDescription) {
        this._word = _word;
        this._wordDescription = _wordDescription;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_word() {
        return _word;
    }

    public void set_word(String _word) {
        this._word = _word;
    }

    public String get_wordDescription() {
        return _wordDescription;
    }

    public void set_wordDescription(String _wordDescription) {
        this._wordDescription = _wordDescription;
    }
}
