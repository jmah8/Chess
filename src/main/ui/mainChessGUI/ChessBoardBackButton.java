package main.ui.mainChessGUI;

import main.model.Load;
import main.ui.ChessBoard;
import main.ui.SaveAndQuitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoardBackButton extends ChessBoard {
    private JButton backButton;
    private JPanel chessGUI;

    public ChessBoardBackButton() {
        super();
        chessGUI = new JPanel();
        chessGUI.setLayout(new BoxLayout(chessGUI, BoxLayout.Y_AXIS));
        chessGUI.setSize(750, 800);
        chessGUI.add(chessPanel);
        addReverseButton();
        addSaveAndQuitButton();
        Load l = new Load(board);
        l.load("/home/jonathan/Desktop/Personal Projects/Chess/data/ChessGame.txt");
    }

    public JPanel getChessGUI() {
        return chessGUI;
    }

    public void addSaveAndQuitButton() {
        JButton button = new SaveAndQuitButton(board, "/home/jonathan/Desktop/Personal Projects/Chess/data/ChessGame.txt");
        button.setPreferredSize(new Dimension(750, 50));
        button.setMaximumSize(new Dimension(750, 50));
        button.setMinimumSize(new Dimension(750, 50));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        chessGUI.add(button);
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
                reverseMove();
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

    @Override
    public void refreshBoard() {
        chessPanel.removeAll();
        setUpBoard();
        makeClickablePieces();
        enableBackButton();
        chessPanel.repaint();
        chessPanel.revalidate();
        checkGameOverBlackKing();
        checkGameOverWhiteKing();
    }
}
