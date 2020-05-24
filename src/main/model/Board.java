package main.model;

import main.model.pieces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Board extends Observable implements Serializable{
    private final BoardChecker boardChecker = new BoardChecker(this);
    private ChessPiece[][] board;
    private ArrayList<ChessPiece> piecesAlive;
//    private List<EventHistory> history;
    private EventLog eventLog;

    public Board() {
        board = new ChessPiece[8][8];
//        history = new ArrayList<>();
        // TODO: piecesAlive to keep list of alive pieces so can iterate over that list instead of whole board
        piecesAlive = new ArrayList<>();
        eventLog = new EventLog();
        addObserver(eventLog);
    }

    public ArrayList<ChessPiece> getPiecesAlive() {
        return piecesAlive;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public ChessPiece getPiece(int xcoord, int ycoord) {
        return board[ycoord][xcoord];
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    public void setEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }

    // MODIFIES: this
    // EFFECT: fills board with no null value
    public void makeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyPiece(j, i, this);
            }
        }
    }

    // MODIFIES: this
    // EFFECT: places chess pieces on board
    public void fillBoard() {
        int teamColour = 1;
        for (int i = 1; i < 7; i += 5) {
            for (int j = 0; j < 8; j++) {
                // Makes row of black pawns first, than row of white pawns
                board[i][j] = new Pawn(j, i, teamColour, this);
            }
            teamColour = 0;
        }
        teamColour = 1;
        for (int i = 0; i < 8; i += 7) {
            board[i][0] = new Rook(0, i, teamColour, this);
            board[i][1] = new Horse(1, i, teamColour, this);
            board[i][2] = new Bishop(2, i, teamColour, this);
            board[i][3] = new Queen(3, i, teamColour, this);
            board[i][4] = new King(4, i, teamColour, this);
            board[i][5] = new Bishop(5, i, teamColour, this);
            board[i][6] = new Horse(6, i, teamColour, this);
            board[i][7] = new Rook(7, i, teamColour, this);
            teamColour = 0;
        }
    }

    // MODIFIES: this
    // EFFECT: places piece at piece's position
    public void placePiece(ChessPiece piece) {
        int xPos = piece.getX();
        int yPos = piece.getY();
        board[yPos][xPos] = piece;
    }

    // EFFECT: sets the board field for every piece to be this
    public void setBoardFieldForPieces() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece cp = board[i][j];
                cp.setBoard(this);
            }
        }
    }

    // TODO: could also add history for using this move but need to change some test
    public void movePieceIrregardlessOfPossibleMove(ChessPiece pieceAtPosition, Position moveToPosition) {
        int xCoord = pieceAtPosition.getX();
        int yCoord = pieceAtPosition.getY();
        int xNew = moveToPosition.getXcoord();
        int yNew = moveToPosition.getYcoord();
        ChessPiece eatenPiece = getPiece(moveToPosition.getXcoord(), moveToPosition.getYcoord());
        if (!pieceAtPosition.getPosition().equals(moveToPosition)) {
            board[yNew][xNew] = pieceAtPosition;
            board[yCoord][xCoord] = new EmptyPiece(xCoord, yCoord, this);
            pieceAtPosition.movePiece(moveToPosition);
        }
    }

    // MODIFIES: this
    // EFFECT: moves pieceToMove to moveToPosition. If another piece is already there from other team
    // eat the piece
    public void movePiece(ChessPiece pieceToMove, Position moveToPosition) {
        int xCoord = pieceToMove.getX();
        int yCoord = pieceToMove.getY();
        int xNew = moveToPosition.getXcoord();
        int yNew = moveToPosition.getYcoord();
        ChessPiece eatenPiece = getPiece(xNew, yNew);
        pieceToMove.updatePossibleMoves();
        List<Position> pieceMoves = pieceToMove.getPossibleMoves();

        int indicator = moveToPosition.getXcoord() - pieceToMove.getX();

        // If piece is king and position to move to is a 2 point difference, then it must be a castle
        if (pieceToMove.getPieceID() == PieceName.KING &&
                Math.abs(indicator) == 2) {
            // If indicator is negative, castling with rook on left
            if (indicator < 0) {
                ChessPiece leftRook = getPiece(0, pieceToMove.getY());
                movePiece(leftRook, new Position(leftRook.getX() + 3, leftRook.getY()));
            } else {
                ChessPiece rightRook = getPiece(7, pieceToMove.getY());
                movePiece(rightRook, new Position(rightRook.getX() - 2, rightRook.getY()));
            }
        }
        
        if (pieceMoves.contains(moveToPosition) && !pieceToMove.getPosition().equals(moveToPosition)) {
            board[yNew][xNew] = pieceToMove;
            board[yCoord][xCoord] = new EmptyPiece(xCoord, yCoord, this);
            if (eatenPiece.getPieceID() != PieceName.EMPTY)
                piecesAlive.remove(eatenPiece);
            pieceToMove.movePosition(moveToPosition);
            setChanged();
            notifyObservers(new EventHistory(pieceToMove, eatenPiece, new Position(xCoord, yCoord)));
        }
    }

    public void reverseMove() {
        EventHistory eventHistory = eventLog.getLatestEventHistory();
        ChessPiece pieceMoved = eventHistory.getPieceMoved();
        ChessPiece pieceEaten = eventHistory.getPieceEaten();
        Position oldPosition = eventHistory.getOldPosition();
        // TODO: see if i need to use movePiece or movePieceIrregardless
        movePieceIrregardlessOfPossibleMove(pieceMoved, oldPosition);
        placePiece(pieceEaten);
    }

    public boolean containPiece(ChessPiece piece) {
        boolean contains = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(piece)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    /**
     * Return list of rooks that are on the same team as @param king
     *
     * @param king piece that is the same team as rooks to find
     * @return list of rooks that are on same team as @param king
     */
    public ArrayList<ChessPiece> searchForRooks(ChessPiece king) {
        return boardChecker.searchForRooks(king);
    }

    /**
     * Returns true if castle is possible for @param king, else false
     *
     * @param king piece to check if castling is possible
     * @return Returns true if castle is possible for @param king, else false
     */
    public boolean checkIfCastlingPossible(ChessPiece king) {
        return boardChecker.checkIfCastlingPossible(king);
    }

    // TODO: see if i need to use delegation to keep cohesion low
    // EFFECT: returns true if black piece has a possible move to eat white king, else false
    public boolean checkIfCheckOccurringForWhiteKing() {
        return boardChecker.checkIfCheckOccurringForWhiteKing();
    }

    // EFFECT: returns true if white piece has a possible move to eat black king, else false
    public boolean checkIfCheckOccurringForBlackKing() {
        return boardChecker.checkIfCheckOccurringForBlackKing();
    }

    // EFFECT: returns true if piecePosition can be reached by piece of team teamNumber, false othewise
    public Boolean checkIfPositionCanBeReachedByAnyPiece(Position piecePosition, int teamNumber) {
        return boardChecker.checkIfPositionCanBeReachedByAnyPiece(piecePosition, teamNumber);
    }

    // TODO: find out why this causes a bug in the GUI but not the test
//    // EFFECT: returns true if piecePosition can be reached by piece of team teamNumber, false othewise
//    public Boolean checkIfPositionCanBeReachedByAnyPiece(Position piecePosition, int teamNumber) {
//        Boolean check = false;
//        for (int i = 0; i < piecesAlive.size(); i++) {
//            if (checkIfPieceAtPosCanMoveToPosition(piecePosition, piecesAlive.get(i), teamNumber)) {
//                check = true;
//            }
//        }
//        return check;
//    }

    // REQUIRES: updatePossibleMove be called on pieceAtPos before calling this method
    // EFFECT: returns true if pieceAtPos can move to position and is of team teamNumber
    public boolean checkIfPieceAtPosCanMoveToPosition(Position position, ChessPiece pieceAtPos, int teamNumber) {
        return boardChecker.checkIfPieceAtPosCanMoveToPosition(position, pieceAtPos, teamNumber);
    }

    // EFFECT: returns white pieces checking black king
    public List<ChessPiece> getCheckingPiecesForBlackKing() {
        return boardChecker.getCheckingPiecesForBlackKing();
    }

    // EFFECT: returns black piece checking white king
    public List<ChessPiece> getCheckingPiecesForWhiteKing() {
        return boardChecker.getCheckingPiecesForWhiteKing();
    }

    // EFFECT: return a list of pieces of team teamColourOfEnemy that have a possible move to position
    private List<ChessPiece> getPiecesThatCanMoveToPosition(Position position, int teamColourOfEnemy) {
        return boardChecker.getPiecesThatCanMoveToPosition(position, teamColourOfEnemy);
    }

    // EFFECT: returns position of black king
    public Position getPosKingBlackTeam() {
        return boardChecker.getPosKingBlackTeam();
    }

    // EFFECT: returns position of white king
    public Position getPosKingWhiteTeam() {
        return boardChecker.getPosKingWhiteTeam();
    }

    /**
     * Return position of king with team number of @param
     *
     * @param teamNumber king's team number we are finding
     * @return pos of king with teamNumber @param
     */
    private Position getKingPos(int teamNumber) {
        return boardChecker.getKingPos(teamNumber);
    }

    // EFFECT: returns King from the team teamNumber
    private ChessPiece getKingOfTeam(int teamNumber) {
        return boardChecker.getKingOfTeam(teamNumber);
    }

    // EFFECT: returns true if white king is checked an has no more possible moves
    public boolean gameOverForWhiteKing() {
        return boardChecker.gameOverForWhiteKing();
    }

    // EFFECT: returns true if black king is checked an has no more possible moves
    public boolean gameOverForBlackKing() {
        return boardChecker.gameOverForBlackKing();
    }

    /**
     * Checks if game is over for team with @param teamNumber
     *
     * @param teamNumber teamNumber of team to check
     * @param isCheckOccuring true if piece of opposite @param teamNumber is checking king
     * @return true if king of teamNumber @param teamNumber is checked an has no more possible moves
     * else false
     */
    private boolean checkGameOverForKing(int teamNumber, boolean isCheckOccuring) {
        return boardChecker.checkGameOverForKing(teamNumber, isCheckOccuring);
    }
}
