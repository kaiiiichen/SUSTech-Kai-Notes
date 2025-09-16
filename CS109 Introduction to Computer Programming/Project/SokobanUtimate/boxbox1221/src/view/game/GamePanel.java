package view.game;

import controller.GameController;
import model.Direction;
import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
public class GamePanel extends ListenerPanel {

    private GridComponent[][] grids;
    private MapMatrix model;
    private GameController controller;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE = 50;
    private Hero hero;
    
    FileUtil fileUtil = new AdvancedFileUtil();

    public GamePanel(MapMatrix model) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE + 4, model.getHeight() * GRID_SIZE + 4);
        this.model = model;
        this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
    }

    public void setMapmatrix(int[][] mapNow) {
        this.model = new MapMatrix(mapNow);
    }

    public void initialGame() {
        java.util.List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
        String username = lines00.getFirst();
        List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
        String level = lines11.getFirst();
        List<String> lines = fileUtil.readFileToList("UserMap/" + username + "/" + "level" + level);
        int steps = Integer.parseInt(lines.getFirst());
        this.steps = steps;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                grids[i][j] = new GridComponent(i, j, model.getId(i, j), this.GRID_SIZE); //
                //if (model.getId(i,j)%10!=0 && model.getId(i,j)/10!=0) System.out.println("getid="+model.getId(i,j));
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                //Ten digit maps to Box or Hero in corresponding location in the GridComponent. (Changed value)
                switch (model.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10, i, j, false));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j, false);

                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();
    }

    public int getSteps() {//
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.UP)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)) {
            this.afterMove();
        }
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public JLabel getStepLabel() {
        return stepLabel;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }

    public void restartGame() {
        steps = 0; //
        this.stepLabel.setText(String.format("Step: %d", this.steps)); //
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                if (grids[i][j].getHero() != null) {
                    grids[i][j].removeHeroFromGrid();
                }
                if (grids[i][j].getBox() != null) {
                    grids[i][j].removeBoxFromGrid();
                }
                switch (model.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10, i, j, false));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j, false);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
    }


}