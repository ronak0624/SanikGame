import java.awt.Color;
import java.awt.Graphics;

public class Block {
	public int X;
	public int Y;
	public int HEIGHT;
	public int WIDTH;
	
	public Block(int a, int b, int c, int d){
		X = a;
		Y = b;
		HEIGHT = d;
		WIDTH = c;
	}
	
	public void drawBlock(Color couleur, Graphics g){
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
		return there;
	}
	
	public void checkCollidingPlayer(int O_X, int O_Y, int O_WIDTH, int O_HEIGHT){
		boolean above = false;
		boolean below = false;
		boolean left = false;
		boolean right = false;
		
		int A = X + WIDTH;
		int B = Y + HEIGHT;
		
		int O_A = (O_X + O_WIDTH);
		int O_B = (O_Y + O_HEIGHT);
		
		int X_DIF = 0;
		int Y_DIF = 0;

		if((((O_A) > X && O_X < X) || ((O_A) > A && O_X < A)) || ((O_X + (O_WIDTH / 2) > X) && (O_X + (O_WIDTH / 2) < A))){
			if(O_Y + (O_HEIGHT / 2) < Y + (HEIGHT / 2)){
				above = true;
			}else{
				below = true;
			}
			if((((O_B) > Y && O_Y < Y) || ((O_B) > B && O_Y < B)) || ((O_Y + (O_HEIGHT / 2) > Y) && (O_Y + (O_HEIGHT / 2) < B))){
				if(O_X + (O_WIDTH / 2) < X + (WIDTH / 2)){
					left = true;
				}else{
					right = true;
				}
				
				if(Math.abs(O_A - X) > Math.abs(A - O_X)){
					X_DIF = Math.abs(A - O_X);
				}else{
					X_DIF = Math.abs(O_A - X);
				}
				
				if(Math.abs(O_B - Y) > Math.abs(B - O_Y)){
					Y_DIF = Math.abs(B - O_Y);
				}else{
					Y_DIF = Math.abs(O_B - Y);
				}
				
				
				if(above && right){
					if(Y_DIF > X_DIF){
						Player.leftallowed = false;
					}else{
						Player.downallowed = false;
					}
					if(Player.x + Player.width < Space.map[Space.player_x][Space.player_y].R_X + Space.map[Space.player_x][Space.player_y].R_WIDTH){
						Player.rightallowed = true;
					}
					if(Player.y > Space.map[Space.player_x][Space.player_y].R_Y){
						Player.upallowed = true;
					}
				}else if(above && left){
					if(Y_DIF > X_DIF){
						Player.rightallowed = false;
					}else{
						Player.downallowed = false;
					}
					if(Player.x > Space.map[Space.player_x][Space.player_y].R_X){
						Player.leftallowed = true;
					}
					if(Player.y > Space.map[Space.player_x][Space.player_y].R_Y){
						Player.upallowed = true;
					}
				}else if(below && right){
					if(Y_DIF > X_DIF){
						Player.leftallowed = false;
					}else{
						Player.upallowed = false;
					}
					if(Player.x + Player.width < Space.map[Space.player_x][Space.player_y].R_X + Space.map[Space.player_x][Space.player_y].R_WIDTH){
						Player.rightallowed = true;
					}
					if(Player.y + Player.height < Space.map[Space.player_x][Space.player_y].R_Y + Space.map[Space.player_x][Space.player_y].R_HEIGHT){
						Player.downallowed = true;
					}
					
				}else if(below && left){
					if(Y_DIF > X_DIF){
						Player.rightallowed = false;
					}else{
						Player.upallowed = false;
					}
					if(Player.x > Space.map[Space.player_x][Space.player_y].R_X){
						Player.leftallowed = true;
					}
					if(Player.y + Player.height < Space.map[Space.player_x][Space.player_y].R_Y + Space.map[Space.player_x][Space.player_y].R_HEIGHT){
						Player.downallowed = true;
					}
				}
			}
		}
	}
	
	public void checkCollidingEnemy(int O_X, int O_Y, int O_WIDTH, int O_HEIGHT, Enemy victor){
		boolean above = false;
		boolean below = false;
		boolean left = false;
		boolean right = false;
		
		int A = X + WIDTH;
		int B = Y + HEIGHT;
		
		int O_A = (O_X + O_WIDTH);
		int O_B = (O_Y + O_HEIGHT);
		
		int X_DIF = 0;
		int Y_DIF = 0;

		if((((O_A) > X && O_X < X) || ((O_A) > A && O_X < A)) || ((O_X + (O_WIDTH / 2) > X) && (O_X + (O_WIDTH / 2) < A))){
			if(O_Y + (O_HEIGHT / 2) < Y + (HEIGHT / 2)){
				above = true;
			}else{
				below = true;
			}
			if((((O_B) > Y && O_Y < Y) || ((O_B) > B && O_Y < B)) || ((O_Y + (O_HEIGHT / 2) > Y) && (O_Y + (O_HEIGHT / 2) < B))){
				if(O_X + (O_WIDTH / 2) < X + (WIDTH / 2)){
					left = true;
				}else{
					right = true;
				}
				
				if(Math.abs(O_A - X) > Math.abs(A - O_X)){
					X_DIF = Math.abs(A - O_X);
				}else{
					X_DIF = Math.abs(O_A - X);
				}
				
				if(Math.abs(O_B - Y) > Math.abs(B - O_Y)){
					Y_DIF = Math.abs(B - O_Y);
				}else{
					Y_DIF = Math.abs(O_B - Y);
				}
				
				
				if(above && right){
					if(Y_DIF > X_DIF){
						victor.leftallowed = false;
					}else{
						victor.downallowed = false;
					}
					
					victor.rightallowed = true;
					victor.upallowed = true;
				}else if(above && left){
					if(Y_DIF > X_DIF){
						victor.rightallowed = false;
					}else{
						victor.downallowed = false;
					}
					victor.leftallowed = true;
					victor.upallowed = true;
				}else if(below && right){
					if(Y_DIF > X_DIF){
						victor.leftallowed = false;
					}else{
						victor.upallowed = false;
					}
					victor.rightallowed = true;
					victor.downallowed = true;
				}else if(below && left){
					if(Y_DIF > X_DIF){
						victor.rightallowed = false;
					}else{
						victor.upallowed = false;
					}
					victor.downallowed = true;
					victor.leftallowed = true;
				}
			}
		}
	}
}
