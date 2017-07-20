package EvacGUI;
import java.awt.*;
import java.awt.event.*;
class My_box extends Rectangle
{
	int testint = 4;
    //default vars
    /*
     * x,y,width,height
     */
    public My_box(int x1,int y,int width,int height)
    {
        super(x1,y,width,height);
    }
    
    
    public void drawBox(Graphics g, String scolor)
    {
    	if(scolor == "red") g.setColor(Color.RED);
    	if(scolor == "green") g.setColor(Color.GREEN);
    	if(scolor == "white") g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }
}
