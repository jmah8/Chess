package main.ui.chessEditorGUI;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel {
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton startButton;

    public ButtonPanel() {
        buttonPanel = new JPanel();
        saveButton = new JButton("Save and Quit?");
        startButton = new JButton("Start Official Game?");
    }

    public void setUpButtonPanel() {
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setSize(950, 100);
        startButton.setPreferredSize(new Dimension(950, 50));
        startButton.setMaximumSize(new Dimension(950, 50));
        startButton.setMinimumSize(new Dimension(950, 50));
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setPreferredSize(new Dimension(950, 50));
        saveButton.setMaximumSize(new Dimension(950, 50));
        saveButton.setMinimumSize(new Dimension(950, 50));
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(startButton);
        buttonPanel.add(saveButton);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}
