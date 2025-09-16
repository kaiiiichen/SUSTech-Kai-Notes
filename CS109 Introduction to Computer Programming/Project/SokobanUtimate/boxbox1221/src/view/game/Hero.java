package view.game;

import javax.swing.*;
import java.awt.*;

public class Hero extends JComponent {
    private int row;
    private int col;
    private boolean chonghe;

    private final int value = 20;
    private static Color color = new Color(87, 171, 220);

    public Hero(int width, int height, int row, int col,boolean chonghe) {
        this.row = row;
        this.col = col;
        this.chonghe = chonghe;
        this.setSize(width, height);
        this.setLocation(8, 8);
    }

    public void paintComponent(Graphics g) {

        if (GameFrame.theme_num%4==1) {
            Toolkit t = Toolkit.getDefaultToolkit();
            Image i = t.getImage("image/ghost1.png");
            //Image i = t.getImage("image/carrot.gif");
            g.drawImage(i, 0, 0, this);
        }
        else if (GameFrame.theme_num%4==2) {
            Toolkit t = Toolkit.getDefaultToolkit();
            //Image i = t.getImage("image/elf1.png");
            Image i = t.getImage("image/carrot.gif");
            g.drawImage(i, 0, 0, this);

        }
        else if (GameFrame.theme_num%4==3) {
            Toolkit t = Toolkit.getDefaultToolkit();
            //Image i = t.getImage("image/elf1.png");
            Image i = t.getImage("image/yogurt1.png");
            g.drawImage(i, 0, 0, this);

        }
        else if (GameFrame.theme_num%4==4) {
            Toolkit t = Toolkit.getDefaultToolkit();
            //Image i = t.getImage("image/elf1.png");
            Image i = t.getImage("image/hamb.gif");
            g.drawImage(i, 0, 0, this);

        }
        else if (GameFrame.theme_num%4==0) {
            g.setColor(Color.BLACK);
            g.fillOval(0, 0, getWidth(), getHeight());
            g.setColor(color);
            g.fillOval(1,1,getWidth()-2,getHeight()-2);
        }
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isChonghe() {
        return chonghe;
    }

    public void setChonghe(boolean chonghe) {
        this.chonghe = chonghe;
    }
}