package main.pieces;

public class Queen extends ChessPiece {

    public Queen(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.QUEEN;
    }

    @Override
    public void updatePossibleMoves() {

    }
}
