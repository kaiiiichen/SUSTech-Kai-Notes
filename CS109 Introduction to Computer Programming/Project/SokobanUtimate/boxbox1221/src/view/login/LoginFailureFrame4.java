package view.login;

import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class LoginFailureFrame4 extends JFrame {
    private JButton cancelBtn;
    private LoginFrame loginFrame;

    public LoginFailureFrame4(int width, int height) {
        this.setTitle("Login Failure Frame 4");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(20, 20), 300, 40, "Warning: You haven't type in your password!");

        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(110, 80), 100, 40);

        cancelBtn.addActionListener(e -> {
            loginFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }
}