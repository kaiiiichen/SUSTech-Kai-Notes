import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Land[] lands = new Land[8];
        for (int i = 0; i < lands.length; i++) {
            lands[i] = new Land(LandColor.values()[i / 2]);
        }
        Player[] players = new Player[2];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i + 1);
        }
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextTurn(3,0);
        gameSystem.nextTurn(3,40);
        for (String s:gameSystem.currentPlayersState()) {
            System.out.println(s);
        }
    }
}
