
package mygraphics;


import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class kbp1 extends Applet implements KeyListener
{

	public void init()
	{
		addKeyListener(this);
		requestFocus();
		
	}
	public void keyPressed(KeyEvent k)
	{
		
               System.out.println("Key  down");
		int key=k.getKeyCode();
		switch(key) 
		{
			case KeyEvent.VK_UP:
				
                                System.out.println("Move to Up");
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Move to Down");
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Move to Left");
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Move to Right");
				break;
		}
		repaint();
	}
	public void keyReleased(KeyEvent k)
	{
		System.out.println("Key Up");
	}
	public void keyTyped(KeyEvent k)
	{
		System.out.println("You have typed"+":  "+k.getKeyChar());
             
		repaint();
	}
	public void paint(Graphics g)
	{
	
	}
}