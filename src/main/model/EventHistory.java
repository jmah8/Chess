package main.model;

import javafx.geometry.Pos;
import main.model.pieces.ChessPiece;
import main.ui.Chess;

import java.util.Observable;
import java.util.Observer;

public class EventHistory {
    private ChessPiece pieceMoved;
    private ChessPiece pieceEaten;
    private Position oldPosition;

    public EventHistory(ChessPiece pieceMoved, ChessPiece pieceEaten, Position oldPosition) {
        this.pieceMoved = pieceMoved;
        this.pieceEaten = pieceEaten;
        this.oldPosition = oldPosition;
    }

    public ChessPiece getPieceMoved() {
        return pieceMoved;
    }

    public ChessPiece getPieceEaten() {
        return pieceEaten;
    }

    public Position getOldPosition() {
        return oldPosition;
    }


}