import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public final class Goomba  extends Sprite{

	
	
	
	public Goomba( int x, int y) {
		
		super(new SpriteImages(50, 50, "Images\\Goomba.png"), x, y);
	
		sensX = -1;
		
		MAX_SPEED  = 3;
		GRAVITY_ACC = 2;
		acceleration[0] =   sensX *MAX_SPEED;
		
		 timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(!isDead) {
					framePass  = (framePass +1) %10;
					if (framePass  == 0)
						if(Animation.currentImageGiven != 1)// alternance entre les images (animation )
							currentImage = Animation.getImageByNumber(1); //animation de marche 
						else
							currentImage = Animation.getImageByNumber(0);//animation de marche
			
				
				
				updatePos();
				if (CollisonX()) {
					sensX *= -1;
					acceleration[0] *= -1;
				}
				}else {
					framePass++;
					if(framePass > 10)
						timer.stop();
					
					
				}
				
				
				
				
			}
		});
		
		
		timer.start();;
		
	}
	
	
	@Override
	public void doDeath()  {
		isDead = true;
		currentImage = Animation.getImageByNumber(2);
	
		
	}
	
	
	
	public void wait(int millis) {
		waiting = millis;
	}

}
