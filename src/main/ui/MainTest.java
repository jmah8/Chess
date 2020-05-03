package main.ui;

import main.model.Board;
import main.model.Load;
import main.model.Save;

public class MainTest {

    public static void main(String[] args) {
        Board b = new Board();
        b.makeBoard();
        b.fillBoard();
        System.out.println(b.getPiece(3, 0));
        Save save = new Save(b);
        save.save("/home/jonathan/Desktop/Personal Projects/Chess/data/ChessGame.txt");
        b.makeBoard();
        save.save("/home/jonathan/Desktop/Personal Projects/Chess/data/BoardEditor.txt");
        System.out.println(b.getPiece(3, 0));
        Load load = new Load(b);
        load.load("/home/jonathan/Desktop/Personal Projects/Chess/data/Board.txt");
        System.out.println(b.getPiece(3, 0));
    }
}
