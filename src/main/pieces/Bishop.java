package main.pieces;

import main.Position;

public class Bishop extends ChessPiece {

    public Bishop(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.BISHOP;
    }

    @Override
    public void updatePossibleMoves() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        int newX = x;
        int newY = y;
        while (newX > 0 && newY > 0) {
            newX--;
            newY--;
            possibleMoves.add(new Position(newX, newY));
        }
        newX = x;
        newY = y;
        while (newX > 0 && newY < 7) {
            newX--;
            newY++;
            possibleMoves.add(new Position(newX, newY));
        }
        newX = x;
        newY = y;
        while (newX < 7 && newY > 0) {
            newX++;
            newY--;
            possibleMoves.add(new Position(newX, newY));
        }
        newX = x;
        newY = y;
        while (newX < 7 && newY < 7) {
            newX++;
            newY++;
            possibleMoves.add(new Position(newX, newY));
        }
    }
}
