


import javax.swing.*;

public class CircleFrame extends JFrame {
    private JButton jbtEnlarge = new JButton("Enlarge");
    private JButton jbtShrink = new JButton("Shrink");
    private CirclePanel canvas;

    public CircleFrame() {
        this.setSize(300, 400);//the size of frame
        this.setLayout(null);// null layout
        this.setLocationRelativeTo(null);
        this.canvas = new CirclePanel(this.getWidth(), this.getHeight());//create a panel
        this.add(canvas);//add panel into frame

        //add click listener into button: jbtEnlarge
        jbtEnlarge.addActionListener(l -> {
            canvas.enlarge();//when the button clicked, do enlarge method.
        });
        //add click listener into button: jbtShrink
        jbtShrink.addActionListener(l -> {
            canvas.shrink();//when the button clicked, do shrink method.
        });
        int btnWidth = (int) (this.getWidth() * 0.4);
        int btnHeight = (int) (this.getHeight() * 0.1);

        jbtShrink.setSize(btnWidth, btnHeight);//set size of jbtShrink button
        jbtEnlarge.setSize(btnWidth, btnHeight);//set size of jbtEnlarge button
        jbtShrink.setLocation(10, 310);// set location of jbtShrink button
        jbtEnlarge.setLocation(160 , 310);// set location of jbtEnlarge button
        this.add(jbtShrink);//add button into frame
        this.add(jbtEnlarge);//add button into frame
        System.out.printf("enlarge location:[%d,%d],size:[%d,%d]\n",jbtEnlarge.getX(),jbtEnlarge.getY(),jbtEnlarge.getWidth(),jbtEnlarge.getHeight());
        System.out.printf("shrink location:[%d,%d],size:[%d,%d]\n",jbtShrink.getX(),jbtShrink.getY(),jbtShrink.getWidth(),jbtShrink.getHeight());
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        CircleFrame frame = new CircleFrame();
        frame.setTitle("ControlCircle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
