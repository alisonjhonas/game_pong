package pong;

import java.awt.Color;

public class Enemy extends Entity {
	
	public Enemy(double x, double y){
		this.x = x;
		this.y = y;
		width = 40;
		height = 5;
		this.color = Color.RED;
	}
	
	@Override
	public void tick() {
		
		x += (Game.ball.x - x) * 0.07; 
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}
		else if(x < 0) {
			x = 0;
		}
	}

}
