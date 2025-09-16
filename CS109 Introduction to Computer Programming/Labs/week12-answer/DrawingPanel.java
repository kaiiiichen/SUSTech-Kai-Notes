package swings1.answers;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private final Shape square;
    private final Triangle triangle;
    private final Rectangle rectangle;
    private final Circle circle;

    public DrawingPanel() {
        setPreferredSize(new Dimension(400, 400));


        square = new Shape();
        triangle = new Triangle();
        rectangle = new Rectangle();
        circle = new Circle();


        square.setPosition(75, 75);
        triangle.setPosition(275, 75);
        rectangle.setPosition(75, 275);
        circle.setPosition(275, 275);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw all shapes
        square.draw(g2d);
        triangle.draw(g2d);
        rectangle.draw(g2d);
        circle.draw(g2d);

        // Draw names under each shape
        g2d.setColor(Color.BLACK);
        g2d.drawString(square.getShapeName(), square.x, square.y + 70);
        g2d.drawString(triangle.getShapeName(), triangle.x, triangle.y + 70);
        g2d.drawString(rectangle.getShapeName(), rectangle.x, rectangle.y + 70);
        g2d.drawString(circle.getShapeName(), circle.x, circle.y + 70);
    }
}