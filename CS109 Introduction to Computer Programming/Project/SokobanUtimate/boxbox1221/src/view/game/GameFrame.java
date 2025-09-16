package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.GameController;
import model.AudioPlayer;
import model.MapMatrix;
import util.FileChooserDemo;
import util.FrameShow;
import view.FrameUtil;
import view.level.LevelFrame;
import view.game.GridComponent;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton returnBtn;
    private JButton pullBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton changeBgmBtn;
    private JButton changeThemeBtn;//
    private JButton changeModeBtn;
    private JButton saveBtn;
    private JButton undoBtn;
    private JLabel stepLabel;
    private JLabel timetagLabel;
    private JLabel timenowLabel;
    private JLabel modeLabel;
    private GamePanel gamePanel;
    private LevelFrame levelFrame;
    private int time = 0;
    private int bgm_num = 1; //
    static int theme_num = 1;
    static int mode_num=1;
    private AudioPlayer nowbgm;  //
    Timer timer = new Timer(1000, new ActionListener() {
        //int time = 0;
        //System.out.println("timeqwq");
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("timeqwq");
            time++;
            timenowLabel.setText("Time: " + time + "s");
        }
    });

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBgm_num() {
        return bgm_num;
    }

    public void setBgm_num(int bgm_num) {
        this.bgm_num = bgm_num;
    }

    public int getTheme_num() {
        return theme_num;
    }

    public void setTheme_num(int theme_num) {
        this.theme_num = theme_num;
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public int getMode_num() {
        return mode_num;
    }

    public void setMode_num(int mode_num) {
        this.mode_num = mode_num;
    }

    public GameFrame(int width, int height, MapMatrix mapMatrix, int nowlevel) {
        this.setTitle("2024 CS109 Level" + nowlevel);
        this.setLayout(null);
        this.setSize(width, height);


        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix, this);

        nowbgm = AudioPlayer.playBgm("bgmmusic/carefree.wav");  //


        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 35);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 170), 80, 35);
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(gamePanel.getWidth() + 80, 220), 80, 35);
        this.pullBtn = FrameUtil.createButton(this, "Pull", new Point(gamePanel.getWidth() + 80, 270), 80, 35);
        this.undoBtn = FrameUtil.createButton(this, "Undo", new Point(gamePanel.getWidth() + 80, 320), 80, 35);

        this.changeBgmBtn = FrameUtil.createButton(this, "change bgm", new Point(20, height - 80), 150, 30);
        this.changeThemeBtn = FrameUtil.createButton(this, "change theme", new Point(190, height - 80), 150, 30);  //
        this.changeModeBtn = FrameUtil.createButton(this, "change mode", new Point(360, height - 80), 150, 30);

        this.upBtn = FrameUtil.createButton(this, "U", new Point(gamePanel.getWidth() + 95, 380), 50, 40);
        this.downBtn = FrameUtil.createButton(this, "D", new Point(gamePanel.getWidth() + 95, 420), 50, 40);
        this.leftBtn = FrameUtil.createButton(this, "L", new Point(gamePanel.getWidth() + 45, 420), 50, 40);
        this.rightBtn = FrameUtil.createButton(this, "R", new Point(gamePanel.getWidth() + 145, 420), 50, 40);

        this.saveBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 495), 80, 40);

        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 19), new Point(gamePanel.getWidth() + 80, 50), 180, 35);
        //    this.timetagLabel = FrameUtil.createJLabel(this, "Time:", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 20), 100, 50);
        this.timenowLabel = FrameUtil.createJLabel(this, "Time", new Font("serif", Font.ITALIC, 19), new Point(gamePanel.getWidth() + 80, 15), 200, 35);
        this.modeLabel = FrameUtil.createJLabel(this,"Mode: normal", new Font("serif", Font.ITALIC, 19), new Point(gamePanel.getWidth() + 80, 85), 200, 35);
        JTextField vicText = FrameUtil.createJTextField(this, new Point(gamePanel.getWidth() + 80, 30), 0, 0);
        gamePanel.setStepLabel(stepLabel);
        //gamePanel.setTimeLabel(timenowLabel);
        timer.start();


//        class Layout implements LayoutManager {//控件自适应窗口大小，需要实现layoutManager接口
//
//            @Override
//            public void addLayoutComponent(String name, Component comp) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void removeLayoutComponent(Component comp) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public Dimension preferredLayoutSize(Container parent) {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public Dimension minimumLayoutSize(Container parent) {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            @Override
//            public void layoutContainer(Container parent) {
//                // TODO Auto-generated method stub
//
//                int w = parent.getWidth();//parent.getWidth()是在拉伸窗口时获得窗口的宽
//                int h = parent.getHeight();//parent.getHeight()是在拉伸窗口是获得窗口的高
//                double x = w * ((double) (gamePanel.getWidth() + 80) / parent.getWidth());//gamePanel.getWidth() + 80代表按钮的x=gamePanel.getWidth() + 80
//                double y = h * (120.0 / parent.getHeight());//120 代表按钮的y=120
//                double btnw = 80.0 / parent.getWidth() * w;//80 代表按钮的宽=80
//                double btnh = 35.0 / parent.getHeight() * h;//35 代表按钮的高=35
//                restartBtn.setBounds((int) x, (int) y, (int) btnw, (int) btnh);
//
//                w = parent.getWidth();
//                h = parent.getHeight();
//                x = w * ((double) (gamePanel.getWidth() + 80) / parent.getWidth());
//                y = h * (170.0 / parent.getHeight());
//                btnw = 80.0 / parent.getWidth() * w;
//                btnh = 35.0 / parent.getHeight() * h;
//                loadBtn.setBounds((int) x, (int) y, (int) btnw, (int) btnh);
//
//                w = parent.getWidth();
//                h = parent.getHeight();
//                x = w * ((double) (gamePanel.getWidth() + 80) / parent.getWidth());
//                y = h * (220.0 / parent.getHeight());
//                btnw = 80.0 / parent.getWidth() * w;
//                btnh = 35.0 / parent.getHeight() * h;
//                returnBtn.setBounds((int) x, (int) y, (int) btnw, (int) btnh);
//
//                w = parent.getWidth();
//                h = parent.getHeight();
//                x = w * ((double) (gamePanel.getWidth() + 80) / parent.getWidth());
//                y = h * (270.0 / parent.getHeight());
//                btnw = 80.0 / parent.getWidth() * w;
//                btnh = 35.0 / parent.getHeight() * h;
//                pullBtn.setBounds((int) x, (int) y, (int) btnw, (int) btnh);
//
//
//            }
//
//        }


        vicText.setEditable(false);


        vicText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                // timenowLabel.setText(TimerExample;
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT
                        || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {

                    if (bgm_num % 4 == 0) {
                        System.out.println("sound");
                        nowbgm = AudioPlayer.playBgm1("bgmmusic/push.wav");
                        //nowbgm.stop();
                    }

                    gamePanel.processKeyEvent(e);

                    if (controller.pd_victory()) {
                        System.out.println("You win!");
                        nowbgm.stop();  //

                        controller.rewriteFile();

                        YouWinFrame YouwinFrame = new YouWinFrame(330, 250, gamePanel.getSteps(), time);
                        YouwinFrame.setLevelFrame(levelFrame);
                        setVisible(false);
                        //this.GameFrame.setVisible(false);
                        YouwinFrame.setVisible(true);
                    }
                    if (controller.pd_failure()) {
                        System.out.println("You lose!");

                        controller.rewriteFile();

                        YouLoseFrame YouloseFrame = new YouLoseFrame(400, 210);

                        //setVisible(false);
                        YouloseFrame.setVisible(true);
                    }

                }

            }

        });

        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            if (controller.pd_victory()) {
                System.out.println("You win!");
                nowbgm.stop();
                controller.rewriteFile();
                YouWinFrame YouwinFrame = new YouWinFrame(330, 250, gamePanel.getSteps(), time);
                YouwinFrame.setLevelFrame(levelFrame);
                setVisible(false);
                //this.GameFrame.setVisible(false);
                YouwinFrame.setVisible(true);
            }
            if (controller.pd_failure()) {
                System.out.println("You lose!");
                controller.rewriteFile();
                YouLoseFrame YouloseFrame = new YouLoseFrame(400, 210);
                //setVisible(false);
                YouloseFrame.setVisible(true);
            }
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            if (controller.pd_victory()) {
                System.out.println("You win!");
                nowbgm.stop();
                controller.rewriteFile();
                YouWinFrame YouwinFrame = new YouWinFrame(330, 250, gamePanel.getSteps(), time);
                YouwinFrame.setLevelFrame(levelFrame);
                setVisible(false);
                //this.GameFrame.setVisible(false);
                YouwinFrame.setVisible(true);
            }
            if (controller.pd_failure()) {
                System.out.println("You lose!");
                controller.rewriteFile();
                YouLoseFrame YouloseFrame = new YouLoseFrame(400, 210);
                //setVisible(false);
                YouloseFrame.setVisible(true);
            }
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            if (controller.pd_victory()) {
                System.out.println("You win!");
                nowbgm.stop();
                controller.rewriteFile();
                YouWinFrame YouwinFrame = new YouWinFrame(330, 250, gamePanel.getSteps(), time);
                YouwinFrame.setLevelFrame(levelFrame);
                setVisible(false);
                //this.GameFrame.setVisible(false);
                YouwinFrame.setVisible(true);
            }
            if (controller.pd_failure()) {
                System.out.println("You lose!");
                controller.rewriteFile();
                YouLoseFrame YouloseFrame = new YouLoseFrame(400, 210);
                //setVisible(false);
                YouloseFrame.setVisible(true);
            }
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            if (controller.pd_victory()) {
                System.out.println("You win!");
                nowbgm.stop();
                controller.rewriteFile();
                YouWinFrame YouwinFrame = new YouWinFrame(330, 250, gamePanel.getSteps(), time);
                YouwinFrame.setLevelFrame(levelFrame);
                setVisible(false);
                //this.GameFrame.setVisible(false);
                YouwinFrame.setVisible(true);
            }
            if (controller.pd_failure()) {
                System.out.println("You lose!");
                controller.rewriteFile();
                YouLoseFrame YouloseFrame = new YouLoseFrame(400, 210);
                //setVisible(false);
                YouloseFrame.setVisible(true);
            }
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });


        this.undoBtn.addActionListener(e -> {
            if (mode_num%2==0) controller.setPulll(true);
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.pullBtn.addActionListener(e -> {
            if (mode_num%2==0) controller.setPulll(true);
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.restartBtn.addActionListener(e -> {
            //this.dispose();
            //GameFrame gameFrame = new GameFrame(600, 520, mapMatrix);
            //this.setVisible(false);
            //gameFrame.setVisible(true);
            timer.stop();
            time = 0;
            timer.start();
            controller.restartGame();
            controller.rewriteFile();
            gamePanel.requestFocusInWindow();//enable key listener
            vicText.requestFocusInWindow();
        });

        this.loadBtn.addActionListener(e -> {
            timer.stop();
            time = 0;
            timer.start();
//            String path = JOptionPane.showInputDialog(this, "Input path:");
            // can write: JOptionPane.
//           need to add:  string path=String.format("resource/%s/game1.txt",user.name());
//            gamePanel.requestFocusInWindow();//enable key listener

            FileChooserDemo app = null; // 创建FileChooserDemo实例
            try {
                app = new FileChooserDemo();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            app.setSize(400, 400); // 设置窗口大小为400x400像素

            Path path = app.getFileOrDirectory();
            LevelFrame.getFrameController().loadGame(String.valueOf(path), this);
            this.setVisible(false);
        });

        this.returnBtn.addActionListener(e -> {
            nowbgm.stop();
            LevelFrame.getFrameController().returnLevelFrame(this);
            gamePanel.requestFocusInWindow();//enable key listener
            controller.saveGame();
            System.out.println("process recorded.");
        });

        this.changeBgmBtn.addActionListener(e -> {    //
            bgm_num++;
            System.out.println(bgm_num);
            nowbgm.stop();
            if (bgm_num % 4 == 1) {
                nowbgm = AudioPlayer.playBgm("bgmmusic/carefree.wav");
                //bgm_num++;
            } else if (bgm_num % 4 == 2) {
                nowbgm = AudioPlayer.playBgm("bgmmusic/aobi2.wav");
                //bgm_num++;
            } else if (bgm_num % 4 == 3) {
                nowbgm = AudioPlayer.playBgm("bgmmusic/liyue.wav");
                //bgm_num++;
            } else {
                //bgm_num++;
            }
            gamePanel.requestFocusInWindow();//enable key listener
            vicText.requestFocusInWindow();


        });

        this.changeModeBtn.addActionListener(e -> {
            mode_num++;
            if (mode_num%2==0) {
                modeLabel.setText("Mode: cheat");
            }
            else {
                modeLabel.setText("Mode: normal");
            }
            gamePanel.requestFocusInWindow();
            vicText.setFocusable(true);
            vicText.requestFocusInWindow();
        });

        this.changeThemeBtn.addActionListener(e -> {  //
            theme_num++;
            gamePanel.requestFocusInWindow();//enable key listener
            vicText.requestFocusInWindow();

        });


        this.saveBtn.addActionListener(e -> {
            controller.saveGame();
            System.out.println("process recorded.");
        });


        this.addWindowListener(new MyWindowListener());
        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    class MyWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            System.out.println("window is closed.");
            controller.saveGame();
            System.out.println("process recorded.");
        }
    }

}