package main.model.pieces;

import main.model.Board;

public class Bishop extends ChessPiece {

//    public Bishop(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.BISHOP;
//    }

    public Bishop(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        board.getPiecesAlive().add(this);
        pieceID = PieceName.BISHOP;
    }

    /**
     * Updates bishop's possible move list with all possible diagonal moves
     */
    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible diagonal positions
    public void updatePossibleMoves() {
        removePossibleMoves();
        updatePossibleMovesDiagonal();
    }
}
