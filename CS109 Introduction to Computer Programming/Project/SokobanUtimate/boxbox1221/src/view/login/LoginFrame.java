package view.login;

import util.AdvancedFileUtil;
import view.FrameUtil;
import util.FileUtil;
import view.level.LevelFrame;
import view.signup.SignUpFrame;
import view.signup.UserInfo;
import view.welcome.WelcomeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton signupBtn;
    private JButton cancelBtn;
    private LevelFrame levelFrame;
    private LoginFailureFrame1 loginFailureFrame1;
    private LoginFailureFrame2 loginFailureFrame2;
    private LoginFailureFrame3 loginFailureFrame3;
    private LoginFailureFrame4 loginFailureFrame4;
    private SignUpFrame signUpFrame;
    private WelcomeFrame welcomeFrame;

    UserInfo userInfo = new UserInfo();

    FileUtil fileUtil = new AdvancedFileUtil();

    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "Username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 70), 70, 40, "Password:");
        JLabel sayingLabel = FrameUtil.createJLabel(this, new Point(56, 170), 200, 40, "New here?  Sign up right now!");
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = new JPasswordField();

        password.setBounds(120, 70, 120, 40);
        this.add(password);
//        password.setEchoChar('*');    // 我注释掉了这句话，默认setEchoChar是一个个的小黑点

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 130), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 130), 100, 40);
        signupBtn = FrameUtil.createButton(this, "Sign up", new Point(100, 210), 100, 40);
        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(100, 260), 100, 40);

        submitBtn.addActionListener(e -> {
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());

            List<String> lines = fileUtil.readFileToList("UserInfo/userinfo_new.txt");
            userInfo.convertToUserRecord(lines);

            boolean isAUser = false;
            int number = 0;
            for (String[] str : userInfo.getUserRecord()) {
                if (str[0].equals(username.getText())) {
                    isAUser = true;
                    break;
                }
                number++;
                if (number >= userInfo.getN()) {
                    break;
                }
            }

            if (isAUser) {
                if (userInfo.getUserRecord()[number][1].equals(password.getText())) {

                    List<String> lines0 = new ArrayList<>();
                    lines0.add(username.getText());

                    fileUtil.writeFileFromList("CurrentPlayer/CurrentPlayer.txt", lines0);
                    //  我们把当前玩家的用户名存在了这个txt中供我们全局使用

                    username.setText("");
                    password.setText("");
                    levelFrame.setVisible(true);
                    this.setVisible(false);
                } else if (password.getText().equals("")) {
                    username.setText("");
                    password.setText("");
                    loginFailureFrame4.setVisible(true);
                    this.setVisible(false);
                } else {
                    username.setText("");
                    password.setText("");
                    loginFailureFrame1.setVisible(true);
                    this.setVisible(false);
                }
            } else {
                if (username.getText().equals("")) {
                    username.setText("");
                    password.setText("");
                    loginFailureFrame3.setVisible(true);
                } else if (password.getText().equals("")) {
                    username.setText("");
                    password.setText("");
                    loginFailureFrame4.setVisible(true);
                } else {
                    username.setText("");
                    password.setText("");
                    loginFailureFrame2.setVisible(true);
                }
                this.setVisible(false);
            }


            //todo: check login info

        });
        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        signupBtn.addActionListener(e -> {
            if (this.signUpFrame != null) {
                this.signUpFrame.setVisible(true);
                this.setVisible(false);
            }
        });

        cancelBtn.addActionListener(e -> {
            welcomeFrame.setVisible(true);
            this.setVisible(false);
            username.setText("");
            password.setText("");
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void setSignUpFrame(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public void setLoginFailureFrame1(LoginFailureFrame1 loginFailureFrame1) {
        this.loginFailureFrame1 = loginFailureFrame1;
    }

    public void setLoginFailureFrame2(LoginFailureFrame2 loginFailureFrame2) {
        this.loginFailureFrame2 = loginFailureFrame2;
    }

    public void setLoginFailureFrame3(LoginFailureFrame3 loginFailureFrame3) {
        this.loginFailureFrame3 = loginFailureFrame3;
    }

    public void setLoginFailureFrame4(LoginFailureFrame4 loginFailureFrame4) {
        this.loginFailureFrame4 = loginFailureFrame4;
    }

    public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
        this.welcomeFrame = welcomeFrame;
    }
}
