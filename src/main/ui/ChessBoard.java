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

public class ChessBoard extends JFrame{
    private JPanel cardPanel;
    private JPanel chessPanel;

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

    public void addButtons() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece chessPiece = board.getPiece(j, i);
                JButton piece = new JButton();
                if (chessPiece.getPieceID() != PieceName.EMPTY) {
                    piece = new JButton(String.valueOf(chessPiece.getPieceID()));
                }
                if ((i + j) % 2 == 0) {
                    piece.setBackground(Color.white);
                }
                chessPanel.add(piece);
                piece.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });
            }
        }
    }

    public void addComponentToPane(Container pane) {
        pane.add(cardPanel);
    }
}
