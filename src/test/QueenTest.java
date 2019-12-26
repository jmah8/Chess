package test;

import main.model.Position;
import main.model.pieces.PieceName;
import main.model.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {
    private Queen queen;

    @BeforeEach
    public void setUp() {
        queen = new Queen(3, 4, 1);
    }

    @Test
    public void constructorTest() {
        assertEquals(new Position(3, 4), queen.getPosition());
        assertEquals(1, queen.getTeam());
        assertEquals(PieceName.QUEEN, queen.getPieceID());
    }

    @Test
    public void updatePossibleMovesTest() {
        queen.updatePossibleMoves();
        List<Position> moves = queen.getPossibleMoves();
        assertEquals(27, moves.size());
        assertTrue(moves.contains(new Position(3, 0)));
        assertTrue(moves.contains(new Position(3, 1)));
        assertTrue(moves.contains(new Position(3, 2)));
        assertTrue(moves.contains(new Position(3, 3)));
        assertTrue(moves.contains(new Position(3, 5)));
        assertTrue(moves.contains(new Position(3, 6)));
        assertTrue(moves.contains(new Position(3, 7)));

        assertTrue(moves.contains(new Position(0, 4)));
        assertTrue(moves.contains(new Position(1, 4)));
        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(4, 4)));
        assertTrue(moves.contains(new Position(5, 4)));
        assertTrue(moves.contains(new Position(6, 4)));
        assertTrue(moves.contains(new Position(7, 4)));

        assertTrue(moves.contains(new Position(0, 1)));
        assertTrue(moves.contains(new Position(1, 2)));
        assertTrue(moves.contains(new Position(2, 3)));

        assertTrue(moves.contains(new Position(4, 5)));
        assertTrue(moves.contains(new Position(5, 6)));
        assertTrue(moves.contains(new Position(6, 7)));

        assertTrue(moves.contains(new Position(4, 3)));
        assertTrue(moves.contains(new Position(5, 2)));
        assertTrue(moves.contains(new Position(6, 1)));
        assertTrue(moves.contains(new Position(7, 0)));

        assertTrue(moves.contains(new Position(2, 5)));
        assertTrue(moves.contains(new Position(1, 6)));
        assertTrue(moves.contains(new Position(0, 7)));
    }
}
