import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public final class Player extends Sprite {
	
	
	int Life = 5;
	Boolean flipped = false;
	public  Player(SpriteImages anime) {
	
	super(anime ,Level.currentLevel.startPoint.x ,Level.currentLevel.startPoint.y);
	MAX_SPEED  = 13;
	GRAVITY_ACC = 2;
	 
	 timer = new Timer(16, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(checkFlag()) {
				Level.currentLevel.nextLevel();
				resetAll();
			}
					if(!isDead) {
						Checkattack();
			if (!CollisonY())
				if (vitesse[1] >0) {
					currentImage = Animation.getImageByNumber(8); // Saut En Monté
					flipped = false;
				}
					else {
					currentImage = Animation.getImageByNumber(9); // Chute
					flipped = false;
					}
					else
				if (vitesse[0] * vitesse[0] < 1) {
					currentImage = Animation.getImageByNumber(4); // à l'arret
					flipped = false;
				}
				else
					
					if( framePass  == 0){// alternance entre les images (animation )
						currentImage = Animation.getImageByNumber(1); //animation de marche 
						flipped = false;
					}else if(framePass == 6) {
						currentImage = Animation.getImageByNumber(4);
						flipped = false;
					}
							
			
			framePass = (framePass + 1) % 7;
			
			if (sensX == 1 && !flipped ) {
				flip();
				flipped = true;
			}
			updatePos();
	
			 checkEnnemi();
			 }else { // Death animation
				if(framePass <= 10) {
				 framePass ++;
				 pos[1] --;;
				 }else if(pos[1] < 1000){
					 	 pos[1] +=10;
					 
					 
				 }else {
					 isDead = false;
					 Life --;
					 
					resetAll();
				 }
			 }
				 
							
					
					
					
		}
	});
	
	
	timer.start();;
	this.flip();
	
	
	
	}
	
	
	
	public boolean checkFlag() {
		for (Sprite flag: Level.currentLevel.EndFlag) {
			if(this.areSpriteInContact(this, flag))
				return true;
				
		}
		return false;
		
	}
	
	public void resetAll() {
		Camera.xScene = 0;
		Level.currentLevel.reProcessEnnemies();
		vitesse[0] = 0;
		vitesse[1] = 0;
		
		pos[0] = Level.currentLevel.startPoint.x;
		pos[1] = Level.currentLevel.startPoint.y;
		
	}
	
	public void Checkattack() {
		int nextYPos = pos[1] + (int)vitesse[1] + hitbox.height;
		int[] toRemove = new int[Level.currentLevel.Ennemies.size() ];
		boolean kill = false;
		for (int i = 0; i < Level.currentLevel.Ennemies.size() ; i++) {
			Sprite ennemi = Level.currentLevel.Ennemies.get(i) ;
			if(ennemi.isDead)
				continue;
			int x = ennemi.getPos()[0] + ennemi.hitbox.x ,y  = ennemi.getPos()[1] + ennemi.hitbox.y ;
			
			if(y < nextYPos && nextYPos - (int)vitesse[1] < y) {
				
				if(x < pos[0] + hitbox.x + hitbox.width && pos[0] +hitbox.x   < x + ennemi.hitbox.width) {
					toRemove[i] = 1;
					
					kill = true;
				}
		}
		}
		int killed = 0;
		for (int i = 0; i < toRemove.length; i++) {
			if(toRemove[i] == 1) {
				Level.currentLevel.Ennemies.get(i - killed).doDeath();
				
				
				
			}else if( !Level.currentLevel.Ennemies.get(i -killed).timer.isRunning() &&  Level.currentLevel.Ennemies.get(i -killed).isDead) {
				Level.currentLevel.Ennemies.remove(i - killed);
				killed ++;
			}
			
		}
		if (kill) {
		
			jump(false);
		}
		
	}
	
	public int getSpeedX() {
		return (int)this.vitesse[0];
	}
	
	
	public void checkEnnemi() {
		
		for(Sprite ennemi :Level.currentLevel.Ennemies) {
			if(ennemi.isDead)
				continue;
			if(areSpriteInContact(this, ennemi))
				doDeath();	
			}
	}
	
	@Override
	public void doDeath() {
		isDead = true;
		pos[1] -= 50;
		currentImage = Animation.getImageByNumber(5);
		}
	public int getcollisonPos(int n) {
		return pos[n] + (n == 0 ? hitbox.x : hitbox.y);
	}
	
	
}
