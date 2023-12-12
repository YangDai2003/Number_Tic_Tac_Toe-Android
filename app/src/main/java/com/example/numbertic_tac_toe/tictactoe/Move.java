package com.example.numbertic_tac_toe.tictactoe;


public class Move {

    private final int x;
    private final int y;
    private final int value;

    public Move(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int value() {
        return value;
    }
}

