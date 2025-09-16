public enum LandColor {
    RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, WHITE, BLACK;

    private int playerId;
    private int count;

    LandColor() {
        this.playerId = 0;
        this.count = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(){
        this.count++;
    }

    public void decrementCount(){
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
