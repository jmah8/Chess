package main.model.pieces;

import main.model.Position;

public class Horse extends ChessPiece {

    public Horse(int xcoord, int ycoord, int team) {
        super(xcoord, ycoord, team);
        pieceID = PieceName.HORSE;
    }

    @Override
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible L moves
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        int nextXCoord = x - 2;
        int nextYCoord = y - 1;
        if (nextXCoord >= 0 && nextYCoord >= 0 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextYCoord = y + 1;
        if (nextXCoord >= 0 && nextYCoord <= 7 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextXCoord = x + 2;
        if (nextXCoord <= 7 && nextYCoord <= 7 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextYCoord = y - 1;
        if (x + 2 <= 7 && nextYCoord >= 0 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }

        nextXCoord = x - 1;
        nextYCoord = y - 2;
        if (nextXCoord >= 0 && nextYCoord >= 0 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextYCoord = y + 2;
        if (nextXCoord >= 0 && nextYCoord <= 7 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextXCoord = x + 1;
        if (nextXCoord <= 7 && nextYCoord <= 7 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
        nextYCoord = y - 2;
        if (nextXCoord <= 7 && nextYCoord >= 0 && checkForEmptyOrEnemy(nextXCoord, nextYCoord)) {
            possibleMoves.add(new Position(nextXCoord, nextYCoord));
        }
    }

    private boolean checkForEmptyOrEnemy(int x, int y) {
        ChessPiece piece = board.getPiece(x, y);
        return piece.checkIfNoTeam() || checkOppositeTeam(piece);
    }
}
