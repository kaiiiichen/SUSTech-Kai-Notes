package controller;

import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;
import util.FrameShow;
import view.game.GameFrame;
import view.level.LevelFrame;

import java.awt.*;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class FrameController {
    private LevelFrame LevelFrame;
    private GameFrame GameFrame;
    FileUtil fileUtil = new AdvancedFileUtil();

    public LevelFrame getLevelFrame() {
        return LevelFrame;
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.LevelFrame = levelFrame;
    }

    public void returnLevelFrame(JFrame frame) {
        frame.dispose();
        LevelFrame.setVisible(true);

    }

    public void setGameFrame(GameFrame gameFrame) {
        this.GameFrame = gameFrame;
    }

    public void loadGame(String path, JFrame frame) {
        System.out.println(path);
        List<String> lines = fileUtil.readFileToList(path);
        for (String line : lines) {
            System.out.println(line);
        }
        int[][] map = new int[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String[] elements = lines.get(i).split(" ");
            map[i] = new int[elements.length];
            for (int j = 0; j < elements.length; j++) {
                map[i][j] = Integer.parseInt(elements[j]);
            }
        }
        frame.dispose();
        MapMatrix mapMatrix = new MapMatrix(map);

        GameFrame gameFrame = new GameFrame(620, 620, mapMatrix, 0);

        //自动化页面大小--全屏
//        int fraWidth = gameFrame.getWidth();//frame的宽
//        int fraHeight = gameFrame.getHeight();//frame的高

//        int fraWidth = 600;//frame的宽
//        int fraHeight = 450;//frame的高
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        gameFrame.setSize(screenWidth, screenHeight);
//        gameFrame.setLocation(0, 0);
//        float proportionW = (float) screenWidth /fraWidth;
//        float proportionH = (float) screenHeight /fraHeight;
//
//        FrameShow.modifyComponentSize(gameFrame, proportionW,proportionH);
//        gameFrame.toFront();

        gameFrame.setVisible(true);
    }
}