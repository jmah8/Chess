package main.ui;

import main.model.Board;
import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.PieceName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChessBoard {
//    private JPanel cardPanel;
    private JPanel chessPanel;
    private JPanel chessGUI;
    private JButton backButton;

    private JButton[][] buttons = new JButton[8][8];

    private Board board;

    private int turncounter = 0;

    public ChessBoard() {
        board = new Board();
        board.makeBoard();
        board.fillBoard();
        chessPanel = new JPanel(new GridLayout(8, 8));
        chessPanel.setPreferredSize(new Dimension(750, 750));
        chessPanel.setMaximumSize(new Dimension(750, 750));
        chessPanel.setMinimumSize(new Dimension(750, 750));
        chessPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        chessGUI = new JPanel();
        chessGUI.setLayout(new BoxLayout(chessGUI, BoxLayout.Y_AXIS));
        chessGUI.setSize(750, 800);
        chessGUI.add(chessPanel);
        addReverseButton();
    }

    public void addComponentToPane(Container pane) {
        pane.add(chessGUI);
    }

    public JPanel getChessPanel() {
        return chessGUI;
    }

    public void addReverseButton() {
        backButton = new JButton("Reverse Button");
        backButton.setPreferredSize(new Dimension(750, 50));
        backButton.setMaximumSize(new Dimension(750, 50));
        backButton.setMinimumSize(new Dimension(750, 50));
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        enableBackButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.reverseMove();
                turncounter--;
                refreshBoard();
            }
        });
        chessGUI.add(backButton);
    }

    public void enableBackButton() {
        if (board.getEventLog().getEventHistoryList().isEmpty()) {
            backButton.setEnabled(false);
        } else {
            backButton.setEnabled(true);
        }
    }

    // EFFECT: sets the background of the button depending on the index of the button
    protected void setBlackOrWhite(int i, int j) {
        if ((i + j) % 2 == 0) {
            buttons[i][j].setBackground(new Color(229, 198, 109));
        } else {
            buttons[i][j].setBackground(new Color(96, 73, 6));
        }
    }

    // EFFECT: sets board up with empty pieces and actual pieces with text on the buttons
    public void setUpBoard() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                if (chessPiece.getPieceID() == PieceName.EMPTY) {
                    buttons[i][j] = new JButton();
                } else {
                    buttons[i][j] = new JButton(String.valueOf(chessPiece.getPieceID()));
                    if (chessPiece.getTeam() == 0) {
                        buttons[i][j].setForeground(Color.WHITE);
                    } else {
                        buttons[i][j].setForeground(Color.BLACK);
                    }
                }
                buttons[i][j].setEnabled(false);
                setBlackOrWhite(i, j);
                chessPanel.add(buttons[i][j]);
            }
        }
    }

    // EFFECT: makes the the pieces that are not empty and current team's turn to clickable buttons
    public void makeClickablePieces() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                if (board.checkIfCheckOccurringForBlackKing()) {
                    Position kingPos = board.getPosKingBlackTeam();
                    int x = kingPos.getXcoord();
                    int y = kingPos.getYcoord();
                    buttons[y][x].setEnabled(true);
                } else if (board.checkIfCheckOccurringForWhiteKing()) {
                    Position kingPos = board.getPosKingWhiteTeam();
                    int x = kingPos.getXcoord();
                    int y = kingPos.getYcoord();
                    buttons[y][x].setEnabled(true);
                } else if (chessPiece.getPieceID() != PieceName.EMPTY && switchOnButtonsForTeamTurn(chessPiece)) {
                    buttons[i][j].setEnabled(true);
                }
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setUpBoardForMovement(e);
                    }
                });
            }
        }
    }

    // EFFECT: makes the pieces clickable buttons that when clicked shows the possible moves of the piece
    public void setUpBoardForMovement(ActionEvent e) {
        Object o = e.getSource();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (buttons[i][j] == o) {
                    ChessPiece pieceClicked = board.getPiece(j, i);
                    chessPanel.removeAll();
                    setUpBoard();
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            refreshBoard();
                            colourCheckingPieces();
                        }
                    });
                    chessPanel.repaint();
                    chessPanel.revalidate();
                    pieceClicked.updatePossibleMoves();
                    List<Position> possibleMoves = pieceClicked.getPossibleMoves();
                    makeMovableButtons(pieceClicked, possibleMoves);
                }
            }
        }
        chessPanel.repaint();
        chessPanel.revalidate();
    }

    // EFFECT: refreshes the board
    private void refreshBoard() {
        chessPanel.removeAll();
        setUpBoard();
        makeClickablePieces();
        enableBackButton();
        chessPanel.repaint();
        chessPanel.revalidate();
        checkGameOverBlackKing();
        checkGameOverWhiteKing();
    }

    // EFFECT: makes yellow clickable buttons that all the piece to move to the position
    private void makeMovableButtons(ChessPiece pieceClicked, List<Position> possibleMoves) {
        for (Position p : possibleMoves) {
            int x = p.getXcoord();
            int y = p.getYcoord();
            buttons[y][x].setEnabled(true);
            buttons[y][x].setBackground(Color.YELLOW);
            buttons[y][x].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.movePiece(pieceClicked, new Position(x, y));
                    turncounter++;
                    refreshBoard();
                    colourCheckingPieces();
                }
            });
        }
    }

    private boolean switchOnButtonsForTeamTurn(ChessPiece chessPiece) {
        if (turncounter % 2 == chessPiece.getTeam()) {
            return true;
        } else {
            return false;
        }
    }

    private void colourCheckingPieces() {
        if (board.checkIfCheckOccurringForBlackKing()) {
            showPieceCheckingBlackKing();
        }
        if (board.checkIfCheckOccurringForWhiteKing()) {
            showPieceCheckingWhiteKing();
        }
    }

    private void showPieceCheckingBlackKing() {
        List<ChessPiece> piecesCheckingBlackKing = board.getCheckingPiecesForBlackKing();
        for (ChessPiece cp : piecesCheckingBlackKing) {
            int xPos = cp.getPosition().getXcoord();
            int yPos = cp.getPosition().getYcoord();
            buttons[yPos][xPos].setBackground(Color.RED);
            chessPanel.repaint();
            chessPanel.revalidate();
        }
    }

    private void showPieceCheckingWhiteKing() {
        List<ChessPiece> piecesCheckingWhiteKing = board.getCheckingPiecesForWhiteKing();
        for (ChessPiece cp : piecesCheckingWhiteKing) {
            int xPos = cp.getPosition().getXcoord();
            int yPos = cp.getPosition().getYcoord();
            buttons[yPos][xPos].setBackground(Color.RED);
            chessPanel.repaint();
            chessPanel.revalidate();
        }
    }

    public void checkGameOverBlackKing() {
        boolean gameOver = board.gameOverForBlackKing();
        if (gameOver) {
            Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(null, "White team won", "Victory", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                makeNewGame();
            }
        }
    }

    private void makeNewGame() {
        turncounter = 0;
        board.makeBoard();
        board.fillBoard();
        refreshBoard();
    }

    public void checkGameOverWhiteKing() {
        boolean gameOver = board.gameOverForWhiteKing();
        if (gameOver) {
            Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(null, "Black team won", "Victory", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0) {
                makeNewGame();
            }
        }
    }

    //    public void addPieces() {
//        for (int i = 0; i < board.getBoard().length; i++) {
//            for (int j = 0; j < board.getBoard()[i].length; j++) {
//                ChessPiece chessPiece = board.getPiece(j, i);
//                if (chessPiece.getPieceID() == PieceName.EMPTY) {
//                    buttons[i][j] = new JButton();
//                    buttons[i][j].setEnabled(false);
//                    setBlackOrWhite(i, j);
//                    chessPanel.add(buttons[i][j]);
//                } else {
//                    buttons[i][j] = new JButton(String.valueOf(chessPiece.getPieceID()));
//                    setBlackOrWhite(i, j);
//                    chessPanel.add(buttons[i][j]);
//                    buttons[i][j].addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            setBoardForPieceMovement(e);
//                        }
//                    });
//                }
//            }
//        }
//    }
//
//    public void setBoardForPieceMovement(ActionEvent e) {
//        Object o = e.getSource();
//        for (int i = 0; i < board.getBoard().length; i++) {
//            for (int j = 0; j < board.getBoard()[i].length; j++) {
//                if (buttons[i][j] == o) {
//                    ChessPiece pieceClicked = board.getPiece(j, i);
//                    pieceClicked.updatePossibleMoves();
//                    List<Position> possibleMoves = pieceClicked.getPossibleMoves();
//                    for (Position p : possibleMoves) {
//                        int x = p.getXcoord();
//                        int y = p.getYcoord();
//                        int index = (y * 8) + x;
//                        chessPanel.remove(index);
//                        JButton movablePosition = new JButton();
//                        movablePosition.setBackground(Color.YELLOW);
//                        chessPanel.add(movablePosition, index);
//                    }
//                }
//            }
//        }
//        chessPanel.repaint();
//        chessPanel.revalidate();
//    }
}
