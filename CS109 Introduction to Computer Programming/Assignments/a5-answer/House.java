public class House {
    private Player player;
    private int housePrice;

    public House(Player player, int housePrice) {
        this.player = player;
        this.housePrice = housePrice;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getHousePrice() {
        return housePrice;
    }

    @Override
    public String toString() {
        return String.format("[H P%d: %d]", player.getId(), housePrice);
    }
}
