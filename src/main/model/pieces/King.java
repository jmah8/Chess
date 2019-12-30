package main.model.pieces;

import com.sun.org.glassfish.gmbal.ManagedObject;
import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

//    public King(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.KING;
//    }

    public King(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        pieceID = PieceName.KING;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible 1 space move
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        // Move left
        if (x - 1 >= 0) {
            moveOneSpace(y, x - 1);
        }
        // Move right
        if (x + 1 <= 7) {
            moveOneSpace(y, x + 1);
        }
        // Move up
        if (y - 1 >= 0) {
            moveOneSpace(y - 1, x);
        }
        // Move down
        if (y + 1 <= 7) {
            moveOneSpace(y + 1, x);
        }
        // Move down and right
        if (x + 1 <= 7 && y + 1 <= 7) {
            moveOneSpace(y + 1, x + 1);
        }
        // Move up and right
        if (x + 1 <= 7 && y - 1 >= 0) {
            moveOneSpace(y - 1, x + 1);
        }
        // Move down and left
        if (x - 1 >= 0 && y + 1 <= 7) {
            moveOneSpace(y + 1, x - 1);
        }
        // Move up and left
        if (x - 1 >= 0 && y - 1 >= 0) {
            moveOneSpace(y - 1, x - 1);
        }
    }

    // TODO: make this specific to team of king. Most likely by refactoring board methods
    // MODIFIES: this
    // EFFECT: add the move of y and x to possible move
    private void moveOneSpace(int y, int x) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y);
        Position positionToMoveTo = new Position(x, y);
        if (team == 1) {
            if (!checkSameTeam(pieceAtPosXY) &&
                    checkIfPossibleMoveForKing(positionToMoveTo, 0)) {
                possibleMoves.add(positionToMoveTo);
            }
        } else {
            if (!checkSameTeam(pieceAtPosXY) &&
                    checkIfPossibleMoveForKing(positionToMoveTo, 1)) {
                possibleMoves.add(positionToMoveTo);
            }
        }
    }

    // TODO: do not make it use != PieceName.KING since this prevents the other king from being taken into account
    // TODO: make it so it that the other king isnt calling this method also result in an infinite recursion
    // EFFECT: returns true if the move is possible for king with no check occurring after moving
    private boolean checkIfPossibleMoveForKing(Position position, int teamNumber) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece piece = board.getBoard()[i][j];
                if (piece.getPieceID().equals(PieceName.KING) && !piece.equals(this)) {
                    return !checkOtherKingsPossibleMove(piece, position);
                }
                if (!piece.getPieceID().equals(PieceName.KING) && piece.getTeam() == teamNumber) {
                    piece.updatePossibleMoves();
                    List<Position> possibleMoves = piece.getPossibleMoves();
                    if (piece.getPieceID().equals(PieceName.PAWN)) {
                        if (checkIfPositionSameAsPawnMovingForward(position, piece)) {
                            return true;
                        }
                    }
                    if (possibleMoves.contains(position)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkIfPositionSameAsPawnMovingForward(Position position, ChessPiece piece) {
        int pawnXCoord = piece.getPosition().getXcoord();
        int pawnYCoord = piece.getPosition().getYcoord();
        Position pawnNewPosition;
        Position pawnNewPosition1;
        if (piece.getTeam() == 1) {
            pawnNewPosition = new Position(pawnXCoord, pawnYCoord + 1);
            pawnNewPosition1 = new Position(pawnXCoord, pawnYCoord + 2);
        } else {
            pawnNewPosition = new Position(pawnXCoord, pawnYCoord - 1);
            pawnNewPosition1 = new Position(pawnXCoord, pawnYCoord - 2);
        }
        if (position.equals(pawnNewPosition) || position.equals(pawnNewPosition1)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECT: returns true if otherKing has a possible move (irregardless of other pieces around) at position
    private boolean checkOtherKingsPossibleMove(ChessPiece otherKing, Position position) {
        Position otherKingPos = otherKing.getPosition();
        int x = otherKingPos.getXcoord();
        int y = otherKingPos.getYcoord();
        List<Position> possibleMovesByOtherKing = new ArrayList<>();
        // Move left
        if (x - 1 >= 0) {
            possibleMovesByOtherKing.add(new Position(x - 1, y));
        }
        // Move right
        if (x + 1 <= 7) {
            possibleMovesByOtherKing.add(new Position(x + 1, y));
        }
        // Move up
        if (y - 1 >= 0) {
            possibleMovesByOtherKing.add(new Position(x, y - 1));
        }
        // Move down
        if (y + 1 <= 7) {
            possibleMovesByOtherKing.add(new Position(x, y + 1));
        }
        // Move down and right
        if (x + 1 <= 7 && y + 1 <= 7) {
            possibleMovesByOtherKing.add(new Position(x + 1, y + 1));
        }
        // Move up and right
        if (x + 1 <= 7 && y - 1 >= 0) {
            possibleMovesByOtherKing.add(new Position(x + 1, y - 1));
        }
        // Move down and left
        if (x - 1 >= 0 && y + 1 <= 7) {
            possibleMovesByOtherKing.add(new Position(x - 1, y + 1));
        }
        // Move up and left
        if (x - 1 >= 0 && y - 1 >= 0) {
            possibleMovesByOtherKing.add(new Position(x - 1, y - 1));
        }
        return possibleMovesByOtherKing.contains(position);
    }
}
