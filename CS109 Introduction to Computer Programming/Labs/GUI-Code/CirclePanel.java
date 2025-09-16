
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class CirclePanel extends JPanel {
    private int radius = 50; // Default circle radius
    private Color color = Color.BLACK;
    private final Random random = new Random();

    public CirclePanel(int width, int height) {
        //add mouse click event. When mouse click current Component, process processMouseEvent method.
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(width, (int) (height * 0.66));
        this.setLocation(0, 0);
    }

    /**
     * Enlarge the circle
     */
    public void enlarge() {
        radius = (int) (radius * 1.1);
        this.repaint();

    }

    /**
     * Enlarge the circle
     */
    public void shrink() {
        radius = (int) (radius * 0.9);
        this.repaint();
    }

    /**
     * when invoke repaint() method, execute paintComponent method
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.color);
        g.drawString(String.format("Radius: %d",this.radius),10,15);
        g.fillOval(this.getWidth() / 2 - radius, this.getHeight() / 2 - radius, 2 * radius, 2 * radius);
    }


    /**
     * when being mouse click , execute this method.
     * @param e the mouse event
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            System.out.println(color);
            repaint();
        }
    }

}
