package main.pieces;

import main.Position;

public class Rook extends ChessPiece {

    public Rook(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.ROOK;
    }

    @Override
    public void updatePossibleMoves() {
        updatePossibleMovesStraight();
    }
}
