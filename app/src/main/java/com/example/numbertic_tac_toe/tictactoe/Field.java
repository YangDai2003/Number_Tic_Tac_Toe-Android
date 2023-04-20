package com.example.numbertic_tac_toe.tictactoe;

  public class Field {

  private final int value;
  private final boolean firstPlayer;

  public Field(int value, boolean firstPlayer) {
  this.value = value;
  this.firstPlayer = firstPlayer;
  }

  public int value() { return value; }

  public boolean firstPlayer() { return firstPlayer; }

  }
