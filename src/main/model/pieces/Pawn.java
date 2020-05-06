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
        board.getPiecesAlive().add(this);
        pieceID = PieceName.PAWN;
        if (this.team == 0 && ycoord != 6)
            hasMoved = true;
        if (this.team == 1 && ycoord != 1)
            hasMoved = true;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with 1 space forward move
    public void updatePossibleMoves() {
        removePossibleMoves();
        if (team == 0) {
            if (!hasMoved) {
                moveUpTwoSpace();
            }
            moveUp();
            eatDiagonalPiece(-1, -1);
            eatDiagonalPiece(1, -1);
        } else {
            if (!hasMoved) {
                moveDownTwoSpace();
            }
            moveDown();
            eatDiagonalPiece(-1, 1);
            eatDiagonalPiece(1, 1);
        }
    }

    /**
     * Eat diagonal piece. Requires diagonal piece be of enemy team
     *
     * @param x Space to move horizontally
     * @param y Space to move vertically
     */
    private void eatDiagonalPiece(int x, int y) {
        int xPos = position.getXcoord();
        int yPos = position.getYcoord();
        if (!checkInBounds(xPos + x, yPos + y))
            return;
        ChessPiece pieceAtPosXY = board.getPiece(xPos + x, yPos + y);
        if (checkOppositeTeam(pieceAtPosXY)) {
            possibleMoves.add(new Position(xPos + x, yPos + y));
        }
    }


    /**
     * Move pawn 2 pieces up or down depending on team. Requires that pawn is still in the starting position
     *
     * @param x Spaces to move horizontally
     * @param y Spaces to move vertically
     */
    private void movePiece(int x, int y) {
        int xPos = position.getXcoord();
        int yPos = position.getYcoord();
        if (!checkInBounds(xPos + x, yPos + y))
            return;
        ChessPiece pieceAtPosXY = board.getPiece(xPos + x, yPos + y);
        if (pieceAtPosXY.checkIfNoTeam()) {
            possibleMoves.add(new Position(xPos + x, yPos + y));
        }
    }

    /**
     * Requires pawn to be white and haven't moved yet
     * <p>
     * Moves pawn 2 spaces up
     */
    private void moveUpTwoSpace() {
        movePiece(0, -2);
    }

    /**
     * Requires pawn to be white
     * <p>
     * Moves pawn 1 space up
     */
    private void moveUp() {
        movePiece(0, -1);
    }

    /**
     * Requires pawn to be black and haven't moved yet
     * <p>
     * Moves pawn 2 spaces down
     */
    private void moveDownTwoSpace() {
        movePiece(0, 2);
    }

    /**
     * Requires pawn to be black
     * <p>
     * Moves pawn 1 space down
     */
    private void moveDown() {
        movePiece(0, 1);
    }
}
