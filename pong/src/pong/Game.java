package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener{

	
	private static final long serialVersionUID = 1L;
	public 	static int WIDTH = 240;
	public 	static int HEIGHT = 120;
	public 	static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	List<Entity> entities;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		entities = new ArrayList<Entity>();
		this.addKeyListener(this);
		setFocusable(true);
		double centerWidth = 100d;
		player = new Player(centerWidth, HEIGHT-5 );
		enemy = new Enemy(centerWidth, 0);
		ball = new Ball(centerWidth+20d, HEIGHT/2);
		entities.add(player);
		entities.add(enemy);
		entities.add(ball);
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new Thread(game).start();
	}
	
	public void tick() {
//		for(Entity entity : entities) {
//			entity.tick();
//		}
		player.tick();
		ball.tick();
		enemy.tick();
	}
	
	public void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics graphics = layer.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
//		for(Entity entity : entities) {
//			entity.render(graphics);
//		}
		player.render(graphics);
		ball.render(graphics);
		enemy.render(graphics);
		
		graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(layer,0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		bufferStrategy.show();
		
		
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
