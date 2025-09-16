package view.load;

import view.FrameUtil;
import view.game.GameFrame;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;

public class LoadingFailureFrame extends JFrame {
    private JButton backBtn;
    private GameFrame gameFrame;
    private LoadingFrame loadingFrame;
    private LevelFrame levelFrame;


    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void setLoadingFrame(LoadingFrame loadingFrame) {
        this.loadingFrame = loadingFrame;
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public LoadingFailureFrame(int width, int height) {
        this.setTitle("Loading Failure Frame");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(60, 20), 250, 40, "Sorry! Your record for this level");
        JLabel warningLabel2 = FrameUtil.createJLabel(this, new Point(35, 50), 280, 40, "has been damaged and we cannot load it.");

        backBtn = FrameUtil.createButton(this, "Back", new Point(110, 100), 100, 40);

        backBtn.addActionListener(e -> {
            levelFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
