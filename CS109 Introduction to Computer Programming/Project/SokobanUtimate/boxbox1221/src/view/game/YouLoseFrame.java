package view.game;

import javax.swing.*;
import java.awt.*;

import controller.GameController;
import model.MapMatrix;
import view.FrameUtil;

public class YouLoseFrame extends JFrame {

    private JButton OKBtn;

    public YouLoseFrame(int width, int height) {

        //this.GameFrame.setvisible(false);

        this.setTitle("Sorry");
        this.setLayout(null);
        this.setSize(width, height);


        OKBtn = FrameUtil.createButton(this, "OK", new Point(160, 120), 80, 40);
        //NextLevelBtn = FrameUtil.createButton(this, "NextLevel", new Point(100, 130), 120, 40);

        JLabel YouLose = FrameUtil.createJLabel(this, new Point(60, 20), 200, 40, String.format("You lose!"));
        JLabel PleaseRestart = FrameUtil.createJLabel(this, new Point(60, 60), 300, 40, String.format("Please press restart!"));

        Font newFont1 = new Font("Arial", Font.BOLD, 30);
        Font newFont2 = new Font("Arial", Font.BOLD, 18);

        // 将新字体应用到JLabel上
        YouLose.setFont(newFont1);
        PleaseRestart.setFont(newFont2);

        OKBtn.addActionListener(e -> {
            this.setVisible(false);
            //this.GameFrame.setVisible(false);
            //LevelFrame levelFrame = new LevelFrame(500,200);
            //this.setLevelFrame(levelFrame);
            //this.levelFrame.setVisible(true);
            //this.GameFrame.setVisible(false);
            //this.GameFrame.dispose();
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}