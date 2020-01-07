package main.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
    private Board board;

    public Load(Board board) {
        this.board = board;
    }

    public void load(String pathName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathName));
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
