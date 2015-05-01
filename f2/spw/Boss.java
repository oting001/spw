package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Boss extends Sprite{
	public static final int Y_TO_FADE = 450;
	public static final int Y_TO_DIE = 600;
	
	private int exp = 1000;
	private int healt = 1;
	private int step = 5;
	private boolean alive = true;
	
	public Boss(){
		super(100, 100, 30 , 20);
	}
	
	public Boss(int healt) {
		this();
		this.healt = healt;
		
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
		
		if(x > 380){
			x -= step;
		}
		else if(x < 20){
			x += step;
		}
		else if(Math.random() < 0.5){
			x += step;
		}
		else{
			x -= step;
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
