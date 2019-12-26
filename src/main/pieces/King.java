package main.pieces;

import main.Position;

public class King extends ChessPiece {

    public King(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.KING;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible 1 space move
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        possibleMoves.add(new Position(x + 1, y));
        possibleMoves.add(new Position(x - 1, y));
        possibleMoves.add(new Position(x, y + 1));
        possibleMoves.add(new Position(x, y - 1));

        possibleMoves.add(new Position(x + 1, y + 1));
        possibleMoves.add(new Position(x + 1, y - 1));
        possibleMoves.add(new Position(x - 1, y + 1));
        possibleMoves.add(new Position(x - 1, y - 1));
    }
}
