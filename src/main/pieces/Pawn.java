package main.pieces;

import main.Position;

public class Pawn extends ChessPiece{

    public Pawn(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.PAWN;
    }

    @Override
    // TODO: make it so that it can move diagonal 1 space if piece is present
    // MODIFIES: this
    // EFFECT: updates possible move list with 1 space forward move
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        // TODO: make this method team specific
        if (team == 0) {
            possibleMoves.add(new Position(x, y - 1));
        } else {
            possibleMoves.add(new Position(x, y + 1));
        }
    }
}
