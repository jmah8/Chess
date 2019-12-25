package main.pieces;

public class EmptyPiece extends ChessPiece {

    public EmptyPiece(int xcoord, int ycoord) {
        super(xcoord, ycoord, -1);
        pieceID = PieceName.EMPTY;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
