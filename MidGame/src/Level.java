import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Level {
	int ID = 0;
	BufferedImage CollisionMap , Background;
	int[][] CollisionMatrice;
	
	
	public Level(BufferedImage COl , BufferedImage Back , int nmb) {
		
		CollisionMap = COl;
		Background = Back;
		ID = nmb;
		
		
		CollisionMatrice = new int [CollisionMap.getWidth()][CollisionMap.getHeight()];	
		for (int i = 0; i < CollisionMatrice.length; i++) {
			for (int j = 0; j < CollisionMatrice[0].length; j++) {
				CollisionMatrice[i][j] = CollisionMap.getRGB(i, j);
			}
		}
	}
	

}
