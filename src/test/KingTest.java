package test;

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
        assertEquals(0, moves.size());;
    }
}


