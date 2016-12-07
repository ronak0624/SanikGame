import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Key {
	
	private Image image;
	int xspeed = 5;
	int yspeed = 5;

	public int x;
	public int y;

	public int width;
	public int height;


	
	public Key(int xcoord, int ycoord){
		x = xcoord;
		y = ycoord;
		
	}
	
	public void drawKey(Graphics g){
		g.drawImage(getKeyImage(), x, y, null);
	}
	

	
	public void move(){
		x += xspeed;
		y += yspeed;
		
		
	}
	
    private Image getKeyImage() {
        
        ImageIcon ii = new ImageIcon(Main.class.getResource("/resources/Key.png"));
        image = ii.getImage();
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        return image;
    }
}