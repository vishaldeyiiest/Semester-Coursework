/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ellipse;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author vishal
 */
public class ellipse extends Applet implements ActionListener, MouseListener {

    private int diff = 5;
    public static int resX = 800;
    public static int resY = 600;
    public static int offsetx = 0;
    public static int offsety = 40;
    int col = 0;
    int rx, ry;
    Button b1;
    Button b2;
    Button b3;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    Label l1 = new Label("xc = ");
    Label l2 = new Label("yc = ");
    Label l3 = new Label("Radius rx = ");
    Label l4 = new Label("Radius ry = ");
    int z1, z2;
    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    TextField t4 = new TextField(3);
    Button b = new Button("DRAW");

    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        b1 = new Button();
        b1.setLabel("Zoom In");
        b2 = new Button();
        b2.setLabel("Reset");
        b3 = new Button();
        b3.setLabel("Zoom out");
        b1.addActionListener(this);
        b2.addActionListener(this);
        this.addMouseListener(this);
        b3.addActionListener(this);
        z1 = z2 = 0;
        add(b1);
        add(b2);
        add(b3);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(b);
        b.addActionListener(this);

    }
    int tempx, tempy;
    public void draw_ellipse(Graphics g) {
        int X = Integer.parseInt(t1.getText());
        int Y = Integer.parseInt(t2.getText());
        rx = Integer.parseInt(t3.getText());
        ry = Integer.parseInt(t4.getText());
        if(z1 == 1)
        {
            rx=tempx + 10;
            ry=tempy + 10;
        }
        if(z2 == 1)
        {
            rx=tempx - 10; 
            ry=tempy - 10;
        }
        int x1 = 0;
        double p = ry * ry - rx * rx * ry + 0.25 * ry * ry;
        int y1 = ry;
        g.setColor(Color.BLUE);
        //int i = 0;
        while (2*ry*ry*x1 < 2*rx*rx*y1) 
        {
            //if (i % 6 == 0) 
            {
                drawPoint(g, -x1 + X, y1 + Y);
                drawPoint(g, -x1 + X, -y1 + Y);
                drawPoint(g, x1 + X, y1 + Y);
                drawPoint(g, x1 + X, -y1 + Y);

            }
            x1++;
            if (p < 0)
                p = p + 2*ry*ry*x1 + ry*ry;
            else 
            {
                y1--;
                p = p + 2*ry*ry*x1 - 2*rx*rx*y1 + ry*ry;
            }
        }

        double p2 = ry*ry*(x1+1/2)*(x1+1/2) + rx*rx*(y1-1)*(y1-1) - ry*ry*rx*rx;
        while (y1 > 0) 
        {
            drawPoint(g, X-x1, Y+y1);
            drawPoint(g, X-x1, Y-y1);
            drawPoint(g, X+x1, Y+y1);
            drawPoint(g, X+x1, Y-y1);
            y1--;
            if (p2 > 0)
                p2 = p2 - (2*rx*rx*y1) + (ry*ry);
            else 
            {
                x1++;
                p2 = p2 + 2*ry*ry*x1 + 2*rx*rx*y1 + ry*ry;
            }
        }
        tempx = rx; tempy = ry;
    }

    @Override
    public void paint(Graphics g) 
    {
        resX = getWidth();
        resY = getHeight();

        for (int i = 0; i < x.size(); i++) {
            drawPoint(g, x.get(i), y.get(i));
        }

        g.setColor(Color.BLACK);
        //setBackground(Color.GRAY);
        //g.fillRect(87, 87, diff, diff);
        for (int i = offsetx; i < resX - offsetx; i = i + diff) {
            g.drawLine(i, offsety, i, resY - offsety);
        }

        for (int i = offsety; i < resY - offsety; i += diff) {
            g.drawLine(offsetx, i, resX - offsetx, i);
        }

        g.drawLine(resX - offsetx, resY - offsety, resX - offsetx, offsety);
        g.drawLine(resX - 1 - offsetx, resY - offsety, offsetx, resY - offsety);
        
        if (col == 1)
            draw_ellipse(g);
            //g.drawOval(resX/2-circle_radius/2, resY/2-circle_radius/2, circle_radius , circle_radius);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
        if (ae.getSource() == b1) 
        {
            diff++;
            z1 = 1;
            z2 = 0;
            repaint();
        } 
        else if (ae.getSource() == b3) 
        {
            diff--;
            z2 = 1;
            z1 = 0;
            repaint();
        }
        else if (ae.getSource() == b2) 
        {
            x.clear();
            y.clear();
            diff = 5;
            z1 = 0; z2 = 0;
            col = 0;
            repaint();
        } 
        else if (ae.getSource() == b) 
        {
            col = 1;
            repaint();
        }

    }

    void drawPoint(Graphics g, int xpos, int ypos) 
    {
        //g.setColor(Color.ORANGE);
        g.fillRect(((int) (xpos - offsetx) / diff) * diff + offsetx, ((int) (ypos - offsety) / diff) * diff + offsety, diff, diff);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Clicked");
        x.add(me.getX());
        y.add(me.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
