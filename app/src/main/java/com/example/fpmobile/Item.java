package com.example.fpmobile;

public class Item {
    private final String letter;
    private final int id;

    public Item(String letter, int id) {
        this.letter = letter;
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public int getId() {
        return id;
    }
}
