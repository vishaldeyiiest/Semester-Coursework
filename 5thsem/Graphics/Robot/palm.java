/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot;

import java.awt.Graphics;
/**
 *
 * @author vishal
 */
public class palm 
{
    private int p[][] = new int[7][2];
    private int x[] = new int[7];
    private int y[] = new int[7];
    Graphics g1;
    public palm(Graphics g, int q[][])
    {
        for(int i = 0; i < 7; i++)
            for(int j = 0; j < 2; j++)
                p[i][j] = q[i][j];
        g1 = g;
    }
    public void setVisible()
    {
        for(int i = 0; i < 7; i++)
        {
            x[i] = p[i][0];
            y[i] = p[i][1];
        }
        g1.fillPolygon(x, y, 7);
    }
    public void rotate(int x1, int y1, int angle)
    {
        int q[][] = new int[7][2];
        g1.drawRect(x1, y1, 5, 5);
        g1.fillRect(x1, y1, 5, 5);
        for(int i = 0; i < 7; i++)
        {
            int a = p[i][0]-x1, b = p[i][1]-y1;
            double r = Math.toRadians(angle);
            x[i] = q[i][0] = (int) (a * Math.cos(r) + b * Math.sin(r)) + x1;    //x = x
            y[i] = q[i][1] = (int) (-a * Math.sin(r) + b * Math.cos(r)) + y1;
        }
        g1.fillPolygon(x, y, 7);
        
    }
}
