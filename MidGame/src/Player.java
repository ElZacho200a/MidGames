import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

public final class Player extends Sprite {
	
	
	int Life = 5;

	public  Player(SpriteImages anime) {
	
	super(anime ,Level.currentLevel.startPoint.x ,Level.currentLevel.startPoint.y);
	MAX_SPEED  = 12;
	GRAVITY_ACC = 3;
	 timer = new Timer(8, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
					if(!isDead) {
						Checkattack();
			if (!CollisonY())
				if (vitesse[1] >0)
					currentImage = Animation.getImageByNumber(8); // Saut En Monté
				else
					currentImage = Animation.getImageByNumber(9); // Chute
			else
				if (vitesse[0] * vitesse[0] < 1)
					currentImage = Animation.getImageByNumber(4); // à l'arret
			
				else
					if(Animation.currentImageGiven != 1)// alternance entre les images (animation )
						currentImage = Animation.getImageByNumber(1); //animation de marche 
					else
						currentImage = Animation.getImageByNumber(4);//animation de marche
			if (sensX == 1){
				flip();
			}
			
			
			updatePos();
	
			 checkEnnemi();
			 }else { // Death animation
				if(framePass <= 10) {
				 framePass ++;
				 pos[1] --;;
				 }else if(pos[1] < 1000){
					 	 pos[1] +=2;
					 
					 
				 }else {
					 isDead = false;
					 Life --;
					 Camera.xScene = 0;
					 Level.currentLevel.reProcessEnnemies();
					resetAll();
				 }
			 }
				 
							
					
					
					
		}
	});
	
	
	timer.start();;
	this.flip();
	
	
	
	}
	
	
	
	public void resetAll() {
		vitesse[0] = 0;
		vitesse[1] = 0;
		System.out.println(Level.currentLevel.startPoint.x);
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
				System.out.println("Almost");
				if(x < pos[0] + hitbox.x + hitbox.width && pos[0] +hitbox.x   < x + ennemi.hitbox.width) {
					toRemove[i] = 1;
					System.out.println("BOOOM HEADSHOT");
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
		System.out.println();
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
	
	
}
