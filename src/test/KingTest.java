package test;

import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;
import main.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest extends ChessPieceTest{
    private ChessPiece king;

    @BeforeEach
    public void setUp() {
        king = new King(5, 3, 0, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(5, king.getPosition().getXcoord());
        assertEquals(3, king.getPosition().getYcoord());
        assertEquals(0, king.getTeam());
        assertEquals(PieceName.KING, king.getPieceID());
    }

    @Test
    public void updatePossibleMovesTest() {
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(8, moves.size());
        assertTrue(moves.contains(new Position(6, 3)));
        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(5, 4)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(6, 4)));
        assertTrue(moves.contains(new Position(6, 2)));
    }

    @Test
    public void updatePossibleMovesTestEdge() {
        king.setPosition(new Position(6, 7));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(5, moves.size());
        assertTrue(moves.contains(new Position(5, 7)));
        assertTrue(moves.contains(new Position(5, 6)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }

    @Test
    public void updatePossibleMovesBlackKingTestEnemyCanCheckIfMoveForward() {
        king = new King(5, 3, 1, board);
        board.placePiece(new Horse(3, 6, 0, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new Position(6, 3)));
        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(5, 4)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(6, 4)));
        assertTrue(moves.contains(new Position(6, 2)));
    }

    @Test
    public void updatePossibleMovesBlackKingTestEnemyCanOnlyMoveForward() {
        king = new King(5, 3, 1, board);
        board.placePiece(new Rook(3, 2, 0, board));
        board.placePiece(new Rook(4, 0, 0, board));
        board.placePiece(new Rook(6, 1, 0, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(1, moves.size());
        assertTrue(moves.contains(new Position(5, 4)));
    }

    @Test
    public void updatePossibleMovesBlackKingTestNoPossibleMovesSinceCheck() {
        king = new King(5, 3, 1, board);
        board.placePiece(new Queen(3, 2, 0, board));
        board.placePiece(new Rook(4, 0, 0, board));
        board.placePiece(new Rook(6, 1, 0, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesWhiteKingTestEnemyCanCheckIfMoveForward() {
        king = new King(5, 3, 0, board);
        board.placePiece(new Horse(3, 6, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new Position(6, 3)));
        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(5, 4)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(6, 4)));
        assertTrue(moves.contains(new Position(6, 2)));
    }

    @Test
    public void updatePossibleMovesWhiteKingTestEnemyCanOnlyMoveForward() {
        king = new King(5, 3, 0, board);
        board.placePiece(new Rook(3, 2, 1, board));
        board.placePiece(new Rook(4, 0, 1, board));
        board.placePiece(new Rook(6, 1, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(1, moves.size());
        assertTrue(moves.contains(new Position(5, 4)));
    }

    @Test
    public void updatePossibleMovesWhiteKingTestNoPossibleMovesSinceCheck() {
        king = new King(5, 3, 0, board);
        board.placePiece(new Queen(3, 2, 1, board));
        board.placePiece(new Rook(4, 0, 1, board));
        board.placePiece(new Rook(6, 1, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesTestBlackKingMovingInfrontOfPawn() {
        king = new King(4, 3, 1, board);
        board.placePiece(new Pawn(3, 5, 0, board));
        board.placePiece(new Rook(6, 2, 0, board));
        board.placePiece(new Rook(5, 1, 0, board));
        board.placePiece(new Horse(5, 6, 0, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(3, 4)));
    }

    @Test
    public void updatePossibleMovesTestWhiteKingMovingInfrontOfPawn() {
        king = new King(4, 4, 0, board);
        board.placePiece(new Pawn(3, 2, 1, board));
        board.placePiece(new Rook(6, 5, 1, board));
        board.placePiece(new Rook(5, 6, 1, board));
        board.placePiece(new Horse(6, 4, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(3, 4)));
    }

    @Test
    public void updatePossibleMovesTestWhiteKingMovingTowardsBlackKing() {
        king = new King(0, 0, 0, board);
        board.placePiece(new King(2, 0, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(1, moves.size());
        assertTrue(moves.contains(new Position(0, 1)));
    }

    @Test
    public void updatePossibleMovesTestBlackKingMovingTowardsWhiteKing() {
        board.placePiece(new King(5, 5, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(5, moves.size());
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(6, 2)));
        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(6, 3)));
    }

    @Test
    public void updatePossibleMovesMoveRightOfRook() {
        king = new King(3, 0, 0, board);
        board.placePiece(new Rook(0, 0, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(3, 1)));
        assertTrue(moves.contains(new Position(4, 1)));
        assertFalse(moves.contains(new Position(4, 0)));
    }

    @Test
    public void updatePossibleMovesUpperRightOfBishop() {
        king = new King(1, 1, 1, board);
        board.placePiece(new Rook(2, 3, 0, board));
        board.placePiece(new Rook(3, 2, 0, board));
        board.placePiece(new Bishop(3, 3, 0, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(1, 0)));
        assertTrue(moves.contains(new Position(0, 1)));
        assertFalse(moves.contains(new Position(0, 0)));
    }

    @Test
    public void updatePossibleMoveBuggedScenarioForWhiteKingOnly() {
        king = new King(3, 4, 0, board);
        board.placePiece(new Queen(6, 4, 1, board));
        board.placePiece(new Bishop(6, 1, 1, board));
        king.updatePossibleMoves();
        List<Position> moves = king.getPossibleMoves();
        assertEquals(4, moves.size());
        assertFalse(moves.contains(new Position(2, 5)));
        assertFalse(moves.contains(new Position(4, 3)));
        assertFalse(moves.contains(new Position(4, 4)));
        assertFalse(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(2, 3)));
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(3, 5)));
        assertTrue(moves.contains(new Position(4, 5)));
    }
}


