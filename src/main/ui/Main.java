package main.ui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.addButtons();
        chessBoard.addComponentToPane(frame.getContentPane());
        frame.setVisible(true);
        frame.pack();
        frame.setSize(750, 750);
    }
}
