package main.model.pieces;

public class Rook extends ChessPiece {

    public Rook(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.ROOK;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible straight moves
    public void updatePossibleMoves() {
        removePossibleMoves();
        updatePossibleMovesStraight();
    }
}
