package main.ui;

import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
//    private JPanel mainPanel;
    private JPanel chessPanel;
    private JTextPane instructionPanel;

    public MainPanel() {
//        mainPanel = new JPanel();
        JFrame frame = new JFrame("Chess");
        frame.setLayout(null);
//        mainPanel.setSize(750, 850);
        InstructionPane instructionPane = new InstructionPane();
        instructionPanel = instructionPane.getInstructionPanel();
        frame.add(instructionPanel);
        instructionPanel.setBounds(0, 0, 750, 100);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setUpBoard();
        chessBoard.makeClickablePieces();
        chessPanel = chessBoard.getChessPanel();
        frame.add(chessPanel);
        chessPanel.setBounds(0, 100, 750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(750, 875);
//        getLayeredPane().setLayout(new BorderLayout());
////        chessPanel.addComponentToPane(frame.getContentPane());
////        instructionPanel.addComponentToPane(frame.getContentPane());
//        mainPanel.add(chessPanel);
//        mainPanel.add(instructionPanel);
//        getContentPane().add(mainPanel);
//        pack();
//        setVisible(true);
//        setSize(950, 750);
    }
}


