import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public final class Level {
	
	public static  Level currentLevel;
	int ID = 0;
	BufferedImage CollisionMap , Background;
	ArrayList<Sprite> Ennemies; 
	Point startPoint ;

	


	int[][] CollisionMatrice;
	
	
	public Level(String COl , String Back , int nmb) {
		
				
		Ennemies = new ArrayList<>();
		try {
			Background = ImageIO.read(new File(Back));
			CollisionMap = ImageIO.read(new File(COl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ID = nmb;
		
		System.out.println(CollisionMap.getRGB(0, 0));
		
		CollisionMatrice = new int [CollisionMap.getWidth()][CollisionMap.getHeight()];	
		for (int i = 0; i < CollisionMatrice.length; i++) {
			for (int j = 0; j < CollisionMatrice[0].length; j++) {
				
				
				if(CollisionMap.getRGB(i, j) == -256) { // red
					CollisionMatrice[i][j] = -1;
					Ennemies.add(new Goomba(i*50, j*50));
				}else if(CollisionMap.getRGB(i, j) == -65281){ // purple
					startPoint = new Point(i *50  , j * 50 );
				}
				else {
					CollisionMatrice[i][j] = CollisionMap.getRGB(i, j);
				}
				
			
				
				
			}
			
		}
	
		
		currentLevel = this;
	}
	
	
	public  final BufferedImage getBackground() {
		return Background;
	}
	
	public void Front(Graphics g , int Xdecal) {
	int SizeX = 1050 + Xdecal , SizeY = 1000;
	SizeX /= 50;
	SizeY /= 50;
	for (int i = Xdecal /50 ; i < SizeX; i++) {
		for (int j = 0; j < SizeY; j++) {
			try {
				g.drawImage(Tiles.getTileByColor(CollisionMatrice[i][j]), (i - Xdecal /50) * 50 - Xdecal %50,j *50, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		}
	}
	}
	
	public void ShowEnnemies(Graphics g , int Xdecal) {
		for (Sprite ennemi : Ennemies) {
			if(ennemi.getPos()[0] > Xdecal && ennemi.getPos()[0] < 1000+Xdecal ) {
				ennemi.timer.start();
				g.drawImage(ennemi.getCurrentImage(),ennemi.getPos()[0] , ennemi.getPos()[1],null);
			
			}else {
				ennemi.timer.stop();
			}
		}
	}
	
	
}
