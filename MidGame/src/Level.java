import java.awt.Graphics;
import java.awt.Graphics2D;
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
				
				
				if(CollisionMap.getRGB(i, j) == -256) {
					CollisionMatrice[i][j] = -1;
					Ennemies.add(new Goomba(i*50, j*50));
				}else {
					CollisionMatrice[i][j] = CollisionMap.getRGB(i, j);
				}
				
			
				
				
			}
			
		}
	
		
		currentLevel = this;
	}
	
	
	public BufferedImage getBackground() {
		return Background;
	}
	
	public void Front(Graphics g) {
		for (int i = 0; i < CollisionMatrice.length; i++) {
			for (int j = 0; j < CollisionMatrice[0].length; j++) {
				
				
				if(CollisionMatrice[i][j]  != -1) {
				
				g.drawImage(Tiles.getTileByColor(CollisionMatrice[i][j]),i * 50, j *50, null);
				}
				
			}
		}
	}
	
	public void ShowEnnemies(Graphics g) {
		for (Sprite ennemi : Ennemies) {
			g.drawImage(ennemi.getCurrentImage(),ennemi.getPos()[0] , ennemi.getPos()[1],null);
		}
	}
	
	
}
