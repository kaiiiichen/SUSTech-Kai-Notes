import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Land1:
0 Land ORANGE:
1 Land BLUE:
2 Land BLACK:
3 Land YELLOW:
4 Land PURPLE:
5 Land RED:
6 Land GREEN:
7 Land WHITE:
8 Land ORANGE:
9 Land BLUE:
10 Land BLACK:
11 Land YELLOW:
12 Land PURPLE:
13 Land RED:
14 Land GREEN:
15 Land WHITE:
16 Land ORANGE:
17 Land BLUE:
18 Land BLACK:
19 Land YELLOW:

Land2:
0 Land RED:
1 Land ORANGE:
2 Land YELLOW:
3 Land GREEN:
4 Land RED:
5 Land ORANGE:
6 Land YELLOW:
7 Land GREEN:
8 Land RED:
9 Land ORANGE:
10 Land YELLOW:
11 Land GREEN:
12 Land RED:
13 Land ORANGE:
14 Land YELLOW:
15 Land GREEN:
 */
public class A5LocalTest {
    private Land[] lands;
    private Land[] lands2;
    private Player[] players;

    @BeforeEach
    public void prepareDatas() {
        players = new Player[3];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i + 1);
        }
        lands = new Land[20];
        for (int i = 0; i < lands.length; i++) {
            int index = (i * 3 + 1) % 8;
            lands[i] = new Land(LandColor.values()[index]);
        }
        lands2 = new Land[16];
        for (int i = 0; i < lands2.length; i++) {
            int index = i % 4;
            lands2[i] = new Land(LandColor.values()[index]);
        }
    }

    @Test
    public void q1Test01PayRent() {
        assertEquals(25, players[0].payRent(50));
        assertEquals(75, players[0].payRent(150));
        assertEquals(50, players[1].payRent(100));
        assertEquals(50, players[1].payRent(101));
        players[2].collectRent(25);
        players[2].collectRent(75);
        players[2].collectRent(50);
        players[2].collectRent(50);
        assertEquals("Player 1: at 0, has 0.0", players[0].toString());
        assertEquals("Player 2: Failed", players[1].toString());
        assertEquals("Player 3: at 0, has 300.0", players[2].toString());
    }

    @Test
    public void q1Test02PlayerBuildHouse() {
        assertTrue(players[2].buildHouse(lands[0], 30));
        assertTrue(players[2].buildHouse(lands[1], 50));
        assertFalse(players[1].buildHouse(lands[0], 20));
        assertTrue(players[0].buildHouse(lands[13], 50));
        assertTrue(players[0].buildHouse(lands[15], 30));
        assertFalse(players[0].buildHouse(lands[5], 30));

        assertEquals("Player 1: at 0, has 20.0", players[0].toString());
        assertEquals("Player 2: at 0, has 100.0", players[1].toString());
        assertEquals("Player 3: at 0, has 20.0", players[2].toString());

        assertEquals("Land ORANGE: [H P3: 30]", lands[0].toString());
        assertEquals("Land BLUE: [H P3: 50]", lands[1].toString());
        assertEquals("Land RED: [H P1: 50]", lands[13].toString());
        assertEquals("Land WHITE: [H P1: 30]", lands[15].toString());

        int count = 0;
        for (Land l : lands) {
            if (l.getHouse() != null) {
                count++;
            }
        }
        assertEquals(4, count);
    }

    @Test
    public void q2Test01CheckNextPlayer() {
        GameSystem gameSystem = new GameSystem(players, lands);
        assertEquals(players[0], gameSystem.nextPlayer());
        assertEquals(players[1], gameSystem.nextPlayer());
        assertEquals(players[2], gameSystem.nextPlayer());
        assertEquals(players[0], gameSystem.nextPlayer());
        players[0].setActive(false);
        assertEquals(players[1], gameSystem.nextPlayer());
        assertEquals(players[2], gameSystem.nextPlayer());
        players[2].setActive(false);
        assertEquals(players[1], gameSystem.nextPlayer());
    }

    @Test
    public void q2Test02CheckDealFailedPlayer() {
        GameSystem gameSystem = new GameSystem(players, lands);
        assertEquals(players[0], gameSystem.nextPlayer());
        assertEquals(players[1], gameSystem.nextPlayer());
        assertEquals(players[2], gameSystem.nextPlayer());

        gameSystem.getCurrentPlayer().payRent(250);
        gameSystem.dealFailedPlayer();

        assertEquals("Player 1: at 0, has 100.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 0, has 100.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: Failed", gameSystem.currentPlayersState()[2]);

        assertEquals(players[0], gameSystem.nextPlayer());
        assertEquals(players[1], gameSystem.nextPlayer());
        assertEquals(players[0], gameSystem.nextPlayer());
        gameSystem.getCurrentPlayer().payRent(250);
        gameSystem.dealFailedPlayer();

        assertEquals("Player 1: Failed", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 0, has 100.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: Failed", gameSystem.currentPlayersState()[2]);
        assertEquals(players[1], gameSystem.nextPlayer());
        assertEquals(players[1], gameSystem.nextPlayer());
    }

    @Test
    public void q2Test03CheckDealFailedPlayer() {
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextPlayer().buildHouse(lands[2], 100);//BLACK
        assertFalse(gameSystem.dealFailedPlayer());
        gameSystem.getCurrentPlayer().payRent(120);
        assertTrue(gameSystem.dealFailedPlayer());
        assertTrue(gameSystem.nextPlayer().buildHouse(lands[10], 40));//BLACK
        assertEquals("Player 1: Failed", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 0, has 60.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 0, has 100.0", gameSystem.currentPlayersState()[2]);
        assertEquals("Land BLACK: [H P2: 40]", gameSystem.currentLandsState()[0]);
        gameSystem.getCurrentPlayer().payRent(220);
        assertTrue(gameSystem.dealFailedPlayer());
        assertEquals(0, gameSystem.currentLandsState().length);
        assertTrue(gameSystem.isGameOver());
    }

    @Test
    public void q2Test04NextStepBuildHouseWithoutConflict() {
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextTurn(1, 35);
        gameSystem.nextTurn(2, 45);
        gameSystem.nextTurn(3, 55);
        gameSystem.nextTurn(3, 35);
        gameSystem.nextTurn(3, 25);
        gameSystem.nextTurn(3, 15);

        assertEquals("Player 1: at 4, has 30.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 5, has 30.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 6, has 30.0", gameSystem.currentPlayersState()[2]);
        assertEquals("Land BLUE: [H P1: 35]", gameSystem.currentLandsState()[0]);
        assertEquals("Land BLACK: [H P2: 45]", gameSystem.currentLandsState()[1]);
        assertEquals("Land YELLOW: [H P3: 55]", gameSystem.currentLandsState()[2]);
        assertEquals("Land PURPLE: [H P1: 35]", gameSystem.currentLandsState()[3]);
        assertEquals("Land RED: [H P2: 25]", gameSystem.currentLandsState()[4]);
        assertEquals("Land GREEN: [H P3: 15]", gameSystem.currentLandsState()[5]);
    }

    @Test
    public void q2Test05CheckNextStepPayRentWithoutFailed() {
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextTurn(5, 35);
        gameSystem.nextTurn(6, 45);
        gameSystem.nextTurn(5, 0);
        gameSystem.nextTurn(1, 20);

        assertEquals("Player 1: at 6, has 60.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 6, has 77.5", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 5, has 82.5", gameSystem.currentPlayersState()[2]);
        assertEquals("Land RED: [H P1: 35]", gameSystem.currentLandsState()[0]);
        assertEquals("Land GREEN: [H P2: 45]", gameSystem.currentLandsState()[1]);
    }

    @Test
    public void q2Test06checkMorePlayerInOneLand() {
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextTurn(4, 0);
        gameSystem.nextTurn(4, 0);
        gameSystem.nextTurn(4, 50);

        assertEquals("Player 1: at 4, has 100.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 4, has 100.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 4, has 50.0", gameSystem.currentPlayersState()[2]);
        gameSystem.nextTurn(3, 50);
        gameSystem.nextTurn(3, 0);
        gameSystem.nextTurn(3, 0);

        assertEquals("Player 1: at 7, has 100.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 7, has 75.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 7, has 25.0", gameSystem.currentPlayersState()[2]);

    }


    @Test
    public void q2Test07checkNextStepBuildHouse() {
        GameSystem gameSystem = new GameSystem(players, lands);

        gameSystem.nextTurn(3, 20);//3 YELLOW:
        gameSystem.nextTurn(5, 30);//5 RED:
        gameSystem.nextTurn(6, 15);//6 GREEN:

        gameSystem.nextTurn(4, 20);//7 WHITE:
        gameSystem.nextTurn(3, 30);//8 ORANGE:
        gameSystem.nextTurn(4, 15);//10 BLACK:

        gameSystem.nextTurn(2, 23);//9 BLUE:
        gameSystem.nextTurn(4, 10);//12 PURPLE:
        gameSystem.nextTurn(5, 30);//15 WHITE:

        gameSystem.nextTurn(5, 0);//14
        gameSystem.nextTurn(5, 0);//17
        gameSystem.nextTurn(5, 0);//0

        gameSystem.nextTurn(6, 10);//0 ORANGE:
        gameSystem.nextTurn(5, 10);//2 BLACK:
        gameSystem.nextTurn(4, 10);//4 PURPLE:

        assertEquals("Player 1: at 0, has 37.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 2, has 30.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 4, has 70.0", gameSystem.currentPlayersState()[2]);

        assertEquals("Land YELLOW: [H P1: 20]", gameSystem.currentLandsState()[0]);
        assertEquals("Land RED: [H P2: 30]", gameSystem.currentLandsState()[1]);
        assertEquals("Land GREEN: [H P3: 15]", gameSystem.currentLandsState()[2]);
        assertEquals("Land WHITE: [H P1: 20]", gameSystem.currentLandsState()[3]);
        assertEquals("Land ORANGE: [H P2: 30]", gameSystem.currentLandsState()[4]);
        assertEquals("Land BLUE: [H P1: 23]", gameSystem.currentLandsState()[5]);
        assertEquals("Land BLACK: [H P3: 15]", gameSystem.currentLandsState()[6]);
        assertEquals("Land PURPLE: [H P2: 10]", gameSystem.currentLandsState()[7]);
    }

    @Test
    public void q2Test07CheckNextStepWithPayRent() {
        GameSystem gameSystem = new GameSystem(players, lands);
        gameSystem.nextTurn(1, 20);//1 Land BLUE:
        gameSystem.nextTurn(3, 20);//3 Land YELLOW:
        gameSystem.nextTurn(5, 20);//5 Land RED:

        gameSystem.nextTurn(4, 30);//5 Land RED:
        gameSystem.nextTurn(2, 0);//5 Land RED:
        gameSystem.nextTurn(6, 30);//11 Land YELLOW:

        gameSystem.nextTurn(5, 30);//10 Land BLACK:
        gameSystem.nextTurn(5, 0);//10 Land BLACK:
        gameSystem.nextTurn(4, 300);//15 Land WHITE:

        gameSystem.nextTurn(5, 30);//15 Land WHITE:
        gameSystem.nextTurn(6, 25);//16 Land ORANGE:
        gameSystem.nextTurn(1, 0);//16 Land ORANGE:
        assertEquals("Player 1: at 15, has 25.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 16, has 42.5", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 16, has 87.5", gameSystem.currentPlayersState()[2]);

        assertEquals("Land BLUE: [H P1: 20]", gameSystem.currentLandsState()[0]);
        assertEquals("Land YELLOW: [H P2: 20]", gameSystem.currentLandsState()[1]);
        assertEquals("Land RED: [H P3: 20]", gameSystem.currentLandsState()[2]);
        assertEquals("Land BLACK: [H P1: 30]", gameSystem.currentLandsState()[3]);
        assertEquals("Land WHITE: [H P1: 30]", gameSystem.currentLandsState()[4]);
        assertEquals("Land ORANGE: [H P2: 25]", gameSystem.currentLandsState()[5]);
    }

    @Test
    public void q2Test08checkNextStepWithPlayerFail() {
        GameSystem gameSystem = new GameSystem(players, lands2);
        gameSystem.nextTurn(4, 100);//4 Land RED:
        gameSystem.nextTurn(4, 20);//
        gameSystem.nextTurn(4, 0);//

        gameSystem.nextTurn(5, 80);//9 Land ORANGE:
        gameSystem.nextTurn(5, 40);//9 Land ORANGE:
        gameSystem.nextTurn(6, 40);//10 Land YELLOW
        assertEquals("Player 1: at 9, has 60.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 9, has 10.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 10, has 10.0", gameSystem.currentPlayersState()[2]);
        assertEquals("Land RED: [H P1: 100]", gameSystem.currentLandsState()[0]);
        assertEquals("Land ORANGE: [H P1: 80]", gameSystem.currentLandsState()[1]);
        assertEquals("Land YELLOW: [H P3: 40]", gameSystem.currentLandsState()[2]);

        gameSystem.nextTurn(6, 50);//15 Land GREEN:
        gameSystem.nextTurn(5, 10);//10 Land YELLOW
        gameSystem.nextTurn(5, 10);//0 Land RED:
        assertEquals("Player 1: at 15, has 20.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 14, has 10.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: Failed", gameSystem.currentPlayersState()[2]);
        assertEquals(3, gameSystem.currentLandsState().length);
        assertEquals("Land GREEN: [H P1: 50]", gameSystem.currentLandsState()[2]);

        gameSystem.nextTurn(3, 0);//2 Land YELLOW
        gameSystem.nextTurn(4, 10);//2 Land YELLOW
        gameSystem.nextTurn(2, 0);//4 Land RED

        assertEquals("Player 1: at 4, has 20.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 2, has 0.0", gameSystem.currentPlayersState()[1]);
        assertEquals(4, gameSystem.currentLandsState().length);
        assertEquals("Land GREEN: [H P1: 50]", gameSystem.currentLandsState()[3]);
    }

    @Test
    public void q2Test09CheckNextStepWithGameOver() {
        GameSystem gameSystem = new GameSystem(players, lands2);
        assertFalse(gameSystem.isGameOver());
        gameSystem.nextTurn(5, 100);//5 Land Orange:
        gameSystem.nextTurn(4, 20);//4 Land red
        gameSystem.nextTurn(5, 0);//
        assertEquals("Player 1: at 5, has 50.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 4, has 80.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 5, has 50.0", gameSystem.currentPlayersState()[2]);
        assertEquals("Land RED: [H P2: 20]", gameSystem.currentLandsState()[0]);
        assertEquals("Land ORANGE: [H P1: 100]", gameSystem.currentLandsState()[1]);

        gameSystem.nextTurn(3, 50);//8 Land RED:
        gameSystem.nextTurn(6, 80);//10 Land YELLOW
        gameSystem.nextTurn(5, 0);// 10 Land YELLOW
        assertEquals("Player 1: at 8, has 50.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 10, has 40.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: at 10, has 10.0", gameSystem.currentPlayersState()[2]);
        assertEquals("Land YELLOW: [H P2: 80]", gameSystem.currentLandsState()[2]);

        gameSystem.nextTurn(5, 50);//13 Land YELLOW:
        gameSystem.nextTurn(3, 0);//13 Land YELLOW:
        gameSystem.nextTurn(3, 0);//13 Land YELLOW:

        assertEquals("Player 1: at 13, has 35.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: at 13, has 15.0", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: Failed", gameSystem.currentPlayersState()[2]);
        assertFalse(gameSystem.isGameOver());

        gameSystem.nextTurn(4, 35);//1 Land ORANGE:
        gameSystem.nextTurn(4, 0);//1 Land ORANGE:

        assertEquals("Player 1: at 1, has 15.0", gameSystem.currentPlayersState()[0]);
        assertEquals("Player 2: Failed", gameSystem.currentPlayersState()[1]);
        assertEquals("Player 3: Failed", gameSystem.currentPlayersState()[2]);

        assertEquals("Land ORANGE: [H P1: 35]", gameSystem.currentLandsState()[0]);
        assertEquals("Land ORANGE: [H P1: 100]", gameSystem.currentLandsState()[1]);
        assertEquals("Land ORANGE: [H P1: 50]", gameSystem.currentLandsState()[2]);

        assertTrue(gameSystem.isGameOver());
    }
}

