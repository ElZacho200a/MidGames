import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Timer;

public class Sprite {
	int[] pos; // Position in x and y , 0  and 1
	BufferedImage currentImage ;
	

	SpriteImages Animation;
	double[] acceleration  = new double[2], vitesse = new double[2] ;  // Sprite vectors
	Rectangle hitbox ;
	int sensX = 1;
	int FPS = 1000/60;
	int hitboxXUnchanged; // a save for the hitbox usefull when image is flipped 
	Timer timer;
	
	int framePass = 0;
	int waiting = 0;
	boolean isMooving ,isDead  = false;
	
	public  double MAX_SPEED  = 0, GRAVITY_ACC = 1;
	
	public Sprite(SpriteImages anime , int x , int y) {
		pos =new int [] { x ,  y}; 
		Animation = anime;
		currentImage = Animation.getImageByNumber(1);
		if (currentImage == null) {
			System.out.print("AAAAAAAAAAAAA");
		}
		this.hitbox = getHitbox(currentImage);
		System.out.println(hitbox);
		hitboxXUnchanged = hitbox.x;
		isMooving = true;
		
	
	
	}
	
	
	
	
	
	public Rectangle getHitbox(BufferedImage img) {// getting a realistic framing from the given picture
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
	
	
	
	// Collison Function and stuff related to it
	
	
	public boolean CollisonY() {
	for (int i = 0; i < hitbox.width; i+=10) {
			if(vitesse[1] >=0 ) {
				if(Level.currentLevel.CollisionMatrice[(i  + pos[0] +hitbox.x+ (int)vitesse[0] /2)/50][(hitbox.height+1+ pos[1]+hitbox.y + (int)vitesse[1]/2) /50] < -1) {
					
					return true;
				}	
				}
			else {
if(Level.currentLevel.CollisionMatrice[(i  + pos[0] +hitbox.x+ (int)vitesse[0] /2)/50][(0+ pos[1]+-1+hitbox.y + (int)vitesse[1]/2) /50] < -1) {
					
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
		
		for (int i = 0; i < hitbox.height; i+=10) {
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
	
	
	public boolean IsThereacollisionBug() {
		
		for (int i = 0; i < hitbox.width; i++) {
			for (int j = 0; j < hitbox.height; j++) {
				if(Level.currentLevel.CollisionMatrice[(i  + pos[0] +hitbox.x)/50][(j+ pos[1]+hitbox.y ) /50] < -1) {
					
					return true;
				}	
			}
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
	//End
	
	
	public final void updatePos() { // Updating POS
		
		if(vitesse[0] * vitesse[0] < 1)
			vitesse[0] = 0;
		
		
		if(sensX == 1)
		hitbox.x = currentImage.getWidth() - hitboxXUnchanged - hitbox.width  ;
		if(sensX == -1)
		hitbox.x = hitboxXUnchanged;
		
		
		vitesse[0] = (acceleration[0] * 2 + vitesse[0]) / 2 ;
		
		
		
		// Y 
		//speed changing
		acceleration[1] = GRAVITY_ACC; // Gravité si non collision
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
	
	
	public void jump (boolean followRule) {
		if(!isOnTheGround() &&  followRule)
			return;
		acceleration[1] = 0;
		vitesse[1] = -4*MAX_SPEED;
	}
	
	
	
	
	public boolean areSpriteInContact(Sprite sp1 , Sprite sp2) { // checking if two Sprite are in superposition , useful for the player
		
		if(sp1.getPos()[0] < sp2.getPos()[0] && sp2.getPos()[0] < sp1.getPos()[0] + sp1.hitbox.width )
			if(sp1.getPos()[1] < sp2.getPos()[1] && sp2.getPos()[1] < sp1.getPos()[1] + sp1.hitbox.height )
				return true;
		
		if(sp2.getPos()[0] < sp1.getPos()[0] && sp1.getPos()[0] < sp2.getPos()[0] + sp2.hitbox.width )
			if(sp2.getPos()[1] < sp1.getPos()[1] && sp1.getPos()[1] < sp2.getPos()[1] + sp2.hitbox.height )
				return true;
		
		return false;
		
		
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
	public void doDeath() { // should Overrided
		
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
