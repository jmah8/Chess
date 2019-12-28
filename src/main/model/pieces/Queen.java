package main.model.pieces;

import main.model.Board;

public class Queen extends ChessPiece {

//    public Queen(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.QUEEN;
//    }

    public Queen(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        pieceID = PieceName.QUEEN;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible diagonal and straight moves
    public void updatePossibleMoves() {
        removePossibleMoves();
        updatePossibleMovesStraight();
        updatePossibleMovesDiagonal();
    }
}
