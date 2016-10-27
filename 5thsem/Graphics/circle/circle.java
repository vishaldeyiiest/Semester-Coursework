package circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author vishal
 */
public class circle extends Applet implements ActionListener, MouseListener {

    private int diff = 5;
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
    Label l1 = new Label("x1 = ");
    Label l2 = new Label("x2 = ");
    Label l3 = new Label("Radius = ");
    //Label l4 = new Label("y2 = ");

    TextField t1 = new TextField(3);
    TextField t2 = new TextField(3);
    TextField t3 = new TextField(3);
    //TextField t4 = new TextField(3);
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
        add(b1);
        add(b2);
        add(b3);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);

        add(b);
        b.addActionListener(this);

    }
    int z1 = 0, z2 = 0, temp;
    public void draw_circle(Graphics g) 
    {
        int X = Integer.parseInt(t1.getText());
        int Y = Integer.parseInt(t2.getText());
        int r = Integer.parseInt(t3.getText());
        if(z1 == 1)
            r = temp + 10;
        if(z2 == 1)
            r = temp - 10;
       
        int x1 = 0;
        int p = 5 / 4 - r, y1 = r;
        g.setColor(Color.BLUE);
        //int i = 0;
        while (x1 < y1) 
        {
            //if (i % 6 == 0) 
            {
                drawPoint(g, y1 + X, x1 + Y);
                drawPoint(g, x1 + X, y1 + Y);
                drawPoint(g, x1 + X, -y1 + Y);
                drawPoint(g, y1 + X, -x1 + Y);
                drawPoint(g, -y1 + X, -x1 + Y);
                drawPoint(g, -x1 + X, -y1 + Y);
                drawPoint(g, -x1 + X, y1 + Y);
                drawPoint(g, -y1 + X, x1 + Y);
                
            }
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

    @Override
    public void paint(Graphics g) {
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
            draw_circle(g);
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
            diff = 5;
            col = 0;
            repaint();
        } 
        else if (ae.getSource() == b)
        {
            col = 1;
            repaint();
        }

    }

    void drawPoint(Graphics g, int xpos, int ypos) {
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
