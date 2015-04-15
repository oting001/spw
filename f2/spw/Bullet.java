package f2.spw;


import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_DIE = 10;
	
	private int step = 14;
	private boolean alive= true;
	
	public Bullet(int x, int y ,int width ,int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
	}

	public void proceed(){
		y -= step;
		if(y <= Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void isDie(){
		alive = false;
	}
}