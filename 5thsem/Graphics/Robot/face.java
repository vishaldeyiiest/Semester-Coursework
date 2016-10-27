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
public class face 
{
    private int p[][] = new int[13][2];
    private int t[][] = new int[13][2];
    private int x[] = new int[13];
    private int y[] = new int[13];       
    Graphics g1;
    public face(Graphics g, int q[][])
    {
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 2; j++)
                p[i][j] = t[i][j] = q[i][j];
        g1 = g;
    }
    public void setVisible()
    {
        for(int i = 0; i < 13; i++)
        {
            x[i] = p[i][0];
            y[i] = p[i][1];
        }
        g1.setColor(Color.orange);
        g1.drawPolygon(x, y, 4);
        /*for(int i = 0; i < 3; i++)
             g1.drawLine(p[i][0], p[i][1], p[i+1][0], p[i+1][1]);
        g1.drawLine(p[0][0], p[0][1], p[3][0], p[3][1]);
        */g1.setColor(Color.BLACK);
        g1.drawOval(p[4][0], p[4][1], 10, 10);
        g1.drawOval(p[5][0], p[5][1], 10, 10);
        int a[] = new int[4];
        int b[] = new int[4];
        for(int i = 6; i < 10; i++)
        {
            a[i-6] = p[i][0];
            b[i-6] = p[i][1];
        }
        g1.setColor(Color.red);
        g1.drawPolygon(a, b, 4);
        for(int i = 10; i < 13; i++)
        {
            a[i-10] = p[i][0];
            b[i-10] = p[i][1];
        }
        g1.setColor(Color.orange);
        g1.fillPolygon(a, b, 3);
    }
    
    public void rotate(int x1, int y1, int angle)
    {
        int q[][] = new int[13][2];
        g1.drawRect(x1, y1, 2, 2);
        g1.fillRect(x1, y1, 2, 2);
        for(int i = 0; i < 13; i++)
        {
            int a = t[i][0]-x1, b = t[i][1]-y1;
            double r = Math.toRadians(angle);
            x[i] = p[i][0] = q[i][0] = (int) (a * Math.cos(r) + b * Math.sin(r)) + x1;    //x = x
            y[i] = p[i][1] = q[i][1] = (int) (-a * Math.sin(r) + b * Math.cos(r)) + y1;
            //System.out.println(q[i][0]+" "+q[i][1]);
        }
        int a[] = new int[4];
        int b[] = new int[4];
        for(int i = 0; i < 4; i++)
        {
            a[i] = q[i][0];
            b[i] = q[i][1];
        }
        g1.setColor(Color.orange);
        g1.drawPolygon(a, b, 4);
        g1.drawOval(q[4][0], q[4][1], 10, 10);
        g1.drawOval(q[5][0], q[5][1], 10, 10);
        
        for(int i = 6; i < 10; i++)
        {
            a[i-6] = q[i][0];
            b[i-6] = q[i][1];
        }
         g1.setColor(Color.red);
        g1.drawPolygon(a, b, 4);
        //int a[] = new int[3];
        //int b[] = new int[3];
        for(int i = 10; i < 13; i++)
        {
            a[i-10] = q[i][0];
            b[i-10] = q[i][1];
        }
         g1.setColor(Color.orange);
        g1.fillPolygon(a, b, 3);
        
    }
}
