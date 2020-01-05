package main.ui;

import javax.swing.*;
import java.awt.*;

public class Chess {
    private JPanel cardPanel;
    private JPanel mainPanel;
    private JPanel chessPanel;
    private JPanel instructionPanel;

    public Chess() {
        JFrame frame = new JFrame("Chess");
        frame.setVisible(true);
        frame.setContentPane(cardPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(950, 750);
        cardPanel.add(mainPanel, "main");
        CardLayout card = (CardLayout) (cardPanel.getLayout());
        card.show(cardPanel, "main");
        setUpMain();
    }

    public void setUpMain() {
//        chessPanel = new ChessBoard();
//        instructionPanel = new InstructionPane();
//        cardPanel.repaint();
//        cardPanel.revalidate();
    }
}
