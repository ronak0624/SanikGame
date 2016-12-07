import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Room {
	public int R_HEIGHT;
	public int R_WIDTH;
	public int R_X;
	public int R_Y;
	public int numDOORS = 0;
	public Color borderColor = Color.GRAY;
	public Color blockColor = Color.LIGHT_GRAY;
	public String lastDir = "NONE";
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Key> keys = new ArrayList<Key>();
	
	Door[] doors = new Door[10];
	
	public Room(int x, int y, int ayy, int lmao){
		R_HEIGHT = lmao;
		R_WIDTH = ayy;
		R_X = x;
		R_Y = y;
	}
	
	public void addDoor(int x, int y, int width, int height, String dir, Color couleur){
		if(numDOORS >= doors.length){
			System.out.println("TOO MANY DOORS");
		}else{
			doors[numDOORS] = new Door(x, y, width, height, dir, couleur);
			numDOORS++;
		}
	}
	
	public void addObstacle(int a, int b, int c, int d){
		blocks.add(new Block(a, b, c, d));
	}
	
	public void addEnemy(int a, int b, int c, int d, java.net.URL name){
		enemies.add(new Enemy(a, b, c, d, name));
	}
	
	public void addKey(int a, int b){
		keys.add(new Key(a, b));
	}
	
	public void drawRoom(Graphics g){
		g.setColor(borderColor);
		g.fillRect(0, 0, R_X, (R_HEIGHT + R_Y));
		g.fillRect(0, 0, (R_WIDTH + R_X), R_Y);
		g.fillRect(R_WIDTH + R_X, 0, R_X, R_HEIGHT + R_Y * 2);
		g.fillRect(0, R_HEIGHT + R_Y, R_WIDTH + R_X * 2, R_Y);
		
		for(int i = 0; i < blocks.size(); i++){
			//System.out.println("Checking");
			blocks.get(i).drawBlock(blockColor, g);
		}
		
		for(int i = 0; i < numDOORS; i++){
			//System.out.println("Checking");
			doors[i].drawDoor(g);
		}
		
		for(int i = 0; i < keys.size(); i++){
			//System.out.println("Checking");
			keys.get(i).drawKey(g);
		}
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).drawEnemy(g);
			for(int j = 0; j < enemies.get(i).fireballs.size(); j++){
				enemies.get(i).fireballs.get(j).drawProjectile(g);
			}
		
		}
		
		for(int i = 0; i < Player.BOOLETS.size(); i++){
			Player.BOOLETS.get(i).drawProjectile(g);
		}
	}
	
	public boolean checkExit(){
		boolean ayy = false;
		
		for(int i = 0; i < numDOORS; i++){
			boolean WHY = doors[i].isColliding(Player.x, Player.y, Player.width, Player.height);
			
			if(WHY){
				ayy = true;
				lastDir = doors[i].getDir();
			}
		}
		return ayy;
	}
	
	public String getExit(){
		return lastDir;
	}
	
	public void checkCollidingPlayer(){
		if(Player.x < R_X){
			Player.leftallowed = false;
		}else{
			Player.leftallowed = true;
		}
		if(Player.x + Player.width > R_X + R_WIDTH){
			Player.rightallowed = false;
		}else{
			Player.rightallowed = true;
		}
		if(Player.y < R_Y){
			Player.upallowed = false;
		}else{
			Player.upallowed = true;
		}
		if(Player.y + Player.height > R_Y + R_HEIGHT){
			Player.downallowed = false;
		}else{
			Player.downallowed = true;
		}
		
		for(int i = 0; i < blocks.size(); i++){
			//System.out.println("Checking");
			
			blocks.get(i).checkCollidingPlayer(Player.x, Player.y, Player.width, Player.height);
		}
	}
	
	public void checkCollidingKey(){
		for(int i = 0; i < keys.size(); i++){
			if(Player.isCollidingKey(keys.get(i))){
				Space.YOUWIN = true;
			}
		}
	}
	
	public void checkCollidingEnemy(){
		for(int victor = 0; victor < enemies.size(); victor++){
			if(Player.isCollidingEnemy(enemies.get(victor))){
				// Space.GAMEOVER = true;
				Player.hp -= 2;
			}
			if(enemies.get(victor).x < R_X){
				enemies.get(victor).leftallowed = false;
			}else{
				enemies.get(victor).leftallowed = true;
			}
			if(enemies.get(victor).x + enemies.get(victor).width > R_X + R_WIDTH){
				enemies.get(victor).rightallowed = false;
			}else{
				enemies.get(victor).rightallowed = true;
			}
			if(enemies.get(victor).y < R_Y){
				enemies.get(victor).upallowed = false;
			}else{
				enemies.get(victor).upallowed = true;
			}
			if(enemies.get(victor).y + enemies.get(victor).height > R_Y + R_HEIGHT){
				enemies.get(victor).downallowed = false;
			}else{
				enemies.get(victor).downallowed = true;
			}
			for(int i = 0; i < blocks.size(); i++){
				//System.out.println("Checking");
				
					blocks.get(i).checkCollidingEnemy(enemies.get(victor).x, enemies.get(victor).y, enemies.get(victor).width, enemies.get(victor).height, enemies.get(victor));
			}
		}
	}

	public void moveEnemies() {
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).move();
			for(int j = 0; j < Player.BOOLETS.size(); j++){
				if(enemies.size() > 0){
					Rectangle baddie = new Rectangle(enemies.get(i).x, enemies.get(i).y, enemies.get(i).width, enemies.get(i).height);
					Rectangle hotlead = new Rectangle(Player.BOOLETS.get(j).x, Player.BOOLETS.get(j).y, Player.BOOLETS.get(j).width, Player.BOOLETS.get(j).height);
					if(baddie.intersects(hotlead)){
						enemies.remove(i);
					}
				}
			}
		}
	}
	
	/* public void makeEnemiesShoot(){
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).shootRandom();
		}
	}*/
	
	public void moveProjectiles(){
		for(int i = 0; i < Player.BOOLETS.size(); i++){
			Player.BOOLETS.get(i).move();
			for(int j = 0; j < blocks.size(); j++){
				if(Player.BOOLETS.size() > i){
					if(blocks.get(j).isColliding(Player.BOOLETS.get(i).x, Player.BOOLETS.get(i).y, Player.BOOLETS.get(i).width, Player.BOOLETS.get(i).height)){
						Player.BOOLETS.remove(i);
					}
				}
			}
		}
		for(int i = 0; i < enemies.size(); i++){
			for(int j = 0; j < enemies.get(i).fireballs.size(); j++){
				enemies.get(i).fireballs.get(j).move();
				for(int a = 0; a < blocks.size(); a++){
					if(enemies.get(i).fireballs.size() > i){
						if(blocks.get(a).isColliding(enemies.get(i).fireballs.get(j).x, enemies.get(i).fireballs.get(j).y, enemies.get(i).fireballs.get(j).width, enemies.get(i).fireballs.get(j).height)){
							System.out.println("ERROR?");
							enemies.get(i).fireballs.remove(j);
						}
					}
				}
			}
		}
	}
}
