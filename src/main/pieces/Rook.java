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
            Position p = new Position(x, i);
            if (!possibleMoves.contains(p) && !p.equals(position)) {
                possibleMoves.add(p);
            }
        }
        for (int j = 0; j < 8; j++) {
            Position p = new Position(j, y);
            if (!possibleMoves.contains(p) && !p.equals(position)) {
                possibleMoves.add(p);
            }
        }
    }
}
