package main.ui.chessEditorGUI;

import javax.swing.*;
import java.awt.*;

public class InstructionPaneEditor {
    private JTextPane instructionPanel;

    public InstructionPaneEditor() {
        instructionPanel = new JTextPane();
        instructionPanel.setSize(750, 100);
        instructionPanel.setFont(new java.awt.Font("Ubuntu", 1, 20));
        instructionPanel.setText("Clicking a piece on the right of the board allows you to press any" +
                " spot on the board to place the piece on that spot. When you are ready to start the game, " +
                "click the start game button.");
    }

    public void addComponentToPane(Container pane) {
        pane.add(instructionPanel);
    }

    public JTextPane getInstructionPanel() {
        return instructionPanel;
    }
}
