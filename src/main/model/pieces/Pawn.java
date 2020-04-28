package main.model.pieces;

import main.model.Board;
import main.model.Position;

public class Pawn extends ChessPiece {


//    public Pawn(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.PAWN;
//    }

    public Pawn(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        pieceID = PieceName.PAWN;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with 1 space forward move
    // TODO: can use field as indicator of if it moved or didn't and check against that for forward 2
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        if (team == 0) {
            if (y == 6) {
//                moveUpEmptyPiece(x, y, 2);
                moveUpTwoSpace();
            }
            if (y - 1 >= 0) {
//                moveUpEmptyPiece(x, y, 1);
                moveUp();
            }
            if (x - 1 >= 0 && y - 1 >= 0) {
                eatDiagonalPiece(x - 1, y - 1);

            }
            if (x + 1 <= 7 && y - 1 >= 0) {
                eatDiagonalPiece(x + 1, y - 1);
            }
        } else {
            if (y == 1) {
//                moveDownEmptyPiece(x, y, 2);
                moveDownTwoSpace();
            }
            if (y + 1 <= 7) {
//                moveDownEmptyPiece(x, y, 1);
                moveDown();
            }
            if (x - 1 >= 0 && y + 1 <= 7) {
                eatDiagonalPiece(x - 1, y + 1);
            }
            if (x + 1 <= 7 && y + 1 <= 7) {
                eatDiagonalPiece(x + 1, y + 1);
            }
        }
    }

    // TODO: could use checkOutOfBounds but not really necessary as there really can't be
    //       pieces out of bounds
    protected void eatDiagonalPiece(int x, int y) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y);
        if (checkOppositeTeam(pieceAtPosXY)) {
            possibleMoves.add(new Position(x, y));
        }
    }

//    // TODO: could use checkOutOfBounds but not really necessary as there really can't be
//    //       pieces out of bounds
//    protected void moveDownEmptyPiece(int x, int y, int i) {
//        ChessPiece pieceAtPosXY = board.getPiece(x, y + i);
//        if (pieceAtPosXY.checkIfNoTeam()) {
//            possibleMoves.add(new Position(x, y + i));
//        }
//    }
//
//    // TODO: could use checkOutOfBounds but not really necessary as there really can't be
//    //       pieces out of bounds
//    protected void moveUpEmptyPiece(int x, int y, int i) {
//        ChessPiece pieceAtPosXY = board.getPiece(x, y - i);
//        if (pieceAtPosXY.checkIfNoTeam()) {
//            possibleMoves.add(new Position(x, y - i));
//        }
//    }

    /**
     * Requires pawn to be white and haven't moved yet
     *
     * Moves pawn 2 spaces up
     */
    private void moveUpTwoSpace() {
        movePiece(-2);
    }

    /**
     * Requires pawn to be white
     *
     * Moves pawn 1 space up
     */
    private void moveUp() {
        movePiece(-1);
    }

    /**
     * Requires pawn to be black and haven't moved yet
     *
     * Moves pawn 2 spaces down
     */
    private void moveDownTwoSpace() {
        movePiece(2);
    }

    /**
     * Requires pawn to be black
     *
     * Moves pawn 1 space down
     */
    private void moveDown() {
        movePiece(1);
    }

    /**
     * Move pawn 2 pieces up or down depending on team. Requires that pawn is still in the starting position
     *
     * @param i Spaces to move
     */
    private void movePiece(int i) {
        ChessPiece pieceAtPosXY = board.getPiece(position.getXcoord(), position.getYcoord() + i);
        if (pieceAtPosXY.checkIfNoTeam()) {
            possibleMoves.add(new Position(position.getXcoord(), position.getYcoord() + i));
        }
    }
}
