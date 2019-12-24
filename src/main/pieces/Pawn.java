package main.pieces;

import main.Position;

public class Pawn extends ChessPiece{

    public Pawn(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.PAWN;
    }

    @Override
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        possibleMoves.add(new Position(x, y-1));
    }
}
