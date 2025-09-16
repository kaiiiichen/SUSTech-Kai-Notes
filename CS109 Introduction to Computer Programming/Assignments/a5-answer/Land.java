public class Land {
    private House house;
    private LandColor color;


    public Land(LandColor color) {
        this.color = color;
    }

    public LandColor getColor() {
        return color;
    }

    public House getHouse() {
        return house;
    }

    public void removeHouse() {
        this.house = null;
        this.color.decrementCount();
        if (this.color.getCount() == 0) {
            this.color.setPlayerId(0);
        }
    }

    public void setHouse(House house) {
        this.house = house;
        this.color.setPlayerId(house.getPlayer().getId());
        this.color.incrementCount();
    }

    public static void initialColor() {
        for (LandColor c : LandColor.values()) {
            c.setPlayerId(0);
            c.setCount(0);
        }
    }

    public String toString() {
        return String.format("Land %s: %s", this.color, house != null ? house.toString() : "");
    }
}
