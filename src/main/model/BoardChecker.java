package main.model;

import main.model.pieces.ChessPiece;
import main.model.pieces.PieceName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardChecker implements Serializable {
    private final Board board;

    public BoardChecker(Board board) {
        this.board = board;
    }

    /**
     * Return list of rooks that are on the same team as @param king
     *
     * @param king piece that is the same team as rooks to find
     * @return list of rooks that are on same team as @param king
     */
    public ArrayList<ChessPiece> searchForRooks(ChessPiece king) {
        ArrayList<ChessPiece> rooks = new ArrayList<ChessPiece>(2);
        int index = 0;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                ChessPiece pieceAtPos = board.getBoard()[i][j];
                if (pieceAtPos.getTeam() == king.getTeam() && pieceAtPos.getPieceID() == PieceName.ROOK) {
                    rooks.add(index, pieceAtPos);
                    index++;
                }
                if (index == 2)
                    return rooks;
            }
        }
        return rooks;
    }

    /**
     * Returns true if castle is possible for @param king, else false
     *
     * @param king piece to check if castling is possible
     * @return Returns true if castle is possible for @param king, else false
     */
    public boolean checkIfCastlingPossible(ChessPiece king) {
        if (king.getHasMoved() == true)
            return false;
        ArrayList<ChessPiece> rooks = searchForRooks(king);
        if (rooks.isEmpty())
            return false;
        for (ChessPiece piece : rooks) {
            int xPos = piece.getX();
            int yPos = piece.getY();
            int iter;
            // If rook.x - king.x is negative, the rook is to the left
            if (xPos - king.getX() < 0)
                iter = 1;
                // Else if its positive, rook is to the right
            else
                iter = -1;
            xPos += iter;
            int kingXPos = king.getX();
            while (xPos != kingXPos) {
                ChessPiece pieceToCheck = board.getPiece(xPos, yPos);
                if (pieceToCheck.getPieceID() != PieceName.EMPTY)
                    return false;
                xPos += iter;
            }
        }
        return true;
    }// TODO: see if i need to use delegation to keep cohesion low

    // EFFECT: returns true if black piece has a possible move to eat white king, else false
    public boolean checkIfCheckOccurringForWhiteKing() {
        return checkIfPositionCanBeReachedByAnyPiece(getPosKingWhiteTeam(), 1);
    }// EFFECT: returns true if white piece has a possible move to eat black king, else false

    public boolean checkIfCheckOccurringForBlackKing() {
        return checkIfPositionCanBeReachedByAnyPiece(getPosKingBlackTeam(), 0);
    }// EFFECT: returns true if piecePosition can be reached by piece of team teamNumber, false othewise

    public Boolean checkIfPositionCanBeReachedByAnyPiece(Position piecePosition, int teamNumber) {
        Boolean check = false;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                ChessPiece pieceAtPos = board.getBoard()[i][j];
                if (checkIfPieceAtPosCanMoveToPosition(piecePosition, pieceAtPos, teamNumber)) {
                    check = true;
                }
            }
        }
        return check;
    }// REQUIRES: updatePossibleMove be called on pieceAtPos before calling this method

    // EFFECT: returns true if pieceAtPos can move to position and is of team teamNumber
    public boolean checkIfPieceAtPosCanMoveToPosition(Position position, ChessPiece pieceAtPos, int teamNumber) {
        pieceAtPos.updatePossibleMoves();
        if (!pieceAtPos.getPieceID().equals(PieceName.EMPTY) && pieceAtPos.getTeam() == teamNumber) {
            List<Position> possibleMoves = pieceAtPos.getPossibleMoves();
            if (possibleMoves.contains(position)) {
                return true;
            }
        }
        return false;
    }// EFFECT: returns white pieces checking black king

    public List<ChessPiece> getCheckingPiecesForBlackKing() {
        return getPiecesThatCanMoveToPosition(getPosKingBlackTeam(), 0);
    }// EFFECT: returns black piece checking white king

    public List<ChessPiece> getCheckingPiecesForWhiteKing() {
        return getPiecesThatCanMoveToPosition(getPosKingWhiteTeam(), 1);
    }// EFFECT: return a list of pieces of team teamColourOfEnemy that have a possible move to position

    List<ChessPiece> getPiecesThatCanMoveToPosition(Position position, int teamColourOfEnemy) {
        List<ChessPiece> piecesWithPossibleMoveToPos = new ArrayList<ChessPiece>();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece pieceAtPos = board.getBoard()[i][j];
                int teamColour = pieceAtPos.getTeam();
                if (teamColour == teamColourOfEnemy && checkIfPieceAtPosCanMoveToPosition(position, pieceAtPos, teamColour)) {
                    piecesWithPossibleMoveToPos.add(board.getBoard()[i][j]);
                }
            }
        }
        return piecesWithPossibleMoveToPos;
    }// EFFECT: returns position of black king

    public Position getPosKingBlackTeam() {
        return getKingPos(1);
    }// EFFECT: returns position of white king

    public Position getPosKingWhiteTeam() {
        return getKingPos(0);
    }

    /**
     * Return position of king with team number of @param
     *
     * @param teamNumber king's team number we are finding
     * @return pos of king with teamNumber @param
     */
    Position getKingPos(int teamNumber) {
        Position p = null;
        try {
            p = getKingOfTeam(teamNumber).getPosition();
        } catch (NullPointerException e) {
            // Do nothing;
        }
        return p;
    }// EFFECT: returns King from the team teamNumber

    ChessPiece getKingOfTeam(int teamNumber) {
        ChessPiece king = null;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece piece = board.getBoard()[i][j];
                if (piece.getPieceID().equals(PieceName.KING) && piece.getTeam() == teamNumber) {
                    king = piece;
                    break;
                }
            }
        }
        return king;
    }// EFFECT: returns true if white king is checked an has no more possible moves

    public boolean gameOverForWhiteKing() {
        return checkGameOverForKing(0, checkIfCheckOccurringForWhiteKing());
    }// EFFECT: returns true if black king is checked an has no more possible moves

    public boolean gameOverForBlackKing() {
        return checkGameOverForKing(1, checkIfCheckOccurringForBlackKing());
    }

    /**
     * Checks if game is over for team with @param teamNumber
     *
     * @param teamNumber      teamNumber of team to check
     * @param isCheckOccuring true if piece of opposite @param teamNumber is checking king
     * @return true if king of teamNumber @param teamNumber is checked an has no more possible moves
     * else false
     */
    boolean checkGameOverForKing(int teamNumber, boolean isCheckOccuring) {
        ChessPiece king = getKingOfTeam(teamNumber);
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        boolean gameOver = false;
        if (isCheckOccuring && moves.size() == 0) {
            gameOver = true;
        }
        return gameOver;
    }
}