package Assignment2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vishal
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.exit;
import java.util.ArrayList;

public class dda extends Applet implements ActionListener, MouseListener, MouseMotionListener {

    private int diff = 5;
    public static int resX = 800;
    public static int resY = 600;
    public static int offset = 35;
    Button btn1;
    Button btn2;
    Button b1;
    Button b2;
    Button b3;
    Button btn4;
    private int click = 0;
    /*
    Label l1 = new Label("x1 = ");
    Label l2 = new Label("x2 = ");
    Label l3 = new Label("y1 = ");
    Label l4 = new Label("y2 = ");
    int col = 0;
    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    TextField t4 = new TextField(3);
    Button b = new Button("DRAW");*/
    //  private int circle_radius=100;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    int flag = 0;

    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        btn1 = new Button();
        btn1.setLabel("Zoom In");
        btn2 = new Button();
        btn2.setLabel("Zoom out");
        btn4 = new Button("reset");
        b1 = new Button("dda");
        b2 = new Button("Bresenhaum");
        b3 = new Button("MidPoint");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn4.addActionListener(this);
        this.addMouseListener(this);
        add(btn1);
        add(btn2);
        add(btn4);
        add(b1);
        add(b2);
        add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }
    Graphics g1;

    @Override
    public void paint(Graphics g) {
        resX = getWidth();
        resY = getHeight();
        /*int X0 = Integer.parseInt(t1.getText());
        int X1 = Integer.parseInt(t2.getText());
        int Y0 = Integer.parseInt(t3.getText());
        int Y1 = Integer.parseInt(t4.getText());
        if(col == 1)
            drawPoint(g,X0,X1,Y0,Y1);
         */
        g.setColor(Color.BLACK);
        //setBackground(Color.GRAY);
        //g.fillRect(87, 87, diff, diff);
        for (int i = 0; i < resX; i = i + diff) {
            g.drawLine(i, offset, i, resY);
        }

        for (int i = offset; i < resY; i += diff) {
            g.drawLine(0, i, resX, i);
        }

        g.drawLine(resX - 1, resY - 1, resX - 1, offset);
        g.drawLine(resX - 1, resY - 1, 0, resY - 1);

        //if(click==2 || click == 4)
        if (flag == 1) {
            drawdda(g, x.get(0), y.get(0), x.get(1), y.get(1));
            //drawBresenhaum(g,x.get(0)+30,y.get(0),x.get(1)+30,y.get(1));
        }

        if (flag == 2) {
            drawdda(g, x.get(0), y.get(0), x.get(1), y.get(1));
            drawBresenhaum(g, x.get(0) + 70, y.get(0), x.get(1) + 70, y.get(1));
        }
        if (flag == 3) {
            drawdda(g, x.get(0), y.get(0), x.get(1), y.get(1));
            drawBresenhaum(g, x.get(0) + 70, y.get(0), x.get(1) + 70, y.get(1));
            draw_midpoint(g, x.get(0) + 150, y.get(0), x.get(1) + 150, y.get(1));
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
        if (ae.getSource() == btn1) {
            diff++;
            repaint();
        } else if (ae.getSource() == btn2) {
            diff--;
            repaint();
        } else if (ae.getSource() == btn4) {
            x.clear();
            y.clear();
            diff = 5;
            flag = 0;
            repaint();
        } else if (ae.getSource() == b1) {
            flag = 1;
            repaint();
        } else if (ae.getSource() == b2) {
            flag = 2;
            repaint();
        } else if (ae.getSource() == b3) {
            flag = 3;
            repaint();
        }
    }

    void drawdda(Graphics g, int X0, int Y0, int X1, int Y1) {
        //System.out.println(x1+" "+y1);
        g.setColor(Color.RED);
        int dx, dy;
        int x_mod, y_mod;
        double x1, y1;
        x1 = X0;
        y1 = Y0;
        dx = X1 - X0;
        dy = Y1 - Y0;
        int steps;
        if (dx > dy) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        float x_inc, y_inc;
        x_inc = (float) dx / steps;
        y_inc = (float) dy / steps;
        for (int v = 0; v <= steps; v++) {
            x1 += x_inc;
            y1 += y_inc;

            x_mod = ((int) Math.ceil(x1) / diff) * diff;
            y_mod = (((int) Math.ceil(y1) - offset) / diff) * diff + offset;

            try {
                g.fillRect(x_mod, y_mod, diff, diff);
            } catch (Exception e) {
                System.out.println("ERROR : " + e);
                exit(0);
            }
        }
        //System.out.println("points: "+steps);
    }

    public void drawBresenhaum(Graphics g, int X0, int Y0, int X1, int Y1) {
        // System.out.println(X1+" "+Y1);
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
            g.fillRect(x1 / diff * diff, ((y1 - offset) / diff) * diff + offset, diff, diff);
            //System.out.println(x1+" "+ y1);
            // g.fillRect((int)(x1/(double)diff)*diff,(int)((y1-offset)/(double)diff)*diff+offset,diff,diff);
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

    public void draw_midpoint(Graphics g, int X0, int Y0, int X1, int Y1) {
        g.setColor(Color.MAGENTA);
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

            g.fillRect(x1 / diff * diff, ((y1 - offset) / diff) * diff + offset, diff, diff);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //x.add(me.getX());
        //y.add(me.getY());
        //repaint();
        System.out.println("Clicked");
        x.add(me.getX());
        y.add(me.getY());
        click++;
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

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
