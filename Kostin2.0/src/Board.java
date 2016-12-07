import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener 
{
	public static int player_x = 2;
	public static int player_y = 2;
	public static int enemy_x;
	public static int enemy_y;
    private Timer timer;
    private Player player;
    private Key key;
   
    private final int DELAY = 10;
    public final static int MAP_WIDTH = 5;
    public final static int MAP_HEIGHT = 5;
    public final static int ROOM_X = 10;
    public final static int ROOM_Y = 10;
    public final static int ROOM_WIDTH = 1180;
    public final static int ROOM_HEIGHT = 650;
    public final static int DOOR_WIDTH = 100;
    public final static int DOOR_OVERLAP = 10;
    public final static int ENEMIES_FIRE_RATE = 50;
    public static boolean GAMEOVER;
    public static boolean YOUWIN;
    public static int ENEMIES_FIRE_TIMER = 0;
    public static Color door_color = Color.ORANGE;
    
    public static Room[][] map = new Room[MAP_WIDTH][MAP_HEIGHT];

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        makeMap();
        
        player = new Player();
        
        timer = new Timer(DELAY, this);
        timer.start();        
    }
    
    private void makeMap(){
    	for(int i = 0;i < MAP_WIDTH;i++){
        	for(int j = 0;j < MAP_HEIGHT;j++){
        		map[i][j] = new Room(ROOM_X, ROOM_Y, ROOM_WIDTH, ROOM_HEIGHT);
        	}
        }
    	
    	map[2][1].addObstacle(200, 200, 200, 100);
    	map[2][2].addObstacle(200, 200, 100, 200);
    	map[2][3].addObstacle(300, 300, 200, 200);
    	map[2][4].addObstacle(200, 200, 300, 100);
    	map[2][4].addObstacle(200, 200, 100, 200);
    	map[3][1].addObstacle(300, 300, 200, 200);
    	map[3][2].addObstacle(200, 200, 300, 100);
    	map[3][3].addObstacle(200, 200, 100, 200);
    	map[3][4].addObstacle(300, 300, 200, 200);
    	map[3][4].addObstacle(200, 200, 300, 100);
    	map[4][1].addObstacle(200, 200, 100, 200);
    	map[4][2].addObstacle(300, 300, 200, 200);
    	map[4][3].addObstacle(200, 200, 300, 100);
    	map[4][4].addObstacle(200, 200, 100, 200);
    	map[4][4].addObstacle(300, 300, 200, 200);
    	map[1][1].addObstacle(100, 500, 200, 100);
    	
    
    	map[2][1].addEnemy(200, 200, 2, 1, "bobby.png");
    	map[2][2].addEnemy(200, 200, 1, 2, "link.png");
    	map[2][3].addEnemy(300, 300, 2, 2, "link.png");
    	map[2][4].addEnemy(200, 200, 3, 1, "enemy.png.gif");
    	map[2][4].addEnemy(200, 200, 1, 2, "enemy.png.gif");
    	map[3][1].addEnemy(300, 300, 2, 2, "bobby.png");
    	map[3][2].addEnemy(200, 200, 3, 1, "link.png");
    	map[3][3].addEnemy(200, 200, 1, 2, "bobby.png");
    	map[3][4].addEnemy(300, 300, 2, 2, "bobby.png");
    	map[3][4].addEnemy(200, 200, 3, 1, "link.png");
    	map[4][1].addEnemy(200, 200, 1, 2, "enemy.png.gif");
    	map[4][2].addEnemy(300, 300, 2, 2, "bobby.png");
    	map[4][3].addEnemy(200, 200, 3, 1, "link.png");
    	map[4][4].addEnemy(200, 200, 1, 2, "enemy.png.gif");
    	map[4][4].addEnemy(300, 300, 2, 2, "link.png");
    	
    	map[1][1].addKey(20, 600);
    	
    	for(int i = 0;i < MAP_WIDTH;i++){
        	for(int j = 0;j < MAP_HEIGHT;j++){
        		if(j < MAP_WIDTH - 1){
        			map[i][j].addDoor((ROOM_X + ROOM_WIDTH - DOOR_WIDTH) / 2, (ROOM_Y + ROOM_HEIGHT) - DOOR_OVERLAP, DOOR_WIDTH, ROOM_Y + DOOR_OVERLAP, "down", door_color);
        		}
        		if(i < MAP_HEIGHT - 1){
        			map[i][j].addDoor((ROOM_X + ROOM_WIDTH - DOOR_OVERLAP), (ROOM_Y + ROOM_HEIGHT - DOOR_WIDTH) / 2, ROOM_X + DOOR_OVERLAP, DOOR_WIDTH, "right", door_color);
        		}
        		if(i > 0){
        			map[i][j].addDoor(0, (ROOM_Y + ROOM_HEIGHT - DOOR_WIDTH) / 2, ROOM_X + DOOR_OVERLAP, DOOR_WIDTH, "left", door_color);
        		}
        		if(j > 0){
        			map[i][j].addDoor((ROOM_X + ROOM_WIDTH - DOOR_WIDTH) / 2, 0, DOOR_WIDTH, ROOM_Y + DOOR_OVERLAP, "up", door_color);
        		}
        	}
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        map[player_x][player_y].drawRoom(g);
        if(Player.hp <= 0){
        	drawGameOver(g2d);
        	timer.stop();
        }
        if(YOUWIN){
        	drawYouWin(g2d);
        	timer.stop();
        }
        g.setColor(Color.WHITE);
        g.drawString("Room: (" + player_x + ", "+ player_y + ")", 30, 690);
        g.drawString("HP", 880, 690);
        g.setColor(Color.RED);
        g.fillRect(900, 680, (int) (Player.hp / 2.5), 10);
        g.drawString("Vladswag", Player.x, Player.y);
        // System.out.println(GAMEOVER);
    }
    
    private void drawYouWin(Graphics g) {

        String msg = "YOU WIN";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Main.B_WIDTH - fm.stringWidth(msg)) / 2,
                Main.B_HEIGHT / 2);
    }
    
    private void drawGameOver(Graphics g) {

        String msg = "GAME OVER";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Main.B_WIDTH - fm.stringWidth(msg)) / 2,
                Main.B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map[player_x][player_y].checkCollidingPlayer();
        map[player_x][player_y].checkCollidingEnemy();
        map[player_x][player_y].checkCollidingKey();
    	
        player.move();
        map[player_x][player_y].moveEnemies();
        map[player_x][player_y].moveProjectiles();
        /* if(ENEMIES_FIRE_TIMER >= ENEMIES_FIRE_RATE){
        	map[player_x][player_y].makeEnemiesShoot();
        	ENEMIES_FIRE_TIMER = 0;
        } */
        ENEMIES_FIRE_TIMER++;
        if(map[player_x][player_y].checkExit()){
        	if(map[player_x][player_y].getExit() == "down"){
        		Player.x = map[player_x][player_y].R_X + map[player_x][player_y].R_WIDTH / 2;
        		Player.y = map[player_x][player_y].R_Y + 50;
        		player_y++;
        		Player.BOOLETS.clear();
        		
        	}else if(map[player_x][player_y].getExit() == "up"){
        		Player.x = map[player_x][player_y].R_X + map[player_x][player_y].R_WIDTH / 2;
        		Player.y = map[player_x][player_y].R_HEIGHT - map[player_x][player_y].R_Y * 2 - 50;
        		player_y--;
        		Player.BOOLETS.clear();
        		
        	}else if(map[player_x][player_y].getExit() == "right"){
        		Player.y = map[player_x][player_y].R_Y + map[player_x][player_y].R_HEIGHT / 2;
        		Player.x = map[player_x][player_y].R_X + 50;
        		player_x++;
        		Player.BOOLETS.clear();
        		
        	}else if(map[player_x][player_y].getExit() == "left"){
        		Player.y = map[player_x][player_y].R_Y + map[player_x][player_y].R_HEIGHT / 2;
        		Player.x = map[player_x][player_y].R_WIDTH - map[player_x][player_y].R_X * 2 - 50;
        		player_x--;
        		Player.BOOLETS.clear();
        		
        	}else{
        		System.out.println("NONE");
        	}
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
    
    private class MAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            player.mousePressed(e);
        }
    }
    
    
    
    
}
