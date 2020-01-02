package test;

import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;
import main.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.html.HTMLObjectElement;

import java.util.List;

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
        board.makeBoard();
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
        board.makeBoard();
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

    @Test
    public void placePieceTest() {
        board.makeBoard();
        board.placePiece(new Rook(5, 1, 0, board));
        ChessPiece piece = board.getPiece(5, 1);
        assertEquals(new Rook(5, 1, 0, board), piece);
    }

    @Test
    public void placePieceTestReplacement() {
        board.makeBoard();
        ChessPiece rook = new Rook(0, 0, 0, board);
        ChessPiece queen = new Rook(0, 0, 1, board);
        board.placePiece(rook);
        assertEquals(rook, board.getBoard()[0][0]);
        board.placePiece(queen);
        assertEquals(queen, board.getBoard()[0][0]);
    }

    @Test
    public void checkPosKingWhiteTeamTest() {
        board.makeBoard();
        ChessPiece piece = new King(3, 0, 0, board);
        board.placePiece(piece);
        assertEquals(new Position(3 , 0), board.checkPosKingWhiteTeam());
    }

    @Test
    public void movePieceTest() {
        board.makeBoard();
        board.fillBoard();
        board.movePiece(new Pawn(0, 1 , 1, board), new Position(0, 3));
        assertTrue(board.containPiece(new Pawn(0, 3 , 1, board)));
        assertTrue(board.getBoard()[1][0].equals(new EmptyPiece(0, 1, board)));
        assertTrue(board.getPiece(0, 1).equals(new EmptyPiece(0, 1, board)));
        assertTrue(board.getBoard()[3][0].equals(new Pawn(0, 3, 1, board)));
        assertTrue(board.getPiece(0, 3).equals(new Pawn(0, 3, 1, board)));
    }

    @Test
    public void checkPosKingBlackTeamTest() {
        board.makeBoard();
        ChessPiece piece = new King(5, 6, 1, board);
        board.placePiece(piece);
        assertEquals(new Position(5 , 6), board.checkPosKingBlackTeam());
    }

    @Test
    public void checkIfCheckOccurringForWhiteKingNoCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 0, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 5, 1, board);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertFalse(board.checkIfCheckOccurringForWhiteKing());
    }

    @Test
    public void checkIfCheckOccurringForWhiteKingCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 0, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 4, 1, board);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForWhiteKing());
    }

    @Test
    public void checkIfCheckOccurringForWhiteKingDoubleCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 0, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 4, 1, board);
        board.placePiece(piece1);
        ChessPiece piece2 = new Pawn(1, 5, 1, board);
        board.placePiece(piece2);
        piece1.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForWhiteKing());
        piece2.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForWhiteKing());
    }

    @Test
    public void checkIfCheckOccurringForWhiteKingSameTeam() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 0, board);
        ChessPiece piece1 = new Queen(7, 7, 0, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertFalse(board.checkIfCheckOccurringForWhiteKing());
    }

    @Test
    public void checkIfCheckOccurringForBlackKingNoCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 1, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 5, 0, board);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertFalse(board.checkIfCheckOccurringForBlackKing());
    }

    @Test
    public void checkIfCheckOccurringForBlackKingCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 1, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 4, 0, board);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForBlackKing());
    }

    @Test
    public void checkIfCheckOccurringForBlackKingDoubleCheck() {
        board.makeBoard();
        ChessPiece piece = new King(2, 4, 1, board);
        board.placePiece(piece);
        ChessPiece piece1 = new Rook(7, 4, 0, board);
        board.placePiece(piece1);
        ChessPiece piece2 = new Pawn(1, 5, 0, board);
        board.placePiece(piece2);
        piece1.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForBlackKing());
        piece2.updatePossibleMoves();
        assertTrue(board.checkIfCheckOccurringForBlackKing());
    }

    @Test
    public void checkIfCheckOccurringForBlackKingSameTeam() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 1, board);
        ChessPiece piece1 = new Queen(7, 7, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        piece1.updatePossibleMoves();
        assertFalse(board.checkIfCheckOccurringForBlackKing());
    }

    @Test
    public void setBoardFieldForPiecesTest() {
        board.makeBoard();
        board.fillBoard();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                assertEquals(board, board.getBoard()[i][j].getBoard());
            }
        }
    }

    @Test
    public void getCheckingPiecesForBlackKingTest1Piece() {
        board.makeBoard();
        ChessPiece piece = new King(4, 2, 1, board);
        ChessPiece piece1 = new Bishop(3, 3, 0, board);
        piece1.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        List<ChessPiece> pieces = board.getCheckingPiecesForBlackKing();
        assertEquals(1, pieces.size());
        assertTrue(pieces.contains(piece1));
    }

    @Test
    public void getCheckingPiecesForBlackKingTestNoPieces() {
        board.makeBoard();
        ChessPiece piece = new King(3, 3, 1, board);
        ChessPiece piece1 = new Rook(4, 4, 0, board);
        piece1.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        List<ChessPiece> pieces = board.getCheckingPiecesForBlackKing();
        assertEquals(0, pieces.size());
    }

    @Test
    public void getCheckingPiecesForBlackKingTest4Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 1, board);
        ChessPiece piece1 = new Rook(2, 4, 0, board);
        ChessPiece piece2 = new Queen(4, 0, 0, board);
        ChessPiece piece3 = new Bishop(7, 7, 0, board);
        ChessPiece piece4 = new Horse(3, 6, 0, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForBlackKing();
        assertEquals(4, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece2));
        assertTrue(pieces.contains(piece3));
        assertTrue(pieces.contains(piece4));
    }

    @Test
    public void getCheckingPiecesForBlackKingTest3Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 1, board);
        ChessPiece piece1 = new Rook(2, 4, 0, board);
        ChessPiece piece2 = new Queen(4, 0, 0, board);
        ChessPiece piece3 = new Bishop(7, 7, 0, board);
        ChessPiece piece4 = new Horse(3, 6, 1, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForBlackKing();
        assertEquals(3, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece2));
        assertTrue(pieces.contains(piece3));
    }

    @Test
    public void getCheckingPiecesForBlackKingTest2Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 1, board);
        ChessPiece piece1 = new Rook(2, 4, 0, board);
        ChessPiece piece2 = new Queen(4, 0, 1, board);
        ChessPiece piece3 = new Bishop(7, 7, 0, board);
        ChessPiece piece4 = new Horse(3, 6, 1, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForBlackKing();
        assertEquals(2, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece3));
    }

    @Test
    public void getCheckingPiecesForWhiteKingTest1Piece() {
        board.makeBoard();
        ChessPiece piece = new King(4, 2, 0, board);
        ChessPiece piece1 = new Bishop(3, 3, 1, board);
        piece1.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        List<ChessPiece> pieces = board.getCheckingPiecesForWhiteKing();
        assertEquals(1, pieces.size());
        assertTrue(pieces.contains(piece1));
    }

    @Test
    public void getCheckingPiecesForWhiteKingTestNoPieces() {
        board.makeBoard();
        ChessPiece piece = new King(3, 3, 0, board);
        ChessPiece piece1 = new Rook(4, 4, 1, board);
        piece1.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        List<ChessPiece> pieces = board.getCheckingPiecesForWhiteKing();
        assertEquals(0, pieces.size());
    }

    @Test
    public void getCheckingPiecesForWhiteKingTest4Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 0, board);
        ChessPiece piece1 = new Rook(2, 4, 1, board);
        ChessPiece piece2 = new Queen(4, 0, 1, board);
        ChessPiece piece3 = new Bishop(7, 7, 1, board);
        ChessPiece piece4 = new Horse(3, 6, 1, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForWhiteKing();
        assertEquals(4, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece2));
        assertTrue(pieces.contains(piece3));
        assertTrue(pieces.contains(piece4));
    }

    @Test
    public void getCheckingPiecesForWhiteKingTest3Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 0, board);
        ChessPiece piece1 = new Rook(2, 4, 1, board);
        ChessPiece piece2 = new Queen(4, 0, 1, board);
        ChessPiece piece3 = new Bishop(7, 7, 1, board);
        ChessPiece piece4 = new Horse(3, 6, 0, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForWhiteKing();
        assertEquals(3, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece2));
        assertTrue(pieces.contains(piece3));
    }

    @Test
    public void getCheckingPiecesForWhiteKingTest2Pieces() {
        board.makeBoard();
        ChessPiece piece = new King(4, 4, 0, board);
        ChessPiece piece1 = new Rook(2, 4, 1, board);
        ChessPiece piece2 = new Queen(4, 0, 0, board);
        ChessPiece piece3 = new Bishop(7, 7, 1, board);
        ChessPiece piece4 = new Horse(3, 6, 0, board);
        piece1.updatePossibleMoves();
        piece2.updatePossibleMoves();
        piece3.updatePossibleMoves();
        piece4.updatePossibleMoves();
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        board.placePiece(piece3);
        board.placePiece(piece4);
        List<ChessPiece> pieces = board.getCheckingPiecesForWhiteKing();
        assertEquals(2, pieces.size());
        assertTrue(pieces.contains(piece1));
        assertTrue(pieces.contains(piece3));
    }

    @Test
    public void gameOverForWhiteKingTest() {
        board.makeBoard();
        ChessPiece piece = new King(0, 0, 0, board);
        ChessPiece piece1 = new Rook(0, 4, 1, board);
        ChessPiece piece2 = new Queen(1, 5, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        piece.updatePossibleMoves();
        List<Position> moves = piece.getPossibleMoves();
        assertEquals(0, moves.size());
        assertTrue(board.gameOverForWhiteKing());
    }

    @Test
    public void gameOverForBlackKingTest() {
        board.makeBoard();
        ChessPiece piece = new King(3, 0, 1, board);
        ChessPiece piece1 = new Rook(0, 0, 0, board);
        ChessPiece piece2 = new Queen(1, 1, 0, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        piece.updatePossibleMoves();
        List<Position> moves = piece.getPossibleMoves();
        assertEquals(0, moves.size());
        assertTrue(board.gameOverForBlackKing());
    }

    @Test
    public void gameOverForWhiteKingTestBuggedScenario() {
        board.makeBoard();
        ChessPiece piece = new King(3, 0, 0, board);
        ChessPiece piece1 = new Rook(0, 0, 1, board);
        ChessPiece piece2 = new Queen(1, 1, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        piece.updatePossibleMoves();
        List<Position> moves = piece.getPossibleMoves();
        assertEquals(0, moves.size());
        assertTrue(board.gameOverForWhiteKing());
    }

    @Test
    public void gameOverForWhiteKingTestBuggedScenario1() {
        board.makeBoard();
        ChessPiece piece = new King(3, 0, 0, board);
        ChessPiece piece1 = new Rook(0, 0, 1, board);
        ChessPiece piece2 = new Rook(0, 1, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        piece.updatePossibleMoves();
        List<Position> moves = piece.getPossibleMoves();
        assertEquals(0, moves.size());
        assertTrue(board.gameOverForWhiteKing());
    }

    @Test
    public void gameOverForWhiteKingTestBuggedScenario2() {
        board.makeBoard();
        ChessPiece piece = new King(3, 0, 0, board);
        ChessPiece piece1 = new Queen(0, 0, 1, board);
        ChessPiece piece2 = new Rook(0, 1, 1, board);
        board.placePiece(piece);
        board.placePiece(piece1);
        board.placePiece(piece2);
        piece.updatePossibleMoves();
        List<Position> moves = piece.getPossibleMoves();
        assertEquals(0, moves.size());
        assertTrue(board.gameOverForWhiteKing());
    }

    @Test
    public void movePieceIrregardlessOfPossibleMoveTest() {
        board.makeBoard();
        board.fillBoard();
        ChessPiece rook = board.getPiece(7, 0);
        ChessPiece king = board.getPiece(3, 7);
        assertEquals(new Position(7, 0), rook.getPosition());
        assertEquals(new Position(3, 7), king.getPosition());
        board.movePieceIrregardlessOfPossibleMove(rook, new Position(7, 4));
        board.movePieceIrregardlessOfPossibleMove(king, new Position(5, 4));
        assertEquals(king, board.getBoard()[4][5]);
        assertEquals(rook, board.getBoard()[4][7]);
        assertEquals(new EmptyPiece(7, 0, board), board.getBoard()[0][7]);
        assertEquals(new EmptyPiece(3, 7, board), board.getBoard()[7][3]);
        assertEquals(new Position(7, 4), rook.getPosition());
        assertEquals(new Position(5, 4), king.getPosition());
    }

    @Test
    public void buggedScenarioForGUI() {
        board.makeBoard();
        board.fillBoard();
        ChessPiece pawn = board.getPiece(7, 6);
        board.movePiece(pawn, new Position(7, 4));
        assertEquals(new Position(7, 4), pawn.getPosition());
        ChessPiece rook = board.getPiece(7, 7);
        ChessPiece king = board.getPiece(3, 0);
        board.movePieceIrregardlessOfPossibleMove(rook, new Position(7, 3));
        board.movePieceIrregardlessOfPossibleMove(king, new Position(6, 3));
        assertEquals(rook, board.getPiece(7, 3));
        assertEquals(king, board.getPiece(6, 3));
        king.updatePossibleMoves();
        assertEquals(rook, board.getPiece(7, 3));
        assertEquals(king, board.getPiece(6, 3));
    }
}
