package swings1;

import javax.swing.*;
import java.awt.*;


public class DrawingPanel extends JPanel {

    private final Shape square;
    private final Circle circle;
    private final Triangle triangle;

    public DrawingPanel() {
        setPreferredSize(new Dimension(400, 200));

        square = new Shape();
        circle = new Circle();
        triangle = new Triangle();

        // Position each shape individually
        square.setPosition(50, 75);
        circle.setPosition(150, 75);
        triangle.setPosition(250, 75);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw each shape individually
        square.draw(g2d);
        circle.draw(g2d);
        triangle.draw(g2d);

        // Draw name for each shape individually
        g2d.setColor(Color.BLACK);
        g2d.drawString(square.getShapeName(), square.x, square.y + 70);
        g2d.drawString(circle.getShapeName(), circle.x, circle.y + 70);
        g2d.drawString(triangle.getShapeName(), triangle.x, triangle.y + 70);
    }
}