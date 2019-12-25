package test;

import javafx.geometry.Pos;
import main.Position;
import main.pieces.Bishop;
import main.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    private Bishop bishop;

    @BeforeEach
    public void setUp() {
        bishop = new Bishop(3, 3, 0);
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
        bishop = new Bishop(3, 1, 1);
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
}
