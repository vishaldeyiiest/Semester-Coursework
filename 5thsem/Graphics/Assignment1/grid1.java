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

public class grid1 extends Applet implements ActionListener,MouseListener,MouseMotionListener{
    
    private int diff=5;
    public static int resX=800;
    public static int resY=600;
    public static int offset=35;
    Button btn1;
    Button btn2;
    //Button clear;
    Button btn4;
    private int click=0;
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
    ArrayList<Integer> x=new ArrayList<>();
    ArrayList<Integer> y=new ArrayList<>();
    
    @Override
    public void init()
    {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        btn1=new Button();
        btn1.setLabel("Zoom In");
        /*clear=new Button();
        clear.setLabel("Clear");
        */btn2=new Button();
        btn2.setLabel("Zoom out");
        btn4=new Button("reset");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn4.addActionListener(this);
        this.addMouseListener(this);
        //clear.addActionListener(this);
        add(btn1);
        add(btn2);
        //add(clear);
        add(btn4);
        /*add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(b);
        b.addActionListener(this);*/
    }
    
    @Override
    public void paint(Graphics g)
    {
        resX=getWidth();
        resY=getHeight();
        /*int X0 = Integer.parseInt(t1.getText());
        int X1 = Integer.parseInt(t2.getText());
        int Y0 = Integer.parseInt(t3.getText());
        int Y1 = Integer.parseInt(t4.getText());
        if(col == 1)
            drawPoint(g,X0,X1,Y0,Y1);
       */ if(click==2){
            drawPoint(g,x.get(0),y.get(0),x.get(1),y.get(1));
        }
        g.setColor(Color.BLACK);
        //setBackground(Color.GRAY);
        //g.fillRect(87, 87, diff, diff);
        for(int i=0;i<resX;i=i+diff)
            g.drawLine(i, offset, i, resY);
        
        for(int i=offset;i<resY;i+=diff)
            g.drawLine(0, i, resX, i);
        
        g.drawLine(resX-1,resY-1,resX-1,offset);
        g.drawLine(resX-1, resY-1, 0, resY-1);
        
        g.setColor(Color.BLACK);
        //g.drawOval(resX/2-circle_radius/2, resY/2-circle_radius/2, circle_radius , circle_radius);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
        if(ae.getSource()==btn1)
        {
            diff++;
           // circle_radius+=14;
            repaint();
        }
        else if(ae.getSource()==btn2)
        {
           // circle_radius-=14;
            diff--;
            repaint();
        }
        else if(ae.getSource()==btn4)
        {
            /*x.clear();
            y.clear();*/
            diff=5;
            repaint();
        }
        /*else if(ae.getSource() == b)
        {
            col = 1;
            repaint();
        }*/
    }
    
    void drawPoint(Graphics g,int x0,int y0,int x1,int y1)
    {
        g.setColor(Color.BLUE);
        int dx, dy;
        int xmod, ymod, x_mod, y_mod;
        xmod = ymod = 0;
        float x,y;
        x = x0;
        y = y0;
        dx = x1-x0;
        dy = y1-y0;
        int steps;
        if(dx > dy)
           steps = Math.abs(dx);
        else
           steps = Math.abs(dy);
        float x_inc, y_inc;
        x_inc = (float)dx/steps;
        y_inc = (float)dy/steps;
        for(int v = 0; v < steps; v++){
           x += x_inc;
           y += y_inc;
           
           x_mod=((int)Math.ceil(x)/diff)*diff;
           y_mod=(((int) Math.ceil(y)-offset)/diff)*diff+offset;
           /*if(x_mod == xmod)
               x_mod ++;
           if(x_mod != xmod)
               xmod = x_mod;
           if(y_mod == ymod)
               y_mod ++;
           if(y_mod != ymod)
               ymod = y_mod;
            */try{
                g.fillRect(x_mod, y_mod, diff, diff);
            }
           catch(Exception e){
               System.out.println("ERROR : "+e);
               exit(0);
           }
        }
        //System.out.println("points: "+steps);
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
        if(click==2)
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
    


