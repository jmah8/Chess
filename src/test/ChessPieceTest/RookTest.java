package test.ChessPieceTest;

import main.model.Position;
import main.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest extends ChessPieceTest{
    private ChessPiece rook;

    @BeforeEach
    public void setUp() {
//        Board board = new Board();
//        board.makeBoard();
        rook = new Rook(7, 7, 0, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(7, rook.getPosition().getXcoord());
        assertEquals(7, rook.getPosition().getYcoord());
        assertEquals(0, rook.getTeam());
        assertEquals(PieceName.ROOK, rook.getPieceID());
    }

    @Test
    public void updatePossibleMovesTest() {
//        Board board = new Board();
//        board.makeBoard();
        rook = new Rook(5, 2, 0, board);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(14, moves.size());
        assertTrue(moves.contains(new Position(0, 2)));
        assertTrue(moves.contains(new Position(1, 2)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(3, 2)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(6, 2)));
        assertTrue(moves.contains(new Position(7, 2)));

        assertTrue(moves.contains(new Position(5, 0)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(5, 3)));
        assertTrue(moves.contains(new Position(5, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(5, 6)));
        assertTrue(moves.contains(new Position(5, 7)));
    }

    @Test
    public void updatePossibleMoveTestBuggedScenario() {
        rook = new Rook(5, 2, 0, board);
        board.placePiece(new Horse(5, 7, 0, board));
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(13, moves.size());
        assertFalse(moves.contains(new Position(5, 7)));
        assertTrue(moves.contains(new Position(5, 3)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy4Allies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece = new Queen(1, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 0, board);
        ChessPiece piece2 = new Horse(2, 2, 0, board);
        ChessPiece piece3 = new Bishop(2, 4, 0, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMoveTestBlockedBy3Allies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 0, board);
        ChessPiece piece2 = new Horse(2, 2, 0, board);
        ChessPiece piece3 = new Bishop(2, 4, 0, board);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy2Allies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 0, board);
        ChessPiece piece3 = new Bishop(2, 4, 0, board);
        board.placePiece(piece1);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(5, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));
        assertTrue(moves.contains(new Position(2, 0)));
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(2, 2)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy1Allies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 0, board);
        board.placePiece(piece1);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(9, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));

        assertTrue(moves.contains(new Position(2, 0)));
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(2, 2)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(2, 5)));
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(2, 7)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy4Enemies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece = new Queen(1, 3, 1, board);
        ChessPiece piece1 = new Rook(3, 3, 1, board);
        ChessPiece piece2 = new Horse(2, 2, 1, board);
        ChessPiece piece3 = new Bishop(2, 4, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(2, 4)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy3Enemies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 1, board);
        ChessPiece piece2 = new Horse(2, 2, 1, board);
        ChessPiece piece3 = new Bishop(2, 4, 1, board);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(5, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(2, 4)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy2Enemies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 1, board);
        ChessPiece piece3 = new Bishop(2, 4, 1, board);
        board.placePiece(piece1);
        board.placePiece(piece3);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));

        assertTrue(moves.contains(new Position(3, 3)));

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(2, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
    }

    @Test
    public void updatePossibleMoveTestBlockedBy1Enemies() {
        rook = new Rook(2, 3, 0, board);
        ChessPiece piece1 = new Rook(3, 3, 1, board);
        board.placePiece(piece1);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(10, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 3)));

        assertTrue(moves.contains(new Position(3, 3)));

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(2, 1)));
        assertTrue(moves.contains(new Position(2, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(2, 5)));
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(2, 7)));
    }
}
