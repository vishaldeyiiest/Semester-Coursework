/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *
 * @author USER PC
 */
public class JavaApplication5 extends Applet implements ActionListener,MouseListener,MouseMotionListener{
    
    private int diff=10;
    public static int resX=800;
    public static int resY=600;
    public static int offset=35;
    Button obtn;
    Button ibtn;
    Button clear;
    private int circle_radius=100;
    ArrayList<Integer> x=new ArrayList<>();
    ArrayList<Integer> y=new ArrayList<>();
    
    @Override
    public void init()
    {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        ibtn=new Button();
        ibtn.setLabel("Zoom In");
        clear=new Button();
        clear.setLabel("Clear");
        obtn=new Button();
        obtn.setLabel("Zoom out");
        ibtn.addActionListener(this);
        obtn.addActionListener(this);
        this.addMouseListener(this);
        clear.addActionListener(this);
        add(ibtn);
        add(obtn);
        add(clear);
    }
    
    @Override
    public void paint(Graphics g)
    {
        resX=getWidth();
        resY=getHeight();
        
        for(int i=0;i<x.size();i++)
        {
            drawPoint(g,x.get(i),y.get(i));
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
        
        g.setColor(Color.RED);
        //g.drawOval(resX/2-circle_radius/2, resY/2-circle_radius/2, circle_radius , circle_radius);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Button Listener");
        if(ae.getSource()==ibtn)
        {
            diff++;
            circle_radius+=14;
            repaint();
        }
        else if(ae.getSource()==obtn)
        {
            circle_radius-=14;
            diff--;
            repaint();
        }
        else if(ae.getSource()==clear)
        {
            x.clear();
            y.clear();
            repaint();
        }
        
    }
    
    void drawPoint(Graphics g,int xpos,int ypos)
    {
        g.setColor(Color.RED);
        g.fillRect(((int)xpos/diff)*diff, ((int)(ypos-offset)/diff)*diff+offset, diff, diff);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //x.add(me.getX());
        //y.add(me.getY());
        //repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Clicked");
        x.add(me.getX());
        y.add(me.getY());
        repaint();
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
    

