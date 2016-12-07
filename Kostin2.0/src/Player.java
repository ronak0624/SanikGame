import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {

    private int dx;
    private int dy;
    static int x;
    static int y;
    private Image image;
    private int speed = 5;
    static int width;
    static int height;
    static int accel = 1;
    
    private static boolean downpress = false;
    private static boolean uppress = false;
    private static boolean leftpress = false;
    private static boolean rightpress = false;
    
    public static boolean downallowed = true;
    public static boolean upallowed = true;
    public static boolean leftallowed = true;
    public static boolean rightallowed = true;
    public static int hp = 250;
    
    public URL player = Main.class.getResource("/resources/player.png.gif");
    
    static ArrayList<Projectile> BOOLETS = new ArrayList<Projectile>();

    public Player() {
        
        initPlayer();
    }
    
    private void initPlayer() {
        
        ImageIcon ii = new ImageIcon(player);
        image = ii.getImage();
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        x = 40;
        y = 60;        
    }
    
    public void getPlayerDimensions() {
    	
    	width = image.getWidth(null);
    	height = image.getHeight(null);
    }

    public void move() {
    	if(leftpress){
    		if(dx > -speed){
    			dx -= accel;
    		}
    	}
    	if(rightpress){
    		if(dx < speed){
    			dx += accel;
    		}
    	}
    	if(uppress){
    		if(dy > -speed){
    			dy -= accel;
    		}
    	}
    	if(downpress){
    		if(dy < speed){
    			dy += accel;
    		}
    	}
    	if(!leftallowed && dx < 0){
    		dx = 0;
    	}
    	if(!rightallowed && dx > 0){
    		dx = 0;
    	}
    	if(!upallowed && dy < 0){
    		dy = 0;
    	}
    	if(!downallowed && dy > 0){
    		dy = 0;
    	}
        x += dx;
        y += dy;
        /*
        if (x < 1) {
        	x = 1;
        }
        
        if (x > Main.B_WIDTH - width) {
        	x = Main.B_WIDTH - width;
        }
        
        if (y < 1) {
        	y = 1;
        }
        
        if (y > Main.B_HEIGHT - height) {
        	y = Main.B_HEIGHT - height;
        }*/
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = -speed;
            leftpress = true;
        }

        if (key == KeyEvent.VK_D) {
            dx = speed;
            rightpress = true;
        }

        if (key == KeyEvent.VK_W) {
            dy = -speed;
            uppress = true;
        }

        if (key == KeyEvent.VK_S) {
            dy = speed;
            downpress = true;
        }
        
        if (key == KeyEvent.VK_SPACE) {
        	if(Space.map[Space.player_x][Space.player_y].doors[0].isColliding(Player.x, Player.y, Player.width, Player.height)){
        	System.out.println(Space.map[Space.player_x][Space.player_y].doors[0].isColliding(Player.x, Player.y, Player.width, Player.height));
        	}
        }
        
    }
    
    public static boolean isCollidingEnemy(Enemy e){
    	Rectangle RE = new Rectangle(e.x, e.y, e.width, e.height);
    	Rectangle RP = new Rectangle(Player.x, Player.y, Player.width, Player.height);
    	
    	return (RE.intersects(RP));
    }
    
    public static boolean isCollidingKey(Key e){
    	Rectangle RK = new Rectangle(e.x, e.y, e.width, e.height);
    	Rectangle RP = new Rectangle(Player.x, Player.y, Player.width, Player.height);
    	
    	return (RK.intersects(RP));
    }
    
    public void mousePressed(MouseEvent e){
    	int button = e.getButton();
    	double hypotenuse = (int) Math.sqrt(Math.abs((e.getX() - (x + (width / 2)))^2 + (e.getY() - (y + (height / 2)))^2));
    	
    	int cos = e.getX() - (x + (width / 2));
    	int sin = e.getY() - (y + (height / 2));
    	
    	System.out.println("cos is " + cos + ", sin is " + sin + ", and hypotenus is " + hypotenuse);
    	if (button == MouseEvent.BUTTON1){
    		BOOLETS.add(new Projectile(x + width / 2, y + height / 2, cos/hypotenuse,   sin / hypotenuse, 1));
    	}
    	//Space.map[Space.player_x][Space.player_y].makeEnemiesShoot();
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
            leftpress = false;
            if(rightpress){
            	dx = speed;
            }
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
            rightpress = false;
            if(leftpress){
            	dx = -speed;
            }
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
            uppress = false;
            if(downpress){
            	dy = speed;
            }
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
            downpress = false;
            if(uppress){
            	dy = -speed;
            }
        }
    }
}
