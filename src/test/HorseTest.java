package test;

import javafx.geometry.Pos;
import main.Position;
import main.pieces.Horse;
import main.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    private Horse horse;

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
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(5, 3)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(4, 6)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(1, 5)));
    }
}
