package main.ui;

import main.model.Board;
import main.model.pieces.ChessPiece;
import main.model.pieces.PieceName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChessBoard implements ActionListener{
    private JPanel cardPanel;
    private JPanel chessPanel;

    private JButton[][] buttons = new JButton[8][8];

    private Board board;

    public ChessBoard() {
        board = new Board();
        board.makeBoard();
        board.fillBoard();
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
                    if ((i + j) % 2 == 0) {
                        buttons[i][j].setBackground(Color.white);
                    } else {
                        buttons[i][j].setBackground(Color.black);
                    }
                    chessPanel.add(buttons[i][j]);
                } else {
                    buttons[i][j] = new JButton(String.valueOf(chessPiece.getPieceID()));
                    if ((i + j) % 2 == 0) {
                        buttons[i][j].setBackground(Color.white);
                    } else {
                        buttons[i][j].setBackground(Color.black);
                    }
                    chessPanel.add(buttons[i][j]);
                    buttons[i][j].addActionListener(this);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (buttons[i][j] == o) {
                    
                }
            }
        }
    }
}
