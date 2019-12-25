package test;

import main.Position;
import main.pieces.Pawn;
import main.pieces.PieceName;
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
        assertEquals(PieceName.PAWN, pawn.getPieceID());
    }

    @Test
    public void updatePossibleMovesTestBlack() {
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(1, 1), moves.get(0));
        assertEquals(1, moves.size());
    }

    @Test
    public void updatePossibleMovesTestWhite() {
        pawn = new Pawn(2, 6, 0);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(2, 5), moves.get(0));
        assertEquals(1, moves.size());
    }
}
