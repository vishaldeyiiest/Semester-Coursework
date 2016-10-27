/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labtest;

/**
 *
 * @author vishal
 */

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Labtest extends Applet implements ActionListener, MouseListener {

    private int diff = 2;
    public static int resX = 800;
    public static int resY = 600;
    public static int offsetx = 0;
    public static int offsety = 40;
    int col = 0;
    int rx , ry ;
    int X, Y;
   // int Y = 300;
    double angle;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    Label l1 = new Label("Radius rx = ");
    Label l2 = new Label("Radius ry = ");
    Label l3 = new Label("Xc = ");
    Label l4 = new Label("Yc = ");
    Label l5 = new Label("angle = ");
    int z1, z2, z3, z4, z5, z6;
    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    TextField t4 = new TextField(3);
    TextField t5 = new TextField(3);
    //TextField t3 = new TextField(3);
    //TextField t4 = new TextField(3);
    //Button b1 = new Button("Major Axis");
    //Button b2 = new Button("Minor Axis");
    Button b3 = new Button("Clockwise");
    Button b4 = new Button("Anticlockwise");
    //Button b5 = new Button("Visualize");
    Button b6 = new Button("Reset");
    
    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
       // b1.addActionListener(this);
        //b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        //b5.addActionListener(this);
        b6.addActionListener(this);
        this.addMouseListener(this);
        z1 = z2 = z3 = z4 = z5 = z6 = 0;
        //add(b1);
        //add(b2);
        add(b3);
        add(b4);
        //add(b5);
        add(b6);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(t5);
    }
    int tempx, tempy;
    int counter = 1;
    public void draw_ellipse(Graphics2D g) {
        rx = Integer.parseInt(t1.getText());
        ry = Integer.parseInt(t2.getText());
        X = Integer.parseInt(t3.getText());
        Y = Integer.parseInt(t4.getText());
        angle = Double.parseDouble(t5.getText());
        drawBresenhaum(g, X - rx, Y, X + rx, Y);
        //if(counter == 1)
        try{Thread.sleep(400);}catch(Exception e){};
        drawBresenhaum(g, X, Y - ry, X, Y + ry);
       // if(counter == 1)
        try{Thread.sleep(400);}catch(Exception e){};
        counter++;
        g.drawRect(X+rx, Y-3, 6, 6);
        g.setColor(Color.MAGENTA);//try{Thread.sleep(500);}catch(Exception e){};
        g.fillRect(X+rx, Y-3, 6, 6);
        g.setColor(Color.BLUE);
        
        int x1 = 0;
        double p = ry * ry - rx * rx * ry + 0.25 * ry * ry;
        int y1 = ry;
        while (2 * ry * ry * x1 < 2 * rx * rx * y1) {
            //if (i % 6 == 0) 
            {
                drawPoint(g, (-x1 + X), (y1 + Y));
                drawPoint(g, (-x1 + X), (-y1 + Y));
                drawPoint(g, (x1 + X), (y1 + Y));
                drawPoint(g, (x1 + X), (-y1 + Y));

            }
            x1++;
            if (p < 0) {
                p = p + 2 * ry * ry * x1 + ry * ry;
            } else {
                y1--;
                p = p + 2 * ry * ry * x1 - 2 * rx * rx * y1 + ry * ry;
            }
        }
        double p2 = ry * ry * (x1 + 1 / 2) * (x1 + 1 / 2) + rx * rx * (y1 - 1) * (y1 - 1) - ry * ry * rx * rx;
        while (y1 > 0) {
            drawPoint(g, X - x1, Y + y1);
            drawPoint(g, X - x1, Y - y1);
            drawPoint(g, X + x1, Y + y1);
            drawPoint(g, X + x1, Y - y1);
            y1--;
            if (p2 > 0) {
                p2 = p2 - (2 * rx * rx * y1) + (ry * ry);
            } else {
                x1++;
                p2 = p2 + 2 * ry * ry * x1 + 2 * rx * rx * y1 + ry * ry;
            }
        }
        tempx = rx;
        tempy = ry;
    }

    public void drawBresenhaum(Graphics2D g, int X0, int Y0, int X1, int Y1) {
        g.setColor(Color.BLUE);
        int dx = X1 - X0;
        int dy = Y1 - Y0;
        int p = 2 * dy - dx;
        int steps;
        if (dx > dy) {
            steps = dx;
        } else {
            steps = dy;
        }
        int x1, y1;
        x1 = X0;
        y1 = Y0;

        for (int k = 0; k <= steps; k++) {
            if (p < 0) {
                p = p + 2 * dy;
            } else {
                y1++;
                p = p + 2 * dy - 2 * dx;
            }
            if (dx < 0) {
                x1--;
            } else if (dx > 0) {
                x1++;
            }

            g.fillRect(x1 / diff * diff, ((y1 - offsety) / diff) * diff + offsety, diff, diff);
            //System.out.println(x1+" "+ y1);
            // g.fillRect((int)(x1/(double)diff)*diff,(int)((y1-offset)/(double)diff)*diff+offset,diff,diff);
        }
    }
    double from = 0;
    double to = 0;
     double a = angle;
 
    int c = 0;
    double x3 = rx;
    double y3 = 0;
    double x4, y4;
    
    int poc_iter = 0;
    public void drawCirclebypoints(Graphics2D g) 
    {
       // int poc_iter = (int) Math.ceil(360 / angle); // points on circle
       double o = angle;
       //o = -o;
       
        double alfa = Math.toRadians(o); // rotation angle  
        double x1 = rx;
        double y1 = 0;
        double x2, y2;

        for (int i = 0; i < poc_iter-1; i++) 
        {
            x2 = x1 * Math.cos(alfa) + y1 * Math.sin(alfa);
            y2 = -x1 * Math.sin(alfa) + y1 * Math.cos(alfa);
            System.out.println((X + x2) + " " + (Y + y2));
            // Draw line from [x1,y1] to [x2,y2]
            //x.add(X+x1);
            g.setColor(Color.red);
            g.drawLine((int)(X + x1),(int)(Y + y1), (int)(X + x2), (int)(Y + y2));
         //   g.drawLine((int)(X + x1),(int)(Y + y1), (int)(X + x2), (int)(Y + y2));
            
            x1 = x2;
            y1 = y2;
        }
    }
    public void drawCirclebypoints_anti(Graphics2D g) {
       // int poc_iter = (int) Math.ceil(360 / angle); // points on circle
       double o = angle;
       o = -o;
       
        double alfa = Math.toRadians(o); // rotation angle  
        double x1 = rx;
        double y1 = 0;
        double x2, y2;

        for (int i = 0; i < poc_iter-1; i++) 
        {
            x2 = x1 * Math.cos(alfa) + y1 * Math.sin(alfa);
            y2 = -x1 * Math.sin(alfa) + y1 * Math.cos(alfa);
            System.out.println((X + x2) + " " + (Y + y2));
            // Draw line from [x1,y1] to [x2,y2]
            g.setColor(Color.red);
            g.drawLine((int)(X + x1),(int)(Y + y1), (int)(X + x2), (int)(Y + y2));
         //   g.drawLine((int)(X + x1),(int)(Y + y1), (int)(X + x2), (int)(Y + y2));
            
            x1 = x2;
            y1 = y2;
        }
    }
    double p, q, r, s;
    public void clockwise(Graphics2D g) 
    {
       drawCirclebypoints(g);
    }

    public void anticlockwise(Graphics2D g) 
    {
        drawCirclebypoints_anti(g);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        for (int i = offsetx; i < resX - offsetx; i = i + diff) {
            g2.drawLine(i, offsety, i, resY - offsety);
        }

        for (int i = offsety; i < resY - offsety; i += diff) {
            g2.drawLine(offsetx, i, resX - offsetx, i);
        }

        g2.drawLine(resX - offsetx, resY - offsety, resX - offsetx, offsety);
        g2.drawLine(resX - 1 - offsetx, resY - offsety, offsetx, resY - offsety);
        resX = getWidth();
        resY = getHeight();

        if (z1 == 1) {
            drawBresenhaum(g2, X - rx, Y, X + rx, Y);
        }
        if (z2 == 1) {
            drawBresenhaum(g2, X, Y - ry, X, Y + ry);
        }

        while (z3 == 1) {
            z3 = 0;
            g2.rotate(Math.toRadians(a), 300, 300);
            draw_ellipse(g2);
            a += angle;
            poc_iter++;
            clockwise(g2);
        }
        while (z4 == 1){
            z4 = 0;
            g2.rotate(Math.toRadians(a), 300, 300);
            a -= angle;
            draw_ellipse(g2);
            poc_iter++;
            anticlockwise(g2);
        }
        /*
        if (z5 == 1) {
            for (double o = 0; o < 180; o += angle) {
                g2.setColor(Color.BLUE);
                g2.rotate(Math.toRadians(o), 300, 300);
                draw_ellipse(g2);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
            }
            drawCirclebypoints(g2);
        }*/
        //catch(Exception e){};
        //g.drawOval(resX/2-circle_radius/2, resY/2-circle_radius/2, circle_radius , circle_radius);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
/*        if (ae.getSource() == b1) {
            //diff++;
            z1 = 1;
            repaint();
        } else if (ae.getSource() == b2) {
            //diff--;
            z2 = 1;
            repaint();
        }*/
        if (ae.getSource() == b3) {
            //diff--;
            z3 = 1;
            z4 = 0;
            repaint();
        } 
        else if (ae.getSource() == b4) {
            //diff--;
            z4 = 1;
            z3 = 0;
            repaint();
        } 
        else if (ae.getSource() == b6) {
            x.clear();
            y.clear();
            diff = 2;
            z1 = z2 = z3 = z4 = z5 = 0;
            poc_iter = 0;
            repaint();
        } /*else if (ae.getSource() == b5) {
            z5 = 1;
            z3 = 0;
            z4 = 0;
           
            repaint();
        }*/
    }

    void drawPoint(Graphics2D g, int xpos, int ypos) {
        //g.setColor(Color.black);
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
