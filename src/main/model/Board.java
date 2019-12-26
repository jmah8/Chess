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
                board[i][j] = new EmptyPiece(i, j);
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
                board[i][j] = new Pawn(i, j, teamColour);
            }
            teamColour = 0;
        }
        teamColour = 1;
        for (int i = 0; i < 8; i += 7) {
            board[i][0] = new Rook(i,0, teamColour);
            board[i][1] = new Horse(i,0, teamColour);
            board[i][2] = new Bishop(i,0, teamColour);
            board[i][3] = new King(i,0, teamColour);
            board[i][4] = new Queen(i,0, teamColour);
            board[i][5] = new Bishop(i,0, teamColour);
            board[i][6] = new Horse(i,0, teamColour);
            board[i][7] = new Rook(i,0, teamColour);
            teamColour = 0;
        }
    }

//    public boolean eatPiece(ChessPiece movingPiece, ChessPiece eatenPiece) {
//        Position movingPosition = movingPiece.getPosition();
//        Position eatenPosition = eatenPiece.getPosition();
//        List<Position> movingMoves = movingPiece.getPossibleMoves();
//        List<Position> eatenMoves = eatenPiece.getPossibleMoves();
//        int i = position.getYcoord();
//        int j = position.getXcoord();
//        return false;
//    }

    // TODO: see if pieceAtPosition is better as position or as a piece (type of parameter)
    // MODIFIES: this
    //
    public boolean movePiece(Position pieceAtPosition, Position moveToPosition) {
        int x = pieceAtPosition.getXcoord();
        int y = pieceAtPosition.getYcoord();
        ChessPiece piece = board[y][x];
        List<Position> pieceMoves = piece.getPossibleMoves();
        if (pieceMoves.contains(moveToPosition)) {
            piece.setPosition(moveToPosition);
            board[y][x] = new EmptyPiece(x, y);
            return true;
        } else {
            return false;
        }
    }

    // TODO: maybe make this for rooks/bishops/queen where not all squares are lighting up
    public boolean eatPiece(Position moveToPosition) {
        int x = moveToPosition.getXcoord();
        int y = moveToPosition.getYcoord();
        ChessPiece piece = board[y][x];
        if (piece.getPieceID() != PieceName.EMPTY) {

        }
        return false;
    }
}
