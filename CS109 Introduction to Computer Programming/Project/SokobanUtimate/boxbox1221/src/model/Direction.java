package model;

public enum Direction {
    LEFT(0, -1), UP(-1, 0), RIGHT(0, 1), DOWN(1, 0),
    ;
    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}