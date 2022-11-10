import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteImages {
	BufferedImage image ;
	File file;
	Rectangle spriteSize;
	int xLength , yLength , currentImageGiven ; 
	
	
	
	

	public SpriteImages(int width , int height , String filename) {
	
	
		
	try {
		this.file = new File(filename);
		image = ImageIO.read(file);
		spriteSize = new Rectangle(width, height);
		xLength = (int) (image.getWidth() / spriteSize.getWidth());
		yLength = (int)(image.getHeight() / spriteSize.getHeight());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Image Not Found");
		e.printStackTrace();
		System.exit(1);
	}
	
	
	}
	
	public BufferedImage getImageByNumber(int nmb) {
		currentImageGiven = nmb;
		 int x = (nmb % xLength) * spriteSize.width , y = (nmb / xLength) * spriteSize.height;
		return image.getSubimage(x, y, spriteSize.width, spriteSize.height);
		
	}
	
	public int getCurrentImageGiven() {
		return currentImageGiven;
	}
	
	

}
