package main.pieces;

import main.Position;

public class Rook extends ChessPiece {

    public Rook(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.ROOK;
    }

    @Override
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        for (int i = 0; i < 8; i++) {
            possibleMoves.add(new Position(x, i));
        }
        for (int j = 0; j < 8; j++) {
            possibleMoves.add(new Position(j, y));
        }
    }
}
