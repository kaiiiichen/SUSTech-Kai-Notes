package view.rank;

import util.AdvancedFileUtil;
import util.FileUtil;
import view.FrameUtil;
import view.level.LevelFrame;
import view.login.LoginFailureFrame1;
import view.login.LoginFailureFrame2;
import view.login.LoginFailureFrame3;
import view.login.LoginFailureFrame4;
import view.signup.SignUpFrame;
import view.signup.UserInfo;
import view.welcome.WelcomeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RankFrame extends JFrame {
    private JTextField title;
    private JButton cancelBtn;
    private LevelFrame levelFrame;

    FileUtil fileUtil = new AdvancedFileUtil();

    public RankFrame(int width, int height) {
        this.setTitle("Rank List Frame");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel title = FrameUtil.createJLabel(this, new Point(60, 20), 2000, 60, "Rank Now");
        JLabel reminderLabel1 = FrameUtil.createJLabel(this, new Point(50, 80), 300, 40, "Only the top 3 players will be shown here!");

        Font newFont1 = new Font("Times New Roman", Font.BOLD, 48);
        Font newFont2 = new Font("Times New Roman", Font.ITALIC, 16);
        Font newFont3 = new Font("Times New Roman", Font.ROMAN_BASELINE, 20);
        title.setFont(newFont1);
        reminderLabel1.setFont(newFont2);

        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(125, 300), 100, 40);

        List<String> lines = fileUtil.readFileToList("rankList/rankList_new.txt");
        String[][] scores = new String[2][100];
        for (int i = 0; i < 2; i++) {
            String[] pieces = lines.get(i).split(",");
            for (int j = 0; j < 3; j++) {
                scores[i][j] = pieces[j];
            }
        }
        for (int i = 0; i < 3; i++) {
            JLabel rank = FrameUtil.createJLabel(this, new Point(30, 130 + 40 * i), 2000, 40, "Rank-" + (i + 1) +" player"+ ":   " + scores[0][i] + "   (" + scores[1][i] + " pts)");
            rank.setFont(newFont3);
        }

        cancelBtn.addActionListener(l -> {
            this.setVisible(false);
            this.levelFrame.setVisible(true);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
}
