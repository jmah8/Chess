package main;

public class Position {
    // 0,0 is the top left of board
    private int xcoord;
    private int ycoord;

    public Position(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }
}
