package EvacGUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
class My_box extends Rectangle
{
	
	int testint = 4;
	
  
	
    public My_box(int x1,int y,int width,int height)
    {
        super(x1,y,width,height);
       // human_child_male d = new human_child_male(null, null);
        
    }
    
    
    public void drawBox(Graphics g, String scolor)
    {
    	if(scolor == "red") g.setColor(Color.RED);
    	if(scolor == "green") g.setColor(Color.GREEN);
    	if(scolor == "white") g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }
}
