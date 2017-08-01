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
	


    public synchronized static void change_direction(int agent_id,int closestdoor_x, int closestdoor_y){
		int index = -1;	
    	for(int i=0; i< EvacGUI.canvas.ballarray.size(); i++){
    		if(EvacGUI.canvas.ballarray.get(i).ball_id==agent_id)
    		index = i;
    	}
    	float x=EvacGUI.canvas.ballarray.get(index).x_pos;
    	float y=EvacGUI.canvas.ballarray.get(index).y_pos;
    	System.out.println("change direction for " + agent_id);
    	float diff_x = closestdoor_x-x;
    	float diff_y = closestdoor_y-y;
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
}
