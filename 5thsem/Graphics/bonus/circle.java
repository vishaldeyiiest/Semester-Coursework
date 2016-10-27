/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonus;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author vishal
 */
class MyPoint implements Comparable <MyPoint> {
    int x, y;
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(MyPoint pt) {
        int cmp = Integer.compare(x, pt.x);
        if (cmp == 0) {
            return Integer.compare(y, pt.y);
        } else {
            return cmp;
        }
    }

}
public class circle extends Applet implements ActionListener, MouseListener {

    private int diff = 2;
    public static int resX = 800;
    public static int resY = 600;
    public static int offsetx = 0;
    public static int offsety = 40;
    int col = 0;
    Button b1;
    Button b2;
    Button b3;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    /*Label l1 = new Label("x1 = ");
    Label l2 = new Label("x2 = ");
    Label l3 = new Label("Radius = ");
    //Label l4 = new Label("y2 = ");

    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    //TextField t4 = new TextField(3);
    */Button b = new Button("DRAW");
    int f = 0;
    int small = 0;
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
        add(b1);
        add(b2);
        add(b3);
      /*  add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
      */
        add(b);
        b.addActionListener(this);

    }
    int z1 = 0, z2 = 0, temp;
    public void draw_circle(Graphics g, int X, int Y, int r) 
    {
       
        //int X = Integer.parseInt(t1.getText());
        //int Y = Integer.parseInt(t2.getText());
        //int r = Integer.parseInt(t3.getText());
        if(small == 1)
        {
            if(z1 == 1)
                r = temp + 1;
            if(z2 == 1)
                r = temp - 1;
        }
        else
        {
            if(z1 == 1)
                r = temp + 10;
            if(z2 == 1)
                r = temp - 10;
        }
        int x1 = 0;
        int p = 5 / 4 - r, y1 = r;
        //int i = 0;
        while (x1 < y1) 
        {
            //if (i % 6 == 0) 
                drawPoint(g, x1 + X, y1 + Y);
                drawPoint(g, x1 + X, -y1 + Y);
                drawPoint(g, -x1 + X, +y1 + Y);
                drawPoint(g, -x1 + X, -y1 + Y);
                drawPoint(g, y1 + X, x1 + Y);
                drawPoint(g, y1 + X, -x1 + Y);
                drawPoint(g, -y1 + X, x1 + Y);
                drawPoint(g, -y1 + X, -x1 + Y);
                         
            //i++;
            if (p < 0)
                p = p + 2 * x1 + 3;
            else {
                p = p + 2 * x1 + 1 - 2 * (y1 - 1);
                y1--;
            }
            x1++;
        }
        temp = r;
    }

    public void draw_midpoint(Graphics g, int X0, int Y0, int X1, int Y1) {
        
        int dx = X1 - X0;
        int dy = Y1 - Y0;

        int steps;
        if (dx > dy) {
            steps = dx;
        } else {
            steps = dy;
        }

        int x1 = X0, y1 = Y0;
        for (int k = 0; k <= steps; k++) {
            //double mx = x1 + 1;
            double my = y1 + 0.5;
            Point a, b;
            if (dx < 0) {
                a = new Point(x1 - 1, y1 + 1);
                b = new Point(x1 - 1, y1);
                x1--;
            } else {
                a = new Point(x1 + 1, y1 + 1);
                b = new Point(x1 + 1, y1);
                x1++;
            }
            Point c = new Point(X0, Y0);
            Point d = new Point(X1, Y1);
            double q = intersection(a, b, c, d);
            if (q >= my) {
                y1++;
            }

            g.fillRect(x1 / diff * diff, ((y1 - offsety) / diff) * diff + offsety, diff, diff);
        }
    }
    public double intersection(Point a, Point b, Point c, Point d) {
        double x1 = a.getX();
        double y1 = a.getY();
        double x2 = b.getX();
        double y2 = b.getY();
        double x3 = c.getX();
        double y3 = c.getY();
        double x4 = d.getX();
        double y4 = d.getY();
        double d1 = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d1 == 0) {
            return 0;
        }

        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d1;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d1;
        return yi;
    }
    @Override
    public void paint(Graphics g) 
    {
        resX = getWidth();
        resY = getHeight();

        g.setColor(Color.BLACK);
        
        for (int i = offsetx; i < resX - offsetx; i = i + diff) {
            g.drawLine(i, offsety, i, resY - offsety);
        }

        for (int i = offsety; i < resY - offsety; i += diff) {
            g.drawLine(offsetx, i, resX - offsetx, i);
        }

        g.drawLine(resX - offsetx, resY - offsety, resX - offsetx, offsety);
        g.drawLine(resX - 1 - offsetx, resY - offsety, offsetx, resY - offsety);
        int X = 300, Y = 300, r = 250;
        if (col == 1)
        {
            g.setColor(Color.RED);
            draw_circle(g, X, Y, r);
            f = 1;
            g.setColor(Color.BLUE);
            draw_circle(g, X, Y, r/2);
            f = 0;
            g.setColor(Color.ORANGE);
           
           int tempx = X, tempy = Y/2, x1, y1;
           x.add(tempx);y.add(tempy);
             for(int a = 0; a < 8; a++)
            {
                double o = Math.toRadians(45);
                x1 = (int)(tempx*Math.cos(o))+(int)(tempy*Math.sin(o));
                y1 = (int)(X-tempx*Math.sin(o))+(int)(tempy*Math.cos(o));
                tempx = x1;
                tempy = y1;
                x.add(tempx);
                y.add(tempy);
            }
                      
            for(int i = 0; i < x.size();i++)
            {
                System.out.println(x.get(i)+" "+y.get(i));
                small = 1;
                draw_circle(g, x.get(i), y.get(i), 10);
            }
            ArrayList<MyPoint> a = new ArrayList<MyPoint>();
            for(int i = 0; i < x.size();i++)
                a.add(new MyPoint(x.get(i),y.get(i)));
            Collections.sort(a);
           System.out.println();
            for(int i = 0; i < a.size()-1;i = i++)
            {
                draw_midpoint(g, a.get(i).x, a.get(i).y, a.get(i+1).x, a.get(i+1).y);
                 System.out.println(a.get(i).x+ " "+ a.get(i).y);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == b1) 
        {
            diff++;
            z1 = 1;
            repaint();
        } 
        else if (ae.getSource() == b3) 
        {
            diff--;
            z2 = 1;
            repaint();
        } 
        else if (ae.getSource() == b2)
        {
            x.clear();
            y.clear();
            z1 = 0; z2 = 0;
            diff = 2;
            col = 0;
            repaint();
        } 
        else if (ae.getSource() == b)
        {
            col = 1;
            repaint();
        }

    }
    //int count = 0;
    void drawPoint(Graphics g, int xpos, int ypos) {
        /*
        if(f == 1)
        {
            x.add(xpos);
            y.add(ypos);
            System.out.println(xpos+" "+ypos);
        }*/
        //count++;
        if(f != 1)
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
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
