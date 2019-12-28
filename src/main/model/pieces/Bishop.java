package main.model.pieces;

public class Bishop extends ChessPiece {

    public Bishop(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.BISHOP;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible diagonal positions
    public void updatePossibleMoves() {
        removePossibleMoves();
        updatePossibleMovesDiagonal();
    }
}
