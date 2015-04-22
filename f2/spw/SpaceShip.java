package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import static java.lang.Math.pow;

public class SpaceShip extends Sprite{

	int step = 8;
	private long exp = 0;
	private int level = 1;
	private int MAX_LEVEL = 10;
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);	
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void moveUp(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}
	public void expUP(int e){
		exp += e;
		if(exp > (5*level) + pow(5 , level)){
			levelUp();
		}
	}
	public long getEXP(){
		return exp;
	}
	
	public void levelUp(){
		if(level < MAX_LEVEL){
			level++;
		}
		
	}
	public void levelDown(){
		if(level > 1){
			level--;
		}				
	}
	public int getLevel(){
		return level;
	}
}
