import view.load.LoadingFailureFrame;
import view.load.LoadingFrame;
import view.level.LevelFrame;
import view.login.*;
import view.rank.RankFrame;
import view.signup.*;
import view.welcome.WelcomeFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            WelcomeFrame welcomeFrame = new WelcomeFrame(500,400);
            welcomeFrame.setVisible(true);
            LoginFrame loginFrame = new LoginFrame(300, 340);
            loginFrame.setVisible(false);
            LevelFrame levelFrame = new LevelFrame(525, 400);
            levelFrame.setVisible(false);
            LoadingFrame loadingFrame = new LoadingFrame(340, 200);
            loadingFrame.setVisible(false);
            LoadingFailureFrame loadingFailureFrame = new LoadingFailureFrame(320, 200);
            loadingFailureFrame.setVisible(false);
            RankFrame rankFrame = new RankFrame(350,400);
            rankFrame.setVisible(false);

            welcomeFrame.setLevelFrame(levelFrame);
            welcomeFrame.setLoginFrame(loginFrame);
            loginFrame.setWelcomeFrame(welcomeFrame);
            levelFrame.setWelcomeFrame(welcomeFrame);
            rankFrame.setLevelFrame(levelFrame);
            levelFrame.setRankFrame(rankFrame);
            LoginFailureFrame1 loginFailureFrame1 = new LoginFailureFrame1(320, 170);
            LoginFailureFrame2 loginFailureFrame2 = new LoginFailureFrame2(320, 170);
            LoginFailureFrame3 loginFailureFrame3 = new LoginFailureFrame3(320, 170);
            LoginFailureFrame4 loginFailureFrame4 = new LoginFailureFrame4(320, 170);
            SignUpFrame signUpFrame = new SignUpFrame(300, 400);
            SignUpSuccessFrame signUpSuccessFrame = new SignUpSuccessFrame(320, 170);
            SignUpFailureFrame1 signUpFailureFrame1 = new SignUpFailureFrame1(320, 170);
            SignUpFailureFrame2 signUpFailureFrame2 = new SignUpFailureFrame2(320, 170);
            SignUpFailureFrame3 signUpFailureFrame3 = new SignUpFailureFrame3(320, 170);
            SignUpFailureFrame4 signUpFailureFrame4 = new SignUpFailureFrame4(320, 170);
            SignUpFailureFrame5 signUpFailureFrame5 = new SignUpFailureFrame5(320, 170);
            levelFrame.setVisible(false);


            loginFrame.setLevelFrame(levelFrame);
            loginFrame.setSignUpFrame(signUpFrame);
            loginFrame.setLoginFailureFrame1(loginFailureFrame1);
            loginFrame.setLoginFailureFrame2(loginFailureFrame2);
            loginFrame.setLoginFailureFrame3(loginFailureFrame3);
            loginFrame.setLoginFailureFrame4(loginFailureFrame4);
            signUpFrame.setLoginFrame(loginFrame);
            signUpFrame.setSignUpSuccessFrame(signUpSuccessFrame);
            signUpFrame.setSignUpFailureFrame1(signUpFailureFrame1);
            signUpFrame.setSignUpFailureFrame2(signUpFailureFrame2);
            signUpFrame.setSignUpFailureFrame3(signUpFailureFrame3);
            signUpFrame.setSignUpFailureFrame4(signUpFailureFrame4);
            signUpFrame.setSignUpFailureFrame5(signUpFailureFrame5);

            loginFailureFrame1.setLoginFrame(loginFrame);
            loginFailureFrame2.setLoginFrame(loginFrame);
            loginFailureFrame3.setLoginFrame(loginFrame);
            loginFailureFrame4.setLoginFrame(loginFrame);
            signUpSuccessFrame.setLoginFrame(loginFrame);
            signUpFailureFrame1.setSignUpFrame(signUpFrame);
            signUpFailureFrame2.setSignUpFrame(signUpFrame);
            signUpFailureFrame3.setSignUpFrame(signUpFrame);
            signUpFailureFrame4.setSignUpFrame(signUpFrame);
            signUpFailureFrame5.setSignUpFrame(signUpFrame);

            levelFrame.setLoadingFrame(loadingFrame);
            levelFrame.setLoadingFailureFrame(loadingFailureFrame);
            loadingFrame.setLevelFrame(levelFrame);
            loadingFrame.setLoadingFailureFrame(loadingFailureFrame);
            loadingFailureFrame.setLevelFrame(levelFrame);
            loadingFailureFrame.setLoadingFrame(loadingFrame);

        });
    }
}
