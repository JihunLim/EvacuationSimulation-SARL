package EvacGUI.Behaviors;
import EvacGUI.canvas.ball;

public class state {

public static int javatest = 100;

	public int getCoordX(int i){
		ball temp_ball = EvacGUI.canvas.ballarray.get(i);
		return temp_ball.x_pos;
	}
	
	public int getCoordY(int i){
		ball temp_ball = EvacGUI.canvas.ballarray.get(i);
		return temp_ball.y_pos;
	}
	
	
}