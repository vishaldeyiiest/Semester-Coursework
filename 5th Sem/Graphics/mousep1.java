package mygraphics;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class mousep1 extends Applet
implements MouseListener,MouseMotionListener
{
	int X=0,Y=0;
	String msg="MouseEvents";
	public void init()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setSize(800,600);
	
	}
	public void mouseEntered(MouseEvent m)
	{
		
	//repaint();	
	}
	public void mouseExited(MouseEvent m)
	{
		
	}
	public void mousePressed(MouseEvent m)
	{
	
		int x = (int) m.getX();
		int y = (int) m.getY();
                
		
                
		switch(m.getModifiers())
                {
	      case InputEvent.BUTTON1_MASK:
	      {
	    	  
				 
				
				   
	        System.out.println("That's the LEFT button");     
	        break;
	        }
	      case InputEvent.BUTTON2_MASK: 
	      {
	    	  System.out.println("That's the MIDDLE button");     
	        break;
	        }
	      case InputEvent.BUTTON3_MASK:
	      {
	        
	    	
	    	  
	    	  System.out.println("That's the RIGHT button");     
	        break;
	        }
	      }
		
	

	
		//repaint();
	}
	public void mouseReleased(MouseEvent m)
	{
	
		//repaint();
	}
	public void mouseMoved(MouseEvent m)
	{
          
	
		//repaint();
	}
	public void mouseDragged(MouseEvent m)
	{
		
		//repaint();
	}
	public void mouseClicked(MouseEvent m)
	{
		
		//repaint();
	}
	public void paint(Graphics g)
	{
		
		
        }
}
