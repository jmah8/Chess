package main.pieces;

import main.Position;

public class Horse extends ChessPiece {

    public Horse(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.HORSE;
    }

    @Override
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        possibleMoves.add(new Position(x+2, y+1));
        possibleMoves.add(new Position(x+2, y-1));
        possibleMoves.add(new Position(x-2, y+1));
        possibleMoves.add(new Position(x-2, y-1));

        possibleMoves.add(new Position(x-1, y-2));
        possibleMoves.add(new Position(x-1, y+2));
        possibleMoves.add(new Position(x+1, y-2));
        possibleMoves.add(new Position(x+1, y+2));
    }
}
