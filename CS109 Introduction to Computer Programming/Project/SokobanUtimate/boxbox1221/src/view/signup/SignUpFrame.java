package view.signup;

import util.AdvancedFileUtil;
import util.FileUtil;
import view.FrameUtil;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class SignUpFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton cancelBtn;
    private LoginFrame loginFrame;
    private SignUpSuccessFrame signUpSuccessFrame;
    private SignUpFailureFrame1 signUpFailureFrame1;
    private SignUpFailureFrame2 signUpFailureFrame2;
    private SignUpFailureFrame3 signUpFailureFrame3;
    private SignUpFailureFrame4 signUpFailureFrame4;
    private SignUpFailureFrame5 signUpFailureFrame5;

    UserInfo userInfo = new UserInfo();
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

    public SignUpFrame(int width, int height) {
        this.setTitle("Sign Up Frame");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "Username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 120), 70, 40, "Password:");

        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);


//        JPasswordField pwd = new JPasswordField();
//        pwd.setEchoChar('*');
//
//        //添加显示密码图标按钮
//        JButton viewBtn = new JButton(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("view.png"))));

//        //添加隐藏密码图标按钮
//        JButton viewHideBtn = new JButton(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("view_hide.png"))));
//
//        //将图标按钮添加进密码框里面，需要使用FlatLightLaf主题库
//        //导入依赖后在所有swing组件的最前面（或者在方法一开始）添加 FlatLightLaf.setup(); 即可，maven地址放最下面了
//
//        //如果不需要将按钮添加进框里则无需使用FlatLightLaf，添加相应按钮到面板相应位置即可
//        pwd.putClientProperty("JTextField.trailingComponent", viewBtn);
//
//        //给显示密码图标绑定单击事件
//        viewBtn.addActionListener(e -> {
//            pwd.putClientProperty("JTextField.trailingComponent", viewHideBtn);//设置隐藏按钮显示，未使用FlatLightLaf则不需要
//            pwd.setEchoChar((char) 0);//设置密码显示
//        });
//
//        //给隐藏密码图标绑定单击事件
//        viewHideBtn.addActionListener(e -> {
//            pwd.putClientProperty("JTextField.trailingComponent", viewBtn);//设置显示按钮显示，未使用FlatLightLaf则不需要
//            pwd.setEchoChar('*');//设置密码隐藏
//        });


        password = FrameUtil.createJTextField(this, new Point(120, 120), 120, 40);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(45, 60), 250, 40, "Warning: a valid user name only");
        JLabel warningLabel2 = FrameUtil.createJLabel(this, new Point(45, 80), 250, 40, "contains letters, numbers and '_'.");

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 240), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 240), 100, 40);
        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(100, 300), 100, 40);

        JLabel warningLabel3 = FrameUtil.createJLabel(this, new Point(30, 160), 300, 40, "Warning: for your account's safety, the");
        JLabel warningLabel4 = FrameUtil.createJLabel(this, new Point(15, 180), 300, 40, "password should be at least 8-letters long.");

        submitBtn.addActionListener(e -> {
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());

            if (username.getText().equals("")) {
                username.setText("");
                password.setText("");
                signUpFailureFrame3.setVisible(true);
                this.setVisible(false);
                return;
            } else if (password.getText().equals("")) {
                username.setText("");
                password.setText("");
                signUpFailureFrame4.setVisible(true);
                this.setVisible(false);
                return;
            }

            boolean validUsername = true;
            for (int i = 0; i < username.getText().length(); i++) {
                if ((!Character.isDigit(username.getText().charAt(i)) && !Character.isLetter(username.getText().charAt(i))) || username.getText().charAt(i) == ' ') {
                    validUsername = false;
                }
            }

            boolean validPassword = true;
            for (int i = 0; i < password.getText().length(); i++) {
                if (password.getText().charAt(i) == ' ') {
                    validPassword = false;
                }
            }

            if (validUsername) {
                List<String> lines = fileUtil.readFileToList("UserInfo/userinfo_new.txt");
                userInfo.convertToUserRecord(lines);

                int number = 0;
                for (String[] str : userInfo.getUserRecord()) {
                    if (str[0].equals(username.getText())) {
                        if (password.getText().equals("")) {
                            username.setText("");
                            password.setText("");
                            signUpFailureFrame4.setVisible(true);
                            this.setVisible(false);
                        } else {
                            username.setText("");
                            password.setText("");
                            this.signUpFailureFrame5.setVisible(true);
                            this.setVisible(false);
                            return;
                        }
                    }
                    number++;
                    if (number >= userInfo.getN()) {
                        break;
                    }
                }

                if (validPassword) {
                    userInfo.signUp(username.getText(), password.getText());

                    this.setVisible(false);
                    this.signUpSuccessFrame.setVisible(true);

                    fileUtil.writeFileFromList("UserInfo/userInfo_new.txt", userInfo.convertToList());

                    String folderPath = "UserMap/" + username.getText();
                    String filePath1 = "UserMap/" + username.getText() + "/level1";
                    String filePath2 = "UserMap/" + username.getText() + "/level2";
                    String filePath3 = "UserMap/" + username.getText() + "/level3";
                    String filePath4 = "UserMap/" + username.getText() + "/level4";
                    String filePath5 = "UserMap/" + username.getText() + "/level5";
                    String filePath6 = "UserMap/" + username.getText() + "/level6";
                    String filePath7 = "UserMap/" + username.getText() + "/level7";
                    String filePath8 = "UserMap/" + username.getText() + "/level8";
                    String filePath9 = "UserMap/" + username.getText() + "/level9";
                    String filePath10 = "UserMap/" + username.getText() + "/level10";
                    String filePath11 = "UserMap/" + username.getText() + "/level11";
                    String filePath12 = "UserMap/" + username.getText() + "/level12";
                    String filePath13 = "UserMap/" + username.getText() + "/level13";
                    String filePath14 = "UserMap/" + username.getText() + "/level14";
                    String filePath15 = "UserMap/" + username.getText() + "/level15";
                    String filePath16 = "rankList/" + "userScore/" + username.getText() + ".txt";

                    File folder = new File(folderPath);
                    File file1 = new File(filePath1);
                    File file2 = new File(filePath2);
                    File file3 = new File(filePath3);
                    File file4 = new File(filePath4);
                    File file5 = new File(filePath5);
                    File file6 = new File(filePath6);
                    File file7 = new File(filePath7);
                    File file8 = new File(filePath8);
                    File file9 = new File(filePath9);
                    File file10 = new File(filePath10);
                    File file11 = new File(filePath11);
                    File file12 = new File(filePath12);
                    File file13 = new File(filePath13);
                    File file14 = new File(filePath14);
                    File file15 = new File(filePath15);
                    File file16 = new File(filePath16);

                    try {
                        boolean success = folder.mkdir();
                        if (success) {
                            System.out.println("New user's player record folder created successfully");
                        } else {
                            System.out.println("Failed to create new user's player record folder");
                        }

                        success = file1.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 1 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level1", lines1);
                        } else {
                            System.out.println("Failed to create new user's player record for level 1");
                        }

                        success = file2.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 2 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level2", lines2);
                        } else {
                            System.out.println("Failed to create new user's player record for level 2");
                        }

                        success = file3.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 3 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level3", lines3);
                        } else {
                            System.out.println("Failed to create new user's player record for level 3");
                        }

                        success = file4.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 4 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level4", lines4);
                        } else {
                            System.out.println("Failed to create new user's player record for level 4");
                        }

                        success = file5.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 5 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level5", lines5);
                        } else {
                            System.out.println("Failed to create new user's player record for level 5");
                        }

                        success = file6.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 6 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level6", lines6);
                        } else {
                            System.out.println("Failed to create new user's player record for level 6");
                        }

                        success = file7.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 7 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level7", lines7);
                        } else {
                            System.out.println("Failed to create new user's player record for level 7");
                        }

                        success = file8.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 8 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level8", lines8);
                        } else {
                            System.out.println("Failed to create new user's player record for level 8");
                        }

                        success = file9.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 9 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level9", lines9);
                        } else {
                            System.out.println("Failed to create new user's player record for level 9");
                        }

                        success = file10.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 10 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level10", lines10);
                        } else {
                            System.out.println("Failed to create new user's player record for level 10");
                        }

                        success = file11.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 11 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level11", lines11);
                        } else {
                            System.out.println("Failed to create new user's player record for level 11");
                        }

                        success = file12.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 12 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level12", lines12);
                        } else {
                            System.out.println("Failed to create new user's player record for level 12");
                        }

                        success = file13.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 13 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level13", lines13);
                        } else {
                            System.out.println("Failed to create new user's player record for level 13");
                        }

                        success = file14.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 14 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level14", lines14);
                        } else {
                            System.out.println("Failed to create new user's player record for level 14");
                        }

                        success = file15.createNewFile();
                        if (success) {
                            System.out.println("New user's player record for level 15 created successfully");

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
                            fileUtil.writeFileFromList("UserMap/" + username.getText() + "/level15", lines15);
                        } else {
                            System.out.println("Failed to create new user's player record for level 15");
                        }

                        success = file16.createNewFile();
                        if (success) {
                            List<String> lines16 = new ArrayList<>();
                            lines16.add("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
                            lines16.add("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
                            fileUtil.writeFileFromList(filePath16, lines16);
                            System.out.println("Succeeded in creating new user's scores' record");
                        } else {
                            System.out.println("Failed to create new user's scores' record");
                        }

                    } catch (IOException c) {
                        c.printStackTrace();
                    }

                    username.setText("");
                    password.setText("");

                } else {
                    username.setText("");
                    password.setText("");
                    this.setVisible(false);
                    this.signUpFailureFrame2.setVisible(true);
                }
            } else {
                username.setText("");
                password.setText("");
                this.setVisible(false);
                this.signUpFailureFrame1.setVisible(true);
            }
        });

        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        cancelBtn.addActionListener(e -> {
            loginFrame.setVisible(true);
            this.setVisible(false);
            username.setText("");
            password.setText("");
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void setSignUpSuccessFrame(SignUpSuccessFrame signUpSuccessFrame) {
        this.signUpSuccessFrame = signUpSuccessFrame;
    }

    public void setSignUpFailureFrame1(SignUpFailureFrame1 signUpFailureFrame1) {
        this.signUpFailureFrame1 = signUpFailureFrame1;
    }

    public void setSignUpFailureFrame2(SignUpFailureFrame2 signUpFailureFrame2) {
        this.signUpFailureFrame2 = signUpFailureFrame2;
    }

    public void setSignUpFailureFrame3(SignUpFailureFrame3 signUpFailureFrame3) {
        this.signUpFailureFrame3 = signUpFailureFrame3;
    }

    public void setSignUpFailureFrame4(SignUpFailureFrame4 signUpFailureFrame4) {
        this.signUpFailureFrame4 = signUpFailureFrame4;
    }

    public void setSignUpFailureFrame5(SignUpFailureFrame5 signUpFailureFrame5) {
        this.signUpFailureFrame5 = signUpFailureFrame5;
    }
}
