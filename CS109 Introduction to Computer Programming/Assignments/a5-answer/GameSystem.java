import java.util.ArrayList;
import java.util.Arrays;

public class GameSystem {
    private final Land[] LANDS;
    private Player[] players;
    private ArrayList<Player> playerList;//2-5
    private final int LANDS_SIZE;
    private int currentPlayer;
    private boolean gameOver;


    public GameSystem(Player[] players, Land[] lands) {
        this.playerList = new ArrayList<>();
        this.players = players;
        this.playerList.addAll(Arrays.asList(players));
        this.LANDS = lands;
        this.LANDS_SIZE = lands.length;
        this.currentPlayer = -1;
        this.gameOver = false;
        Land.initialColor();
    }

    public Player nextPlayer() {
        if (!playerList.isEmpty()) {
            currentPlayer++;
            currentPlayer %= playerList.size();
        }
        while (dealFailedPlayer()) {
            currentPlayer++;
        }
        return playerList.get(currentPlayer);
    }

    public Player getCurrentPlayer() {
        if (currentPlayer == -1)
            return null;
        return playerList.get(currentPlayer);
    }

    public boolean dealFailedPlayer() {
        Player player = getCurrentPlayer();

        if (player == null) {
            return false;
        }
        if (!player.isActive()) {
            currentPlayer--;
            playerList.remove(player);
            for (Land l : LANDS) {
                if (l.getHouse() != null) {
                    if (l.getHouse().getPlayer().getId() == player.getId()) {
                        l.removeHouse();
                    }
                }
            }
            if (playerList.size() == 1) {
                gameOver = true;
            }
            return true;
        }
        return false;
    }

    public void nextTurn(int step, int cost) {
        //1. find next Player
        Player p = nextPlayer();
        //2. move to target Location
        int currentLocation = p.getLocation() + step;
        currentLocation %= LANDS_SIZE;
        p.setLocation(currentLocation);
        //3. get Land
        Land currentLand = LANDS[currentLocation];

        //if there has a house
        if (currentLand.getHouse() != null) {
            Player targetPlayer = currentLand.getHouse().getPlayer();
            if (targetPlayer.getId() != p.getId()) {
                double rent = p.payRent(currentLand.getHouse().getHousePrice());
                dealFailedPlayer();
                targetPlayer.collectRent(rent);
            }
        } else { //if there is no house
            if (cost != 0) {
                int playerId = currentLand.getColor().getPlayerId();
                if (playerId == 0 || playerId == p.getId()) {
                    p.buildHouse(currentLand, cost);
                }
            }
        }
    }

    public String[] currentPlayersState() {
        String[] states = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            states[i] = players[i].toString();
        }
        return states;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String[] currentLandsState() {
        int houseCount = 0;
        for (Land l : LANDS) {
            if (l.getHouse() != null) {
                houseCount++;
            }
        }
        String[] states = new String[houseCount];
        int index = 0;
        for (Land l : LANDS) {
            if (l.getHouse() != null) {
                states[index++] = l.toString();
            }
        }
        return states;
    }
}



