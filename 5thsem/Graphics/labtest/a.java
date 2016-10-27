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
class newshape
{
    private int r;
    Graphics g1;
    newshape(Graphics g, int ra){r = ra;g1=g;}
    public void setVisible(){g1.drawOval(r, r, 50, 50);}
    public void setInvisible(){};
    
}
public class a extends Applet
{
    private int diff = 2;
    public static int resX = 800;
    public static int resY = 600;
    public static int offsetx = 0;
    public static int offsety = 40;
     @Override
    public void init()
    {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
    } 
    @Override
    public void paint(Graphics g)
    {
      
       try{
            
            { 
                Thread.sleep(10);
                newshape n = new newshape(g,300);
                n.setVisible();
                //i += 50;
                repaint();
            }
        }
        catch(Exception e){};
    }
    
}
