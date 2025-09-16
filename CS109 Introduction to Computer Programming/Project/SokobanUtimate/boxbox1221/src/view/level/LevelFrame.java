package view.level;

import controller.FrameController;
import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;
import view.FrameUtil;
import view.game.GameFrame;
import view.load.LoadingFailureFrame;
import view.load.LoadingFrame;
import view.rank.RankFrame;
import view.welcome.WelcomeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LevelFrame extends JFrame {
    private static FrameController frameController = new FrameController();
    private LoadingFrame loadingFrame;
    private LoadingFailureFrame loadingFailureFrame;
    private WelcomeFrame welcomeFrame;
    private RankFrame rankFrame;

    FileUtil fileUtil = new AdvancedFileUtil();

    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        JButton level1Btn = FrameUtil.createButton(this, "Level 1", new Point(40, 20), 85, 60);
        JButton level2Btn = FrameUtil.createButton(this, "Level 2", new Point(130, 20), 85, 60);
        JButton level3Btn = FrameUtil.createButton(this, "Level 3", new Point(220, 20), 85, 60);
        JButton level4Btn = FrameUtil.createButton(this, "Level 4", new Point(310, 20), 85, 60);
        JButton level5Btn = FrameUtil.createButton(this, "Level 5", new Point(400, 20), 85, 60);
        JButton level6Btn = FrameUtil.createButton(this, "Level 6", new Point(40, 110), 85, 60);
        JButton level7Btn = FrameUtil.createButton(this, "Level 7", new Point(130, 110), 85, 60);
        JButton level8Btn = FrameUtil.createButton(this, "Level 8", new Point(220, 110), 85, 60);
        JButton level9Btn = FrameUtil.createButton(this, "Level 9", new Point(310, 110), 85, 60);
        JButton level10Btn = FrameUtil.createButton(this, "Level 10", new Point(400, 110), 85, 60);
        JButton level11Btn = FrameUtil.createButton(this, "Level 11", new Point(40, 200), 85, 60);
        JButton level12Btn = FrameUtil.createButton(this, "Level 12", new Point(130, 200), 85, 60);
        JButton level13Btn = FrameUtil.createButton(this, "Level 13", new Point(220, 200), 85, 60);
        JButton level14Btn = FrameUtil.createButton(this, "Level 14", new Point(310, 200), 85, 60);
        JButton level15Btn = FrameUtil.createButton(this, "Level 15", new Point(400, 200), 85, 60);
        JButton backBtn = FrameUtil.createButton(this,"Back to the initial page", new Point(40,280), 220,50);
        JButton rankBtn = FrameUtil.createButton(this,"Click to see ranking list", new Point(265,280), 220,50);
        frameController.setLevelFrame(this);

        Font font = new Font("Times New Roman", Font.BOLD, 12);
        JLabel notice = FrameUtil.createJLabel(this, new Point(15, 320), 2000, 60, "You can modify your own map by editing a map matrix txt file and loading it in the game frame.");
        notice.setFont(font);

        // 在level frame中我们需要先判断是否有存档，然后读取。
        // 也就是说，读取后如果状态是new，就直接重启游戏，
        // 如果是processing，我们来到loading frame，显示有存档，
        // 然后检测是否有损坏，损坏就来到loading failure frame然后重启这个level的游戏，并且重新写一遍这个存档，回到最初的map

        // 我们在level frame中要做的判断是，这个存档中，有没有进度保留，如果有，就转到loading frame，如果没有就直接开始新游戏

        backBtn.addActionListener(l ->{
            this.setVisible(false);
            this.welcomeFrame.setVisible(true);
        });

        rankBtn.addActionListener(l->{
            this.setVisible(false);
            RankFrame rankFrame1 = new RankFrame(350,400);
            rankFrame1.setLevelFrame(this);
            rankFrame1.setVisible(true);
        });

        level1Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("1");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);
            // 写入当前level供读取

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1},
                    {1, 20, 0, 0, 0, 1},
                    {1, 0, 0, 10, 2, 1},
                    {1, 0, 2, 10, 0, 1},
                    {1, 1, 1, 1, 1, 1},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(520, 630, mapMatrix, 1);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level2Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("2");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0},
                    {1, 20, 0, 0, 0, 1, 1},
                    {1, 0, 10, 10, 0, 0, 1},
                    {1, 0, 1, 2, 0, 2, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(570, 640, mapMatrix, 2);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level3Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("3");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1, 0},
                    {1, 1, 1, 0, 0, 1, 0},
                    {1, 20, 0, 2, 10, 1, 1},
                    {1, 0, 0, 0, 10, 0, 1},
                    {1, 0, 1, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(570, 630, mapMatrix, 3);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });


        level4Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("4");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 1, 1, 1, 1, 1, 0},
                    {1, 1, 20, 0, 0, 1, 1},
                    {1, 0, 0, 1, 0, 1, 1},
                    {1, 0, 10, 12, 10, 0, 1},
                    {1, 0, 0, 2, 0, 0, 1},
                    {1, 1, 0, 2, 0, 1, 1},
                    {0, 1, 1, 1, 1, 1, 0},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(580, 640, mapMatrix, 4);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level5Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("5");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 0, 0, 0, 2, 2, 0, 1},
                    {1, 0, 10, 10, 10, 20, 0, 1},
                    {1, 0, 0, 1, 0, 2, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(650, 620, mapMatrix, 5);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level6Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("6");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 2, 0, 0, 10, 0, 1},
                    {1, 2, 10, 0, 0, 10, 2, 1},
                    {1, 0, 10, 0, 2, 20, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(650, 650, mapMatrix, 6);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level7Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("7");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 1, 1, 1, 1, 1, 1},
                    {0, 1, 0, 0, 0, 0, 1},
                    {1, 1, 0, 1, 0, 0, 1},
                    {1, 0, 2, 2, 2, 0, 1},
                    {1, 0, 10, 10, 10, 1, 1},
                    {1, 1, 1, 20, 0, 1, 0},
                    {0, 0, 1, 1, 1, 1, 0},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(600, 620, mapMatrix, 7);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level8Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("8");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 0, 0, 1},
                    {1, 0, 0, 0, 10, 0, 0, 1},
                    {1, 0, 10, 10, 1, 22, 2, 1},
                    {1, 0, 0, 0, 0, 2, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 0},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(630, 670, mapMatrix, 8);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level9Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("9");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1, 0, 0, 0},
                    {1, 1, 1, 0, 20, 1, 1, 1, 1},
                    {1, 0, 0, 10, 0, 1, 0, 0, 1},
                    {1, 0, 10, 0, 10, 0, 0, 0, 1},
                    {1, 1, 0, 0, 1, 1, 0, 0, 1},
                    {0, 1, 0, 2, 2, 2, 0, 1, 1},
                    {0, 1, 1, 1, 1, 1, 1, 1, 0},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(670, 620, mapMatrix, 9);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level10Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("10");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 2, 2, 1, 0},
                    {1, 1, 1, 0, 1, 10, 1, 1},
                    {1, 0, 0, 0, 10, 0, 0, 1},
                    {1, 0, 0, 20, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(620, 640, mapMatrix, 10);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level11Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("11");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 2, 0, 0, 2, 0, 1, 0},
                    {1, 0, 2, 2, 0, 0, 1, 0},
                    {1, 1, 0, 1, 0, 1, 1, 1},
                    {1, 0, 10, 10, 10, 10, 0, 1},
                    {1, 0, 20, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(640, 650, mapMatrix, 11);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level12Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("12");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 1, 0, 0},
                    {1, 0, 0, 0, 1, 1, 1},
                    {1, 0, 10, 10, 12, 0, 1},
                    {1, 1, 0, 0, 2, 0, 1},
                    {0, 1, 1, 20, 2, 0, 1},
                    {0, 0, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(580, 650, mapMatrix, 12);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level13Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("13");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 2, 0, 0, 0, 10, 0, 1},
                    {1, 1, 2, 1, 1, 1, 10, 1, 1},
                    {1, 0, 2, 0, 0, 0, 10, 0, 1},
                    {1, 0, 0, 0, 1, 0, 20, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(680, 640, mapMatrix, 13);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level14Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("14");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 20, 2, 1, 1, 10, 1, 1},
                    {1, 0, 2, 0, 0, 10, 0, 1},
                    {1, 0, 10, 0, 0, 2, 0, 1},
                    {1, 1, 10, 1, 1, 2, 0, 1},
                    {1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(640, 640, mapMatrix, 14);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });

        level15Btn.addActionListener(l -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("15");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentLevel.txt", lines0);

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 10, 0, 0, 2, 1},
                    {1, 20, 0, 1, 1, 10, 2, 1},
                    {1, 0, 10, 0, 10, 0, 2, 1},
                    {1, 0, 0, 1, 0, 0, 2, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},

            });


            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

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

            if (status.equals("processing")) {
                // for status equaling processing, check whether there is a breaking part and gon on
                // the checking part is left for the loading frame
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
            } else if (status.equals("new")) {
                // for status.equals("new"): simply renew the file by writing the original mapMatrix into it(refresh file)
                List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
                int[][] userMapNow0 = new int[lines0001.size() - 3][];
                for (int i = 3; i < lines0001.size(); i++) {
                    String[] pieces = lines0001.get(i).split(",");
                    userMapNow0[i - 3] = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) {
                        userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                    }
                }
                MapMatrix mapMatrix001 = new MapMatrix(userMapNow0);
                List<String> linesnow = new ArrayList<>();
                linesnow.add(Integer.toString(0));
                linesnow.add(Integer.toString(0));
                linesnow.add("new");
                StringBuilder sb = new StringBuilder();
                for (int[] ints : mapMatrix001.getMatrix()) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesnow.add(sb.toString());
                }
                fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                GameFrame gameFrame = new GameFrame(630, 640, mapMatrix, 15);
                gameFrame.setLevelFrame(this);
                this.setVisible(false);
                gameFrame.setVisible(true);
            } else {
                this.loadingFrame.setVisible(true);
                this.setVisible(false);
                // if the status is changed, we stil sent to the loading frame and let this problem detected there.
            }
        });


        //todo: complete all level.

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static FrameController getFrameController() {
        return frameController;
    }

    public void setLoadingFrame(LoadingFrame loadingFrame) {
        this.loadingFrame = loadingFrame;
    }

    public void setLoadingFailureFrame(LoadingFailureFrame loadingFailureFrame) {
        this.loadingFailureFrame = loadingFailureFrame;
    }

    public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
        this.welcomeFrame = welcomeFrame;
    }

    public void setRankFrame(RankFrame rankFrame) {
        this.rankFrame = rankFrame;
    }
}