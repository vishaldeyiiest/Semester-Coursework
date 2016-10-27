/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 *
 * @author ALOKE DIP
 */
public class NewClass extends Applet implements ActionListener{
   private final int major_x=400;
   private final int major_y=300;
   private final int major_length=400;
   private final int minor_length=100;
   private final int center_x=major_x+(major_length)/2;
   private final int center_y=major_y;
   private final int diff=2;
   ArrayList<Integer> x=new ArrayList<>();
   ArrayList<Integer> y=new ArrayList<>();
   ArrayList<Integer> f_x=new ArrayList<>();
   ArrayList<Integer> f_y=new ArrayList<>();
   private int  angle=0;
   private int precision_angle=5;
   Button b1;
   Button b2;
   private  int flag=0; 
   @Override
   public void init(){
       b1=new Button("anti clock wise");
       b2=new Button("clock wise");
       b1.addActionListener(this);
       b2.addActionListener(this);
       add(b1);
       add(b2);
   }
   @Override  
   public void paint(Graphics g){
       //g.drawLine(major_x,major_y,major_x+major_length,major_y); //major_axis
       //g.drawLine(center_x,center_y-minor_length,center_x,center_y+minor_length); //minor axis
       draw_circle(g,center_x,center_y,major_length/2);
       g.setColor(Color.GREEN);
       g.fillRect(center_x,center_y,8,8);
       g.setColor(Color.BLUE);
       draw_ellipse(g,center_x,center_y,major_length/2,minor_length);
       if(flag==0)
           translate(x,y,angle);
       else if(flag==1)
           translate(x,y,-angle); // rotate each points of the ellipse.
       shot(g,f_x,f_y); //draw the rotated ellipse
       angle=(angle+precision_angle)%360;
       x.clear();
       y.clear();
       f_x.clear();
       f_y.clear();
       try {
           Thread.sleep(100);
       } catch (Exception e) {
           //Logger.getLogger(Graphics_test.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("I cannot sleep please give me some work :(( ");
       }
      // repaint();
   }

    private void draw_ellipse(Graphics g, Integer x_c, Integer y_c, int r_x, int r_y) {
        int rx_sq=r_x*r_x;
        int ry_sq=r_y*r_y;
        int X=0;
        int Y=r_y;
        int p_x=0;
        int p_y=2*rx_sq*Y;
        draw(g,x_c,y_c,X,Y);
        double p=ry_sq-(rx_sq*r_y)+(0.25*rx_sq);
        while(p_x<p_y){
            X++;
            p_x+=2*ry_sq;
            if(p<0){
                p+=ry_sq+p_x;
            }
            else{
                Y--;
                p_y-=2*rx_sq;
                p+=ry_sq+p_x-p_y;
            }
            draw(g,x_c,y_c,X,Y);
        }
        p=ry_sq*(X+0.5)*(X+0.5)+rx_sq*(Y-1)*(Y-1)-rx_sq*ry_sq;
        while(Y>0){
            Y--;
            p_y-=2*rx_sq;
            if(p>0){
                p+=rx_sq-p_y;
            }
            else{
                X++;
                p_x+=2*ry_sq;
                p+=rx_sq-(p_y-p_x);
            }
            draw(g,x_c,y_c,X,Y);
        }
    }

    private void draw(Graphics g,Integer x_c, Integer y_c, int X, int Y) {
        int x_mod,y_mod;
        x_mod=x_c+X;
        y_mod=y_c+Y;
        x.add(x_mod);
        y.add(y_mod);
        x_mod=x_c-X;
        y_mod=y_c+Y;
        x.add(x_mod);
        y.add(y_mod);
        x_mod=x_c+X;
        y_mod=y_c-Y;
        x.add(x_mod);
        y.add(y_mod);
        x_mod=x_c-X;
        y_mod=y_c-Y;
        x.add(x_mod);
        y.add(y_mod);
    }

    private void translate(ArrayList<Integer> x, ArrayList<Integer> y,int ang) {
        int X,Y;
        for(int i=0;i<x.size();i++){
            // transform the origin to center then rotate about it and again place the origin to its initial position//////////
            X=(int)(center_x+(x.get(i)-center_x)*(Math.cos(Math.toRadians(ang)))+(y.get(i)-center_y)*(Math.sin(Math.toRadians(ang))));
            Y=(int)(center_y+(y.get(i)-center_y)*(Math.cos(Math.toRadians(ang)))-(x.get(i)-center_x)*(Math.sin(Math.toRadians(ang))));
            f_x.add(X);
            f_y.add(Y);
        }   
        
    }

    private void shot(Graphics g,ArrayList<Integer> f_x, ArrayList<Integer> f_y) {
        for(int i=0;i<f_x.size();i++){
            g.fillRect(f_x.get(i),f_y.get(i),diff,diff);
        }
    }

    private void draw_circle(Graphics g, int x0, int y0,int r) {
        int x = 0;
        int y = r;
        int d = 1 - r;
        int x_mod, y_mod;
        g.setColor(Color.red);
        while (x <= y) {
            x_mod = x0 + x;
            y_mod = y0 + y;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 + x;
            y_mod = y0 - y;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 - x;
            y_mod = y0 + y;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 - x;
            y_mod = y0 - y;
            g.fillRect(x_mod, y_mod, diff, diff);
            //////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////
            x_mod = x0 + y;
            y_mod = y0 + x;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 + y;
            y_mod = y0 - x;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 - y;
            y_mod = y0 + x;
            g.fillRect(x_mod, y_mod, diff, diff);
            x_mod = x0 - y;
            y_mod = y0 - x;
            g.fillRect(x_mod, y_mod, diff, diff);
            if (d > 0) {
                d += 2 * x + 3 - (2 * y - 2);
                y = y - 1;

            } else {
                d += 2 * x + 3;
            }

            x++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            flag=0;
            repaint();
        }
        if(e.getSource()==b2){
            flag=1;
            repaint();
        }
    }
}