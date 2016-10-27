/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Robot;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Robot extends Applet implements ActionListener {

    public static int resX = 800;
    public static int resY = 600;
    int hx = 150, hy = 50;
    int f = 0, re = 0, hand = 0, leg = 0;
    Button b1 = new Button("Move");
    Button b2 = new Button("Move Head");
    Button b3 = new Button("Move hand");
    Button b5 = new Button("Move leg");
    Button b4 = new Button("Stop");
  //  Button b6 = new Button("Reset");
    int angle = 0, anglel = 0, angleh = 0;
    @Override
    public void init() {
        this.setSize(resX, resY);
        this.setBackground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);
        b2.addActionListener(this);
        add(b2);
        b3.addActionListener(this);
        add(b3);
        b4.addActionListener(this);
        add(b4);
        b5.addActionListener(this);
        add(b5);
    }

    @Override
    public void paint(Graphics g) 
    {    
        Point head = new Point(hx, hy);
        int headw = 50, headl = 50;
        Point eyel = new Point(hx + 7, hy + 10);
        Point eyer = new Point(hx + 33, hy + 10);
        Point mouth = new Point(hx + 16, hy + 35);
        int mouthl = 6, mouthw = 18;
        Point neck = new Point(hx + headw / 4, hy + headl);
        int neckw = 25, neckl = 10;
        Point body = new Point(hx - 20, hy + headl + neckl);
        int bodyl = 140, bodyw = 90;
        Point lbody = new Point(hx - 20, hy + headl + neckl + bodyl);
        int lbodyl = 80, lbodyw = bodyw;
        Point legl = new Point(hx - 20, hy + headl + neckl + bodyl + lbodyl);
        int legll = 80, leglw = 25;
        Point legr = new Point(hx + leglw + 20, hy + headl + neckl + bodyl + lbodyl);
        
        //  Point shoel = new Point(hx-20, hy+headl+neckl+bodyl+lbodyl+legll);
        int p[][] = new int[4][2];
        int h[][] = new int[13][2];
        h[0][0] = hx;
        h[0][1] = hy;
        h[1][0] = hx + headw;
        h[1][1] = hy;
        h[2][0] = hx + headw;
        h[2][1] = hy + headl;
        h[3][0] = hx;
        h[3][1] = hy + headl;
        h[4][0] = eyel.x;
        h[4][1] = eyel.y;
        h[5][0] = eyer.x;
        h[5][1] = eyer.y;
        h[6][0] = mouth.x;
        h[6][1] = mouth.y;
        h[7][0] = mouth.x + mouthw;
        h[7][1] = mouth.y;
        h[8][0] = mouth.x + mouthw;
        h[8][1] = mouth.y + mouthl;
        h[9][0] = mouth.x;
        h[9][1] = mouth.y + mouthl;
        h[10][0] = hx + 25;
        h[10][1] = hy + 15;
        h[11][0] = hx + 20;
        h[11][1] = hy + 30;
        h[12][0] = hx + 30;
        h[12][1] = hy + 30;
        face headr = new face(g, h);
        if (f == 0) {
            headr.setVisible();
        }
        else{
            headr.rotate(neck.x + neckw / 2, neck.y + neckl / 2, angleh);
            //headr.setVisible();
        }
        //repaint();
        g.setColor(Color.CYAN);
        p[0][0] = body.x;
        p[0][1] = body.y;
        p[1][0] = body.x + bodyw;
        p[1][1] = body.y;
        p[2][0] = body.x + bodyw;
        p[2][1] = body.y + bodyl;
        p[3][0] = body.x;
        p[3][1] = body.y + bodyl;
        Rectangle bodyr = new Rectangle(g, p);
        bodyr.setVisible();
        g.setColor(Color.YELLOW);
        p[0][0] = neck.x;
        p[0][1] = neck.y;
        p[1][0] = neck.x + neckw;
        p[1][1] = neck.y;
        p[2][0] = neck.x + neckw;
        p[2][1] = neck.y + neckl;
        p[3][0] = neck.x;
        p[3][1] = neck.y + neckl;
        Rectangle neckr = new Rectangle(g, p);
        neckr.setVisible();
        int d[][] = new int[8][2];
        d[0][0] = lbody.x;
        d[0][1] = lbody.y;
        d[1][0] = lbody.x + lbodyw;
        d[1][1] = lbody.y;
        d[2][0] = lbody.x + lbodyw;
        d[2][1] = lbody.y + lbodyl;
        d[3][0] = lbody.x + lbodyw - 25;
        d[3][1] = lbody.y + lbodyl;
        d[4][0] = lbody.x + lbodyw - 25;
        d[4][1] = lbody.y + 40;
        d[5][0] = lbody.x + 25;
        d[5][1] = lbody.y + 40;
        d[6][0] = lbody.x + 25;a
        d[6][1] = lbody.y + lbodyl;
        d[7][0] = lbody.x;
        d[7][1] = lbody.y + lbodyl;
        lbody lb = new lbody(g, d);
        lb.setVisible();
        /**
         * **hands***
         */
        g.setColor(Color.yellow);
        p[0][0] = body.x;
        p[0][1] = body.y;
        p[1][0] = body.x - 50;
        p[1][1] = body.y + 40;
        p[2][0] = body.x - 50;
        p[2][1] = body.y + 65;
        p[3][0] = body.x;
        p[3][1] = body.y + 25;
        Rectangle hl = new Rectangle(g, p);
        if (hand != 1)
            hl.setVisible();
        else
            hl.rotate(body.x, body.y + 12, -angle);
        p[0][0] = body.x + bodyw;
        p[0][1] = body.y;
        p[1][0] = body.x + bodyw + 50;
        p[1][1] = body.y + 40;
        p[2][0] = body.x + bodyw + 50;
        p[2][1] = body.y + 65;
        p[3][0] = body.x + bodyw;
        p[3][1] = body.y + 25;
        Rectangle hr = new Rectangle(g, p);
        if (hand != 1)
            hr.setVisible();
        else
            hr.rotate(body.x + bodyw, body.y + 12, angle);
            

        /**
         * *arm***
         */
        g.setColor(Color.yellow);
        p[0][0] = body.x - 50;
        p[0][1] = body.y + 40;
        p[1][0] = body.x - 90;
        p[1][1] = body.y + 10;
        p[2][0] = body.x - 90;
        p[2][1] = body.y + 10 + 25;
        p[3][0] = body.x - 50;
        p[3][1] = body.y + 65;
        Rectangle al = new Rectangle(g, p);
        if (hand != 1)
            al.setVisible();
        else
            al.rotate(body.x, body.y + 12, -angle);
            
        p[0][0] = body.x + bodyw + 50;
        p[0][1] = body.y + 40;
        p[1][0] = body.x + bodyw + 90;
        p[1][1] = body.y + 10;
        p[2][0] = body.x + bodyw + 90;
        p[2][1] = body.y + 10 + 25;
        p[3][0] = body.x + bodyw + 50;
        p[3][1] = body.y + 65;
        Rectangle ar = new Rectangle(g, p);
        if (hand != 1)
            ar.setVisible();
        else
           ar.rotate(body.x + bodyw, body.y + 12, angle);
        /**
         * palm***
         */
        g.setColor(Color.ORANGE);
        int c[][] = new int[7][2];
        c[0][0] = body.x - 90;
        c[0][1] = body.y + 10;
        c[1][0] = body.x - 100;
        c[1][1] = body.y;
        c[2][0] = body.x - 105;
        c[2][1] = body.y;
        c[3][0] = body.x - 100;
        c[3][1] = body.y + 10;
        c[4][0] = body.x - 110;
        c[4][1] = body.y + 10;
        c[5][0] = body.x - 110;
        c[5][1] = body.y + 35;
        c[6][0] = body.x - 90;
        c[6][1] = body.y + 35;
        palm pl = new palm(g, c);
        if (hand != 1)
          pl.setVisible();
        else
            pl.rotate(body.x, body.y + 12, -angle);
           
        c[0][0] = body.x + bodyw + 90;
        c[0][1] = body.y + 10;
        c[1][0] = body.x + bodyw + 100;
        c[1][1] = body.y;
        c[2][0] = body.x + bodyw + 105;
        c[2][1] = body.y;
        c[3][0] = body.x + bodyw + 100;
        c[3][1] = body.y + 10;
        c[4][0] = body.x + bodyw + 110;
        c[4][1] = body.y + 10;
        c[5][0] = body.x + bodyw + 110;
        c[5][1] = body.y + 35;
        c[6][0] = body.x + bodyw + 90;
        c[6][1] = body.y + 35;
        palm pr = new palm(g, c);

        if (hand == 1)
            pr.rotate(body.x + bodyw, body.y + 12, angle);
        else
            pr.setVisible();
            
        g.setColor(Color.yellow);
        p[0][0] = legl.x;
        p[0][1] = legl.y;
        p[1][0] = legl.x + leglw;
        p[1][1] = legl.y;
        p[2][0] = legl.x + leglw;
        p[2][1] = legl.y + legll;
        p[3][0] = legl.x;
        p[3][1] = legl.y + legll;
        Rectangle leglr = new Rectangle(g, p);
        if(leg == 1)
            leglr.rotate(legl.x+leglw/2, legl.y, -anglel);
        else
            leglr.setVisible();
        p[0][0] = legr.x;
        p[0][1] = legr.y;
        p[1][0] = legr.x + leglw;
        p[1][1] = legr.y;
        p[2][0] = legr.x + leglw;
        p[2][1] = legr.y + legll;
        p[3][0] = legr.x;
        p[3][1] = legr.y + legll;
        Rectangle legrr = new Rectangle(g, p);
        if(leg == 1)
            legrr.rotate(legr.x+leglw/2, legr.y, anglel);
        else
            legrr.setVisible();
        c[0][0] = legl.x;
        c[0][1] = legl.y + legll;
        c[1][0] = legl.x + leglw;
        c[1][1] = legl.y + legll;
        c[2][0] = legl.x + leglw;
        c[2][1] = legl.y + legll + 10;
        c[3][0] = legl.x + leglw + 15;
        c[3][1] = legl.y + legll + 10;
        c[4][0] = legl.x + leglw + 15;
        c[4][1] = legl.y + legll + 10 + 10;
        c[5][0] = legl.x;
        c[5][1] = legl.y + legll + 10 + 10;
        shoe l = new shoe(g, c);
        if(leg == 1)
            l.rotate(legl.x+leglw/2, legl.y, -anglel);
        else    
            l.setVisible();
        c[0][0] = legr.x;
        c[0][1] = legr.y + legll;
        c[1][0] = legr.x + leglw;
        c[1][1] = legr.y + legll;
        c[2][0] = legr.x + leglw;
        c[2][1] = legr.y + legll + 10;
        c[3][0] = legr.x + leglw + 15;
        c[3][1] = legr.y + legll + 10;
        c[4][0] = legr.x + leglw + 15;
        c[4][1] = legr.y + legll + 10 + 10;
        c[5][0] = legr.x;
        c[5][1] = legr.y + legll + 10 + 10;
        shoe r = new shoe(g, c);
        if(leg == 1)
            r.rotate(legr.x+leglw/2, legr.y, anglel);
        else
            r.setVisible();
        if (re == 1 || hand == 1 || leg == 1 || f == 1)
            draw();
    }

    public void draw()
    {
        try 
        {
            if(re == 1)
                hx = hx + 10;
            if(hand == 1)
                angle += 10;
            if(leg == 1)
                anglel += 5;
            if(f == 1)
                angleh += 5;
            if(angle > 60)
                angle = 20;
            if(anglel > 40)
                anglel = 0;
            if(angleh > 40)
                angleh = -angleh;
            
            Thread.sleep(300);
        } catch (Exception e) {
        };
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == b1) {
            re = 1;
            hand = 1;
            leg = 1;
            repaint();
        }
        if (e.getSource() == b2) {
            f = 1;
            repaint();
        }
        if (e.getSource() == b3) {
            hand = 1;
            repaint();
        }
        if (e.getSource() == b5) 
        {
            leg = 1;
            repaint();
        }
        if (e.getSource() == b4) {
            re = 0; f = 0;
            hand = 0;
            leg = 0;
            angle = 0; anglel = 0;
            hx = 150; hy = 50;
            repaint();
        }
        
    }
}
