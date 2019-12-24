package main;

import main.pieces.ChessPiece;
import main.pieces.EmptyPiece;

public class Board {
    private ChessPiece[][] board;

    public Board() {
        board = new ChessPiece[8][8];
    }

    // MODIFIES: this
    // EFFECT: fills board with no null value
    public void makeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
//                if (i % 2 == 0 && j % 2 == 0) {
                int colourOfPiece;
                // TODO: check this
                if (i % 2 == 0 || j % 2 == 0)
                    board[i][j] = new EmptyPiece(i,j, );
//                } else {
//                    board[i][j] = 1;
//                }
            }
        }
    }
}
