package test;

import main.Position;
import main.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private Pawn pawn;

    @BeforeEach
    public void setUp() {
        pawn = new Pawn(1, 0, 1);
    }

    @Test
    public void constructorTest() {
        assertEquals(1, pawn.getPosition().getXcoord());
        assertEquals(0, pawn.getPosition().getYcoord());
        assertEquals(1, pawn.getTeam());
    }

    @Test
    public void updatePossibleMovesTest() {
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertTrue(pawn.g);
    }
}
