package view.signup;

import view.FrameUtil;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class SignUpFailureFrame2 extends JFrame {
    private JButton cancelBtn;
    private SignUpFrame signUpFrame;

    public void setSignUpFrame(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public SignUpFailureFrame2(int width, int height) {
        this.setTitle("Sign Up Failure Frame 2");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(45, 20), 300, 40, "Warning: You contained blanks");
        JLabel warningLabel2 = FrameUtil.createJLabel(this, new Point(105, 40), 250, 40, " in your password!");

        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(110, 80), 100, 40);

        cancelBtn.addActionListener(e -> {
            signUpFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
