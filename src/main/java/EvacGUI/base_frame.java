package EvacGUI;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//******************************************************************** import part
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//******************************************************************** import part
//******************************************************************** jframe part
public class base_frame extends JFrame implements ActionListener, ChangeListener {
    // ********************************************************************
    // define
    canvas draw_panel;
    JButton b_changebg,b_add,b_remove,b_start,b_doorAdd;
    static int agent_num = 0;
    static int agent_eva = 0;
    JPanel ui_panel,ui_panel1,ui_panel2,ui_panel3,ui_panel1_2;
    JLabel agent_number = new JLabel("total agent number : ");
    static JLabel agent_number2 = new JLabel("0");
    static JLabel agent_number3 = new JLabel("0");
    JLabel layout_agentNum = new JLabel("Input the agent number : ");
    JTextField agent_total_num = new JTextField(5);
    JTextField door_total_num = new JTextField(5);
    
    static boolean isSpawnAgent = false;
    static boolean isButtonClicked = false;
    
    public static int timepassed=0;
    static int startflag=0;
    
    //door settings
    private float door_x = .0f;
    private float door_y = .0f;
    private int door_slideValue = 0;
    private int door_size = 0;
    private int door_width = 0;
    private int door_height = 0;
    
    static List<EscapeDoor> listDoor = new ArrayList<EscapeDoor>();
    
    static JLabel timerLabel = new JLabel("timer :");
    static JLabel timerLabel2 = new JLabel("0");
    
    //static int SDOOR_MAX = 1200; 
    
    JRadioButton b_top = new JRadioButton("TOP");
    JRadioButton b_right = new JRadioButton("RIGHT");
    JRadioButton b_bottom = new JRadioButton("BOTTOM");
    JRadioButton b_left = new JRadioButton("LEFT");
    JRadioButton b_doorBig = new JRadioButton("Big door");
    JRadioButton b_doorSmall = new JRadioButton("Small door");
    ButtonGroup group = new ButtonGroup();
    ButtonGroup group1 = new ButtonGroup();
    JSlider slider = new JSlider(JSlider.HORIZONTAL,0,100,0); 
    
    // ********************************************************************
    base_frame() {
        super("Evacuation Simulation Based on Agent System");
        //set slider additional setting
        slider.setPaintLabels(true); // 
    	slider.setPaintTicks(true); //
    	slider.setPaintTrack(true); // slider box
    	
    	slider.setMajorTickSpacing(10); // 
    	slider.setMajorTickSpacing(5); //
    	
        // our UI panel which contains gui comps
        ui_panel = new JPanel();
        ui_panel1 = new JPanel();
        ui_panel2 = new JPanel();
        ui_panel1_2 = new JPanel();
        ui_panel3 = new JPanel();
        
        b_changebg = new JButton("Change Background");
        b_add = new JButton("Add");
        b_remove = new JButton("Remove");
        b_start = new JButton("Start!");
        b_doorAdd = new JButton("Add door");
        
        // add listeners
        slider.addChangeListener(this);
        b_changebg.addActionListener(this);
        b_doorAdd.addActionListener(this);
        b_add.addActionListener(this);
        b_remove.addActionListener(this);
        b_start.addActionListener(this);
        b_top.addActionListener(this);
        b_right.addActionListener(this);
        b_bottom.addActionListener(this);
        b_left.addActionListener(this);
        b_doorBig.addActionListener(this);
        b_doorSmall.addActionListener(this);
        
        setLayout(new FlowLayout());
        draw_panel = new canvas();
        //adding timer
    
        // add ui_panel1 which incudes buttons
        ui_panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        ui_panel1.add(layout_agentNum);
        ui_panel1.add(agent_total_num);
        ui_panel1.add(b_start);
        //ui_panel1.add(b_changebg);
        //ui_panel1.add(b_add);
        //ui_panel1.add(b_remove);
        
        //add ui_panel2 which is information
        ui_panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ui_panel2.add(agent_number);
        ui_panel2.add(agent_number2);
        ui_panel2.add(timerLabel);
        ui_panel2.add(timerLabel2);
        
        ui_panel1_2.setLayout(new FlowLayout());
        ui_panel1_2.add(ui_panel1);
        ui_panel1_2.add(ui_panel2);
        
        //add ui_panel3 which decide the num & position the doors
        group.add(b_top);
        group.add(b_right);
        group.add(b_bottom);
        group.add(b_left);
        
        group1.add(b_doorBig);
        group1.add(b_doorSmall);
        
        ui_panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        ui_panel3.add(new JLabel("Make door!!  door direction : "));
        ui_panel3.add(new JLabel("door size : "));
        ui_panel3.add(b_doorBig);
        ui_panel3.add(b_doorSmall);
        ui_panel3.add(new JLabel("door position(%) : "));
        ui_panel3.add(slider);
        ui_panel3.add(b_top);
        ui_panel3.add(b_right);
        ui_panel3.add(b_bottom);
        ui_panel3.add(b_left);
        ui_panel3.add(b_doorAdd);
        
        
        //ui_panel3.add(door_total_num);
        
        //add ui_panel which is the basic UI
        ui_panel.setLayout(new GridLayout(3,1));
        ui_panel.add(ui_panel1_2);
        ui_panel.add(ui_panel3);
        // adding to our JFrame
        add(draw_panel);
        add(ui_panel);

        setSize(1200, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public float getDoorX(){
    	return door_x;
    }
    public float getDoorY(){
    	return door_y;
    }
    public float getDoorSize(){
    	return door_size;
    }
    
    public static void main(String[] args) {
        new base_frame();
    }
    // ********************************************************************
    // button settings
    @Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==slider){
			door_slideValue = slider.getValue();
			System.out.println("slider의 값 : " + door_slideValue);
		}
		
	}
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b_changebg)
        {
                draw_panel.changeBGcolor();
        }else if(e.getSource()==b_add)
        {
            //adds a ball of size 20;
            draw_panel.addBall();
            System.out.println("Added a ball");
        }else if(e.getSource()==b_remove)
        {
                draw_panel.removeBall();
                System.out.println("Removed a ball");
        }else if(e.getSource()==b_start){
            draw_panel.removeAll();
            for(int i=0;i<Integer.parseInt(agent_total_num.getText());i++){
            	//jihun will add..
            	
                draw_panel.addBall();
                startflag=1;
            }
            
            //call def to make event in sarl about the spawning agents!
            isSpawnAgent = true;
            isButtonClicked = true;
        }
        if(e.getSource() == b_doorBig){
        	door_size = 50;
        }else if(e.getSource() == b_doorSmall){
        	door_size = 30;
        }
        
        if(e.getSource() == b_top){
        	door_y = -5; 
        	door_x = (((float)canvas.wall_width-door_size)/100)*door_slideValue;
        	door_width = door_size; door_height = 20;
        	
        	//System.out.printf("%f, %f, %d, %d", door_x, door_y, door_width, door_height);
    	}else if(e.getSource() == b_right){
    		door_x = canvas.wall_width-5;
    		door_y = (((float)canvas.wall_height-door_size)/100)*door_slideValue;
    		door_width = 20; door_height = door_size;
    		
    	}else if(e.getSource() == b_bottom){
    		door_y = canvas.wall_height-5;
    		door_x = (((float)canvas.wall_width-door_size)/100)*door_slideValue;
    		door_width = door_size; door_height = 20;
    		
    	}else if(e.getSource() == b_left){
    		door_x = -5;
    		door_y = (((float)canvas.wall_height-door_size)/100)*door_slideValue;
    		door_width = 20; door_height = door_size;
    		
    	}
        if(e.getSource() == b_doorAdd){
        	//make door
        	canvas.addDoor(door_x, door_y, door_width, door_height);
        	//create the 'EscapeDoor' class with the x,y information and make the ArrayList as listDoor
        	listDoor.add(new EscapeDoor(door_x, door_y, door_width, door_height));
        	group.clearSelection();
        }
    }
    
    public static void balladded() {
        agent_num = agent_num + 1;
        agent_number2.setText(Integer.toString(agent_num));
    }
    
    public static void ballremoved(){
        agent_num = agent_num - 1;
        agent_number2.setText(Integer.toString(agent_num));
        
    }
    
    public static void timercount() {
        timepassed = timepassed + 1;
        timerLabel2.setText(Integer.toString(timepassed));
    }
}