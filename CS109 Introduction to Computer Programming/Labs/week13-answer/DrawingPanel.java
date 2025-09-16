import javax.swing.*;
import java.awt.*;


public class DrawingPanel extends JPanel {

    public DrawingPanel() {
        setLayout(new BorderLayout());

        // Create shapes panel with grid layout
        JPanel shapesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        shapesPanel.setPreferredSize(new Dimension(400, 400));

        // Initialize arrays
        Shape[] shapes = new Shape[4];
        shapes[0] = new Rectangle(50, 50);
        shapes[1] = new Triangle();
        shapes[2] = new Rectangle(80, 30);
        shapes[3] = new Circle();

        JButton[] buttons = new JButton[4];

        // Create buttons with shapes
        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];

            buttons[i] = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    shape.setCenter(getWidth(),getHeight());
                    shape.draw(g2d);
                }
            };

            // TODO remember the selected shape
            buttons[i].addActionListener(e ->
                    JOptionPane.showMessageDialog(
                            this,
                            "Selected: " + shape.getShapeName(),
                            "Shape Information",
                            JOptionPane.INFORMATION_MESSAGE
                    ));

            shapesPanel.add(buttons[i]);
        }

        // Create control panel for shift/reset buttons
        JPanel controlPanel = new JPanel();
        JButton shiftButton = new JButton("Shift");
        JButton resetButton = new JButton("Reset");

        // Add shift button action
        shiftButton.addActionListener(e -> {
            // TODO if users already selected a shape, then shift the selected shape;
            //  otherwise pops up a warning message "No shape selected"
            for (Shape s:shapes) {
                s.shift();
            }
            repaint();
        });

        // Add reset button action
        resetButton.addActionListener(e -> {
            // TODO reset all shapes to its original form
            for (Shape s:shapes) {
                s.reset();
            }
            repaint();
        });

        controlPanel.add(shiftButton);
        controlPanel.add(resetButton);

        // Add panels to main panel
        add(shapesPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
}