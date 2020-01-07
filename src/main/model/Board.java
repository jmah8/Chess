package main.model;

import main.model.pieces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Board extends Observable implements Serializable{
    private ChessPiece[][] board;
//    private List<EventHistory> history;
    private EventLog eventLog;

    public Board() {
        board = new ChessPiece[8][8];
//        history = new ArrayList<>();
        eventLog = new EventLog();
        addObserver(eventLog);
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
        int boardIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
//                // TODO: leave this in if you want pieces to have different team values
//                if (boardIndex % 2 == 0) {
//                    board[i][j] = new EmptyPiece(i, j, 0);
//                } else {
//                    board[i][j] = new EmptyPiece(i, j, 1);
//                }
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
            board[i][3] = new King(3, i, teamColour, this);
            board[i][4] = new Queen(4, i, teamColour, this);
            board[i][5] = new Bishop(5, i, teamColour, this);
            board[i][6] = new Horse(6, i, teamColour, this);
            board[i][7] = new Rook(7, i, teamColour, this);
            teamColour = 0;
        }
    }

    // MODIFIES: this
    // EFFECT: places piece at piece's position
    public void placePiece(ChessPiece piece) {
        int xPos = piece.getPosition().getXcoord();
        int yPos = piece.getPosition().getYcoord();
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

    public void movePieceIrregardlessOfPossibleMove(ChessPiece pieceAtPosition, Position moveToPosition) {
        int xCoord = pieceAtPosition.getPosition().getXcoord();
        int yCoord = pieceAtPosition.getPosition().getYcoord();
        int xNew = moveToPosition.getXcoord();
        int yNew = moveToPosition.getYcoord();
        if (!pieceAtPosition.getPosition().equals(moveToPosition)) {
            board[yNew][xNew] = pieceAtPosition;
            board[yCoord][xCoord] = new EmptyPiece(xCoord, yCoord, this);
            pieceAtPosition.movePiece(moveToPosition);
        }
    }

    // TODO: add javas observable update method
    // MODIFIES: this
    // EFFECT: moves pieceAtPosition to moveToPosition. If another piece is already there from other team
    // eat the piece
    public boolean movePiece(ChessPiece pieceAtPosition, Position moveToPosition) {
        int xCoord = pieceAtPosition.getPosition().getXcoord();
        int yCoord = pieceAtPosition.getPosition().getYcoord();
        int xNew = moveToPosition.getXcoord();
        int yNew = moveToPosition.getYcoord();
        ChessPiece eatenPiece = getPiece(xNew, yNew);
        pieceAtPosition.updatePossibleMoves();
        List<Position> pieceMoves = pieceAtPosition.getPossibleMoves();
        if (pieceMoves.contains(moveToPosition) && !pieceAtPosition.getPosition().equals(moveToPosition)) {
            board[yNew][xNew] = pieceAtPosition;
            board[yCoord][xCoord] = new EmptyPiece(xCoord, yCoord, this);
            pieceAtPosition.movePosition(moveToPosition);
            // TODO: see if i should include this here or in GUI class. Most likely here as I can use observer pattern easier
            setChanged();
            notifyObservers(new EventHistory(pieceAtPosition, eatenPiece, new Position(xCoord, yCoord)));
//            history.add(new EventHistory(pieceAtPosition, eatenPiece, new Position(xCoord, yCoord)));
            return true;
        } else {
            return false;
        }
    }

    // TODO: test this and movePiece
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

//    public void save() {
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/jonathan/Desktop/Personal Projects/Chess/data/Board.txt"));
//            out.writeObject(this);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void load() {
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/jonathan/Desktop/Personal Projects/Chess/data/Board.txt"));
//            Board b = (Board) in.readObject();
//            in.close();
//        } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // TODO: see if i need to use delegation to keep cohesion low
    // EFFECT: returns true if black piece has a possible move to eat white king, else false
    public boolean checkIfCheckOccurringForWhiteKing() {
        return checkIfPositionCanBeReachedByAnyPiece(getPosKingWhiteTeam(), 1);
    }

    // EFFECT: returns true if white piece has a possible move to eat black king, else false
    public boolean checkIfCheckOccurringForBlackKing() {
        return checkIfPositionCanBeReachedByAnyPiece(getPosKingBlackTeam(), 0);
    }

    // EFFECT: returns true if piecePosition can be reached by piece of team teamNumber, false othewise
    public Boolean checkIfPositionCanBeReachedByAnyPiece(Position piecePosition, int teamNumber) {
        Boolean check = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                ChessPiece pieceAtPos = board[i][j];
                if (checkIfPieceAtPosCanMoveToPosition(piecePosition, pieceAtPos, teamNumber)) {
                    check = true;
                }
            }
        }
        return check;
    }

    // REQUIRES: updatePossibleMove be called on pieceAtPos before calling this method
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
    }

    // EFFECT: returns white pieces checking black king
    public List<ChessPiece> getCheckingPiecesForBlackKing() {
        return getPiecesThatCanMoveToPosition(getPosKingBlackTeam(), 0);
    }

    // EFFECT: returns black piece checking white king
    public List<ChessPiece> getCheckingPiecesForWhiteKing() {
        return getPiecesThatCanMoveToPosition(getPosKingWhiteTeam(), 1);
    }

    // EFFECT: return a list of pieces of team teamColourOfEnemy that have a possible move to position
    private List<ChessPiece> getPiecesThatCanMoveToPosition(Position position, int teamColourOfEnemy) {
        List<ChessPiece> piecesWithPossibleMoveToPos = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece pieceAtPos = board[i][j];
                int teamColour = pieceAtPos.getTeam();
                if (teamColour == teamColourOfEnemy && checkIfPieceAtPosCanMoveToPosition(position, pieceAtPos, teamColour)) {
                    piecesWithPossibleMoveToPos.add(board[i][j]);
                }
            }
        }
        return piecesWithPossibleMoveToPos;
    }

    // EFFECT: returns position of black king
    public Position getPosKingBlackTeam() {
        Position p = null;
        try {
            p = getKingOfTeam(1).getPosition();
        } catch (NullPointerException e) {
            // Do nothing;
        }
        return p;
    }

    // EFFECT: returns position of white king
    public Position getPosKingWhiteTeam() {
        Position p = null;
        try {
            p = getKingOfTeam(0).getPosition();
        } catch (NullPointerException e) {
            // Do nothing;
        }
        return p;
    }

    // EFFECT: returns King from the team teamNumber
    private ChessPiece getKingOfTeam(int teamNumber) {
        ChessPiece king = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece.getPieceID().equals(PieceName.KING) && piece.getTeam() == teamNumber) {
                    king = piece;
                    break;
                }
            }
        }
        return king;
    }

    // EFFECT: returns true if white king is checked an has no more possible moves
    public boolean gameOverForWhiteKing() {
        ChessPiece king = getKingOfTeam(0);
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        boolean gameOver = false;
        if (checkIfCheckOccurringForWhiteKing() && moves.size() == 0) {
            gameOver = true;
        }
        return gameOver;
    }

    // EFFECT: returns true if black king is checked an has no more possible moves
    public boolean gameOverForBlackKing() {
        ChessPiece king = getKingOfTeam(1);
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        boolean gameOver = false;
        if (checkIfCheckOccurringForBlackKing() && moves.size() == 0) {
            gameOver = true;
        }
        return gameOver;
    }
}
