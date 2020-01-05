package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessPanel {
//    private JPanel mainPanel;
    private JPanel chessPanel;
    private JTextPane instructionPanel;
    private JButton backButton;
    private ChessBoard chessBoard;
    private InstructionPane instructionPane;

    public ChessPanel() {
//        mainPanel = new JPanel();
        JFrame frame = new JFrame("Chess");
        frame.setLayout(null);
//        mainPanel.setSize(750, 850);
        instructionPane = new InstructionPane();
        instructionPanel = instructionPane.getInstructionPanel();
        frame.add(instructionPanel);
        instructionPanel.setBounds(0, 0, 750, 100);
        chessBoard = new ChessBoard();
        chessBoard.setUpBoard();
        chessBoard.makeClickablePieces();
        chessBoard.addReverseButton();
        chessPanel = chessBoard.getChessPanel();
        frame.add(chessPanel);
        chessPanel.setBounds(0, 100, 750, 800);
        makeBackButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(750, 930);
    }

    private void makeBackButton() {
        backButton = new JButton("Reverse Button");
        backButton.setPreferredSize(new Dimension(750, 50));
        backButton.setMaximumSize(new Dimension(750, 50));
        backButton.setMinimumSize(new Dimension(750, 50));
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        chessBoard.enableBackButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessBoard.reverseMove();
            }
        });
    }
}


