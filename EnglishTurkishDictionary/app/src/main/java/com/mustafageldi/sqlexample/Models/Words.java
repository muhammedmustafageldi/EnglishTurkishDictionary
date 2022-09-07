package com.mustafageldi.sqlexample.Models;

import java.io.Serializable;

public class Words implements Serializable {

    private int wordId;
    private String english;
    private String turkish;

    public Words() {
    }

    public Words(int wordId, String english, String turkish) {
        this.wordId = wordId;
        this.english = english;
        this.turkish = turkish;
    }


    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTurkish() {
        return turkish;
    }

    public void setTurkish(String turkish) {
        this.turkish = turkish;
    }
}
