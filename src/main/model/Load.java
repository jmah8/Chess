package main.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
    private Board board;

    public Load(Board board) {
        this.board = board;
        load();
    }

    public void load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/jonathan/Desktop/Personal Projects/Chess/data/Board.txt"));
            Board b = (Board) in.readObject();
            in.close();
            board.setBoard(b.getBoard());
            board.setEventLog(b.getEventLog());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
