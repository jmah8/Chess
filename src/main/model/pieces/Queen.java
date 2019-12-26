package main.model.pieces;

public class Queen extends ChessPiece {

    public Queen(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.QUEEN;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible diagonal and straight moves
    public void updatePossibleMoves() {
        updatePossibleMovesStraight();
        updatePossibleMovesDiagonal();
    }
}
