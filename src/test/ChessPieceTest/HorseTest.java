package test.ChessPieceTest;

import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.Horse;
import main.model.pieces.Pawn;
import main.model.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest extends ChessPieceTest {
    private ChessPiece horse;

    @BeforeEach
    public void setUp() {
        horse = new Horse(3, 4, 0, board);
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
        horse = new Horse(7, 7, 1, board);
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(6, 5)));
        assertTrue(moves.contains(new Position(5, 6)));
    }

    @Test
    public void updatePossibleMovesTestAt66() {
        horse.setPosition(new Position(6, 6));
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Position(4, 7)));
        assertTrue(moves.contains(new Position(4, 5)));
        assertTrue(moves.contains(new Position(5, 4)));
        assertTrue(moves.contains(new Position(7, 4)));
    }

    @Test
    public void updatePossibleMovesAlliesAround() {
        board.placePiece(new Pawn(1, 3, 0, board));
        board.placePiece(new Pawn(2, 2, 0, board));
        board.placePiece(new Pawn(5, 3, 0, board));
        board.placePiece(new Pawn(4, 2, 0, board));
        board.placePiece(new Pawn(1, 5, 0, board));
        board.placePiece(new Pawn(2, 6, 0, board));
        board.placePiece(new Pawn(5, 5, 0, board));
        board.placePiece(new Pawn(4, 6, 0, board));
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesSomeAlliesAround() {
        board.placePiece(new Pawn(1, 3, 0, board));
        board.placePiece(new Pawn(2, 2, 0, board));
        board.placePiece(new Pawn(5, 3, 0, board));
        board.placePiece(new Pawn(2, 6, 0, board));
        board.placePiece(new Pawn(5, 5, 0, board));
        board.placePiece(new Pawn(4, 6, 0, board));
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(1, 5)));
    }

    @Test
    public void updatePossibleMovesSomeLessAlliesAround() {
        board.placePiece(new Pawn(5, 3, 0, board));
        board.placePiece(new Pawn(4, 6, 0, board));
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(6, moves.size());
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(1, 5)));
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(5, 5)));
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(2, 2)));
    }

    @Test
    public void updatePossibleMovesEnemiesAround() {
        board.placePiece(new Pawn(1, 3, 1, board));
        board.placePiece(new Pawn(2, 2, 1, board));
        board.placePiece(new Pawn(5, 3, 1, board));
        board.placePiece(new Pawn(4, 2, 1, board));
        board.placePiece(new Pawn(1, 5, 1, board));
        board.placePiece(new Pawn(2, 6, 1, board));
        board.placePiece(new Pawn(5, 5, 1, board));
        board.placePiece(new Pawn(4, 6, 1, board));
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
    public void updatePossibleMovesEnemiesAndAlliesAround() {
        board.placePiece(new Pawn(1, 3, 1, board));
        board.placePiece(new Pawn(1, 5, 0, board));
        board.placePiece(new Pawn(2, 2, 1, board));
        board.placePiece(new Pawn(2, 6, 0, board));
        board.placePiece(new Pawn(4, 2, 1, board));
        board.placePiece(new Pawn(4, 6, 1, board));
        board.placePiece(new Pawn(5, 3, 0, board));
        board.placePiece(new Pawn(5, 5, 0, board));
        horse.updatePossibleMoves();
        List<Position> moves = horse.getPossibleMoves();
        assertEquals(4, moves.size());
        assertTrue(moves.contains(new Position(1, 3)));
        assertTrue(moves.contains(new Position(2, 2)));
        assertTrue(moves.contains(new Position(4, 2)));
        assertTrue(moves.contains(new Position(4, 6)));
    }
}
