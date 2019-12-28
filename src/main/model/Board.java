package main.model;

import javafx.geometry.Pos;
import main.model.pieces.*;

import java.util.List;

public class Board {
    private ChessPiece[][] board;

    public Board() {
        board = new ChessPiece[8][8];
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public ChessPiece getPiece(int xcoord, int ycoord) {
        return board[ycoord][xcoord];
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

    public void placePiece(ChessPiece piece, int xPos, int yPos) {
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


    // MODIFIES: this
    // EFFECT: moves pieceAtPosition to moveToPosition. If another piece is already there from other team
    // eat the piece
    public boolean movePiece(ChessPiece pieceAtPosition, Position moveToPosition) {
//        int x = pieceAtPosition.getXcoord();
//        int y = pieceAtPosition.getYcoord();
//        ChessPiece piece = board[y][x];
        int xCoord = pieceAtPosition.getPosition().getXcoord();
        int yCoord = pieceAtPosition.getPosition().getYcoord();
        int xNew = moveToPosition.getXcoord();
        int yNew = moveToPosition.getYcoord();
        List<Position> pieceMoves = pieceAtPosition.getPossibleMoves();
        if (pieceMoves.contains(moveToPosition)) {
            board[yNew][xNew] = pieceAtPosition;
            board[yCoord][xCoord] = new EmptyPiece(xCoord, yCoord, this);
            pieceAtPosition.setPosition(moveToPosition);
            return true;
        } else {
            return false;
        }
    }

    // TODO: make it so that it checks if any piece has a possible move on the opposite teams king and return true if yes
    public boolean seeIfCheckOccurringForWhiteKing() {
        Position kingTeamWhite = checkPosKingWhiteTeam();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                ChessPiece pieceAtPos = board[i][j];
                if (!pieceAtPos.getPieceID().equals(PieceName.EMPTY) && pieceAtPos.getTeam() == 1) {
                    List<Position> possibleMoves = pieceAtPos.getPossibleMoves();
                    if (possibleMoves.contains(kingTeamWhite)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean seeIfCheckOccurringForBlackKing() {
        Position kingTeamBlack = checkPosKingBlackTeam();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                ChessPiece pieceAtPos = board[i][j];
                if (!pieceAtPos.getPieceID().equals(PieceName.EMPTY) && pieceAtPos.getTeam() == 0) {
                    List<Position> possibleMoves = pieceAtPos.getPossibleMoves();
                    if (possibleMoves.contains(kingTeamBlack)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // EFFECT: returns position of black king
    public Position checkPosKingBlackTeam() {
        Position kingPosition = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece.getPieceID().equals(PieceName.KING) && piece.getTeam() == 1) {
                    kingPosition = piece.getPosition();
                    break;
                }
            }
        }
        return kingPosition;
    }

    // EFFECT: returns position of white king
    public Position checkPosKingWhiteTeam() {
        Position kingPosition = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece.getPieceID().equals(PieceName.KING) && piece.getTeam() == 0) {
                    kingPosition = piece.getPosition();
                    break;
                }
            }
        }
        return kingPosition;
    }

//    public boolean eatPiece(Position moveToPosition) {
//        int x = moveToPosition.getXcoord();
//        int y = moveToPosition.getYcoord();
//        ChessPiece piece = board[y][x];
//        if (piece.getPieceID() != PieceName.EMPTY) {
//
//        }
//        return false;
//    }
}
