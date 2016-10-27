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

public class pointtransform extends Applet implements ActionListener, MouseListener, MouseMotionListener {

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
    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        /*b1 = new Button();
        b1.setLabel("Zoom In");
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
        add(btn4);*/
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
        add(l3);  add(t3);
        add(l4);  add(t4);
        add(l5);  add(t5);
        add(l6);  add(t6);
        add(l7);  add(t7);
        add(l1); add(t1);
        add(l2); add(t2);
    }
    Graphics g1;
    int x0 = 300; int y0 = 300;
    @Override
    public void paint(Graphics g) {
        resX = getWidth();
        resY = getHeight();
        //int x0 = Integer.parseInt(t1.getText());
        //int y0 = Integer.parseInt(t2.getText());
        g1 = g;
        g.fillRect(X, Y, 10, 10);
        g.setColor(Color.red);
        g.fillRect(x0, y0, 10, 10);
        //draw();
        if(t == 1)
            transform();
        if(s == 1)
            scale();
        if(r == 1)
            rotate();
        if(r1 == 1)
            rotate1();
            
    }
    
    void draw(int t0, int t1)
    {
        g1.drawRect(t0, t1, 10, 10);
        g1.fillRect(t0, t1, 10, 10);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == b4)
        {
           t = s = r = r1 = 0;
           repaint();
        }
        if(ae.getSource() == b1)
        {
            t = 1;
            repaint();
        }
        else if(ae.getSource() == b2)
        {
            s = 1;
            repaint();
        }
        else if(ae.getSource() == b3)
        {
            r = 1;
            repaint();
        }
        else if(ae.getSource() == b5)
        {
            r1 = 1;
            repaint();
        }
    }

    public void transform()
    {
        int tx = Integer.parseInt(t3.getText());
        int ty = Integer.parseInt(t4.getText());
        int t0, t1;
        t0 = x0 + tx;
        t1 = y0 + ty;
        g1.setColor(Color.BLUE);
        draw(t0, t1);
    }        
    
    public void scale()
    {
        double sx = Double.parseDouble(t5.getText());
        double sy = Double.parseDouble(t6.getText());
        int t0, t1;
        t0 = x0;
        t1 = y0;
        t0 = (int)(sx*t0);
        t1 = (int)(sy*t1);
        g1.setColor(Color.MAGENTA);
        draw(t0, t1);
    } 

    public void rotate()
    {
        int angle = Integer.parseInt(t7.getText());
        int mx = x0 - X;
        int my = y0 - Y;
        //int angle = (int) Math.atan2(my, mx);
        double r1 = Math.toRadians(angle);
        int t0, t1;
        t0 =(int)(mx*Math.cos(r1)+my*Math.sin(r1))+X;
        t1 = (int)(-mx*Math.sin(r1)+my*Math.cos(r1))+Y;
        g1.setColor(Color.orange);
        g1.drawLine(x0, y0, t0, t1);
        draw(t0, t1);
    }
    
    public void rotate1()
    {
        int cx = Integer.parseInt(t1.getText());
        int cy = Integer.parseInt(t2.getText());
        int angle = Integer.parseInt(t7.getText());
        int mx = x0 - cx;
        int my = y0 - cy;
        double r1 = Math.toRadians(angle);
        int t0, t1;
        t0 =(int)(mx*Math.cos(r1)+my*Math.sin(r1))+cx;
        t1 = (int)(-mx*Math.sin(r1)+my*Math.cos(r1))+cy;
        g1.setColor(Color.CYAN);
        g1.drawLine(x0, y0, t0, t1);
        draw(t0, t1);
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
