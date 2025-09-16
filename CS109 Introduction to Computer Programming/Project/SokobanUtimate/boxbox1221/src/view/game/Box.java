package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import view.game.GameFrame;


public class Box extends JComponent {

    private int row;
    private int col;
    private boolean chonghe;

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

    private final int value = 10;

    public Box(int width, int height, int row, int col,boolean chonghe) {
        this.row = row;
        this.col = col;
        this.chonghe = chonghe;
        this.setSize(width, height);
        this.setLocation(5, 5);
    }

    public void paintComponent(Graphics g) {

        if (GameFrame.theme_num%4==1) {
            this.setBorder(null);
            Toolkit t=Toolkit.getDefaultToolkit();
            Image i=t.getImage("image/ice4.png");
            g.drawImage(i, 0,0,this);
        }
        else if (GameFrame.theme_num%4==2) {
            this.setBorder(null);
            if (true) {
                Toolkit t = Toolkit.getDefaultToolkit();
                Image i=t.getImage("image/sun1.png");
                g.drawImage(i, 0,0,this);
            }
            else {
                System.out.println("qwqqqqqq");
                Toolkit t = Toolkit.getDefaultToolkit();
                Image i = t.getImage("image/cloud_and_sun.png");
                g.drawImage(i, 0, 0, this);
            }

        }
        else if (GameFrame.theme_num%4==3) {
            this.setBorder(null);
            Toolkit t=Toolkit.getDefaultToolkit();
            Image i=t.getImage("image/egg1.png");
            g.drawImage(i, 0,0,this);
        }
        else if (GameFrame.theme_num%4==4) {
            this.setBorder(null);
            Toolkit t=Toolkit.getDefaultToolkit();
            Image i=t.getImage("image/ice4.png");
            g.drawImage(i, 0,0,this);
        }
        else if (GameFrame.theme_num%4==0) {
            g.setColor(Color.ORANGE);
            g.fillRect(0, 0, getWidth(), getHeight());
            Border border = BorderFactory.createLineBorder(Color.black, 1);
            this.setBorder(border);
        }


    }

    public int getValue() {
        return value;
    }

    public boolean isChonghe() {
        return chonghe;
    }

    public void setChonghe(boolean chonghe) {
        this.chonghe = chonghe;
    }
}