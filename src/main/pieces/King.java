package main.pieces;

public class King extends ChessPiece {

    public King(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.KING;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
