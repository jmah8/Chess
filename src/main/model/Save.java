package main.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

    private Board board;

    public Save(Board board) {
        this.board = board;
    }

    public void save(String pathName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathName));
            out.writeObject(board);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
