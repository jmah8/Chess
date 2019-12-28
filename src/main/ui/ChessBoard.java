package main.ui;

import javafx.geometry.Pos;
import main.model.Board;
import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.PieceName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ChessBoard {
    private JPanel cardPanel;
    private JPanel chessPanel;

    private JButton[][] buttons = new JButton[8][8];

    private Board board;

    public ChessBoard() {
        board = new Board();
        board.makeBoard();
        board.fillBoard();
        board.setBoardFieldForPieces();
        cardPanel = new JPanel(new CardLayout());
        chessPanel = new JPanel(new GridLayout(8, 8));
        cardPanel.add(chessPanel, "ChessBoard");
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, "ChessBoard");
    }

    public void addComponentToPane(Container pane) {
        pane.add(cardPanel);
    }

    public void addPieces() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                if (chessPiece.getPieceID() == PieceName.EMPTY) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setEnabled(false);
                    setBlackOrWhite(i, j);
                    chessPanel.add(buttons[i][j]);
                } else {
                    buttons[i][j] = new JButton(String.valueOf(chessPiece.getPieceID()));
                    setBlackOrWhite(i, j);
                    chessPanel.add(buttons[i][j]);
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setBoardForPieceMovement(e);
                        }
                    });
                }
            }
        }
    }

    protected void setBlackOrWhite(int i, int j) {
        if ((i + j) % 2 == 0) {
            buttons[i][j].setBackground(Color.WHITE);
        } else {
            buttons[i][j].setBackground(Color.BLACK);
        }
    }

    public void setBoardForPieceMovement(ActionEvent e) {
        Object o = e.getSource();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (buttons[i][j] == o) {
                    ChessPiece pieceClicked = board.getPiece(j, i);
                    pieceClicked.updatePossibleMoves();
                    List<Position> possibleMoves = pieceClicked.getPossibleMoves();
                    for (Position p : possibleMoves) {
                        int x = p.getXcoord();
                        int y = p.getYcoord();
                        int index = (y * 8) + x;
                        chessPanel.remove(index);
                        JButton movablePosition = new JButton();
                        movablePosition.setBackground(Color.YELLOW);
                        chessPanel.add(movablePosition, index);
                    }
                }
            }
        }
        chessPanel.repaint();
        chessPanel.revalidate();
    }

    public void setUpBoard() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                if (chessPiece.getPieceID() == PieceName.EMPTY) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setEnabled(false);
                    setBlackOrWhite(i, j);
                    chessPanel.add(buttons[i][j]);
                } else {
                    buttons[i][j] = new JButton(String.valueOf(chessPiece.getPieceID()));
                    buttons[i][j].setEnabled(false);
                    setBlackOrWhite(i, j);
                    chessPanel.add(buttons[i][j]);
                }
            }
        }
    }

    public void makeClickablePieces() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                if (chessPiece.getPieceID() != PieceName.EMPTY) {
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

    public void setUpBoardForMovement(ActionEvent e) {
        Object o = e.getSource();
//        chessPanel.removeAll();
//        setUpBoard();
//        chessPanel.repaint();
//        chessPanel.revalidate();
//        JButton button = (JButton) o;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (buttons[i][j] == o) {
                    ChessPiece pieceClicked = board.getPiece(j, i);
                    pieceClicked.updatePossibleMoves();
                    List<Position> possibleMoves = pieceClicked.getPossibleMoves();
                    for (Position p : possibleMoves) {
                        int x = p.getXcoord();
                        int y = p.getYcoord();
//                        int index = (y * 8) + x;
                        buttons[y][x].setEnabled(true);
                        buttons[y][x].setBackground(Color.YELLOW);
                        buttons[y][x].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                board.movePiece(pieceClicked, new Position(x, y));
                                chessPanel.removeAll();
                                setUpBoard();
                                makeClickablePieces();
                                chessPanel.repaint();
                                chessPanel.revalidate();
                            }
                        });
                    }
                }
            }
        }
        chessPanel.repaint();
        chessPanel.revalidate();
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        for (int i = 0; i < board.getBoard().length; i++) {
//            for (int j = 0; j < board.getBoard()[i].length; j++) {
//                if (buttons[i][j] == o) {
//                    ChessPiece pieceClicked = board.getPiece(j, i);
//                    List<Position> possibleMoves = pieceClicked.getPossibleMoves();
//                    for (Position p : possibleMoves) {
//                        int x = p.getXcoord();
//                        int y = p.getYcoord();
//                        int index = getComponentZOrder(buttons[y][x]);
//                        chessPanel.remove(buttons[y][x]);
//                        JButton movablePosition = new JButton();
//                        movablePosition.setBackground(Color.YELLOW);
//                        chessPanel.add(movablePosition, index);
//                    }
//                }
//            }
//        }
//    }
}
