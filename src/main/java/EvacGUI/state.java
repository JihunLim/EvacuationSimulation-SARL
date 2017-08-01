package EvacGUI;

public class state {
	static int agent_number = 0;

	public static float getCoordX(int agent_id) {
		int index = 0;
		
		
		for (int i = 0; i < EvacGUI.canvas.ballarray.size(); i++) {
			if (EvacGUI.canvas.ballarray.get(i).ball_id == agent_id)
				index = i;
		}
		System.out.println("aaaa");
		EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(index);
		return temp_ball.x_pos;
	}

	public static float getCoordY(int agent_id) {
		int index = 0;
		for (int i = 0; i < EvacGUI.canvas.ballarray.size(); i++) {
			if (EvacGUI.canvas.ballarray.get(i).ball_id == agent_id)
				index = i;
		}
	
		EvacGUI.canvas.ball temp_ball = EvacGUI.canvas.ballarray.get(index);
		return temp_ball.y_pos;
	}

	public synchronized static void change_direction(int agent_id, float goal_x, float goal_y) {
		int index = -1;
		
		if (EvacGUI.canvas.ballarray.size() < 1){
			return ;
		}
		
		for (int i = 0; i < EvacGUI.canvas.ballarray.size(); i++) {
			if (EvacGUI.canvas.ballarray.get(i).ball_id == agent_id)
				index = i;
		}
		
		float x = EvacGUI.canvas.ballarray.get(index).x_pos;
		float y = EvacGUI.canvas.ballarray.get(index).y_pos;
		System.out.println("change direction for " + agent_id);
		float diff_x = goal_x - x;
		float diff_y = goal_y - y;
		float diff_xx = 0;
		float diff_yy = 0;
		if (diff_x < 0) {
			diff_xx = -diff_x;
		} else
			diff_xx = diff_x;
		if (diff_y < 0) {
			diff_yy = -diff_y;
		} else
			diff_yy = diff_y;
		double intensity = java.lang.Math.sqrt(diff_xx * diff_xx + diff_yy * diff_yy);
		diff_x = (float) (diff_x / intensity);
		diff_y = (float) (diff_y / intensity);
		/*
		 * float normalized_diff_y; if(diff_x<0){
		 * normalized_diff_y=diff_y/diff_x; diff_x=1;
		 * 
		 * } else { normalized_diff_y=-diff_y/diff_x; diff_x=-1; }
		 */
		EvacGUI.canvas.ballarray.get(index).dir_x = -diff_x;
		EvacGUI.canvas.ballarray.get(index).dir_y = -diff_y;// normalized_diff_y;
		System.out.println(diff_x);
		// System.out.println(normalized_diff_y);

	}

	public synchronized static void calcMinDirection(int agent_id) {

		int i;
		int index = 0;
		float minDis;
		minDis = 999999;
		float exp_min;
		float door_x;
		float door_y;
		//float goal_x = 0;
		//float goal_y = 0;
		
		
		if (EvacGUI.canvas.ballarray.size() < 1){
			return ;
		}
		
		for (int j = 0; j < EvacGUI.canvas.ballarray.size(); j++) {
			if (EvacGUI.canvas.ballarray.get(j).ball_id == agent_id)
				index = j;
		}
		
		float getX = EvacGUI.state.getCoordX(index);
		float getY = EvacGUI.state.getCoordY(index);

		for (i = 0; i < base_frame.listDoor.size(); i++) {
			door_x = base_frame.listDoor.get(i).door_center_x;
			door_y = base_frame.listDoor.get(i).door_center_y;

			exp_min = (door_x - getX) * (door_x - getX) + (door_y - getY) * (door_y - getY);
			if (exp_min < minDis) {
				minDis = exp_min;
				EvacGUI.canvas.ballarray.get(index).goal_x = door_x;
				EvacGUI.canvas.ballarray.get(index).goal_y = door_y;
			}
		}
		// define
		EvacGUI.state.change_direction(agent_id, EvacGUI.canvas.ballarray.get(index).goal_x, EvacGUI.canvas.ballarray.get(index).goal_y);
		minDis = 999999;

	}
	
	public synchronized void reCalcDirection(int agent_id){
		int index=-1;
		for (int i = 0; i < EvacGUI.canvas.ballarray.size(); i++) {
			if (EvacGUI.canvas.ballarray.get(i).ball_id == agent_id)
				index = i;
				break;
		}
		if(index==-1)
			return;
		
		EvacGUI.state.change_direction(agent_id, EvacGUI.canvas.ballarray.get(index).goal_x, EvacGUI.canvas.ballarray.get(index).goal_y);
	}

	public synchronized int collisionAvoid(int agent_id) {
		
		//matching the agent_id and real array index
		int index = -1;
		for (int i = 0; i < EvacGUI.canvas.ballarray.size(); i++) {
			if (EvacGUI.canvas.ballarray.get(i).ball_id == agent_id){
				index = i;
				break;
			}
		}
		if(index==-1){
			return 0;
			
		}
	    float x = canvas.ballarray.get(index).x_pos;
		float y = canvas.ballarray.get(index).y_pos;
		float otherx = 0;
		float othery = 0;
		if (canvas.ballarray.get(index).backup_dirx != -1) {
			canvas.ballarray.get(index).dir_x = canvas.ballarray.get(index).backup_dirx;
			canvas.ballarray.get(index).dir_y = canvas.ballarray.get(index).backup_diry;
			canvas.ballarray.get(index).backup_dirx=-1;
			canvas.ballarray.get(index).backup_diry=-1;
		}
		for (int i = 0; i < canvas.ballarray.size(); i++) {
			otherx = canvas.ballarray.get(i).x_pos;
			othery = canvas.ballarray.get(i).y_pos;
			x = x - otherx;
			y = y - othery;
			if (x < 0)
				x = -x;
			if (y < 0)
				y = -y;
			if (x + y < 10&&canvas.ballarray.get(index).backup_dirx == -1) {
				if (agent_id != canvas.ballarray.get(i).ball_id) {
					canvas.ballarray.get(index).backup_dirx = canvas.ballarray.get(index).dir_x;
					canvas.ballarray.get(index).backup_diry = canvas.ballarray.get(index).dir_y;
					canvas.ballarray.get(index).dir_x =  -canvas.ballarray.get(index).dir_x;
					canvas.ballarray.get(index).dir_y =  -canvas.ballarray.get(index).dir_y;
				}
			}

		}
		return 1;
	}

}
