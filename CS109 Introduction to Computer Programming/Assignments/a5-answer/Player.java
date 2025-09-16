public class Player {
    private int id;
    private double money;
    private boolean isActive;
    private int location;

    public Player(int id) {
        this.id = id;
        this.money = 100;
        this.location = 0;
        this.isActive = true;
    }

    public boolean buildHouse(Land land, int housePrice) {
        if (land.getHouse() != null) {
            return false;
        }
        if (money < housePrice || housePrice == 0) {
            return false;
        }
        money -= housePrice;
        land.setHouse(new House(this, housePrice));
        return true;
    }

    public double payRent(int housePrice) {
        double changedPrice = housePrice * 0.5;
        if (money < changedPrice) {
            isActive = false;
            double leftMoney = money;
            money -= changedPrice;
            return leftMoney;
        }
        money -= changedPrice;
        return changedPrice;
    }

    public void collectRent(double rentPrice) {
        money += rentPrice;
    }

    public int getId() {
        return id;
    }


    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        if (isActive) {
            return String.format("Player %d: at %d, has %.1f", id, location, money);
        } else {
            return String.format("Player %d: Failed", id);
        }
    }
}
