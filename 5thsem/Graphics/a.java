/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ALOKE DIP
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.System.exit;
import java.util.ArrayList;
/**
 *
 * @author USER PC
 */
public class a extends Applet implements ActionListener,MouseListener,MouseMotionListener{
    
    private int diff=2;
    public static int resX=800;
    public static int resY=600;
    public static int offset=35;
    public  int r=400;
    Button btn1;
    Button btn2;
    Button clear;
    Button btn4;
    private int click=0;
  //  private int circle_radius=100;
    ArrayList<Integer> x=new ArrayList<>();
    ArrayList<Integer> y=new ArrayList<>();
    ArrayList<Integer> z_x=new ArrayList<>();
    ArrayList<Integer> z_y=new ArrayList<>();
    @Override
    public void init()
    {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        btn1=new Button();
        btn1.setLabel("Zoom In");
        clear=new Button();
        clear.setLabel("Clear");
        btn2=new Button();
        btn2.setLabel("Zoom out");
        btn4=new Button("reset");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn4.addActionListener(this);
        this.addMouseListener(this);
        clear.addActionListener(this);
        add(btn1);
        add(btn2);
        add(clear);
        add(btn4);
    }
    
    @Override
    public void paint(Graphics g)
    {
        resX=getWidth();
        resY=getHeight();
        if(click==1){
            //drawPoint(g,x.get(0),y.get(0),x.get(1),x.get(1));
           
            
            drawCircle(g,x.get(0),y.get(0),r);
            drawCircle_(g,x.get(0)+1,y.get(0)+1,(r/2));
            for(int i=0;i<z_x.size();i+=15){
                drawCircle(g,z_x.get(i),z_y.get(i),15);
            }
         /* System.out.println("DDA has : ");
            System.out.println("x  :  "+x.get(0)+"  y  :  "+y.get(0));
            System.out.println("x  :  "+x.get(1)+"  y  :  "+y.get(1));
            System.out.println("*****************************************************");
            System.out.println("");
            System.out.println("Bresenhum has : ");
            System.out.println("x  :  "+x.get(2)+"  y  :  "+y.get(2));
            System.out.println("x  :  "+x.get(3)+"  y  :  "+y.get(3));
            System.out.println("*****************************************************");*/
        }
        g.setColor(Color.BLACK);
        for(int i=0;i<resX;i=i+diff)
            g.drawLine(i, offset, i, resY);
        
        for(int i=offset;i<resY;i+=diff)
            g.drawLine(0, i, resX, i);
        
        g.drawLine(resX-1,resY-1,resX-1,offset);
        g.drawLine(resX-1, resY-1, 0, resY-1);
        
        g.setColor(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btn1)
        {
            diff++;
            //r+=10;
            z_x.clear();
            z_y.clear();
            repaint();
        }
        else if(ae.getSource()==btn2)
        {
            diff-=0.5;
            //r-=2;
            if(diff==0)
                exit(0);
            repaint();
        }
        else if(ae.getSource()==clear)
        {
            x.clear();
            y.clear();
            z_x.clear();
            z_y.clear();
            click=0;
            repaint();
        }else if(ae.getSource()==btn4)
        {
            diff=2;
            r=100;
            repaint();
        }
        
    }
    
    void drawPoint(Graphics g,int x0,int y0,int x1,int y1)
    {
        //DDA ALGORITHM*****************************************/////////////////////
        g.setColor(Color.BLUE);
        int dx,dy;
        int temp_x,temp_y,xmod,ymod,f_x,f_y,x_mod,y_mod;
        temp_x=temp_y=xmod=ymod=0;
        float x,y;
        x=(float)x0;
        y=(float)y0;
        dx=x1-x0;
        dy=y1-y0;
        int steps;
        if(dx>dy)
           steps=Math.abs(dx);
        else
           steps=Math.abs(dy);
        float x_inc,y_inc;
        x_inc=(float)dx/steps;
        y_inc=(float)dy/steps;
        for(int v=0;v<steps;v++){
           x+=x_inc;
           y+=y_inc;
           f_x=(int)Math.ceil(x);
           f_y=(int) Math.ceil(y);
           x_mod=(f_x/diff)*diff;
           y_mod=((f_y-offset)/diff)*diff+offset;
           try{
               if(Math.abs(y0-y1)>10){
                   
                    g.fillRect(x_mod, y_mod, diff, diff);
                    ymod=y_mod;
                   
               }
               else{
                   g.fillRect(x_mod, y_mod, diff, diff);               
               }
            }
           catch(Exception e){
               System.out.println("ERROR : "+e);
               exit(0);
           }
        }
        
  /////////Bresenham’s  ALGORITHM////////////////////////////////////////////
  
        g.setColor(Color.GREEN);
        int p0,p0_mod;
        int dx_=dx;
        int dy_=dy;
        p0=2*dy_-dx_;
        int x_,y_;
        x_=x0+10;
        y_=y0+20;
        int x_mod1,y_mod1;
        for(int i=0;i<dx_;i++){
            if(p0<0){
                x_mod1=((x_+1)/diff)*diff;
                y_mod1=(((y_)-offset)/diff)*diff+offset;
                g.fillRect(x_mod1, y_mod1, diff, diff);
                p0+=2*dy_;
                x_=x_+1;
            }
            else{
                x_mod1=((x_+1)/diff)*diff;
                y_mod1=(((y_+1)-offset)/diff)*diff+offset;
                g.fillRect(x_mod1, y_mod1, diff, diff);
                p0+=2*(dy_-dx_);
                x_=x_+1;
                y_=y_+1;
            }
        }
        
       //////////////////// MID POINT LINE DRAWING ALGORITHM////////////////////////////////
       
        int  dx1=dx;
        int dy1=dy;
        if(dx1==0)
               dx1=1;
        if(dy1==0)
            dy1=1;
        double slope=(double)dy1/dx1;
        int b=(int)(y1-slope*x1);
        System.out.println("equation : "+"y = "+slope+"x+("+b+")");
        g.setColor(Color.MAGENTA);
        double p1,p0_mod1;
        int x5,y5;
        x5=x0;
        y5=y0;
        p1=F(x5+1,y5+0.5,slope,b);
        int x_mod2,y_mod2;
        for(int i=0;i<dx1;i++){
            if(p1<0){
                x_mod2=((x5+1)/diff)*diff;
                y_mod2=(((y5)-offset)/diff)*diff+offset;
                g.fillRect(x_mod2, y_mod2, diff, diff);
                x5=x5+1;
                p1=F(x5+1,y5+0.5,slope,b);
            }
            else{
                x_mod2=((x5+1)/diff)*diff;
                y_mod2=(((y5+1)-offset)/diff)*diff+offset;
                g.fillRect(x_mod2, y_mod2, diff, diff);
                x5=x5+1;
                y5=y5+1;
                p1=F(x5+1,y5+0.5,slope,b);
            }
        }
    }
    void drawCircle(Graphics g,int x0,int y0,int r){
        
        if(r<10){
             g.setColor(Color.GREEN);
        }
        else
            g.setColor(Color.blue);
        int x=0;
        int y=r;
        int d=1-r;
        /*int ddx=12;
        int ddy=20-(r<<3);*/
        int x_mod,y_mod;
        while(x<=y){
            x_mod=x0+x;
            y_mod=y0+y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0+x;
            y_mod=y0-y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0-x;
            y_mod=y0+y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0-x;
            y_mod=y0-y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            //////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////
            x_mod=x0+y;
            y_mod=y0+x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0+y;
            y_mod=y0-x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0-y;
            y_mod=y0+x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod=x0-y;
            y_mod=y0-x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            g.fillRect(x_mod, y_mod, diff, diff);
            if(d>0){
                d+=2*x+3-(2*y-2);
                y=y-1;
                
            }
            else{
                d+=2*x+3;
            }
            
            x++;
        }
    }
    void drawCircle_(Graphics g,int x0,int y0,int r){
        int x=0;
        int y=r;
        int d=1-r;
        /*int ddx=12;
        int ddy=20-(r<<3);*/
        int x_mod,y_mod;
        while(x<=y){
            x_mod=x0+x;
            y_mod=y0+y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
          //  g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0+x;
            y_mod=y0-y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            //g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0-x;
            y_mod=y0+y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
           // g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0-x;
            y_mod=y0-y;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            //g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            //////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////
            x_mod=x0+y;
            y_mod=y0+x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            //g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0+y;
            y_mod=y0-x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
           // g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0-y;
            y_mod=y0+x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
           // g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            x_mod=x0-y;
            y_mod=y0-x;
            x_mod=(x_mod/diff)*diff;
            y_mod=((y_mod-offset)/diff)*diff+offset;
            //g.fillRect(x_mod, y_mod, diff, diff);
            z_x.add(x_mod);
            z_y.add(y_mod);
            if(d>0){
                d+=2*x+3-(2*y-2);
                y=y-1;
                
            }
            else{
                d+=2*x+3;
            }
            
            x++;
        }
        /*for(int i=0;i<z_x.size();i++)
            System.out.println(z_x.size());*/
        /*for(int i=0;i<z_x.size();i+=15){
            drawCircle(g,z_x.get(i),z_y.get(i),5);
        }*/
        
    }
    @Override
    public void mouseClicked(MouseEvent me) {
     //   System.out.println("Clicked");
        x.add(me.getX());
        y.add(me.getY());
        click++;
        if(click==1)
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

    private double F(int x_, double y_, double slope, int b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double val;
        val= (slope*x_+b-y_);
        return val;
    }

    private int G(int x0, int y0, int r) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int res;
        res=x0*x0+y0*y0+r*r;
        return res;
    }
}
    


