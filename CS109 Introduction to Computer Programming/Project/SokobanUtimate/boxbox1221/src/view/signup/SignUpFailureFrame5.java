package view.signup;

import view.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class SignUpFailureFrame5 extends JFrame {
    private JButton cancelBtn;
    private SignUpFrame signUpFrame;

    public SignUpFailureFrame5(int width, int height) {
        this.setTitle("Sign Up Failure Frame 5");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel warningLabel1 = FrameUtil.createJLabel(this, new Point(10, 20), 300, 40, "Warning: Current username has been occupied!");

        cancelBtn = FrameUtil.createButton(this, "Cancel", new Point(110, 80), 100, 40);

        cancelBtn.addActionListener(e -> {
            signUpFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setSignUpFrame(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;
    }
}