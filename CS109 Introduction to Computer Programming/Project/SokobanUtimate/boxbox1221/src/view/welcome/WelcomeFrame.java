package view.welcome;

import util.AdvancedFileUtil;
import view.FrameUtil;
import util.FileUtil;
import view.level.LevelFrame;
import view.login.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class WelcomeFrame extends JFrame {
    private JButton playerBtn;
    private JButton touristBtn;
    private LoginFrame loginFrame;
    private LevelFrame levelFrame;
    private Image backgroundImage;

    FileUtil fileUtil = new AdvancedFileUtil();

    int[][] level1 = new int[][]{
            {1, 1, 1, 1, 1, 1},
            {1, 20, 0, 0, 0, 1},
            {1, 0, 0, 10, 2, 1},
            {1, 0, 2, 10, 0, 1},
            {1, 1, 1, 1, 1, 1},
    };

    int[][] level2 = new int[][]{
            {1, 1, 1, 1, 1, 1, 0},
            {1, 20, 0, 0, 0, 1, 1},
            {1, 0, 10, 10, 0, 0, 1},
            {1, 0, 1, 2, 0, 2, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level3 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0},
            {1, 1, 1, 0, 0, 1, 0},
            {1, 20, 0, 2, 10, 1, 1},
            {1, 0, 0, 0, 10, 0, 1},
            {1, 0, 1, 2, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level4 = new int[][]{
            {0, 1, 1, 1, 1, 1, 0},
            {1, 1, 20, 0, 0, 1, 1},
            {1, 0, 0, 1, 0, 1, 1},
            {1, 0, 10, 12, 10, 0, 1},
            {1, 0, 0, 2, 0, 0, 1},
            {1, 1, 0, 2, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 0},
    };

    int[][] level5 = new int[][]{
            {1, 1, 1, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 2, 2, 0, 1},
            {1, 0, 10, 10, 10, 20, 0, 1},
            {1, 0, 0, 1, 0, 2, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level6 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 2, 0, 0, 10, 0, 1},
            {1, 2, 10, 0, 0, 10, 2, 1},
            {1, 0, 10, 0, 2, 20, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level7 = new int[][]{
            {0, 1, 1, 1, 1, 1, 1},
            {0, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 1},
            {1, 0, 2, 2, 2, 0, 1},
            {1, 0, 10, 10, 10, 1, 1},
            {1, 1, 1, 20, 0, 1, 0},
            {0, 0, 1, 1, 1, 1, 0},
    };

    int[][] level8 = new int[][]{
            {0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 10, 0, 0, 1},
            {1, 0, 10, 10, 1, 22, 2, 1},
            {1, 0, 0, 0, 0, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0},
    };

    int[][] level9 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 20, 1, 1, 1, 1},
            {1, 0, 0, 10, 0, 1, 0, 0, 1},
            {1, 0, 10, 0, 10, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 1, 0, 0, 1},
            {0, 1, 0, 2, 2, 2, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 0},
    };

    int[][] level10 = new int[][]{
            {0, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 2, 2, 1, 0},
            {1, 1, 1, 0, 1, 10, 1, 1},
            {1, 0, 0, 0, 10, 0, 0, 1},
            {1, 0, 0, 20, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level11 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 0},
            {1, 2, 0, 0, 2, 0, 1, 0},
            {1, 0, 2, 2, 0, 0, 1, 0},
            {1, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 10, 10, 10, 10, 0, 1},
            {1, 0, 20, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level12 = new int[][]{
            {1, 1, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 1, 1, 1},
            {1, 0, 10, 10, 12, 0, 1},
            {1, 1, 0, 0, 2, 0, 1},
            {0, 1, 1, 20, 2, 0, 1},
            {0, 0, 1, 1, 1, 1, 1},
    };

    int[][] level13 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 0, 0, 0, 10, 0, 1},
            {1, 1, 2, 1, 1, 1, 10, 1, 1},
            {1, 0, 2, 0, 0, 0, 10, 0, 1},
            {1, 0, 0, 0, 1, 0, 20, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level14 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 20, 2, 1, 1, 10, 1, 1},
            {1, 0, 2, 0, 0, 10, 0, 1},
            {1, 0, 10, 0, 0, 2, 0, 1},
            {1, 1, 10, 1, 1, 2, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] level15 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 10, 0, 0, 2, 1},
            {1, 20, 0, 1, 1, 10, 2, 1},
            {1, 0, 10, 0, 10, 0, 2, 1},
            {1, 0, 0, 1, 0, 0, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制背景图片
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public WelcomeFrame(int width, int height) {
        this.setTitle("Welcome Frame");
        this.setLayout(null);
        this.setSize(width, height);

        backgroundImage = Toolkit.getDefaultToolkit().getImage("image/woodbox.png");

        // 创建自定义的 JPanel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 绘制背景图片
                g.drawImage(backgroundImage, 0, 10, this.getWidth(), this.getHeight(), this);
            }
        };

        panel.setLayout(null); // 使用 null 布局以便可以绝对定位组件
        this.add(panel);

        JLabel title = FrameUtil.createJLabel(this, new Point(130, 90), 2000, 60, "Sokoban");
        JLabel reminderLabel1 = FrameUtil.createJLabel(this, new Point(150, 180), 200, 40, "Play now as a ...");


        Font newFont1 = new Font("Times New Roman", Font.BOLD, 72);
        Font newFont2 = new Font("Times New Roman", Font.ITALIC, 24);
        title.setFont(newFont1);
        reminderLabel1.setFont(newFont2);

        playerBtn = FrameUtil.createButton(this, "Registered Player", new Point(150, 230), 200, 50);
        touristBtn = FrameUtil.createButton(this, "Tourist", new Point(150, 290), 200, 50);

        playerBtn.addActionListener(e -> {
            this.setVisible(false);
            this.loginFrame.setVisible(true);
        });

        touristBtn.addActionListener(e -> {
            List<String> lines0 = new ArrayList<>();
            lines0.add("tourist");
            fileUtil.writeFileFromList("CurrentPlayer/CurrentPlayer.txt", lines0);

            List<String> lines1 = new ArrayList<>();
            lines1.add("0");
            lines1.add("0");
            lines1.add("new");
            StringBuilder sb1 = new StringBuilder();
            for (int[] ints : this.level1) {
                sb1.setLength(0);
                for (int anInt : ints) {
                    sb1.append(anInt).append(",");
                }
                sb1.setLength(sb1.length() - 1);
                lines1.add(sb1.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level1", lines1);

            List<String> lines2 = new ArrayList<>();
            lines2.add("0");
            lines2.add("0");
            lines2.add("new");
            StringBuilder sb2 = new StringBuilder();
            for (int[] ints : this.level2) {
                sb2.setLength(0);
                for (int anInt : ints) {
                    sb2.append(anInt).append(",");
                }
                sb2.setLength(sb2.length() - 1);
                lines2.add(sb2.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level2", lines2);

            List<String> lines3 = new ArrayList<>();
            lines3.add("0");
            lines3.add("0");
            lines3.add("new");
            StringBuilder sb3 = new StringBuilder();
            for (int[] ints : this.level3) {
                sb3.setLength(0);
                for (int anInt : ints) {
                    sb3.append(anInt).append(",");
                }
                sb3.setLength(sb3.length() - 1);
                lines3.add(sb3.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level3", lines3);

            List<String> lines4 = new ArrayList<>();
            lines4.add("0");
            lines4.add("0");
            lines4.add("new");
            StringBuilder sb4 = new StringBuilder();
            for (int[] ints : this.level4) {
                sb4.setLength(0);
                for (int anInt : ints) {
                    sb4.append(anInt).append(",");
                }
                sb4.setLength(sb4.length() - 1);
                lines4.add(sb4.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level4", lines4);

            List<String> lines5 = new ArrayList<>();
            lines5.add("0");
            lines5.add("0");
            lines5.add("new");
            StringBuilder sb5 = new StringBuilder();
            for (int[] ints : this.level5) {
                sb5.setLength(0);
                for (int anInt : ints) {
                    sb5.append(anInt).append(",");
                }
                sb5.setLength(sb5.length() - 1);
                lines5.add(sb5.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level5", lines5);

            List<String> lines6 = new ArrayList<>();
            lines6.add("0");
            lines6.add("0");
            lines6.add("new");
            StringBuilder sb6 = new StringBuilder();
            for (int[] ints : this.level6) {
                sb6.setLength(0);
                for (int anInt : ints) {
                    sb6.append(anInt).append(",");
                }
                sb6.setLength(sb6.length() - 1);
                lines6.add(sb6.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level6", lines6);

            List<String> lines7 = new ArrayList<>();
            lines7.add("0");
            lines7.add("0");
            lines7.add("new");
            StringBuilder sb7 = new StringBuilder();
            for (int[] ints : this.level7) {
                sb7.setLength(0);
                for (int anInt : ints) {
                    sb7.append(anInt).append(",");
                }
                sb7.setLength(sb7.length() - 1);
                lines7.add(sb7.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level7", lines7);

            List<String> lines8 = new ArrayList<>();
            lines8.add("0");
            lines8.add("0");
            lines8.add("new");
            StringBuilder sb8 = new StringBuilder();
            for (int[] ints : this.level8) {
                sb8.setLength(0);
                for (int anInt : ints) {
                    sb8.append(anInt).append(",");
                }
                sb8.setLength(sb8.length() - 1);
                lines8.add(sb8.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level8", lines8);

            List<String> lines9 = new ArrayList<>();
            lines9.add("0");
            lines9.add("0");
            lines9.add("new");
            StringBuilder sb9 = new StringBuilder();
            for (int[] ints : this.level9) {
                sb9.setLength(0);
                for (int anInt : ints) {
                    sb9.append(anInt).append(",");
                }
                sb9.setLength(sb9.length() - 1);
                lines9.add(sb9.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level9", lines9);

            List<String> lines10 = new ArrayList<>();
            lines10.add("0");
            lines10.add("0");
            lines10.add("new");
            StringBuilder sb10 = new StringBuilder();
            for (int[] ints : this.level10) {
                sb10.setLength(0);
                for (int anInt : ints) {
                    sb10.append(anInt).append(",");
                }
                sb10.setLength(sb10.length() - 1);
                lines10.add(sb10.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level10", lines10);

            List<String> lines11 = new ArrayList<>();
            lines11.add("0");
            lines11.add("0");
            lines11.add("new");
            StringBuilder sb11 = new StringBuilder();
            for (int[] ints : this.level11) {
                sb11.setLength(0);
                for (int anInt : ints) {
                    sb11.append(anInt).append(",");
                }
                sb11.setLength(sb11.length() - 1);
                lines11.add(sb11.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level11", lines11);

            List<String> lines12 = new ArrayList<>();
            lines12.add("0");
            lines12.add("0");
            lines12.add("new");
            StringBuilder sb12 = new StringBuilder();
            for (int[] ints : this.level12) {
                sb12.setLength(0);
                for (int anInt : ints) {
                    sb12.append(anInt).append(",");
                }
                sb12.setLength(sb12.length() - 1);
                lines12.add(sb12.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level12", lines12);

            List<String> lines13 = new ArrayList<>();
            lines13.add("0");
            lines13.add("0");
            lines13.add("new");
            StringBuilder sb13 = new StringBuilder();
            for (int[] ints : this.level13) {
                sb13.setLength(0);
                for (int anInt : ints) {
                    sb13.append(anInt).append(",");
                }
                sb13.setLength(sb13.length() - 1);
                lines13.add(sb13.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level13", lines13);

            List<String> lines14 = new ArrayList<>();
            lines14.add("0");
            lines14.add("0");
            lines14.add("new");
            StringBuilder sb14 = new StringBuilder();
            for (int[] ints : this.level14) {
                sb14.setLength(0);
                for (int anInt : ints) {
                    sb14.append(anInt).append(",");
                }
                sb14.setLength(sb14.length() - 1);
                lines14.add(sb14.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level14", lines14);

            List<String> lines15 = new ArrayList<>();
            lines15.add("0");
            lines15.add("0");
            lines15.add("new");
            StringBuilder sb15 = new StringBuilder();
            for (int[] ints : this.level15) {
                sb15.setLength(0);
                for (int anInt : ints) {
                    sb15.append(anInt).append(",");
                }
                sb15.setLength(sb15.length() - 1);
                lines15.add(sb15.toString());
            }
            fileUtil.writeFileFromList("UserMap/" + "tourist" + "/level15", lines15);


            this.setVisible(false);
            this.levelFrame.setVisible(true);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



//    @Override
//    public void paintComponents(Graphics g) {
//        // 绘制背景图片
//        if (backgroundImage != null) {
//            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
//        }
//        super.paintComponents(g); // 确保其他组件继续正常绘制
//    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void background(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("image/stars.gif"); //添加图片
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, Integer.MIN_VALUE);
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

}