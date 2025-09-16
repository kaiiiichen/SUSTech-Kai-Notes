//2024.12.9

package controller;

import model.Direction;
import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;
import view.game.*;
import view.game.GameFrame; //
import view.load.LoadingFailureFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;
    private final MapMatrix model;
    private final GameFrame Frame;
    private LoadingFailureFrame loadingFailureFrame2;
    private boolean backk;
    private boolean pulll;
    private boolean undo;
    private int preboxTRow=-1,preboxTCol=-1;

    int [][] pre_map=new int[233][233];


    FileUtil fileUtil = new AdvancedFileUtil();

    public boolean isPulll() {
        return pulll;
    }

    public void setPulll(boolean pulll) {
        this.pulll = pulll;
    }

    public boolean isUndo() {
        return undo;
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
    }

    public boolean isBackk() {
        return backk;
    }

    public void setBackk(boolean backk) {
        this.backk = backk;
    }
    //    private GameFrame gameFrame;

    public GameController(GamePanel view, MapMatrix model, GameFrame frame) {
        this.view = view;
        this.model = model;
        this.Frame = frame;
        view.setController(this);
    }


//    public void setGameFrame(GameFrame gameFrame) {
//        this.gameFrame = gameFrame; // 提供一个方法来设置 GameFrame
//    }
//
//
//    public void closeGameFrame() {
//        if (this.gameFrame != null) {
//            this.gameFrame.setVisible(false); // 关闭 GameFrame
//            this.gameFrame.dispose(); // 释放资源
//        }
//    }


    public void restartGame() {
        System.out.println("Do restart game here");
        this.model.resetMapMatrix();
        this.view.restartGame();

    }

    public boolean pd_victory() {
        int[][] mapp = model.getMatrix();
//        for (int i = 0; i < mapp.length; i++) {
//            for (int j = 0; j < mapp[i].length; j++) {
//                System.out.print(mapp[i][j] + " ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < mapp.length; i++) {
            for (int j = 0; j < mapp[i].length; j++) {
                if (mapp[i][j] == 2 || mapp[i][j] == 22) {
                    return false;
                }
            }
        }

//        List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
//        String username = lines0.getFirst();
//        List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
//        String level = lines1.getFirst();
//
//        int[][] userMapNow = model.getMatrix();
//        List<String> lines = new ArrayList<>();
//        int steps = view.getSteps();
//        int time = Frame.getTime();
//        lines.add(Integer.toString(0));
//        lines.add(Integer.toString(0));
//
//        // todo: we have refreshed the record, we still need to record the score.
//
//        lines.add("new");
//        StringBuilder sb = new StringBuilder();
//        for (int[] ints : userMapNow) {
//            sb.setLength(0);
//            for (int anInt : ints) {
//                sb.append(anInt).append(",");
//            }
//            sb.setLength(sb.length() - 1);
//            lines.add(sb.toString());
//        }
//        fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);

        return true;
    }

    public void rewriteFile(){
        List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
        String username = lines0.getFirst();
        List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
        String level = lines1.getFirst();

        List<String> lines = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
        fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);
        System.out.println("Rewriting process done!");
    }

    public boolean pd_failure() {
        //      System.out.println("Do failure game here");
        int[][] mapp = model.getMatrix();
//        System.out.println("pd_failure");
//        for (int i=0; i<mapp.length; i++) {
//            for (int j=0; j<mapp[i].length; j++) {
//                System.out.print(mapp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("pd_failure");
        for (int i = 1; i < mapp.length - 1; i++) {
            for (int j = 1; j < mapp[i].length - 1; j++) {
                if (mapp[i][j] != 10) continue;
                //int temp=0;
                //if (mapp[i-1][j]==1) temp++; if (mapp[i+1][j]==1) temp++;
                //if (mapp[i][j-1]==1) temp++; if (mapp[i][j+1]==1) temp++;
//                if (mapp[i-1][j]==10) temp++; if (mapp[i+1][j]==10) temp++;
//                if (mapp[i][j-1]==10) temp++; if (mapp[i][j+1]==10) temp++;
//                if (mapp[i-1][j]==12) temp++; if (mapp[i+1][j]==12) temp++;
//                if (mapp[i][j-1]==12) temp++; if (mapp[i][j+1]==12) temp++;
                //if (temp>=2) return true;
//                if ((mapp[i-1][j]==1||mapp[i-1][j]/10==1)&&(mapp[i][j-1]==1||mapp[i][j-1]/10==1)) return true;
//                if ((mapp[i-1][j]==1||mapp[i-1][j]/10==1)&&(mapp[i][j+1]==1||mapp[i][j+1]/10==1)) return true;
//                if ((mapp[i+1][j]==1||mapp[i+1][j]/10==1)&&(mapp[i][j-1]==1||mapp[i][j-1]/10==1)) return true;
//                if ((mapp[i+1][j]==1||mapp[i+1][j]/10==1)&&(mapp[i][j+1]==1||mapp[i][j+1]/10==1)) return true;
                if (mapp[i - 1][j] == 1 && mapp[i][j + 1] == 1) {
                    return true;
                }
                if (mapp[i - 1][j] == 1 && mapp[i][j - 1] == 1) {
                    return true;
                }
                if (mapp[i + 1][j] == 1 && mapp[i][j + 1] == 1) {
                    return true;
                }
                if (mapp[i + 1][j] == 1 && mapp[i][j - 1] == 1) {
                    return true;
                }
            }
        }
        boolean tf=false; boolean appear=false;
        for (int i=1;i<mapp.length-1;i++) {
            for (int j=1;j<mapp[i].length-1;j++) {
                if (mapp[i][j] != 10) continue;
                appear=true;
                boolean canmove=false;
                if (mapp[i-1][j]!=1&&mapp[i-1][j]!=10&&mapp[i+1][j]!=1&&mapp[i+1][j]!=10) {
                    canmove=true;
                }
                if (mapp[i][j-1]!=1&&mapp[i][j-1]!=10&&mapp[i][j+1]!=1&&mapp[i][j+1]!=10) {
                    canmove=true;
                }
                if (canmove==true) {
                    tf=true;
                    //System.out.println("i="+i+" j="+j);
                }
            }
        }
        System.out.println("fail0");
        if (tf==false&&appear==true) return true;

        for (int i=1;i<mapp.length-1;i++) {
            if (i != 1 && i != mapp.length - 2) continue;
            boolean findbox=false;
            boolean findtarget=false;
            for (int j=1;j<mapp[i].length-1;j++) {
                if (mapp[i][j]==10) findbox=true;
                if (mapp[i][j]==2) findtarget=true;
            }
            if (findbox==true&&findtarget==false) {
                System.out.println("fail1");
                return true;
            }
        }

        for (int i=1;i<mapp.length-1;i++) {
            for (int j=1;j<mapp[i].length-1;j++) {
                if (j!=1&&j!=mapp[i].length-2) continue;
                boolean findbox=false;
                boolean findtarget=false;
                for (int ii=1;ii<mapp.length-1;ii++) {
                    if (mapp[ii][j]==10) findbox=true;
                    if (mapp[ii][j]==2) findtarget=true;
                }
                if (findbox==true&&findtarget==false) {
                    System.out.println("fail2");
                    return true;
                }
            }

        }

        return false;
    }


//                if (isBackk()) {
//        System.out.println("Backk");
//        tRow=pretRow;tCol=pretCol;
//        boxTRow=preboxTRow;boxTCol=preboxTCol;
//        model.getMatrix()[row][col] -= 20;
//        model.getMatrix()[tRow][tCol] += 20;
//        model.getMatrix()[tRow][tCol] -= 10;
//        model.getMatrix()[boxTRow][boxTCol] += 10;
//        //update hero in GamePanel
//        Hero h = currentGrid.removeHeroFromGrid();
//        targetGrid.setHeroInGrid(h);
//        //update box in GamePanel
//        Box b= view.getGridComponent(tRow,tCol).removeBoxFromGrid();
//        view.getGridComponent(boxTRow, boxTCol).setBoxInGrid(b);
//        //Update the row and column attribute in hero and box
//        h.setRow(tRow); h.setCol(tCol);
//        b.setRow(boxTRow); b.setCol(boxTCol);
//        this.setBackk(false);
//    }
//

    public boolean doMove(int row, int col, Direction direction) {
        String username;
        String level;

//        List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
//        username = lines0.get(0);
//        List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
//        level = lines1.get(0);
//
//        List<String> lines2 = fileUtil.readFileToList("UserMap/" + username + "/level" + level + ".txt");
//        int[][] userMapNow = new int[lines2.size() - 3][];
//        for (int i = 0; i < lines2.size() - 3; i++) {
//            String[] pieces = lines2.get(i).split(",");
//            userMapNow[i] = new int[pieces.length];
//            for (int j = 0; j < pieces.length; j++) {
//                userMapNow[i][j] = Integer.parseInt(pieces[j]);
//            }
//        }

        //        this.setGameFrame(gameFrame);
        GridComponent currentGrid = view.getGridComponent(row, col);

        //target row can column.
        //int pretRow=1, pretCol=1,preboxTRow=1,preboxTCol=1;
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        int[][] map = model.getMatrix();



        //write in the txt
        if (isUndo()) {
            System.out.println("Do pulll");
            System.out.println("row=" + row);
            System.out.println("col=" + col);
            System.out.println("tRow: " + tRow);
            System.out.println("tCol: " + tCol);

            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;

            int pboxTRow = 1, pboxTCol = 1;
            int boxTRow = row, boxTCol = col;
            pboxTRow = row - (tRow - row);
            pboxTCol = col - (tCol - col);
//            System.out.println("boxTRow=" + boxTRow);
//            System.out.println("boxTCol=" + boxTCol);
//            System.out.println("pboxTRow=" + pboxTRow);
//            System.out.println("pboxTCol=" + pboxTCol);
            model.getMatrix()[pboxTRow][pboxTCol] -= 10;
            model.getMatrix()[boxTRow][boxTCol] += 10;

            //System.out.println("qwq1");

            //update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //System.out.println("qwq2");
            //update box in GamePanel
            Box b = view.getGridComponent(pboxTRow, pboxTCol).removeBoxFromGrid();
            view.getGridComponent(boxTRow, boxTCol).setBoxInGrid(b);
            //System.out.println("qwq3");

            preboxTRow=boxTRow; preboxTCol=boxTCol;

            //Update the row and column attribute in hero and box
            h.setRow(tRow);
            h.setCol(tCol);
            b.setRow(boxTRow);
            b.setCol(boxTCol);


//            userMapNow = model.getMatrix();
//            List<String> lines = new ArrayList<>();
//            int steps = view.getSteps();
//            int time = Frame.getTime();
//            lines.add(Integer.toString(steps + 1));
//            lines.add(Integer.toString(time + 1));
//            StringBuilder sb = new StringBuilder();
//            for (int[] ints : userMapNow) {
//                sb.setLength(0);
//                for (int anInt : ints) {
//                    sb.append(anInt).append(",");
//                }
//                sb.setLength(sb.length() - 1);
//                lines.add(sb.toString());
//            }
//            fileUtil.writeFileFromList("UserMap/" + username + "/level" + level + ".txt", lines);

            this.setPulll(false);

            saveGame();
            System.out.println("process recorded.");
            pre_map=model.getMatrix();
            return true;

        }
        else if (isPulll()) {

            System.out.println("Do pulll");
            System.out.println("row=" + row);
            System.out.println("col=" + col);
            System.out.println("tRow: " + tRow);
            System.out.println("tCol: " + tCol);

            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;

            int pboxTRow = 1, pboxTCol = 1;
            int boxTRow = row, boxTCol = col;
            pboxTRow = row - (tRow - row);
            pboxTCol = col - (tCol - col);
//            System.out.println("boxTRow=" + boxTRow);
//            System.out.println("boxTCol=" + boxTCol);
//            System.out.println("pboxTRow=" + pboxTRow);
//            System.out.println("pboxTCol=" + pboxTCol);
            model.getMatrix()[pboxTRow][pboxTCol] -= 10;
            model.getMatrix()[boxTRow][boxTCol] += 10;

            //System.out.println("qwq1");

            //update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //System.out.println("qwq2");
            //update box in GamePanel
            Box b = view.getGridComponent(pboxTRow, pboxTCol).removeBoxFromGrid();
            view.getGridComponent(boxTRow, boxTCol).setBoxInGrid(b);
            //System.out.println("qwq3");

            preboxTRow=boxTRow; preboxTCol=boxTCol;

            //Update the row and column attribute in hero and box
            h.setRow(tRow);
            h.setCol(tCol);
            b.setRow(boxTRow);
            b.setCol(boxTCol);


//            userMapNow = model.getMatrix();
//            List<String> lines = new ArrayList<>();
//            int steps = view.getSteps();
//            int time = Frame.getTime();
//            lines.add(Integer.toString(steps + 1));
//            lines.add(Integer.toString(time + 1));
//            StringBuilder sb = new StringBuilder();
//            for (int[] ints : userMapNow) {
//                sb.setLength(0);
//                for (int anInt : ints) {
//                    sb.append(anInt).append(",");
//                }
//                sb.setLength(sb.length() - 1);
//                lines.add(sb.toString());
//            }
//            fileUtil.writeFileFromList("UserMap/" + username + "/level" + level + ".txt", lines);

            this.setPulll(false);

            saveGame();
            System.out.println("process recorded.");
            pre_map=model.getMatrix();
            return true;
        } else {


            if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {
                //update hero in MapMatrix
                model.getMatrix()[row][col] -= 20;
                model.getMatrix()[tRow][tCol] += 20;
                //Update hero in GamePanel
                Hero h = currentGrid.removeHeroFromGrid();
                targetGrid.setHeroInGrid(h);
                //Update the row and column attribute in hero
                h.setRow(tRow);
                h.setCol(tCol);

                preboxTRow=-1;preboxTCol=-1;

//                userMapNow = model.getMatrix();
//                List<String> lines = new ArrayList<>();
//                int steps = view.getSteps();
//                int time = Frame.getTime();
//                lines.add(Integer.toString(steps + 1));
//                lines.add(Integer.toString(time + 1));
//                StringBuilder sb = new StringBuilder();
//                for (int[] ints : userMapNow) {
//                    sb.setLength(0);
//                    for (int anInt : ints) {
//                        sb.append(anInt).append(",");
//                    }
//                    sb.setLength(sb.length() - 1);
//                    lines.add(sb.toString());
//                }
//                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);

                saveGame();
                System.out.println("process recorded.");
                pre_map=model.getMatrix();
                return true;
            } else if (map[tRow][tCol] == 10 || map[tRow][tCol] == 12) {

                int boxTRow, boxTCol;
                boxTRow = tRow + direction.getRow();
                boxTCol = tCol + direction.getCol();
                //pretRow=tRow;pretCol=tCol;
                //preboxTRow=boxTRow;preboxTCol=boxTCol;

                if (map[boxTRow][boxTCol] == 0 || map[boxTRow][boxTCol] == 2) {

                    //update hero in MapMatrix
                    model.getMatrix()[row][col] -= 20;
                    model.getMatrix()[tRow][tCol] += 20;
                    //update box in MapMatrix
                    model.getMatrix()[tRow][tCol] -= 10;
                    model.getMatrix()[boxTRow][boxTCol] += 10;

                    //update hero in GamePanel
                    Hero h = currentGrid.removeHeroFromGrid();
                    targetGrid.setHeroInGrid(h);
                    //update box in GamePanel
                    Box b = view.getGridComponent(tRow, tCol).removeBoxFromGrid();
                    view.getGridComponent(boxTRow, boxTCol).setBoxInGrid(b);

                    preboxTRow=boxTRow;preboxTCol=boxTCol;

                    //Update the row and column attribute in hero and box
                    h.setRow(tRow);
                    h.setCol(tCol);
                    b.setRow(boxTRow);
                    b.setCol(boxTCol);

//                    userMapNow = model.getMatrix();
//                    List<String> lines = new ArrayList<>();
//                    int steps = view.getSteps();
//                    int time = Frame.getTime();
//                    lines.add(Integer.toString(steps + 1));
//                    lines.add(Integer.toString(time + 1));
//                    StringBuilder sb = new StringBuilder();
//                    for (int[] ints : userMapNow) {
//                        sb.setLength(0);
//                        for (int anInt : ints) {
//                            sb.append(anInt).append(",");
//                        }
//                        sb.setLength(sb.length() - 1);
//                        lines.add(sb.toString());
//                    }
//                    fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);

                    saveGame();
                    System.out.println("process recorded.");
                    pre_map=model.getMatrix();
                    return true;
                }
            }
        }

//        userMapNow = model.getMatrix();
//        List<String> lines = new ArrayList<>();
//        int steps = view.getSteps();
//        int time = Frame.getTime();
//        lines.add(Integer.toString(steps));
//        lines.add(Integer.toString(time));
//        StringBuilder sb = new StringBuilder();
//        for (int[] ints : userMapNow) {
//            sb.setLength(0);
//            for (int anInt : ints) {
//                sb.append(anInt).append(",");
//            }
//            sb.setLength(sb.length() - 1);
//            lines.add(sb.toString());
//        }
//        fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);

        saveGame();
        System.out.println("process recorded.");
        pre_map=model.getMatrix();

        return false;
    }

/**
    public void loadGame() {
        System.out.println("start loading game...");

        List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
        String username = lines0.getFirst();
        List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
        String level = lines1.getFirst();
        List<String> lines = fileUtil.readFileToList("UserMap/" + username + "/" + "level" + level);
        int steps = Integer.parseInt(lines.getFirst());
        int time = Integer.parseInt(lines.get(1));
        String status = lines.get(2);
        int[][] userMapNow = new int[lines.size() - 3][];
        for (int i = 3; i < lines.size(); i++) {
            String[] pieces = lines.get(i).split(",");
            userMapNow[i - 3] = new int[pieces.length];
            for (int j = 0; j < pieces.length; j++) {
                userMapNow[i - 3][j] = Integer.parseInt(pieces[j]);
            }
        }

        System.out.println("reading file complete, start check player's progress now");

        if (status.equals("new")) {
            System.out.println("there isn't a record for this level for this user");

//            this.Frame.setVisible(false);

            return;
        } else if (status.equals("processing")) {
            view.setMapmatrix(userMapNow);
        } else {
            System.out.println("the record has been damaged");

//            this.Frame.setVisible(false);
        }
    }
*/

    public void saveGame() {
        List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
        String username = lines0.getFirst();
        List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
        String level = lines1.getFirst();

        int[][] userMapNow = model.getMatrix();
        List<String> lines = new ArrayList<>();
        int steps = view.getSteps();
        int time = Frame.getTime();
        lines.add(Integer.toString(steps));
        lines.add(Integer.toString(time));
        lines.add("processing");
        StringBuilder sb = new StringBuilder();
        for (int[] ints : userMapNow) {
            sb.setLength(0);
            for (int anInt : ints) {
                sb.append(anInt).append(",");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }
        fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);
    }


    //todo: add other methods such as loadGame, saveGame...

}