import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.Timer;

public class Sprite {
	int[] pos;
	BufferedImage currentImage ;
	

	SpriteImages Animation;
	double[] acceleration  = new double[2], vitesse = new double[2] ; 
	Rectangle hitbox ;
	int sensX = 1;
	int FPS = 1000/200;
	
	int framePass = 0;

	boolean isMooving ;
	
	public static double MAX_SPEED  = 7, GRAVITY_ACC = 1;
	
	public Sprite(SpriteImages anime , int x , int y) {
		pos =new int [] { x ,  y}; 
		Animation = anime;
		currentImage = Animation.getImageByNumber(1);
		if (currentImage == null) {
			System.out.print("AAAAAAAAAAAAA");
		}
		this.hitbox = new Rectangle(pos[0], pos[1],currentImage.getWidth(),currentImage.getHeight());
		
		isMooving = true;
		
		
		Timer timer = new Timer(FPS, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatePos();
				
				if (pos[1] < 800)
					if (vitesse[1] >0)
						currentImage = Animation.getImageByNumber(8);
					else
						currentImage = Animation.getImageByNumber(9);
				else
					if (vitesse[0] == 0)
						currentImage = Animation.getImageByNumber(4);
				
					else
						if(Animation.currentImageGiven != 1)
							currentImage = Animation.getImageByNumber(1);
						else
							currentImage = Animation.getImageByNumber(4);
				if (sensX == 1){
					flip();
				}
			}
		});
		
		
		timer.start();
	}
	
	
	
	
	
	
	
	
	
	
	public void accelerateX(int sens) {
		
		acceleration[0] = ((double)sens) * MAX_SPEED;
		if(sens != 0 && sens != sensX) {
			flip();
			sensX = sens;
		}
	}
	
	public void updatePos() {
		
		
		//speed changing
		if (pos[1] >= 800)
 		vitesse[0] = (acceleration[0] * 2 + vitesse[0]) / 2 ;
		if (vitesse[0] > -1 && vitesse[0] < 1)
			vitesse[0] = 0;
		else
			vitesse[0] += acceleration[0] / 10; //Air Controle
		
		
		
		vitesse[1] +=  acceleration[1]  ; // Y 
		
		
		if(pos[1] <800) {
			acceleration[1] = GRAVITY_ACC; // Gravité si non collision
		}else if(pos[1] == 800){
			acceleration[1] = 0;
		}else {
			acceleration[1] = 0;
		}
		
		//POS changing
		pos[0] += vitesse[0]/2;
		
		
		pos[1] += vitesse[1]/2;
		if (pos[1] > 800)
			pos[1] = 800;
		
	}
	
	
	public void jump () {
		vitesse[1] = -3*MAX_SPEED;
	}
	
	
	
	public void flip() {
		BufferedImage imgFlip = new BufferedImage(hitbox.width, hitbox.height, currentImage.getType());
		Graphics2D g2 = imgFlip.createGraphics();
		g2.drawImage(currentImage, hitbox.width, 0,-hitbox.width,hitbox.height, null);
		g2.dispose();
		currentImage = imgFlip;
		
	}
	
		
		
	public int getSensX() {
		return sensX;
	}
	
	public BufferedImage getCurrentImage() {
		return currentImage;
	}
	public int[] getPos() {
		return pos;
	}

	public void setPos(int[] pos) {
		this.pos = pos;
	}
	
	

}
