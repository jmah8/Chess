package main.model.pieces;

import main.model.Board;
import main.model.Position;

import java.io.Serializable;
import java.util.*;


public abstract class ChessPiece implements Serializable {
    // team = 0 is white, team = 1 is black, team = -1 is no team (empty piece)
    // white is at bottom of board and black is at top
    protected int team;
    protected PieceName pieceID;
    protected Position position;
    protected List<Position> possibleMoves;
    protected Board board;
    protected boolean hasMoved;

    public ChessPiece(int xcoord, int ycoord, int team, Board board) {
        this.position = new Position(xcoord, ycoord);
        this.team = team;
        possibleMoves = new ArrayList<>();
        this.board = board;
        this.hasMoved = false;
        updateBoardToIncludePiece();
    }

    /**
     * Updates possibleMove list of piece
     */
    // MODIFIES: this
    // EFFECT: update possible move list depending on the piece
    public abstract void updatePossibleMoves();

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

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }


    /**
     * Returns true if x,y is in bound ( 0 <= x <= 7 && 0 <= y <= 7)
     * @param x x coordinate to move to
     * @param y y coordinate to move to
     * @return true if x,y is in bound, else return false
     */
    public boolean checkInBounds(int x, int y) {
        return x <= 7 && x >= 0 && y <= 7 && y >= 0;
    }

    /**
     * If position is in possibleMoves and position is not the same as this.position then set this.position
     * to position and then move the piece on the board
     *
     * @param position position to move to
     */
    // MODIFIES: this
    // EFFECT: if position is in possibleMoves and position is not the same as this.position,
    // then set this.position to position and then move the piece on the board
    public void movePosition(Position position) {
        if (!position.equals(this.position) && possibleMoves.contains(position)) {
            this.hasMoved = this.hasMoved == false ? true : false;
            this.position = position;
            board.movePiece(this, position);
        }
    }

    public void updateBoardToIncludePiece() {
        board.placePiece(this);
    }

    // this is not for user movement of pieces
    // MODIFIES: this
    // EFFECT:  move piece to position
    public void movePiece(Position position) {
        if (!position.equals(this.position)) {
            this.position = position;
            board.movePieceIrregardlessOfPossibleMove(this, position);
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

    // MODIFIES: this
    // EFFECT: update possible move list with all possible diagonal positions
    protected void updatePossibleMovesDiagonal() {
        int x = position.getXcoord();
        int y = position.getYcoord();
        int newX = x - 1;
        int newY = y - 1;
        // Checks diagonally up and left
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
            newX--;
            newY--;
        }
        // Checks diagonally down and left
        newX = x - 1;
        newY = y + 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
            newX--;
            newY++;
        }
        // Checks diagonally up and right
        newX = x + 1;
        newY = y - 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
            newX++;
            newY--;
        }
        // Checks diagonally down and right
        newX = x + 1;
        newY = y + 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, newY)) {
                break;
            }
            newX++;
            newY++;
        }
    }

    // MODIFIES: this
    // EFFECT: updates possible move list with all possible vertical and horizontal positions
    protected void updatePossibleMovesStraight() {
        int currentXCoord = position.getXcoord();
        int currentYCoord = position.getYcoord();
        int newX = currentXCoord - 1;
        int newY = currentYCoord;
        // Checks to the left of piece
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, currentYCoord)) {
                break;
            }
            newX--;
        }
        // Checks to the right of piece
        newX = currentXCoord + 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(newX, currentYCoord)) {
                break;
            }
            newX++;
        }
        // Checks above the piece
        newX = currentXCoord;
        newY = currentYCoord - 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(currentXCoord, newY)) {
                break;
            }
            newY--;
        }
        // Checks below the piece
        newY = currentYCoord + 1;
        while (checkInBounds(newX, newY)) {
            if (makingMoveBasedOnPieceInGivenXandY(currentXCoord, newY)) {
                break;
            }
            newY++;
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
