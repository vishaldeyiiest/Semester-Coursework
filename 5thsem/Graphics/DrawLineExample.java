import java.applet.Applet;
import java.awt.Graphics;
 
public class DrawLineExample extends Applet{
 
        public void init()
        {
		setSize(400, 300);
        }
        public void paint(Graphics g){
               
                /*
                 * to draw line in an applet window use,
                 * void drawLine(int x1,int y1, int x2, int y2)
                 * method.
                 *
                 * This method draws a line between (x1,y1) and (x2,y2)
                 * coordinates in a current color.
                 */
               
                //this will draw a line between (10,10) and (50,50) coordinates.
               int X0 = 10, X1 = 50, Y0 = 10, Y1 = 50;
               int dx = X1-X0;
               int dy = Y1-Y0;
               int steps;
               if(dx > dy)
                   steps = Math.abs(dx);
               else
                   steps = Math.abs(dy);
               int xinc = dx/steps;
               int yinc = dy/steps;
               int x = X0, y = Y0;
               for(int k = 0; k < steps; k++)
               {
                   g.drawOval(x, y, 1, 1);
                   x = x + xinc;
                   y = y + yinc;
               }
        }
}

