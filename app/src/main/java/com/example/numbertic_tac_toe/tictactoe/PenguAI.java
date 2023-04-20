package com.example.numbertic_tac_toe.tictactoe;

abstract public class PenguAI {
    public abstract Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
                         boolean[] secondPlayedPieces) ;
}
