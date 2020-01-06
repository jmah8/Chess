package main.ui.mainChessGUI;

import javax.swing.*;

public class ChessPanel {
//    private JPanel mainPanel;
    private JPanel chessPanel;
    private JTextPane instructionPanel;
    private ChessBoardBackButton chessBoard;
    private InstructionPaneChess instructionPaneChess;

    public ChessPanel() {
        JFrame frame = new JFrame("Chess");
        frame.setLayout(null);
        instructionPaneChess = new InstructionPaneChess();
        instructionPanel = instructionPaneChess.getInstructionPanel();
        frame.add(instructionPanel);
        instructionPanel.setBounds(0, 0, 750, 100);

        chessBoard = new ChessBoardBackButton();
        chessBoard.setUpBoard();
        chessBoard.makeClickablePieces();
        chessPanel = chessBoard.getChessGUI();
        frame.add(chessPanel);
        chessPanel.setBounds(0, 100, 750, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(750, 930);
    }
}


