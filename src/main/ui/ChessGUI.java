package main.ui;

import javax.swing.*;
import java.awt.*;

public class ChessGUI{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InstructionPane instructionPane = new InstructionPane();
//        instructionPane.addComponentToPane(frame.getContentPane());
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setUpBoard();
        chessBoard.makeClickablePieces();
//        chessBoard.addComponentToPane(frame.getContentPane());
        JPanel mainPanel = new JPanel();
        instructionPane.addComponentToPane(mainPanel);
        chessBoard.addComponentToPane(mainPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(750, 850);
    }
}
