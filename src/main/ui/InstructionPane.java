package main.ui;

import javax.swing.*;
import java.awt.*;

public class InstructionPane {
    private JTextPane instructionPanel;

    public InstructionPane() {
        instructionPanel = new JTextPane();
        instructionPanel.setSize(750, 100);
        instructionPanel.setFont(new java.awt.Font("Ubuntu", 1, 20));
        instructionPanel.setText("Clicking a piece will show the possible moves in yellow squares." +
                " After clicking the piece, you can click it again to go back to choosing which piece to move again." +
                " A red square on a piece means that the piece in red is checking the opposite team's king.");
        JTextArea instruction = new JTextArea();
        instruction.setText("Clicking a piece will show the possible moves in yellow squares. " +
                "After clicking the piece, you can click it again to go back to choosing which piece to move again." +
                "A red square on a piece means that the piece in red is checking the opposite team's king.");
        instruction.setLineWrap(true);
        instructionPanel.add(instruction);
    }

    public void addComponentToPane(Container pane) {
        pane.add(instructionPanel);
    }

    public JTextPane getInstructionPanel() {
        return instructionPanel;
    }

}
