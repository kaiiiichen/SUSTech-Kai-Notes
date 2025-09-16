package view.game;

import java.util.ArrayList;
import java.util.List;

public class GameRecord {
    int[][] mapMatrix;

    public int[][] getMapMatrix(){
        return mapMatrix;
    }

    public void convertToMapMatrix(List<String> readlines) {
        this.mapMatrix = new int[readlines.size()][];

        for (int i = 0; i < readlines.size(); i++) {
            String[] pieces = readlines.get(i).split(",");
            this.mapMatrix[i] = new int[pieces.length];
            for (int j = 0; j < pieces.length; j++) {
                this.mapMatrix[i][j] = Integer.parseInt(pieces[j]);
            }
        }
    }

    public void playing(int x, int y, int player) {
        this.mapMatrix[x][y] = player;
    }

    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int[] ints : this.mapMatrix) {
            sb.setLength(0);
            for (int anInt : ints) {
                sb.append(anInt).append(",");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }
        return lines;
    }

}
