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
public class dda_grid extends Applet implements ActionListener, MouseListener {

    private int diff = 10;
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
    Label l3 = new Label("y1 = ");
    Label l4 = new Label("y2 = ");

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

    public void draw_Line(Graphics g) {
        int X0 = Integer.parseInt(t1.getText());
        int X1 = Integer.parseInt(t2.getText());
        int Y0 = Integer.parseInt(t3.getText());
        int Y1 = Integer.parseInt(t4.getText());
        int dx = X1 - X0;
        int dy = Y1 - Y0;
        double steps;
        //int flag = 0;
        if (dx > dy) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        double xinc = dx / steps;
        double yinc = dy / steps;
        double x1 = X0, y1 = Y0;
        int xmod, ymod, x_mod, y_mod;
        xmod = ymod = 0;    
        for (int k = 0; k < steps; k++) {
           //g.fillRect(((int)x1/diff ) * diff + offsetx, ((int)(y1-offsety)/diff) * diff + offsety, diff, diff);
           //drawPoint(g,(int)Math.ceil(x1),(int)Math.ceil(y1));
           x1 = x1 + xinc;
            y1 = y1 + yinc;
           x_mod=((int)Math.ceil(x1)/diff)*diff;
           y_mod=((int)(Math.ceil(y1)-offsety)/diff)*diff+offsety;
           if(x_mod==xmod)
               x_mod++;
           if(x_mod!=xmod)
               xmod=x_mod;
           if(y_mod==ymod)
               y_mod++;
           if(y_mod!=ymod)
               ymod=y_mod;
            
            g.fillRect(x_mod, y_mod, diff, diff);
        }

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
        if(col == 1){
        g.setColor(Color.RED);
        
        draw_Line(g);
        //g.drawOval(resX/2-circle_radius/2, resY/2-circle_radius/2, circle_radius , circle_radius);
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
        if (ae.getSource() == b1) {
            diff++;
            repaint();
        } else if (ae.getSource() == b3) {
            diff--;
            repaint();
        } else if (ae.getSource() == b2) {
            x.clear();
            y.clear();
            diff = 10;
            col = 0;
            repaint();
        } else if (ae.getSource() == b) {
            col = 1;
            repaint();
        }

    }

    void drawPoint(Graphics g, int xpos, int ypos) {
        //g.setColor(Color.ORANGE);
        g.fillRect(((int) (xpos - offsetx) / diff) * diff + offsetx, ((int) (ypos-offsety) / diff) * diff + offsety, diff, diff);
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
