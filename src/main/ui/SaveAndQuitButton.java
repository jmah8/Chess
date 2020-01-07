package main.ui;

import main.model.Board;
import main.model.Save;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveAndQuitButton extends JButton implements ActionListener {
    private Board board;
    private String pathName;

    public SaveAndQuitButton(Board board, String pathName) {
        this.setText("Save and Quit");
        this.board = board;
        this.pathName = pathName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = JOptionPane.showConfirmDialog(null, "Really Quit?",
                "Warning", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
                Save s = new Save(board);
                s.save(pathName);
                System.exit(0);
        }
    }
}
