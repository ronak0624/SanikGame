import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

public class Enemy {
	
	private Image image;
	int xspeed = 5;
	int yspeed = 2;
	public boolean vis;
	public int x;
	public int y;
	
	public boolean downallowed = true;
    public boolean upallowed = true;
    public boolean leftallowed = true;
    public boolean rightallowed = true;
	public int width;
	public int height;
	public java.net.URL imageName;
	ArrayList<Projectile> fireballs = new ArrayList<Projectile>();
	
	public Enemy(int xcoord, int ycoord, int speedx, int speedy, java.net.URL name){
		x = xcoord;
		y = ycoord;
		xspeed = speedx;
		yspeed = speedy;
		imageName = name;
		
	}
	
	public void drawEnemy(Graphics g){
		g.drawImage(getEnemyImage(), x, y, null);
	}
	
	/* public void shootRandom(){
		int angle = (int) (Math.random() * 360);
		System.out.println(angle);
		fireballs.add(new Projectile(x + width / 2, y + height / 2, 4 * Math.cos(angle * Math.PI / 180), 4 *  Math.sin(angle * Math.PI / 180), 1));
	} */
	
	public void move(){
		x += xspeed;
		y += yspeed;
		
		/*if (x > Board.ROOM_WIDTH + Board.ROOM_X - 24 || !rightallowed){
			xspeed = -5;
		}
		if (x < 0 || !leftallowed){
			xspeed = 5;
		}
		if (y < 0 || !upallowed){
			yspeed = 5;
		}
		if (y > Board.ROOM_HEIGHT + Board.ROOM_Y - 24 || !downallowed){
			yspeed = -5;
		}*/
		
		if(!leftallowed && xspeed < 0){
    		xspeed *= -1;
    	}
    	if(!rightallowed && xspeed > 0){
    		xspeed *= -1;
    	}
    	if(!upallowed && yspeed < 0){
    		yspeed *= -1;
    	}
    	if(!downallowed && yspeed > 0){
    		yspeed *= -1;
    	}
        x += xspeed;
        y += yspeed;
	}
	
    private Image getEnemyImage() {
        
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        return image;
    }
}
