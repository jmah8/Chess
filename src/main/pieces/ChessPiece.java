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
