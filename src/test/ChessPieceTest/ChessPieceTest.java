package test.ChessPieceTest;

import main.model.Board;
import org.junit.jupiter.api.BeforeEach;

public class ChessPieceTest {
    protected Board board;

    @BeforeEach
    protected void setBoard() {
        board = new Board();
        board.makeBoard();
    }
}
