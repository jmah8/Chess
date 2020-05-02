package main.model.pieces;

import main.model.Board;

public class Rook extends ChessPiece {

//    public Rook(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.ROOK;
//    }

    public Rook(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        board.getPiecesAlive().add(this);
        pieceID = PieceName.ROOK;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible straight moves
    public void updatePossibleMoves() {
        removePossibleMoves();
        updatePossibleMovesStraight();
    }
}
