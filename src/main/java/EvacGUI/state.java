package EvacGUI;

public class state {
	static int agent_number = 0;
	
	public static float getCoordX(int agent_id){
        int index = -1;    
        for(int i=0; i< EvacGUI.canvas.ballarray.size(); i++){
            if(EvacGUI.canvas.ballarray.get(i).ball_id==agent_id)
            index = i;
        }
        EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(index);
        return temp_ball.x_pos;
    }
    
    public static float getCoordY(int agent_id){
        int index = -1;    
        for(int i=0; i< EvacGUI.canvas.ballarray.size(); i++){
            if(EvacGUI.canvas.ballarray.get(i).ball_id==agent_id)
            index = i;
        }
        EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(index);
        return temp_ball.y_pos;
    }
	


    public synchronized static void change_direction(int agent_id,float goal_x, float goal_y){
		int index = -1;	
    	for(int i=0; i< EvacGUI.canvas.ballarray.size(); i++){
    		if(EvacGUI.canvas.ballarray.get(i).ball_id==agent_id)
    		index = i;
    	}
    	float x=EvacGUI.canvas.ballarray.get(index).x_pos;
    	float y=EvacGUI.canvas.ballarray.get(index).y_pos;
    	System.out.println("change direction for " + agent_id);
    	float diff_x = goal_x-x;
    	float diff_y = goal_y-y;
    	float diff_xx = 0;
    	float diff_yy = 0;
    	if(diff_x<0){
    		diff_xx=-diff_x;
    	}
    	else
    		diff_xx=diff_x;
    	if(diff_y<0){
    		diff_yy=-diff_y;
    	}
    	else
    		diff_yy=diff_y;
    	double intensity=java.lang.Math.sqrt(diff_xx*diff_xx+diff_yy*diff_yy);
    	diff_x=(float) (diff_x/intensity);
    	diff_y=(float) (diff_y/intensity);
    	/*
    	float normalized_diff_y;
    	if(diff_x<0){
    		normalized_diff_y=diff_y/diff_x;
    		diff_x=1;
    		
    	}
    	else {
    		normalized_diff_y=-diff_y/diff_x;
    		diff_x=-1;
    	}
    	*/
    	EvacGUI.canvas.ballarray.get(index).dir_x=-diff_x;
    	EvacGUI.canvas.ballarray.get(index).dir_y=-diff_y;//normalized_diff_y;
    	System.out.println(diff_x);
    	//System.out.println(normalized_diff_y);
    
	}
    
    
    public synchronized static void calcMinDirection(int agent_id) {

		int i;
		float minDis;
		minDis = 999999;
		float exp_min ;
		float door_x;
		float door_y ;
		float goal_x = 0;
		float goal_y = 0;

		float getX = EvacGUI.state.getCoordX(1);
		float getY = EvacGUI.state.getCoordY(1);

		for (i = 0; i < base_frame.listDoor.size(); i++) {
			door_x = base_frame.listDoor.get(i).door_center_x;
			door_y = base_frame.listDoor.get(i).door_center_y;

			exp_min = (door_x - getX) * (door_x - getX) + (door_y - getY) * (door_y - getY);
			if (exp_min < minDis) {
				minDis = exp_min;
				goal_x = door_x;
				goal_y = door_y;
			}
		}
		//define
        EvacGUI.state.change_direction(agent_id,goal_x,goal_y);
        
    }
    
}
