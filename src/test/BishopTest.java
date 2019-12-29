package test;

import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;
import main.model.pieces.Bishop;
import main.model.pieces.Pawn;
import main.model.pieces.PieceName;
import org.jcp.xml.dsig.internal.dom.ApacheOctetStreamData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest extends ChessPieceTest{
    private Bishop bishop;

    @BeforeEach
    public void setUp() {
//        Board board = new Board();
//        board.makeBoard();
        bishop = new Bishop(3, 3, 0, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(3, bishop.getPosition().getXcoord());
        assertEquals(3, bishop.getPosition().getYcoord());
        assertEquals(0, bishop.getTeam());
        assertEquals(PieceName.BISHOP, bishop.getPieceID());
        assertEquals(new Position(3, 3), bishop.getPosition());
    }

    @Test
    public void updatePossibleMovesTest() {
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(13, moves.size());
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(0, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(0, 6)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }

    @Test
    public void updatePossibleMovesDifferentAmountTest() {
//        Board board = new Board();
//        board.makeBoard();
        bishop = new Bishop(3, 1, 1, board);
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(9, moves.size());
        assertTrue(moves.contains(new Position(2, 0)));
        assertTrue(moves.contains(new Position(4, 0)));

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(0, 4)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 3)));
        assertTrue(moves.contains(new Position(6, 4)));
        assertTrue(moves.contains(new Position(7, 5)));
    }

    @Test
    public void updatePossibleMovesBlockedBy4Ally() {
        board.placePiece(new Pawn(2, 2, 0, board));
        board.placePiece(new Pawn(4, 4, 0, board));
        board.placePiece(new Pawn(2, 4, 0, board));
        board.placePiece(new Pawn(4, 2, 0, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesBlockedBy3Ally() {
        board.placePiece(new Pawn(2, 2, 0, board));
        board.placePiece(new Pawn(2, 4, 0, board));
        board.placePiece(new Pawn(4, 2, 0, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }

    @Test
    public void updatePossibleMovesBlockedBy2Ally() {
        board.placePiece(new Pawn(2, 2, 0, board));
        board.placePiece(new Pawn(4, 2, 0, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(7, moves.size());

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(0, 6)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }

    @Test
    public void updatePossibleMovesBlockedBy1Ally() {
        board.placePiece(new Pawn(2, 2, 0, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(10, moves.size());

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(0, 6)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }

    @Test
    public void updatePossibleMovesBlockedBy4Enemy() {
        board.placePiece(new Pawn(1, 1, 1, board));
        board.placePiece(new Pawn(5, 5, 1, board));
        board.placePiece(new Pawn(1, 5, 1, board));
        board.placePiece(new Pawn(5, 1, 1, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(8, moves.size());

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 1)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
    }

    @Test
    public void updatePossibleMovesBlockedBy3Enemy() {
        board.placePiece(new Pawn(5, 5, 1, board));
        board.placePiece(new Pawn(1, 5, 1, board));
        board.placePiece(new Pawn(5, 1, 1, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(9, moves.size());

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(0, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
    }

    @Test
    public void updatePossibleMovesBlockedBy2Enemy() {
        board.placePiece(new Pawn(5, 5, 1, board));
        board.placePiece(new Pawn(1, 5, 1, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(10, moves.size());

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(0, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
    }

    @Test
    public void updatePossibleMovesBlockedBy1Enemy() {
        board.placePiece(new Pawn(1, 5, 1, board));
        bishop.updatePossibleMoves();
        List<Position> moves = bishop.getPossibleMoves();
        assertEquals(12, moves.size());

        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(0, 0)));

        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(1, 5)));

        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 1)));
        assertTrue(moves.contains(new Position(6, 0)));

        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(6, 6)));
        assertTrue(moves.contains(new Position(7, 7)));
    }
}
