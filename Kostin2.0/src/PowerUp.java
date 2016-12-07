import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerUp extends Coord {
	
	private Image image;
	int speed = 5;
	
	public PowerUp(int x, int y){
		super(x, y);
	}
	
	public void drawPowerUp(Graphics2D g2d){
		g2d.drawImage(getEnemyImage(), x, y, null);
	}
	
	public void move(){
		x += speed;
		y += speed;
				
		if (x > Main.B_WIDTH - 24){
			speed = -5;
		}
		if (x < 0){
			speed = 5;
		}
		if (y < 0){
			speed = 5;
		}
		if (y > Main.B_HEIGHT - 24){
			speed = -5;
		}
	}
	
    private Image getEnemyImage() {
        
        ImageIcon ii = new ImageIcon("powerup.png");
        image = ii.getImage();
        return image;
    }
}
