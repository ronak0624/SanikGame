import java.awt.Color;
import java.awt.Graphics;

public class Door {
	final int X;
	final int Y;
	final int WIDTH;
	final int HEIGHT;
	final String DIR;
	final Color couleur;
	
	public Door(int x, int y, int length, int height, String dir, Color yeet){
		X = x;
		Y = y;
		WIDTH = length;
		HEIGHT = height;
		DIR = dir;
		couleur = yeet; 
	}
	
	public void drawDoor(Graphics g){
		g.setColor(couleur);
		g.fillRect(X, Y, WIDTH, HEIGHT);
	}
	
	public boolean isColliding(int O_X, int O_Y, int O_WIDTH, int O_HEIGHT){
		boolean there = false;
		
		int A = X + WIDTH;
		int B = Y + HEIGHT;
		
		int O_A = (O_X + O_WIDTH);
		int O_B = (O_Y + O_HEIGHT);
		
		if((((O_A) > X && O_X < X) || ((O_A) > A && O_X < A)) || ((O_X + (O_WIDTH / 2) > X) && (O_X + (O_WIDTH / 2) < A))){
			if((((O_B) > Y && O_Y < Y) || ((O_B) > B && O_Y < B)) || ((O_Y + (O_HEIGHT / 2) > Y) && (O_Y + (O_HEIGHT / 2) < B))){
				there = true;
			}
		}
		
		if(there){
			System.out.println(DIR);
		}
		return there;
	}
	
	public String getDir(){
		return DIR;
	}
}