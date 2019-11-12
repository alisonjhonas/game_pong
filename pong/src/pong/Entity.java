package pong;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity {
	
	public double x, y;
	public int width, height;
	public Color color;
	
	public abstract void tick();
	
	public void render(Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect((int)x, (int)y, width, height);
	}
}
