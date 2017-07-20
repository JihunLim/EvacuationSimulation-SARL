package EvacGUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class canvas extends JComponent implements KeyListener,MouseMotionListener
{
    //display details
    private final int width=1100;
    private final int height=600;
    static int wall_width = 800;
    static int wall_height = 500;
    boolean bg_change=false;
    int color=254,incr=1;
    //a   array
    static ArrayList<ball> ballarray;
    static ArrayList<My_box> wallarray;
    ArrayList<ball> tempballarray;
    ball temp_ball = new ball(20);
    ball evac_ball = new ball(20);
    int time_counter=0;
    //my custom box
    
    My_box b1,b2,b3,b4,b5,wall1;
    
    canvas()
    {
        ballarray=new ArrayList(1);
        wallarray=new ArrayList(1);
        b1=new My_box(0,0,wall_width,10);//top(-)
        b2=new My_box(0,0,10,wall_height);//top-right(|)
        b3=new My_box(0,0,wall_width+10,10);//bottom-left(-)
        //b4=new My_box(0,0,(wall_width/2)-30,10);//bottom-right(-)
        b5=new My_box(0,0,10,wall_height);//top-left(|)
        
        wall1=new My_box(400,wall_height-5,60, 20);//top-left(|)
        
        b1.setLocation(0,0);
        b2.setLocation(wall_width,0);
        b3.setLocation(0,wall_height);
        //b4.setLocation((wall_width/2)+40,wall_height);
        b5.setLocation(0,0);
        addKeyListener(this);
        addMouseMotionListener(this);
 
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
    }
    
    
 
    //change to background color dynamically(default:white)
    public void changeBGcolor()
    {
        if(bg_change)
        {
            bg_change=false;
        }
        else
        {
            bg_change=true;
        }
    }
    
    //add a new wall
    public static void addDoor(float _door_x, float _door_y, int _door_width, int _door_height){
    	//add the additional doors
        My_box temp_box = new My_box((int)_door_x, (int)_door_y, _door_width, _door_height );
        wallarray.add(temp_box);
    }
 
    //add a new ball by passing a “ball” class instance
    public void addBall()
    {
        //added to the arraylist
        ball temp_ball = new ball(20);
        temp_ball.x_pos=(int) (Math.random()*wall_width%(wall_width-10));
        temp_ball.y_pos= (int) (Math.random()*wall_height%(wall_height-10));
        ballarray.add(temp_ball);
        base_frame.balladded();
    }
 
    public void removeBall()
    {
        if(!ballarray.isEmpty())
        {
            ballarray.remove(ballarray.size()-1);
        }
    }
    
    public void removeExitBall(){
    	 //checking for exit wall collision with our movable
          tempballarray = ballarray;
          evac_ball = temp_ball;
          tempballarray.remove(temp_ball);
          base_frame.ballremoved();
          System.out.println("removed a ball!");
          //repaint();//calls the paint method
          if(tempballarray!=null){
              ballarray=tempballarray;
              ballarray.remove(evac_ball);
          }
          repaint();//calls the paint method
    }
    
    public int getBallCount()
    {
            return ballarray.size();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
 
        //fill rect
        if(bg_change)
        {
            color=color-incr;
 
             if(color<1)                         
                        {
                                    incr=-1;                         
                        }             
                         if(color>250)
            {
                incr=1;
            }
            g.setColor(new Color(color,color,color));
        }
        else
        {
            color=254;
            g.setColor(Color.WHITE);
        }
        g.fillRect(0,0,getWidth(),getHeight());
 
        //my box
        g.setColor(Color.BLACK);
        b1.drawBox(g, "red");
        b2.drawBox(g, "red");
        b3.drawBox(g, "red");
        //b4.drawBox(g, "red");
        b5.drawBox(g, "red");
        //wall1.drawBox(g,  "green");
        
        ball evac_ball = new ball(20);
        try{
            Thread.sleep(5);
            time_counter=time_counter+5;
            if(time_counter%1000==0&&base_frame.startflag==1){
                base_frame.timercount();
            }
            for(My_box temp_box: wallarray){
            	temp_box.drawBox(g, "green");
            }
            
            for(ball temp_ball: ballarray)
            {
                //drawing the ball using “drawBall(Graphics g,boolean)“.
                //boolean is for displaying ball’s bounding rectangle which deals with 
                temp_ball.drawBall(g, false);
              //******************************************************************** check touching part
                //checking for collision with our movable pad
                temp_ball.collision(b1);
                temp_ball.collision(b2);
                temp_ball.collision(b3);
                //temp_ball.collision(b4);
                temp_ball.collision(b5);
                //temp_ball.collisionExit(wall1);
                
                //벽 추가 버튼을 누르면 리스트에 둬서 리스트를 차례로 번갈아가면서 확인하는 것으로 바꾸면 됨
                
                //checking for exit wall collision with our movable
                for(My_box temp_box: wallarray){
                	if(temp_ball.collisionExit(temp_box)){
                        tempballarray = ballarray;
                        evac_ball = temp_ball;
                        tempballarray.remove(temp_ball);
                        base_frame.ballremoved();
                        System.out.println("removed a ball!");
                        repaint();//calls the paint method
                    } 
                	
                }
              //******************************************************************** check touching part
            }
            if(tempballarray!=null){
                ballarray=tempballarray;
                ballarray.remove(evac_ball);
            }
            repaint();//calls the paint method
 
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
 
    //keyboard call-back methods
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT)//increase x
        {
 
            b1.translate(10,0);
        }
        else
        if(ke.getKeyCode()==KeyEvent.VK_LEFT)
        {
            b1.translate(-10,0);
        }
        }
    public void keyReleased(KeyEvent ke)
    {
    }
    public void keyTyped(KeyEvent ke)
    {
 
    }
 
    //mouse call-back methods
 
    public void mouseMoved(MouseEvent me)
    {
        //move the rectangle box too
        //b1.setLocation(me.getX(),me.getY());
    }
    public void mouseDragged(MouseEvent me)
    {
 
    }
 
    //Ball inner-class
    class ball
    {
        private int x_pos=0;
        private int y_pos=0;
        private int dir_x=1;
        private int dir_y=1;
        private int size=0;
 
        ball(int size)
        {
            this.size=size;
        }
        
        public void removeBall()
        {
            if(!ballarray.isEmpty())
            {
            	
                ballarray.remove(ballarray.size()-1);
            }
        }
        
        private void calculate_direction()
        {
            //move the ball
            x_pos=x_pos-dir_x;
            y_pos=y_pos-dir_y;
 
            //for X-direction
            if(x_pos<0)
                        {              
                            x_pos=0;    
                    dir_x=-1;//incr   
                        }
                        else  
                       if(x_pos+size>getWidth())
            {
                 dir_x=1;//decr
            }
 
            //for Y-direction
            if(y_pos<0)  
                        {  
                                 y_pos=0;     
                             dir_y=-1;//incr                         
 
                        }                         
                         else                                           
                         if(y_pos+size>getHeight())
            {
                dir_y=1;//decr
            }
        }
 
        public void changeDirection_Y()
        {
                    if(dir_y==-1)
            {
                dir_y=1;
            }
            else
            {
                dir_y=-1;
            }
        }
 
        public void changeDirection_X()
        {
            if(dir_x==-1)
            {
                dir_x=1;
            }
            else
            {
                dir_x=-1;
            }
        }
 
        public void drawBall(Graphics g, boolean bound)
        {
            calculate_direction();
            g.setColor(Color.BLUE);
            g.fillOval(x_pos,y_pos,size,size);
 
            if(bound)
            {
                g.drawRect(x_pos,y_pos,size,size);
            }
        }
 
        public Rectangle getRectBounds()
        {
            return new Rectangle(x_pos,y_pos,size,size);
        }
                //this method is called for all balls and checked whether it touches the rect.if so,the ball’s direction gets changed accordingly.
        public void collision(My_box rect)
        {
            //if ball collides with top/bottom part of the rect
            if(this.getRectBounds().intersectsLine(rect.x,rect.y,rect.x+rect.width+10,rect.y) ||
                           this.getRectBounds().intersectsLine(rect.x,rect.y+rect.height, rect.x+rect.width+10,rect.y+rect.height))
            {
 
                changeDirection_Y();//reverses the direction along Y
 
            }
 
             //if ball collides with left/right part of the rect
            if(this.getRectBounds().intersectsLine(rect.x,rect.y    ,    rect.x,rect.y+rect.height+5) ||
                           this.getRectBounds().intersectsLine(rect.x+rect.width,rect.y  ,   rect.x+rect.width,rect.y+rect.height))
            {
                changeDirection_X();//reverses the direction along X
            }
        }
        
        public Boolean collisionExit(My_box rect){
        	//if ball collides with top/bottom part of the exit rect
        	if(this.getRectBounds().intersectsLine(rect.x,rect.y,rect.x+rect.width,rect.y) ||
                    this.getRectBounds().intersectsLine(rect.x,rect.y+rect.height, rect.x+rect.width,rect.y+rect.height))
		     {
        		//removeExitBall();//reverses the direction along Y
        		return true;
		     }
        	 
        	//if ball collides with left/right part of the exit rect
            if(this.getRectBounds().intersectsLine(rect.x,rect.y    ,    rect.x,rect.y+rect.height) ||
                           this.getRectBounds().intersectsLine(rect.x+rect.width,rect.y  ,   rect.x+rect.width,rect.y+rect.height))
            {
            	//removeExitBall();//reverses the direction along X
            	return true;
            }
        	return false;
        }
    }
}