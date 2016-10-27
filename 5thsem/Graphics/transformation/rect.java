package transformation;

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

class Rectangle {

    private int x, y, l, w;
    Graphics g1;
    private int p[][] = new int[4][2];
    public Rectangle(Graphics g, int a, int b, int le, int wi) {
        x = a;
        y = b;
        l = le;
        w = wi;
        g1 = g;
        p[0][0] = x; p[0][1] = y;
        p[1][0] = x+w; p[1][1] = y;
        p[2][0] = x+w; p[2][1] = y+l;
        p[3][0] = x; p[3][1] = y+l;
    }

    public void setVisible() 
    {
        for(int i = 0; i < 3; i++)
            g1.drawLine(p[i][0], p[i][1], p[i+1][0], p[i+1][1]);
        g1.drawLine(p[0][0], p[0][1], p[3][0], p[3][1]);
    }
    
    public void translate(int x1, int y1)
    {
        g1.setColor(Color.BLUE);
        int q[][] = new int[4][2];
        for(int i = 0; i < 4; i++)
        {
            q[i][0] = p[i][0] + x1;
            q[i][1] = p[i][1] + y1;
        }
        for(int i = 0; i < 3; i++)
            g1.drawLine(q[i][0], q[i][1], q[i+1][0], q[i+1][1]);
        g1.drawLine(q[0][0], q[0][1], q[3][0], q[3][1]);

    }
    
    public void scale(double sx, double sy)
    {
        g1.setColor(Color.MAGENTA);
        int q[][] = new int[4][2];
        for(int i = 0; i < 4; i++)
        {
            q[i][0] = (int)(sx*p[i][0]);
            q[i][1] = (int)(sy*p[i][1]);
        }
        for(int i = 0; i < 3; i++)
            g1.drawLine(q[i][0], q[i][1], q[i+1][0], q[i+1][1]);
        g1.drawLine(q[0][0], q[0][1], q[3][0], q[3][1]);
    }
    
    public void rotate(int x1, int y1, int angle)
    {
        int q[][] = new int[4][2];
        g1.drawRect(x1, y1, 5, 5);
        g1.fillRect(x1, y1, 5, 5);
        for(int i = 0; i < 4; i++)
        {
            int a = p[i][0]-x1, b = p[i][1]-y1;
            double r = Math.toRadians(angle);
            q[i][0] = (int) (a * Math.cos(r) + b * Math.sin(r)) + x1;    //x = x
            q[i][1] = (int) (-a * Math.sin(r) + b * Math.cos(r)) + y1;
        }
        for(int i = 0; i < 3; i++)
            g1.drawLine(q[i][0], q[i][1], q[i+1][0], q[i+1][1]);
        g1.drawLine(q[0][0], q[0][1], q[3][0], q[3][1]);
    }
}

public class rect extends Applet implements ActionListener, MouseListener, MouseMotionListener {

    private int diff = 5;
    public static int resX = 800;
    public static int resY = 600;
    public static int offset = 35;
    private int click = 0;

    Label l1 = new Label("Arb x = ");
    Label l2 = new Label("Arb y = ");
    Label l3 = new Label("x = ");
    Label l4 = new Label("y = ");
    Label l5 = new Label("sx = ");
    Label l6 = new Label("sy = ");
    Label l7 = new Label("angle = ");
    int col = 0;
    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    TextField t4 = new TextField(3);
    TextField t5 = new TextField(3);
    TextField t6 = new TextField(3);
    TextField t7 = new TextField(3);
    Button b1 = new Button("Translate");
    Button b2 = new Button("Scale");
    Button b3 = new Button("Rotate-origin");
    Button b5 = new Button("Rotate-arbitrary");
    Button b4 = new Button("Reset");
    //  private int circle_radius=100;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    int t = 0, r = 0, s = 0, r1 = 0;
    int X = 400;
    int Y = 300;
    int l = 50;
    int w = 40;

    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        add(b1);
        add(b2);
        add(b3);
        add(b5);
        add(b4);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(t5);
        add(l6);
        add(t6);
        add(l7);
        add(t7);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
    }
    Graphics g1;
    int x0 = 300;
    int y0 = 300;
    Rectangle re;
    @Override
    public void paint(Graphics g) {
        resX = getWidth();
        resY = getHeight();
        g1 = g;
        re = new Rectangle(g, x0, y0, 20, 20);
        re.setVisible();        
        //g.fillRect(X, Y, 10, 10);
        
        int angle;
        if (t == 1)
        {
            int tx = Integer.parseInt(t3.getText());
            int ty = Integer.parseInt(t4.getText());
            re.translate(tx, ty);
        }
        if (s == 1)
        {
            double sx = Double.parseDouble(t5.getText());
            double sy = Double.parseDouble(t6.getText());
            re.scale(sx, sy);
        }
        if (r == 1)
        {
            angle = Integer.parseInt(t7.getText());
            g.setColor(Color.RED);
            g.drawString("Origin", X, Y);
            re.rotate(X, Y, angle);
        }
        if (r1 == 1) 
        {
            int cx = Integer.parseInt(t1.getText());
            int cy = Integer.parseInt(t2.getText());
            angle = Integer.parseInt(t7.getText());
            g.setColor(Color.MAGENTA);
            g.drawString("Arb point", cx, cy);
            re.rotate(cx, cy, angle);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b4) {
            t = s = r = r1 = 0;
            repaint();
        }
        if (ae.getSource() == b1) {
            t = 1;
            repaint();
        } else if (ae.getSource() == b2) {
            s = 1;
            repaint();
        } else if (ae.getSource() == b3) {
            r = 1;
            //re.rotate();
            repaint();
        } else if (ae.getSource() == b5) {
            r1 = 1;
            repaint();
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
