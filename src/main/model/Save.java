package main.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

    private Board board;

    public Save(Board board) {
        this.board = board;
        save();
    }

    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/jonathan/Desktop/Personal Projects/Chess/data/Board.txt"));
            out.writeObject(board);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
