public class Person {
    private Direction[] directions;// represents the directions the preson can go
    private int i;// represents the vertical position
    private int j;// represents the horizontal position
    private int index;// represents the index of current directions

    public Person(int i, int j, int index) {
        this.i = i;
        this.j = j;
        this.index = index;
        directions = new Direction[8];
        directions[0] = new Direction(0, 1);
        directions[1] = new Direction(-1, 1);
        directions[2] = new Direction(-1, 0);
        directions[3] = new Direction(-1, -1);
        directions[4] = new Direction(0, -1);
        directions[5] = new Direction(1, -1);
        directions[6] = new Direction(1, 0);
        directions[7] = new Direction(1, 1);
    }

    public void changeDirection() {
        index = (index + 1) % directions.length;
    }

    public void walk(int step) {
        i += step * directions[index].row;
        j += step * directions[index].col;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", i, j);
    }

    public static void main(String[] args) {
        Person p = new Person(0,-1,0);
        p.walk(3);
        p.changeDirection();
        System.out.println(p);
        p.walk(2);
        p.changeDirection();
        System.out.println(p);
        p.walk(5);
        p.changeDirection();
        System.out.println(p);
    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

class Direction {
    int row;
    int col;

    public Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}