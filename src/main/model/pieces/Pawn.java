package main.model.pieces;

import main.model.Position;

public class Pawn extends ChessPiece {

    public Pawn(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.PAWN;
    }

    @Override
    // TODO: make it so that it can move diagonal 1 space if piece is present
    // MODIFIES: this
    // EFFECT: updates possible move list with 1 space forward move
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        if (team == 0) {
            if (y == 6) {
                moveUpEmptyPiece(x, y, 2);
            }
            if (y - 1 >= 0) {
                moveUpEmptyPiece(x, y, 1);
            }
            if (x - 1 >= 0) {
                eatDiagonalPiece(x - 1, y - 1);
            }
            if (x + 1 <= 7) {
                eatDiagonalPiece(x + 1, y - 1);
            }
        } else {
            if (y == 1) {
                moveDownEmptyPiece(x, y, 2);
            }
            if (y + 1 <= 7) {
                moveDownEmptyPiece(x, y, 1);
            }
            if (x - 1 >= 0) {
                eatDiagonalPiece(x - 1, y + 1);
            }
            if (x + 1 <= 7) {
                eatDiagonalPiece(x + 1, y + 1);
            }
        }
    }

    protected void eatDiagonalPiece(int x, int y) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y);
        if (checkOppositeTeam(pieceAtPosXY)) {
            possibleMoves.add(new Position(x, y));
        }
    }

    protected void moveDownEmptyPiece(int x, int y, int i) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y + i);
        if (pieceAtPosXY.checkIfNoTeam()) {
            possibleMoves.add(new Position(x, y + i));
        }
    }

    protected void moveUpEmptyPiece(int x, int y, int i) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y - i);
        if (pieceAtPosXY.checkIfNoTeam()) {
            possibleMoves.add(new Position(x, y - i));
        }
    }
}
