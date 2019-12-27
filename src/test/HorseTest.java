package test;

import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.Horse;
import main.model.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    private ChessPiece horse;

    @BeforeEach
    public void setUp() {
        horse = new Horse(3, 4, 0);
    }

    @Test
    public void constructorTest() {
        assertEquals(3, horse.getPosition().getXcoord());
        assertEquals(4, horse.getPosition().getYcoord());
        assertEquals(0, horse.getTeam());
        assertEquals(PieceName.HORSE, horse.getPieceID());
    }

    @Test
    public void updatePossibleMovesTest() {
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(8, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(4, 6)));
        assertTrue(moves.contains(new Position(5, 3)));
        assertTrue(moves.contains(new Position(5, 5)));
    }

    @Test
    public void updatePossibleMovesTestAtEdge() {
        horse = new Horse(7, 7, 1);
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(2, moves.size());
    }
}
