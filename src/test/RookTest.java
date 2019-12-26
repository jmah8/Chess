package test;

import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.PieceName;
import main.model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    private ChessPiece rook;

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

//        for (int j = 0; j < 8; j++) {
//            if (j != 5) {
//                assertTrue(moves.contains(new Position(j, 2)));
//            }
//        }
//        for (int i = 0; i < 8; i++) {
//            if (i != 2) {
//                assertTrue(moves.contains(new Position(5, i)));
//            }
//        }
    }
}
