package main.ui;

import main.ui.mainChessGUI.InstructionPaneChess;

import javax.swing.*;

public class ChessGUI{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InstructionPaneChess instructionPaneChess = new InstructionPaneChess();
//        instructionPane.addComponentToPane(frame.getContentPane());
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setUpBoard();
        chessBoard.makeClickablePieces();
//        chessBoard.addComponentToPane(frame.getContentPane());
        JPanel mainPanel = new JPanel();
        instructionPaneChess.addComponentToPane(mainPanel);
        chessBoard.addComponentToPane(mainPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(750, 850);
    }
}
