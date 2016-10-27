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
public class lbody 
{
    private int p[][] = new int[8][2];
    private int x[] = new int[8];
    private int y[] = new int[8];
    Graphics g1;
    public lbody(Graphics g, int q[][])
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 2; j++)
                p[i][j] = q[i][j];
        g1 = g;
    }
    public void setVisible()
    {
        for(int i = 0; i < 8; i++)
        {
            x[i] = p[i][0];
            y[i] = p[i][1];
        }
        g1.setColor(Color.RED);
        g1.fillPolygon(x, y, 8);        
    }
}
