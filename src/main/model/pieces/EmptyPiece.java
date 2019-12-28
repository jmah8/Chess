package main.model.pieces;

import main.model.Board;

public class EmptyPiece extends ChessPiece {

//    public EmptyPiece(int xcoord, int ycoord) {
//        super(xcoord, ycoord, -1);
//        pieceID = PieceName.EMPTY;
//    }

    public EmptyPiece(int xcoord, int ycoord, Board board) {
        super(xcoord, ycoord, -1, board);
        pieceID = PieceName.EMPTY;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
