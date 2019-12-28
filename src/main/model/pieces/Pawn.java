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
                ChessPiece piece = board.getPiece(x, y - 2);
                if (piece.checkIfNoTeam()) {
                    possibleMoves.add(new Position(x, y - 2));
                }
            }
            if (y - 1 >= 0) {
                ChessPiece piece = board.getPiece(x, y - 1);
                if (piece.checkIfNoTeam()) {
                    possibleMoves.add(new Position(x, y - 1));
                }
            }
            if (x - 1 >= 0) {
                ChessPiece piece = board.getPiece(x - 1, y - 1);
                if (checkOppositeTeam(piece)) {
                    possibleMoves.add(new Position(x - 1, y - 1));
                }
            }
            if (x + 1 <= 7) {
                ChessPiece piece = board.getPiece(x + 1, y - 1);
                if (checkOppositeTeam(piece)) {
                    possibleMoves.add(new Position(x + 1, y - 1));
                }
            }
        } else {
            if (y == 1) {
                ChessPiece piece = board.getPiece(x, y + 2);
                if (piece.checkIfNoTeam()) {
                    possibleMoves.add(new Position(x, y + 2));
                }
            }
            if (y + 1 <= 7) {
                ChessPiece piece = board.getPiece(x, y + 1);
                if (piece.checkIfNoTeam()) {
                    possibleMoves.add(new Position(x, y + 1));
                }
            }
            if (x - 1 >= 0) {
                ChessPiece piece = board.getPiece(x - 1, y + 1);
                if (checkOppositeTeam(piece)) {
                    possibleMoves.add(new Position(x - 1, y + 1));
                }
            }
            if (x + 1 <= 7) {
                ChessPiece piece = board.getPiece(x + 1, y + 1);
                if (checkOppositeTeam(piece)) {
                    possibleMoves.add(new Position(x + 1, y + 1));
                }
            }
        }
    }
}
