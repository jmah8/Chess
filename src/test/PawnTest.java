package test;

import com.sun.xml.internal.ws.model.CheckedExceptionImpl;
import main.model.Board;
import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.Pawn;
import main.model.pieces.PieceName;
import main.model.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest extends ChessPieceTest {
    private ChessPiece pawn;

    @BeforeEach
    public void setUp() {
        pawn = new Pawn(1, 0, 1, board);
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
        pawn = new Pawn(4, 3, 0, board);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(4, 2), moves.get(0));
        assertEquals(1, moves.size());
    }

    @Test
    public void updatePossibleMovesTestWhiteWithDoubleMovement() {
        pawn = new Pawn(2, 6, 0, board);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(2, 4), moves.get(0));
        assertEquals(new Position(2, 5), moves.get(1));
        assertEquals(2, moves.size());
    }

    @Test
    public void updatePossibleMovesTestBlackWithDoubleMovement() {
        pawn = new Pawn(2, 1, 1, board);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(2, 2), moves.get(1));
        assertEquals(new Position(2, 3), moves.get(0));
        assertEquals(2, moves.size());
    }

    @Test
    public void updatePossibleMovesBlackTestEdge() {
        pawn.setPosition(new Position(6, 7));
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesWhiteTestEdge() {
        pawn = new Pawn(3, 0, 0, board);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void updatePossibleMovesWhiteEatDiagonal() {
        pawn = new Pawn(2, 5, 0, board);
        board.placePiece(new Rook(3, 4, 1, board));
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(2, 4)));
        assertTrue(moves.contains(new Position(3, 4)));
    }

    @Test
    public void updatePossibleMovesBlackEatDiagonal() {
        pawn = new Pawn(2, 5, 1, board);
        board.placePiece(new Rook(3, 6, 0, board));
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Position(2, 6)));
        assertTrue(moves.contains(new Position(3, 6)));
    }
}
