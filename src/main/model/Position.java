package main.model;

import java.util.Objects;

public class Position {
    // 0,0 is the top left of board. Right is positive and down is positive
    private int xcoord;
    private int ycoord;

    // REQUIRES: xcoord and ycoord 0 <= x <= 7
    public Position(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public void setXYcoord(int xcoord, int ycoord) {
        setXcoord(xcoord);
        setYcoord(ycoord);
    }

    public int getXcoord() {
        return xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return xcoord == position.xcoord &&
                ycoord == position.ycoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord);
    }
}
