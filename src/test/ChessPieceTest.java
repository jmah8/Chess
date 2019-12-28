package test;

import main.model.Board;
import org.junit.jupiter.api.BeforeEach;

public class ChessPieceTest {
    private Board board;

    private void setBoard() {
        board = new Board();
        board.fillBoard();

    }
}
