package main.model.pieces;

import main.model.Position;

import java.util.*;

public abstract class ChessPiece {
    // team = 0 is white, team = 1 is black, team = -1 is no team (empty piece)
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

    // MODIFIES: this
    // EFFECT: if position is in possibleMoves, then move piece to position and return true,
    // else return false
    public boolean movePiece(Position position) {
        if (possibleMoves.contains(position)) {
            this.position = position;
            // TODO: should i include updatePossibleMoves here to update it without calling it
            return true;
        }
        return false;
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

    public void setTeam(int team) {
        this.team = team;
    }

    public void setPieceID(PieceName pieceID) {
        this.pieceID = pieceID;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPossibleMoves(List<Position> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public void removePossibleMoves() {
        possibleMoves = new ArrayList<>();
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
