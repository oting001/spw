package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	private ArrayList<EnemyE> enemies2 = new ArrayList<EnemyE>();

	private SpaceShip v;	
	
	private Timer timer;
	
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				generateBullet();
				process();
				
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
		EnemyE e2 = new EnemyE((int)(Math.random()*390), 30);
		gp.sprites.add(e2);
		enemies2.add(e2);
	}
	
	private void generateBullet(){
		Bullet bs = new Bullet( v.x + (v.width/2) - 1,v.y, 1+v.getLevel(),6+(int)(v.getLevel()/2));
	    gp.sprites.add(bs);
		bullets.add(bs);
	}
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();		
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		Iterator<EnemyE> e_iter2 = enemies2.iterator();
		while(e_iter2.hasNext()){
			EnemyE e = e_iter2.next();
			e.proceed();		
			if(!e.isAlive()){
				e_iter2.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		
		Iterator<Bullet> bullet_iter = bullets.iterator();
		while(bullet_iter.hasNext()){
			Bullet b = bullet_iter.next();
			b.proceed();
			if(!b.isAlive()){
				bullet_iter.remove();
				gp.sprites.remove(b);
				bullets.remove(b);
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
		processBullet();
	}
	
	private void processBullet(){
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double br;
		
		for(Enemy e : enemies){
			er = e.getRectangle();
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(er.intersects(br)){
					v.expUP(e.getExp());
					e.isDie();
				}				
			}	
		}
		for(EnemyE e : enemies2){
			er = e.getRectangle();
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(er.intersects(br)){
					v.expUP(e.getExp());
					e.isDie();
				}				
			}	
		}	
	}
	
	public void die(){
		timer.stop();
	}
	
	
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_UP:
			v.moveUp(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.moveUp(1);
			break;
		}
	}

	public long getScore(){
		return score;
	}
	public int getLevel(){
		return v.getLevel();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
