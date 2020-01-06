package main.ui.chessEditorGUI;

import javax.swing.*;

public class EditorPanel {
    private JPanel chessPanel;
    private JTextPane instructionPanel;
    private ChessBoardEditor chessBoardEditor;
    private InstructionPaneEditor instructionPaneEditor;

    public EditorPanel() {
        JFrame frame = new JFrame("Chess editor");
        frame.setLayout(null);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        instructionPaneEditor = new InstructionPaneEditor();
        instructionPanel = instructionPaneEditor.getInstructionPanel();
        frame.add(instructionPanel);
        instructionPanel.setBounds(0, 0, 950, 75);

        chessBoardEditor = new ChessBoardEditor();
        chessBoardEditor.setUpBoard();
        chessBoardEditor.makeEditorPane();
        chessBoardEditor.makeEditorGUI();
        chessPanel = chessBoardEditor.getEditorGUI();
        frame.add(chessPanel);
        chessPanel.setBounds(0, 50, 950, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(950, 900);
    }

    public static void main(String[] args) {
        new EditorPanel();
    }
}
