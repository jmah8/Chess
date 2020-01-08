package main.ui.chessEditorGUI;

import main.model.Load;
import main.model.pieces.*;
import main.ui.ChessBoard;
import main.ui.SaveAndQuitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoardEditor extends ChessBoard {
    private JPanel editorPane;
    private ChessPiece pieceToPlace;
    private JPanel editorGUI;
    private JPanel editorWithStartAndSave;

    public ChessBoardEditor() {
        super();
        board.makeBoard();
        Load l = new Load(board);
        l.load("/home/jonathan/Desktop/Personal Projects/Chess/data/BoardEditor.txt");
    }

    public JPanel getEditorWithStartAndSave() {
        return editorGUI;
    }

    public void makeEditorPane() {
        editorPane = new JPanel();
        editorPane.setLayout(new GridLayout(0, 2));
        editorPane.setPreferredSize(new Dimension(200, 735));
        editorPane.setMaximumSize(new Dimension(200, 735));
        editorPane.setMinimumSize(new Dimension(200, 735));
    }

    public void makeEditorGUI() {
        editorGUI = new JPanel();
        editorGUI.setLayout(new BoxLayout(editorGUI, BoxLayout.X_AXIS));
        editorGUI.setPreferredSize(new Dimension(950, 750));
        editorGUI.setMinimumSize(new Dimension(950, 750));
        editorGUI.setMaximumSize(new Dimension(950, 750));
        editorGUI.add(chessPanel);
//        chessPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        makeChessPieceButtons();
        editorGUI.add(editorPane);
    }

    public void makeStartGameButton() {
        JButton button = new JButton("Start official game?");
        button.setPreferredSize(new Dimension(950, 50));
        button.setMinimumSize(new Dimension(950, 50));
        button.setMaximumSize(new Dimension(950, 50));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        editorWithStartAndSave.add(button);
    }

    public void makeSaveAndQuitButton() {
        JButton button = new SaveAndQuitButton(board, "/home/jonathan/Desktop/Personal Projects/Chess/data/BoardEditor.txt");
        button.setPreferredSize(new Dimension(950, 50));
        button.setMinimumSize(new Dimension(950, 50));
        button.setMaximumSize(new Dimension(950, 50));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        editorWithStartAndSave = new JPanel();
        editorWithStartAndSave.setLayout(new BoxLayout(editorWithStartAndSave, BoxLayout.Y_AXIS));
        editorWithStartAndSave.setSize(950, 850);
        editorWithStartAndSave.add(editorGUI);
        editorWithStartAndSave.add(button);
    }

    public void makeChessPieceButtons() {
        makePieceButtons(PieceName.PAWN);
        makePieceButtons(PieceName.ROOK);
        makePieceButtons(PieceName.HORSE);
        makePieceButtons(PieceName.BISHOP);
        makePieceButtons(PieceName.QUEEN);
        makePieceButtons(PieceName.KING);
        makePieceButtons(PieceName.EMPTY);
    }

    // TODO: bug where clicking on one piece button then another doesn't refresh the piece being placed
    public void makePieceButtons(PieceName name) {
        JButton piece = new JButton(String.valueOf(name));
        piece.setForeground(Color.WHITE);
        piece.setBackground(Color.ORANGE);
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeClickablePieces(name, 0);
                refreshEditorPanel();
            }
        });
        JButton piece1 = new JButton(String.valueOf(name));
        piece1.setForeground(Color.BLACK);
        piece1.setBackground(Color.ORANGE);
        piece1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeClickablePieces(name, 1);
            }
        });
        editorPane.add(piece);
        editorPane.add(piece1);
    }

    public void makeClickablePieces(PieceName name, int teamNunber) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                buttons[i][j].setEnabled(true);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makePieceCorrespondingToName(name, teamNunber, finalJ, finalI);
                        refreshBoard();
                    }
                });
            }
        }
        refreshEditorPanel();
    }

    public void makePieceCorrespondingToName(PieceName name, int teamNumber, int x, int y) {
        switch(name) {
            case PAWN:
                pieceToPlace = new Pawn(x, y, teamNumber, board);
                break;
            case ROOK:
                pieceToPlace = new Rook(x, y, teamNumber, board);
                break;
            case HORSE:
                pieceToPlace = new Horse(x, y, teamNumber, board);
                break;
            case BISHOP:
                pieceToPlace = new Bishop(x, y, teamNumber, board);
                break;
            case QUEEN:
                pieceToPlace = new Queen(x, y, teamNumber, board);
                break;
            case KING:
                pieceToPlace = new King(x, y, teamNumber, board);
                break;
            default:
                pieceToPlace = new EmptyPiece(x, y, board);
                break;
        }
    }

    @Override
    protected boolean switchOnButtonsForTeamTurn(ChessPiece chessPiece) {
        return true;
    }

    @Override
    public void makeClickablePieces() {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                ChessPiece piece = board.getPiece(j, i);
                if (!piece.getPieceID().equals(PieceName.EMPTY)) {
                    buttons[i][j].setEnabled(true);
                }
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setUpBoardForMovement(e);
                    }
                });
            }
        }
    }

    @Override
    public void refreshBoard() {
        chessPanel.removeAll();
        setUpBoard();
        makeClickablePieces();
        colourCheckingPieces();
        chessPanel.revalidate();
        chessPanel.repaint();
    }

    public void refreshEditorPanel() {
        editorPane.removeAll();
        makeChessPieceButtons();
        editorPane.revalidate();
        editorPane.repaint();
    }
}
