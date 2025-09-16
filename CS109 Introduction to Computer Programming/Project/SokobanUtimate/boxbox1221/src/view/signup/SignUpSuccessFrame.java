package view.signup;

import view.FrameUtil;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class SignUpSuccessFrame extends JFrame {
    private JButton cancelBtn;
    private LoginFrame loginFrame;

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public SignUpSuccessFrame(int width, int height) {
        this.setTitle("Sign Up Success Frame");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(110, 10), 250, 40, "Congratulations!");
        JLabel warningLabel2 = FrameUtil.createJLabel(this, new Point(55, 40), 250, 40, "You have successfully signed up!");

        cancelBtn = FrameUtil.createButton(this, "Back to login", new Point(110, 80), 100, 40);

        cancelBtn.addActionListener(e -> {
            loginFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
