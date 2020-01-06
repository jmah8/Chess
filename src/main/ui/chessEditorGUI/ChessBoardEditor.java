package main.ui.chessEditorGUI;

import main.model.Position;
import main.model.pieces.*;
import main.ui.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoardEditor extends ChessBoard {
    private JPanel editorPane;
    private ChessPiece pieceToPlace;
    private JPanel editorGUI;

    public ChessBoardEditor() {
        super();
        board.makeBoard();
    }

    public JPanel getEditorGUI() {
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
        editorGUI.setSize(950, 750);
        editorGUI.add(chessPanel);
//        chessPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        makeChessPieceButtons();
        editorGUI.add(editorPane);
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

    public void makePieceButtons(PieceName name) {
        JButton piece = new JButton(String.valueOf(name));
        piece.setForeground(Color.WHITE);
        piece.setBackground(Color.ORANGE);
        piece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeClickablePieces(name, 0);
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
//                        pieceToPlace.setPosition(new Position(finalJ, finalI));
//                        board.placePiece(pieceToPlace);
                        makePieceCorrespondingToName(name, teamNunber, finalJ, finalI);
                        refreshBoard();
                    }
                });
            }
        }
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
        chessPanel.revalidate();
        chessPanel.repaint();
    }
}
