package com.example.fpmobile;

public class Item {
    private String letter;

    public Item(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public String getImageName() {
        return letter + ".png";
    }
}
