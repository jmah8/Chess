package main.model.pieces;

import main.model.Board;
import main.model.Position;

import java.util.*;

public abstract class ChessPiece {
    // team = 0 is white, team = 1 is black, team = -1 is no team (empty piece)
    // white is at bottom of board and black is at top
    protected int team;
    protected PieceName pieceID;
    protected Position position;
    protected List<Position> possibleMoves;

    // TODO: check if i should do bidirectional association
    protected Board board;

    public ChessPiece(int xcoord, int ycoord, int team) {
        this.position = new Position(xcoord, ycoord);
        this.team = team;
        possibleMoves = new ArrayList<>();
    }

    public ChessPiece(int xcoord, int ycoord, int team, Board board) {
        this.position = new Position(xcoord, ycoord);
        this.team = team;
        possibleMoves = new ArrayList<>();
        this.board = board;
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

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
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
        if (!position.equals(this.position)) {
            this.position = position;
        }
    }

    // EFFECT: returns true if the piece is of the opposite team (empty doesn't count)
    public boolean checkOppositeTeam(ChessPiece piece) {
        return (piece.getTeam() != -1 && this.team != piece.getTeam());
    }

    // EFFECT: returns true if piece is of the same team
    public boolean checkSameTeam(ChessPiece piece) {
        return this.team == piece.getTeam();
    }

    // EFFECT: returns true if this is empty piece
    public boolean checkIfNoTeam() {
        return this.team == -1;
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
        // Checks diagonally up and left
        while (newX > 0 && newY > 0) {
            newX--;
            newY--;
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
        }
        // Checks diagonally down and left
        newX = x;
        newY = y;
        while (newX > 0 && newY < 7) {
            newX--;
            newY++;
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
        }
        // Checks diagonally up and right
        newX = x;
        newY = y;
        while (newX < 7 && newY > 0) {
            newX++;
            newY--;
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
        }
        // Checks diagonally down and right
        newX = x;
        newY = y;
        while (newX < 7 && newY < 7) {
            newX++;
            newY++;
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECT: updates possible move list with all possible vertical and horizontal positions
    protected void updatePossibleMovesStraight() {
        int currentXCoord = position.getXcoord();
        int currentYCoord = position.getYcoord();
        int newX = currentXCoord;
        int newY = currentYCoord;
        // Checks to the left of piece
        while (newX > 0) {
            newX--;
            if (makingMoveBasedOnPieceInGivenXandY(newX, currentYCoord)) {
                break;
            }
        }
        // Checks to the right of piece
        newX = currentXCoord;
        while (newX < 7) {
            newX++;
            if (makingMoveBasedOnPieceInGivenXandY(newX, currentYCoord)) {
                break;
            }
        }
        // Checks above the piece
        while (newY > 0) {
            newY--;
            if (makingMoveBasedOnPieceInGivenXandY(currentXCoord, newY)) {
                break;
            }
        }
        // Checks below the piece
        newY = currentYCoord;
        while (newY < 7) {
            newY++;
            if (makingMoveBasedOnPieceInGivenXandY(currentXCoord, newY)) {
                break;
            }
        }
    }

    private boolean makingMoveBasedOnPieceInGivenXandY (int newX, int newY) {
        ChessPiece piece = board.getPiece(newX, newY);
        if (piece.checkIfNoTeam()) {
            possibleMoves.add(new Position(newX, newY));
        } else if (checkSameTeam(piece)) {
            // Do nothing
            return true;
        } else if (checkOppositeTeam(piece)) {
            possibleMoves.add(new Position(newX, newY));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece)) return false;
        ChessPiece that = (ChessPiece) o;
        return team == that.team &&
                pieceID == that.pieceID &&
                position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, pieceID, position);
    }
}
