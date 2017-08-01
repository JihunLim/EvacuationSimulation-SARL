package EvacGUI;
import EvacGUI.canvas;
import EvacGUI.canvas.ball;

public class state {
	static int agent_number = 0;
	/*
	public float getCoordX(int i){
		EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(i);
		return temp_ball.x_pos;
	}
	
	public float getCoordY(int i){
		EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(i);
		return temp_ball.y_pos;
	}
	*/
	

    public void change_direction(int agent_id,int closestdoor_x, int closestdoor_y){
    		synchronized (this) {
			float x=EvacGUI.canvas.ballarray.get(agent_id).x_pos;
    	float y=EvacGUI.canvas.ballarray.get(agent_id).y_pos;
    		
    	float diff_x = closestdoor_x-x;
    	float diff_y = closestdoor_y-y;
    		
    	float normalized_diff_y;
    	if(diff_x<0){
    		normalized_diff_y=diff_y/diff_x;
    		diff_x=1;
    		
    	}
    	else {
    		normalized_diff_y=-diff_y/diff_x;
    		diff_x=-1;
    	}
    	
    	EvacGUI.canvas.ballarray.get(0).dir_x=diff_x;
    	EvacGUI.canvas.ballarray.get(0).dir_y=normalized_diff_y;
    	System.out.println(diff_x);
    	System.out.println(normalized_diff_y);
    	
    }
	}
}
