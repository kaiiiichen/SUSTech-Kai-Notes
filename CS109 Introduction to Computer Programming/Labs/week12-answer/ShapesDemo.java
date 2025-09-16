package swings1.answers;

import javax.swing.*;

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
