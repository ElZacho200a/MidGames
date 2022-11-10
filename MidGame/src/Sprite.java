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
	int FPS = 1000/60;
	int hitboxXUnchanged;
	
	int framePass = 0;

	boolean isMooving ;
	
	public static double MAX_SPEED  = 8, GRAVITY_ACC = 1;
	
	public Sprite(SpriteImages anime , int x , int y) {
		pos =new int [] { x ,  y}; 
		Animation = anime;
		currentImage = Animation.getImageByNumber(1);
		if (currentImage == null) {
			System.out.print("AAAAAAAAAAAAA");
		}
		this.hitbox = getHitbox(currentImage);
		hitboxXUnchanged = hitbox.x;
		isMooving = true;
		
	
		Timer timer = new Timer(FPS, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if (!CollisonY())
					if (vitesse[1] >0)
						currentImage = Animation.getImageByNumber(8); // Saut En Mont�
					else
						currentImage = Animation.getImageByNumber(9); // Chute
				else
					if (vitesse[0] * vitesse[0] < 1)
						currentImage = Animation.getImageByNumber(4); // � l'arret
				
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
	}
	
	
	
	
	
	public Rectangle getHitbox(BufferedImage img) {
		
		int w =0 , h =0 , acc =0;
		int x = 0 ,y = 0;
		
		boolean asBegin = false , asEnded = false, isEmpty = true;
		int emptyColor = img.getRGB(0, 0);
		for (int i = 0; i < img.getWidth(); i++) { // recherche de la Largeur du Sprite ( 
			for (int j = 0; j < img.getHeight(); j++) {
				if(img.getRGB(i,j) != emptyColor  && !asBegin) {
					asBegin = true;
					x = i ;
				}
				if(img.getRGB(i,j) != emptyColor)
					isEmpty = false;
				
			}
			
			if (asBegin && !asEnded)
				w++;
			if(isEmpty && asBegin)
				break;
			
			isEmpty = true;
				
		}
		
		asBegin = false ;
		asEnded = false;
		isEmpty = true;
		for (int i = 0; i < img.getHeight(); i++) { // recherche de la Hauteur du Sprite ( 
			for (int j = 0; j < img.getWidth(); j++) {
				if(img.getRGB(j,i) != emptyColor  && !asBegin) {
					asBegin = true;
					y = i;
					
				}
				if(img.getRGB(j,i) != emptyColor)
					isEmpty = false;
				
			
			}
			
			
			
			if (asBegin && !asEnded) {
				h++;
			
			}
			
			if(isEmpty && asBegin) {
				asEnded = true;
				break;
			}
			
			
			isEmpty = true;
				
		}
		
		
		
		
		return  new Rectangle(x,y,w,h);
	}
	
	
	
	
	public void accelerateX(int sens) {
		
		acceleration[0] = ((double)sens) * MAX_SPEED;
		if(sens != 0 && sens != sensX) {
			flip();
			sensX = sens;
		}
	}
	
	
	
	
	
	
	public boolean CollisonY() {
	for (int i = 0; i < hitbox.width; i++) {
			if(vitesse[1] >=0 ) {
				if(Level.currentLevel.CollisionMatrice[(i  + pos[0] +hitbox.x+ (int)vitesse[0] /2)/50][(hitbox.height+ pos[1]+hitbox.y + (int)vitesse[1]/2) /50] < -1) {
					
					return true;
				}	
				}
			else {
if(Level.currentLevel.CollisionMatrice[(i  + pos[0] +hitbox.x+ (int)vitesse[0] /2)/50][(0+ pos[1]+hitbox.y + (int)vitesse[1]/2) /50] < -1) {
					
					return true;
				}	
			}
		}
		return false;
	}
	
	
	public void getOnGround() {
		for (int i = 0; i < hitbox.width; i++) {
			for (int j = 0; j < hitbox.height; j++) {
				if(Level.currentLevel.CollisionMatrice[(i  + pos[0])/50][(j+pos[1]) /50] < -1) {
					pos[1] -= hitbox.height - j + 1; 
					
				}	
			}
		}
	}
	
	public boolean CollisonX() {
		
		for (int i = 0; i < hitbox.height; i++) {
			if (sensX == 1) {
			if(Level.currentLevel.CollisionMatrice[(hitbox.width  + pos[0] +hitbox.x +1  + (int)vitesse[0] /2)/50][(i+ pos[1]+hitbox.y + (int)vitesse[1]/2) /50] < -1) {
				return true;
			}		}
			else {
				
			if(Level.currentLevel.CollisionMatrice[(pos[0] +hitbox.x  + (int)vitesse[0] /2)/50][(i+ pos[1] +hitbox.y+ (int)vitesse[1]/2) /50] < -1) {
				return true;
			}}
	}
		
	
		return false;
	}
	
	
	
	private boolean isOnTheGround() {
		for (int i = 0; i < hitbox.width; i++) {
			
			if(Level.currentLevel.CollisionMatrice[(i  + pos[0]+hitbox.x )/50][(hitbox.height+ pos[1]+hitbox.y +1) /50] < -1) {
				
				return true;
			}		
	}
	return false;
	}
	
	
	
	public void updatePos() {
		
		
		
		if(sensX == 1)
		hitbox.x = hitboxXUnchanged +(hitboxXUnchanged + hitbox.width - currentImage.getWidth()/2) ;
		if(sensX == -1)
		hitbox.x = hitboxXUnchanged;
		
		
		vitesse[0] = (acceleration[0] * 2 + vitesse[0]) / 2 ;
		
		
		
		// Y 
		//speed changing
		acceleration[1] = GRAVITY_ACC; // Gravit� si non collision
		//POS changing
		if(!CollisonX()) {
		
		pos[0] += vitesse[0]/2;
		
		
		}else {
			
			vitesse[0] = 0;
		
			
		}
		
		if(!CollisonY()) {
			
			   
			
			vitesse[1]  += acceleration[1];	
		pos[1] += vitesse[1]/2;
		
		
		}else
		{
			//getOnGround();
			vitesse[1] = 0;
			
		}
	
		
		
	}
	
	
	public void jump () {
		if(!isOnTheGround())
			return;
		acceleration[1] = 0;
		vitesse[1] = -5*MAX_SPEED;
	}
	
	
	
	public  void flip() {
		
		
		
		
		BufferedImage imgFlip = new BufferedImage(currentImage.getWidth(), currentImage.getHeight(), currentImage.getType());
		Graphics2D g2 = imgFlip.createGraphics();
		g2.drawImage(currentImage,currentImage.getWidth(), 0,-currentImage.getWidth(),currentImage.getHeight(), null);
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
