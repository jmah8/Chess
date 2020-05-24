package main.ui.chessEditorGUI;

import javax.swing.*;
import java.awt.*;

public class InstructionPaneEditor {
    private JTextPane instructionPanel;

    public InstructionPaneEditor() {
        instructionPanel = new JTextPane();
        instructionPanel.setEditable(false);
        instructionPanel.setSize(750, 100);
        instructionPanel.setFont(new java.awt.Font("Ubuntu", 1, 20));
        instructionPanel.setText("Clicking a piece on the right of the board allows you to press any" +
                " spot on the board to place the piece on that spot. You can then move the pieces in an order and " +
                "move irregardless of whose turn it is.");
    }

    public void addComponentToPane(Container pane) {
        pane.add(instructionPanel);
    }

    public JTextPane getInstructionPanel() {
        return instructionPanel;
    }
}
