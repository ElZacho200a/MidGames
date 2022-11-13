import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Player extends Sprite {
	
	

	public Player(SpriteImages anime) {
	
	super(anime ,400 ,500);
	MAX_SPEED  = 8;
	GRAVITY_ACC = 1;
	Timer timer = new Timer(FPS, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
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
		}
	});
	
	
	timer.start();;
	this.flip();
	
	
	
	}
	
	
	public int getSpeedX() {
		return (int)this.vitesse[0];
	}
	
	
	
	
}
