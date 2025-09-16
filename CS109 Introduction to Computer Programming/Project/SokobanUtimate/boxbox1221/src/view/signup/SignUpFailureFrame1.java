package view.signup;

import view.FrameUtil;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class SignUpFailureFrame1 extends JFrame {
    private JButton cancelBtn;
    private SignUpFrame signUpFrame;

    public void setSignUpFrame(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public SignUpFailureFrame1(int width, int height) {
        this.setTitle("Sign Up Failure Frame 1");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(45, 20), 250, 40, "Warning: a valid user name only");
        JLabel warningLabel2 = FrameUtil.createJLabel(this, new Point(45, 40), 250, 40, "contains letters, numbers and '_'.");

        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(110, 80), 100, 40);

        cancelBtn.addActionListener(e -> {
            signUpFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
