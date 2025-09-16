package view.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import controller.GameController;
import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;
import view.FrameUtil;
import view.level.LevelFrame;
import view.signup.UserInfo;

public class YouWinFrame extends JFrame {

    private JButton MenuBtn;
    private JButton NextLevelBtn;
    private LevelFrame levelFrame;
    private GameFrame GameFrame;

    FileUtil fileUtil = new AdvancedFileUtil();

    public YouWinFrame(int width, int height, int step, int tim) {

        //this.GameFrame.setvisible(false);

        this.setTitle("Congratulations");
        this.setLayout(null);
        this.setSize(width, height);


        MenuBtn = FrameUtil.createButton(this, "Menu", new Point(120, 150), 80, 40);
        //NextLevelBtn = FrameUtil.createButton(this, "NextLevel", new Point(100, 130), 120, 40);

        JLabel YouWin = FrameUtil.createJLabel(this, new Point(110, 15), 200, 40, String.format("You win!"));
        JLabel ShowStep = FrameUtil.createJLabel(this, new Point(120, 60), 200, 35, String.format("step:%d", step));
        JLabel ShowTime = FrameUtil.createJLabel(this, new Point(120, 95), 200, 35, String.format("time: %ds", tim));


        ImageIcon gifIcon = new ImageIcon("image/congratulations-13773_128 (3).gif");

        // 创建一个 JLabel 并设置图标
        JLabel giflabel1 = new JLabel(gifIcon);
        JLabel giflabel2 = new JLabel(gifIcon);
        giflabel1.setBounds(5, 1, gifIcon.getIconWidth(), gifIcon.getIconHeight()); // 设置位置和大小
        giflabel2.setBounds(5, 110, gifIcon.getIconWidth(), gifIcon.getIconHeight()); // 设置位置和大小

        // 将 JLabel 添加到 JFrame
        this.add(giflabel1);
        this.add(giflabel2);


        Font newFont1 = new Font("Arial", Font.BOLD, 36);
        Font newFont2 = new Font("Arial", Font.BOLD, 24);

        // 将新字体应用到JLabel上
        YouWin.setFont(newFont1);
        ShowStep.setFont(newFont2);
        ShowTime.setFont(newFont2);

        MenuBtn.addActionListener(e -> {
            java.util.List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines0.getFirst();
            java.util.List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines1.getFirst();
            int levelnum = Integer.parseInt(level);
            System.out.println("username: " + username);
            System.out.println("level: " + level);

            if (!username.equals("user0001") && !username.equals("tourist")) {
                List<String> linesPast = fileUtil.readFileToList("rankList/userScore/" + username + ".txt");
                int[][] scores = new int[2][15];
                for (int i = 0; i < 2; i++) {
                    String[] pieces = linesPast.get(i).split(",");
                    for (int j = 0; j < 15; j++) {
                        scores[i][j] = Integer.parseInt(pieces[j]);
                    }
                }
                if (scores[0][levelnum - 1] > step || scores[0][levelnum - 1] == 0) {
                    scores[0][levelnum - 1] = step;
                    scores[1][levelnum - 1] = tim;
                } else if (scores[0][levelnum - 1] == step && scores[1][levelnum - 1] > tim) {
                    scores[1][levelnum - 1] = tim;
                }
                List<String> linesNew = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                for (int[] ints : scores) {
                    sb.setLength(0);
                    for (int anInt : ints) {
                        sb.append(anInt).append(",");
                    }
                    sb.setLength(sb.length() - 1);
                    linesNew.add(sb.toString());
                }
                fileUtil.writeFileFromList("rankList/userScore/" + username + ".txt", linesNew);
            }
            List<String> lines = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
            fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);
            System.out.println("Rewriting process done!");

            // todo: we want to renew our rank list here

            UserInfo userInfo = new UserInfo();
            List<String> lineslines = fileUtil.readFileToList("UserInfo/userinfo_new.txt");
            userInfo.convertToUserRecord(lineslines);
            String[][] rank = new String[2][userInfo.getN()];
            int[] score = new int[userInfo.getN()];
            for (int i = 0; i < userInfo.getN(); i++) {
                List<String> linesUsing = fileUtil.readFileToList("rankList/userScore/" + userInfo.getUserRecord()[i][0] + ".txt");
                int sum = 0;
                for (int j = 0; j < 15; j++) {
                    if(Integer.parseInt(linesUsing.get(0).split(",")[j]) != 0 && Integer.parseInt(linesUsing.get(1).split(",")[j])!= 0){
                        sum += (100 - Integer.parseInt(linesUsing.get(0).split(",")[j])) * (1000 - Integer.parseInt(linesUsing.get(1).split(",")[j]));
                    }
                }
                score[i] = sum;
            }
            for (int i = 0; i < userInfo.getN(); i++) {
                rank[0][i] = userInfo.getUserRecord()[i][0];
                rank[1][i] = String.valueOf(score[i]);
            }
            for (int i = 0; i < userInfo.getN() - 1; i++) {
                for (int j = 0; j < userInfo.getN() - 1 - i; j++) {
                    if (Integer.parseInt(rank[1][j]) < Integer.parseInt(rank[1][j + 1])) {
                        String temp = rank[0][j];
                        rank[0][j] = rank[0][j + 1];
                        rank[0][j + 1] = temp;
                        temp = rank[1][j];
                        rank[1][j] = rank[1][j + 1];
                        rank[1][j + 1] = temp;
                    }
                }
            }
            List<String> linesNew = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                sb.setLength(0);
                for (int j = 0; j < userInfo.getN(); j++) {
                    sb.append(rank[i][j]).append(",");
                }
                sb.setLength(sb.length() - 1);
                linesNew.add(sb.toString());
            }
            fileUtil.writeFileFromList("rankList/rankList_new.txt", linesNew);

            this.setVisible(false);
            //this.GameFrame.setVisible(false);
            this.levelFrame.setVisible(true);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public view.game.GameFrame getGameFrame() {
        return GameFrame;
    }

    public void setGameFrame(view.game.GameFrame gameFrame) {
        GameFrame = gameFrame;
    }
}