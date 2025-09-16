public enum Direction {
    NORTH(-1,0), SOUTH(1,0), EAST(0,1), WEST(0,-1);

    //Add attributes
    private int row;
    private int col;

    //Add constructor:
    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //Add methods
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("%s (%d, %d)",this.name(),this.row, this.col);
    }
}
