package EvacGUI;

public class EscapeDoor {
	
	float door_x, door_y, door_center_x, door_center_y;
	int door_width, door_height;
	
	public EscapeDoor(float _door_x, float _door_y, int _door_width, int _door_height) {
		// TODO Auto-generated constructor stub
		door_x = _door_x; 
		door_y = _door_y;
		door_width = _door_width;
		door_height = _door_height;
		door_center_x = door_x + (door_width / 2);
		door_center_y = door_y + (door_height / 2);
		System.out.printf("x : %f, y: %f, w: %d, h: %d, cx: %f, cy: %f\n", door_x, door_y, door_width, door_height, 
				door_center_x, door_center_y);
	}
}
