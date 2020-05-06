package main.model.pieces;

import main.model.Board;
import main.model.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

//    public King(int xcoord, int ycoord, int team) {
//        super(xcoord, ycoord, team);
//        pieceID = PieceName.KING;
//    }

    public King(int xcoord, int ycoord, int team, Board board) {
        super(xcoord, ycoord, team, board);
        board.getPiecesAlive().add(this);
        pieceID = PieceName.KING;
        if (team == 1)
            if (xcoord != 3 && ycoord != 0)
                hasMoved = true;
        if (team == 0)
            if (xcoord != 3 && ycoord != 7)
                hasMoved = true;
    }

    @Override
    // TODO: bug where rooks/queen/bishops possible moves stop at the enemy, so the king could move 1 space away
    //  from them and be in a place where it isnt a possible move
    // MODIFIES: this
    // EFFECT: updates possible move list with all possible 1 space move
    public void updatePossibleMoves() {
        removePossibleMoves();
        int x = position.getXcoord();
        int y = position.getYcoord();
        // Move left
        if (x - 1 >= 0) {
            moveOneSpace(y, x - 1);
        }
        // Move right
        if (x + 1 <= 7) {
            moveOneSpace(y, x + 1);
        }
        // Move up
        if (y - 1 >= 0) {
            moveOneSpace(y - 1, x);
        }
        // Move down
        if (y + 1 <= 7) {
            moveOneSpace(y + 1, x);
        }
        // Move down and right
        if (x + 1 <= 7 && y + 1 <= 7) {
            moveOneSpace(y + 1, x + 1);
        }
        // Move up and right
        if (x + 1 <= 7 && y - 1 >= 0) {
            moveOneSpace(y - 1, x + 1);
        }
        // Move down and left
        if (x - 1 >= 0 && y + 1 <= 7) {
            moveOneSpace(y + 1, x - 1);
        }
        // Move up and left
        if (x - 1 >= 0 && y - 1 >= 0) {
            moveOneSpace(y - 1, x - 1);
        }
        if (!hasMoved)
            castle();
    }

    /**
     * Requires king and atleast one rook that hasn't moved yet. Moves king and rook at the same time
     */
    private void castle() {
        if (!board.checkIfCastlingPossible(this))
            return;
        ArrayList<ChessPiece> rooks = board.searchForRooks(this);
        if (rooks.size() == 2) {
            possibleMoves.add(new Position(position.getXcoord() - 2, position.getYcoord()));
            possibleMoves.add(new Position(position.getXcoord() + 2, position.getYcoord()));
            // Rook to the left of king
        } else if (rooks.get(0).getX() - position.getXcoord() < 0) {
            possibleMoves.add(new Position(position.getXcoord() - 2, position.getYcoord()));
            // Rook to the right of king
        } else {
            possibleMoves.add(new Position(position.getXcoord() + 2, position.getYcoord()));
        }
    }

    // MODIFIES: this
    // EFFECT: add the move of y and x to possible move
    private void moveOneSpace(int y, int x) {
        ChessPiece pieceAtPosXY = board.getPiece(x, y);
        Position positionToMoveTo = new Position(x, y);
        if (team == 1) {
            if (!checkSameTeam(pieceAtPosXY) &&
                    checkIfPossibleMove(positionToMoveTo)) {
                possibleMoves.add(positionToMoveTo);
            }
        } else {
            if (!checkSameTeam(pieceAtPosXY) &&
                    checkIfPossibleMove(positionToMoveTo)) {
                possibleMoves.add(positionToMoveTo);
            }
        }
    }

    // TODO: can add genrec to solve first bug where king can still eat piece and get into check position
    // TODO: bug where if there is WKing, BQueen, BRook from left to right, the King is still allowed to eat the Queen eventhough the move will make a check
    // TODO: bug where the White King specifically still has moves unaffected by the Black Queen/Bishop/Rook
    // TODO: bug maybe is because of the multiple if statements but not if/else if i only return possible moves. Since it could go to the pawn if statement,
    //  change it to true, then go to the possiblemoves.contains if statement which makes it false
    // TODO: bug maybe is also because removing the piece by placing a new empty piece and somehow we didn't replace it with the king
    // EFFECT: returns true if the move is possible for king with no check occurring after moving
    private boolean checkIfPossibleMoveForKing(Position position, int teamNumberOfEnemy) {
        boolean possibleMove = true;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece piece = board.getBoard()[i][j];
                // Check if piece is on the enemy team and not an empty piece
                if (piece.getTeam() == teamNumberOfEnemy && !piece.getPieceID().equals(PieceName.EMPTY)) {
                    // Check if piece is the enemy king
                    if (piece.getPieceID().equals(PieceName.KING) && !piece.equals(this)) {
                        // If the piece is an enemy king then check if the position this is moving to is safe
                        // from enemy king. If not safe return false.
                        if (checkOtherKingsPossibleMove(piece, position)) {
                            possibleMove = false;
                            break;
                        }
                    } else if (!piece.getPieceID().equals(PieceName.KING)) {
                        // Check if pawn can eat king
                        if (piece.getPieceID().equals(PieceName.PAWN)) {
                            if (checkIfPositionSameAsPawnEatingDiagonal(position, piece)) {
                                possibleMove = false;
                                break;
                            }
                        } else {
                            piece.updatePossibleMoves();
                            List<Position> possibleMoves = piece.getPossibleMoves();
                            // Check if enemy piece has same possible move as what king is moving to
                            if (possibleMoves.contains(position)) {
                                possibleMove = false;
                                break;
                            } else if (possibleMoves.contains(this.position)) {
                                Position oldPosition = this.position;
                                int x = position.getXcoord();
                                int y = position.getYcoord();
                                // Simulates king move and see if piece has possible move to eat king
                                ChessPiece pieceAtNewPosition = board.getPiece(x, y);
                                board.movePieceIrregardlessOfPossibleMove(this, position);
                                piece.updatePossibleMoves();
                                possibleMoves = piece.getPossibleMoves();
                                board.movePieceIrregardlessOfPossibleMove(this, oldPosition);
                                board.placePiece(pieceAtNewPosition);
                                if (possibleMoves.contains(position)) {
                                    possibleMove = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return possibleMove;
    }

    // TODO: could maybe use the reverse move to redo move
    // TODO: can change this to resemble CPSC 110 genrec
    // TODO: for everyother piece updatePossibleMoves, call this after to check if king is checked
    // Must have the board.placePiece(pieceRemoved) since when testing if move is valid, we remove piece
    // and so need to place it back on the board
    private boolean checkIfPossibleMove(Position pos) {
        boolean possibleMove;
        Position oldPos = this.getPosition();
        ChessPiece pieceRemoved = board.getPiece(pos.getXcoord(), pos.getYcoord());
        board.movePieceIrregardlessOfPossibleMove(this, pos);
        possibleMove = updateEnemyMoves(pos);
        board.movePieceIrregardlessOfPossibleMove(this, oldPos);
        board.placePiece(pieceRemoved);
        return possibleMove;
    }

    private boolean updateEnemyMoves(Position pos) {
        boolean possibleMove = true;
        List<Position> possibleMoves;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
//                ChessPiece piece = board.getPiece(j, i);
                ChessPiece piece = board.getBoard()[i][j];
                if (this.team != piece.team) {
                    switch (piece.pieceID) {
                        case EMPTY:
                            break;
                         // TODO: pawn case might be unnecessary
//                        case PAWN:
//                            possibleMove = !checkIfPositionSameAsPawnEatingDiagonal(pos, piece);
//                            break;
                        case KING:
                            possibleMove = !checkOtherKingsPossibleMove(piece, pos);
                            break;
                        default:
                            piece.updatePossibleMoves();
                            possibleMoves = piece.getPossibleMoves();
                            possibleMove = !possibleMoves.contains(pos);
                            break;
                    }
                }
                if (!possibleMove)
                    return possibleMove;
            }
        }
        return possibleMove;
    }

    // EFFECT: returns true if piece (only pawn) has a possible move diagonal eating king in process, else return false
    private boolean checkIfPositionSameAsPawnEatingDiagonal(Position position, ChessPiece piece) {
        int pawnXCoord = piece.getX();
        int pawnYCoord = piece.getY();
        Position pawnNewPosition;
        Position pawnNewPosition1;
        if (piece.getTeam() == 1) {
            pawnNewPosition = new Position(pawnXCoord + 1, pawnYCoord + 1);
            pawnNewPosition1 = new Position(pawnXCoord - 1, pawnYCoord + 1);
        } else {
            pawnNewPosition = new Position(pawnXCoord + 1, pawnYCoord - 1);
            pawnNewPosition1 = new Position(pawnXCoord - 1, pawnYCoord - 1);
        }
        return (position.equals(pawnNewPosition) || position.equals(pawnNewPosition1));
    }

    // TODO: could get rid of position param and check in every if block, but maybe same asymptotic time complexity
    // EFFECT: returns true if otherKing has a possible move (irregardless of other pieces around) at position
    private boolean checkOtherKingsPossibleMove(ChessPiece otherKing, Position position) {
        Position otherKingPos = otherKing.getPosition();
        int x = otherKingPos.getXcoord();
        int y = otherKingPos.getYcoord();
        List<Position> possibleMovesByOtherKing = new ArrayList<>();
        // Move left
        if (checkInBounds(x - 1, y)) {
            possibleMovesByOtherKing.add(new Position(x - 1, y));
        }
        // Move right
        if (checkInBounds(x + 1, y)) {
            possibleMovesByOtherKing.add(new Position(x + 1, y));
        }
        // Move up
        if (checkInBounds(x, y - 1)) {
            possibleMovesByOtherKing.add(new Position(x, y - 1));
        }
        // Move down
        if (checkInBounds(x, y + 1)) {
            possibleMovesByOtherKing.add(new Position(x, y + 1));
        }
        // Move down and right
        if (checkInBounds(x + 1, y + 1)) {
            possibleMovesByOtherKing.add(new Position(x + 1, y + 1));
        }
        // Move up and right
        if (checkInBounds(x + 1, y - 1)) {
            possibleMovesByOtherKing.add(new Position(x + 1, y - 1));
        }
        // Move down and left
        if (checkInBounds(x - 1, y + 1)) {
            possibleMovesByOtherKing.add(new Position(x - 1, y + 1));
        }
        // Move up and left
        if (checkInBounds(x - 1, y - 1)) {
            possibleMovesByOtherKing.add(new Position(x - 1, y - 1));
        }
        return possibleMovesByOtherKing.contains(position);
    }
}
