package main.pieces;

public class Bishop extends ChessPiece {

    public Bishop(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.BISHOP;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
