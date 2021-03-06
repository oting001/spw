package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class BulletBoss extends Sprite{
	public static final int Y_TO_DIE = 600;
	
	
	private int exp = 8;
	private int step = 12;
	private boolean alive = true;
	
	
	public BulletBoss(int x, int y) {
		super(x, y, 5, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void isDie(){
		alive = false;		
	}
	
	public int getExp(){
		return exp;
	}
}