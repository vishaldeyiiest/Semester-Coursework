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
public class shoe 
{
    private int p[][] = new int[6][2];
    private int x[] = new int[6];
    private int y[] = new int[6];
    private Graphics g1;
    public shoe(Graphics g, int q[][])
    {
        g1 = g;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 2; j++)
                p[i][j] = q[i][j];
    }
    
    public void setVisible()
    {
        for(int i = 0; i < 6; i++)
        {
            x[i] = p[i][0];
            y[i] = p[i][1];
        }
        g1.setColor(Color.BLUE);
        g1.fillPolygon(x, y, 6);
    }
    public void rotate(int x1, int y1, int angle)
    {
        g1.setColor(Color.BLUE);
        int q[][] = new int[6][2];
        g1.drawRect(x1, y1, 5, 5);
        g1.fillRect(x1, y1, 5, 5);
        for(int i = 0; i < 6; i++)
        {
            int a = p[i][0]-x1, b = p[i][1]-y1;
            double r = Math.toRadians(angle);
            x[i] = q[i][0] = (int) (a * Math.cos(r) + b * Math.sin(r)) + x1;    //x = x
            y[i] = q[i][1] = (int) (-a * Math.sin(r) + b * Math.cos(r)) + y1;
        }
        g1.fillPolygon(x, y, 6);
    }
}
