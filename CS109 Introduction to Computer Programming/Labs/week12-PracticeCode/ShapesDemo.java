package swings1;

import javax.swing.*;
import java.awt.*;

public class ShapesDemo extends JFrame {
    public ShapesDemo() {
        setTitle("Shapes Inheritance Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new DrawingPanel());

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ShapesDemo().setVisible(true);
    }
}
