package test;

import main.model.Board;
import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.King;
import main.model.pieces.PieceName;
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
}
