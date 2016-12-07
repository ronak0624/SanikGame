import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Projectile{

    public int x;
    public int y;
    private final int dx;
    private final int dy;
    private final int SPEED;
    protected Image image;
    protected int width;
    protected int height;
    

    public Projectile(int x_ADS, int y_ADS, double d, double e, int speed) {
        dx = (int) d;
        dy = (int) e;
        SPEED = speed;
        x = x_ADS;
        y = y_ADS;
        
        initProjectile();
    }
    
    private void initProjectile() {
        
        loadImage(Main.class.getResource("/resources/projectile.png"));
        getImageDimensions();        
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(java.net.URL imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public void move() {
        x+= dx * SPEED;
        y+= dy * SPEED;
    }

	public void drawProjectile(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}