/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author vishal
 */
public class Rectangle 
{
    
    private int x[] = new int[8];
    private int y[] = new int[8];
    Graphics g1;
    int p[][] = new int[4][2];
    public Rectangle(Graphics g, int q[][]) 
    {    
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 2; j++)
                p[i][j] = q[i][j];
        g1 = g;
    }

    public void setVisible() 
    {
        for(int i = 0; i < 4; i++)
        {
            x[i] = p[i][0];
            y[i] = p[i][1];
        }
        g1.fillPolygon(x, y, 4);
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
            x[i] = q[i][0] = (int) (a * Math.cos(r) + b * Math.sin(r)) + x1;    //x = x
            y[i] = q[i][1] = (int) (-a * Math.sin(r) + b * Math.cos(r)) + y1;
        }
        g1.fillPolygon(x, y, 4);
    }
}   
