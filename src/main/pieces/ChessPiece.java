package main.pieces;

import main.Position;

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

    public abstract void updatePossibleMoves();

    public void movePiece() {

    }
}
