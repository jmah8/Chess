package main.model;

import main.model.pieces.ChessPiece;
import main.ui.Chess;

import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class EventHistory implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventHistory)) return false;
        EventHistory that = (EventHistory) o;
        return Objects.equals(pieceMoved, that.pieceMoved) &&
                Objects.equals(pieceEaten, that.pieceEaten) &&
                Objects.equals(oldPosition, that.oldPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceMoved, pieceEaten, oldPosition);
    }
}