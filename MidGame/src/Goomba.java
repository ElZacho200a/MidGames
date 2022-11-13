import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Goomba  extends Sprite{

	
	
	
	public Goomba( int x, int y) {
		
		super(new SpriteImages(50, 50, "Images\\Goomba.png"), x, y);
	
		sensX = -1;
		
		MAX_SPEED  = 2;
		GRAVITY_ACC = 1;
		acceleration[0] =   sensX *MAX_SPEED;
		
		Timer timer = new Timer(16, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
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
			}
		});
		
		
		timer.start();;
		
	}

}