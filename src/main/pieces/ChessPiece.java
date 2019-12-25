package main.pieces;

import main.Position;
import org.omg.PortableServer.POA;

import java.util.*;

public abstract class ChessPiece {
    // team = 0 is white, team = 1 is black
    // white is at bottom of board and black is at top
    protected int team;
    protected PieceName pieceID;
    protected Position position;
    protected List<Position> possibleMoves;

    public ChessPiece(int xcoord, int ycoord, int team) {
        this.position = new Position(xcoord, ycoord);
        this.team = team;
        possibleMoves = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: update possible move list depending on the piece
    public abstract void updatePossibleMoves();

    public void movePiece(int index) {

    }

    public PieceName getPieceID() {
        return pieceID;
    }

    public int getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public List<Position> getPossibleMoves() {
        return possibleMoves;
    }

    // TODO: check for collisions so that a rook/queen/bishop cant go past a piece

    // MODIFIES: this
    // EFFECT: update possible move list with all possible diagonal positions
    protected void updatePossibleMovesDiagonal() {
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

    // MODIFIES: this
    // EFFECT: updates possible move list with all possible vertical and horizontal positions
    protected void updatePossibleMovesStraight() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        for (int i = 0; i < 8; i++) {
            Position p = new Position(x, i);
            if (!possibleMoves.contains(p) && !p.equals(position)) {
                possibleMoves.add(p);
            }
        }
        for (int j = 0; j < 8; j++) {
            Position p = new Position(j, y);
            if (!possibleMoves.contains(p) && !p.equals(position)) {
                possibleMoves.add(p);
            }
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ChessPiece)) return false;
//        ChessPiece that = (ChessPiece) o;
//        return team == that.team &&
//                pieceID == that.pieceID &&
//                position.equals(that.position);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(team, pieceID, position);
//    }
}
