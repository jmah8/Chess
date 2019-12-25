package test;

import main.Position;
import main.pieces.PieceName;
import main.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    private Rook rook;

    @BeforeEach
    public void setUp() {
        rook = new Rook(7, 7, 0);
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
        rook = new Rook(5, 2, 0);
        rook.updatePossibleMoves();
        List<Position> moves = rook.getPossibleMoves();
        assertEquals(15, moves.size());
        for (int j = 0; j < 8; j++) {
            assertTrue(moves.contains(new Position(j, 2)));
        }
        for (int i = 0; i < 8; i++) {
            if (i != 2) {
                assertTrue(moves.contains(new Position(5, i)));
            }
        }
    }
}
