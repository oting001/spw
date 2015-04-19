package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class EnemyE extends Sprite{
	public static final int Y_TO_FADE = 450;
	public static final int Y_TO_DIE = 600;
	
	private int exp = 1000;
	private int healt = 2;
	private int step = 12;
	private boolean alive = true;
	
	public EnemyE(int x, int y) {
		super(x, y, 5 , 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.BLUE);
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
	public void takeDmg(){
		if(healt <= 1){
			isDie();
		}
		else{
			healt--;
		}
	}
	public void takeDmg(int dmg){
		healt -= dmg;
		if(healt <= 0){
			isDie();
		}
	}
}
