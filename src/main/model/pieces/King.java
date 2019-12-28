package main.model.pieces;

import main.model.Position;

public class King extends ChessPiece {

    public King(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.KING;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible 1 space move
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        if (x - 1 >= 0) {
            possibleMoves.add(new Position(x - 1, y));
        }
        if (x + 1 <= 7) {
            possibleMoves.add(new Position(x + 1, y));
        }
        if (y - 1 >= 0) {
            possibleMoves.add(new Position(x, y - 1));
        }
        if (y + 1 <= 7) {
            possibleMoves.add(new Position(x, y + 1));
        }
        if (x + 1 <= 7 && y + 1 <= 7) {
            possibleMoves.add(new Position(x + 1, y + 1));
        }
        if (x + 1 <= 7 && y - 1 >= 0) {
            possibleMoves.add(new Position(x + 1, y - 1));
        }
        if (x - 1 >= 0 && y + 1 <= 7) {
            possibleMoves.add(new Position(x - 1, y + 1));
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            possibleMoves.add(new Position(x - 1, y - 1));
        }
    }
}
