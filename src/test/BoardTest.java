package test;

import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;
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

    @Test
    public void checkFillBoardPiecePosition() {
        board.fillBoard();
        assertEquals(new Position(0, 0), board.getBoard()[0][0].getPosition());
        assertEquals(new Position(1, 0), board.getBoard()[0][1].getPosition());
        assertEquals(new Position(2, 0), board.getBoard()[0][2].getPosition());
        assertEquals(new Position(3, 0), board.getBoard()[0][3].getPosition());
        assertEquals(new Position(4, 0), board.getBoard()[0][4].getPosition());
        assertEquals(new Position(5, 0), board.getBoard()[0][5].getPosition());
        assertEquals(new Position(6, 0), board.getBoard()[0][6].getPosition());
        assertEquals(new Position(7, 0), board.getBoard()[0][7].getPosition());

        assertEquals(new Position(0, 1), board.getBoard()[1][0].getPosition());
        assertEquals(new Position(1, 1), board.getBoard()[1][1].getPosition());
        assertEquals(new Position(2, 1), board.getBoard()[1][2].getPosition());
        assertEquals(new Position(3, 1), board.getBoard()[1][3].getPosition());
        assertEquals(new Position(4, 1), board.getBoard()[1][4].getPosition());
        assertEquals(new Position(5, 1), board.getBoard()[1][5].getPosition());
        assertEquals(new Position(6, 1), board.getBoard()[1][6].getPosition());
        assertEquals(new Position(7, 1), board.getBoard()[1][7].getPosition());

        assertEquals(new Position(0, 6), board.getBoard()[6][0].getPosition());
        assertEquals(new Position(1, 6), board.getBoard()[6][1].getPosition());
        assertEquals(new Position(2, 6), board.getBoard()[6][2].getPosition());
        assertEquals(new Position(3, 6), board.getBoard()[6][3].getPosition());
        assertEquals(new Position(4, 6), board.getBoard()[6][4].getPosition());
        assertEquals(new Position(5, 6), board.getBoard()[6][5].getPosition());
        assertEquals(new Position(6, 6), board.getBoard()[6][6].getPosition());
        assertEquals(new Position(7, 6), board.getBoard()[6][7].getPosition());

        assertEquals(new Position(0, 7), board.getBoard()[7][0].getPosition());
        assertEquals(new Position(1, 7), board.getBoard()[7][1].getPosition());
        assertEquals(new Position(2, 7), board.getBoard()[7][2].getPosition());
        assertEquals(new Position(3, 7), board.getBoard()[7][3].getPosition());
        assertEquals(new Position(4, 7), board.getBoard()[7][4].getPosition());
        assertEquals(new Position(5, 7), board.getBoard()[7][5].getPosition());
        assertEquals(new Position(6, 7), board.getBoard()[7][6].getPosition());
        assertEquals(new Position(7, 7), board.getBoard()[7][7].getPosition());
    }
}
