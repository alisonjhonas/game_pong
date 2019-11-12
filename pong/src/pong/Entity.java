package pong;

import java.awt.Graphics;

public interface Entity {
	
	public void tick();
	
	public void render(Graphics graphics);
}
