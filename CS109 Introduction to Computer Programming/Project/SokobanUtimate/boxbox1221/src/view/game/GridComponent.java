package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import view.game.GameFrame;


public class GridComponent extends JComponent {
    private int row;
    private int col;
    private final int id; // represents the units digit value. It cannot be changed during one game.
    private int QwQ;


    public int getQwQ() {
        return QwQ;
    }

    public void setQwQ(int qwQ) {
        QwQ = qwQ;
    }

    private Hero hero;
    private Box box;
    static Color color = new Color(246, 246, 229);

    public GridComponent(int row, int col, int id, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.id = id;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        //System.out.println("id="+id);
        Color borderColor = color;
        switch (id % 10) {
            case 1:



                if (GameFrame.theme_num%4==1) {
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image i = t.getImage("image/ice_mountain.png");
                    //Image i = t.getImage("image/carrot.gif");
                    g.drawImage(i, 0, 0, this);
                }
                else if (GameFrame.theme_num%4==2) {
                    Color lightBlue = new Color(173, 216, 230);
                    g.setColor(lightBlue);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image i = t.getImage("image/rainbow2.png");
                    //Image i = t.getImage("image/carrot.gif");
                    g.drawImage(i, 0, 0, this);

                }
                else if (GameFrame.theme_num%4==3) {
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image i = t.getImage("image/bread.png");
                    //Image i = t.getImage("image/carrot.gif");
                    g.drawImage(i, 0, 0, this);

                }
                else if (GameFrame.theme_num%4==4) {
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image i = t.getImage("image/donut.gif");
                    //Image i = t.getImage("image/carrot.gif");
                    g.drawImage(i, 0, 0, this);

                }
                else if (GameFrame.theme_num%4==0) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    borderColor = Color.DARK_GRAY;
                }

                break;
            case 0:




                if (GameFrame.theme_num%4==1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                else if (GameFrame.theme_num%4==2) {
//                    Toolkit t = Toolkit.getDefaultToolkit();
//                    Image i = t.getImage("image/rainbow2.png");
//                    g.drawImage(i, 0, 0, this);
                    Color lightBlue = new Color(173, 216, 230);
                    g.setColor(lightBlue);
                    g.fillRect(0, 0, getWidth(), getHeight());

                }
                else if (GameFrame.theme_num%4==3) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());

                }
                else if (GameFrame.theme_num%4==4) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());

                }
                else if (GameFrame.theme_num%4==0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }

                break;
            case 2:
                if (GameFrame.theme_num%4==1) {
                    Toolkit t=Toolkit.getDefaultToolkit();
                    //Image i=t.getImage("image/fire3.png");
                    Image i=t.getImage("image/firegif.gif");
                    g.drawImage(i, 0,0,this);
                }
                else if (GameFrame.theme_num%4==2) {
                    Color lightBlue = new Color(173, 216, 230);
                    g.setColor(lightBlue);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    if (true) {
                        Toolkit t = Toolkit.getDefaultToolkit();
                        Image i = t.getImage("image/cloud1.png");
                        g.drawImage(i, 1, 3, this);
                    }
                    else {
//                        System.out.println("qwqqqqqq");
                        Toolkit t = Toolkit.getDefaultToolkit();
                        Image i = t.getImage("image/cloud_and_sun.png");
                        g.drawImage(i, 0, 0, this);
                    }

                }
                if (GameFrame.theme_num%4==3) {
                    Toolkit t=Toolkit.getDefaultToolkit();
                    //Image i=t.getImage("image/fire3.png");
                    Image i=t.getImage("image/egg2.png");
                    g.drawImage(i, 0,0,this);
                }
                if (GameFrame.theme_num%4==4) {
                    Toolkit t=Toolkit.getDefaultToolkit();
                    //Image i=t.getImage("image/fire3.png");
                    Image i=t.getImage("image/firegif.gif");
                    g.drawImage(i, 0,0,this);
                }
                else if (GameFrame.theme_num%4==0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.GREEN);
                    int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                    int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                    g.fillPolygon(xPoints, yPoints, 4);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(xPoints, yPoints, 4);
                }







                break;
        }
        Border border = BorderFactory.createLineBorder(borderColor, 1);
        this.setBorder(border);
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

    public int getId() {
        return id;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    //When adding a hero in this grid, invoking this method.
    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);
    }

    //When adding a box in this grid, invoking this method.
    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }
    //When removing hero from this grid, invoking this method
    public Hero removeHeroFromGrid() {
        this.remove(this.hero);//remove hero component from grid component
        Hero h = this.hero;
        this.hero = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return h;
    }
    //When removing box from this grid, invoking this method
    public Box removeBoxFromGrid() {
        //System.out.println("000");
        this.remove(this.box);//remove box component from grid component
        //System.out.println("111");

        Box b = this.box;
        this.box = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return b;
    }
}