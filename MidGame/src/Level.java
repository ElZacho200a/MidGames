
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level {
	
	public static  Level currentLevel;
	int ID = 0;
	BufferedImage CollisionMap , Background;
	ArrayList<Sprite> Ennemies; 
	Point startPoint ;
	ArrayList<Sprite> EndFlag;
	ArrayList<SubLevel> SubLevel;
	int[][] CollisionMatrice;
		
	public Level(String COl , String Back , int nmb) {
		EndFlag = new ArrayList<>();
		Ennemies = new ArrayList<>();
		try {
			Background = ImageIO.read(new File(Back));
			CollisionMap = ImageIO.read(new File(COl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ID = nmb;
		CollisionMatrice = new int [CollisionMap.getWidth()][CollisionMap.getHeight()];	
		for (int i = 0; i < CollisionMatrice.length; i++) {
			for (int j = 0; j < CollisionMatrice[0].length; j++) {
				Construct(i,j);
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
	
	
	private void Construct(int i,int j) {
		
		int color = CollisionMap.getRGB(i, j);
		if(Tiles.isEnnemy(color)) {
			CollisionMatrice[i][j] = -1;
			Ennemies.add(new Goomba(i*50, j*50));
		}else if(CollisionMap.getRGB(i, j) == -65281){ // purple
			startPoint = new Point(i *50  , j * 50 );
		}
		else {
			if(Tiles.isNotCollide( CollisionMap.getRGB(i, j))) {
				CollisionMatrice[i][j] = CollisionMap.getRGB(i, j) * -1;
				if(Tiles.isFlag(CollisionMap.getRGB(i, j)))
						EndFlag.add(new Sprite(new SpriteImages(50, 50, "Images\\Flag.png"), i*50, j*50));
				}
			else
				CollisionMatrice[i][j] = CollisionMap.getRGB(i, j);
		}
	}
	
	
	public void nextLevel() {

		
		// Setting the next level
		//
		currentLevel = new Level(String.format("Images\\LEVEL_%d.png", ID +1),String.format("Images\\BackGround_%d.png" ,ID+1), ID +1);
	}
	
	public Boolean isSubLevel() {
		return false;
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
	
	
	public void reProcessEnnemies() {
		Ennemies = new ArrayList<>();
		for (int i = 0; i < CollisionMatrice.length; i++) 
			for (int j = 0; j < CollisionMatrice[0].length; j++) 
				if(CollisionMap.getRGB(i,j) == -256)
					Ennemies.add(new Goomba(i * 50, j * 50));
	}
	
	
}
