package main.model.pieces;

import main.model.Position;

public class Horse extends ChessPiece {

    public Horse(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.HORSE;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible L moves
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        if (x - 2 >= 0 && y - 1 >= 0) {
            possibleMoves.add(new Position(x-2, y-1));
        }
        if (x - 2 >= 0 && y + 1 <= 7) {
            possibleMoves.add(new Position(x-2, y+1));
        }
        if (x + 2 <= 7 && y + 1 <= 7) {
            possibleMoves.add(new Position(x+2, y+1));
        }
        if (x + 2 <= 7 && y - 1 >= 0) {
            possibleMoves.add(new Position(x+2, y-1));
        }

        if (x - 1 >= 0 && y - 2 >= 0) {
            possibleMoves.add(new Position(x-1, y-2));
        }
        if (x - 1 >= 0 && y + 2 <= 7) {
            possibleMoves.add(new Position(x-1, y+2));
        }
        if (x + 1 <= 7 && y + 2 <= 7) {
            possibleMoves.add(new Position(x+1, y+2));
        }
        if (x + 1 <= 7 && y - 2 >= 0) {
            possibleMoves.add(new Position(x+1, y-2));
        }
    }
}
