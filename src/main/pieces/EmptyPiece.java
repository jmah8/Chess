package main.pieces;

public class EmptyPiece extends ChessPiece {

    public EmptyPiece(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.EMPTY;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
