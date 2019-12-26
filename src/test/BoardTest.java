package test;

import main.model.Board;
import main.model.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void constructorTest() {
        int pieceCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getBoard()[i][j] == null) {
                    pieceCount++;
                }
            }
        }
        assertEquals(64, pieceCount);
    }

    @Test
    public void makeBoardTest() {
        board.makeBoard();
        int pieceCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getBoard()[i][j] != null) {
                    pieceCount++;
                }
            }
        }
        assertEquals(64, pieceCount);
    }

    @Test
    public void fillBoardTest() {
        board.fillBoard();
        assertEquals(PieceName.ROOK, board.getBoard()[0][0].getPieceID());
        assertEquals(PieceName.HORSE, board.getBoard()[0][1].getPieceID());
        assertEquals(PieceName.BISHOP, board.getBoard()[0][2].getPieceID());
        assertEquals(PieceName.KING, board.getBoard()[0][3].getPieceID());
        assertEquals(PieceName.QUEEN, board.getBoard()[0][4].getPieceID());
        assertEquals(PieceName.BISHOP, board.getBoard()[0][5].getPieceID());
        assertEquals(PieceName.HORSE, board.getBoard()[0][6].getPieceID());
        assertEquals(PieceName.ROOK, board.getBoard()[0][7].getPieceID());
        for (int j = 0; j < 8; j++) {
            assertEquals(PieceName.PAWN, board.getBoard()[1][j].getPieceID());
        }
        assertEquals(PieceName.ROOK, board.getBoard()[7][0].getPieceID());
        assertEquals(PieceName.HORSE, board.getBoard()[7][1].getPieceID());
        assertEquals(PieceName.BISHOP, board.getBoard()[7][2].getPieceID());
        assertEquals(PieceName.KING, board.getBoard()[7][3].getPieceID());
        assertEquals(PieceName.QUEEN, board.getBoard()[7][4].getPieceID());
        assertEquals(PieceName.BISHOP, board.getBoard()[7][5].getPieceID());
        assertEquals(PieceName.HORSE, board.getBoard()[7][6].getPieceID());
        assertEquals(PieceName.ROOK, board.getBoard()[7][7].getPieceID());
        for (int j = 0; j < 8; j++) {
            assertEquals(PieceName.PAWN, board.getBoard()[6][j].getPieceID());
        }
    }
}
