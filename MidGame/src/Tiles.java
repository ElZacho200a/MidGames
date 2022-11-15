import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class Tiles {
	
	public  static BufferedImage Brick,Pipe,Surprise, Castle, PipeBody,Ground;
	public static  SpriteImages Flag;
	
	public Tiles() throws IOException {
		
		
			Brick = ImageIO.read(new File("Images\\Brick.png"));
			Castle = ImageIO.read(new File("Images\\Castle.png"));
			Pipe = ImageIO.read(new File("Images\\PipeEnd.png"));
			PipeBody = ImageIO.read(new File("Images\\PipeBody.png"));
			Ground = ImageIO.read(new File("Images\\Ground.png"));
			Flag = new SpriteImages(100, 50, "Images\\Flag.png");
			}
		
		/*blue(0,0,255) = -16776961
		 *black(0,0,0) = -16777216
		 * red (255,0,0) = -65536
		 * green(0,255,0) = -16711936
		 * cyan(0,255,255= -16711681
		 * yellow (255,255,0) = -256
		 * purple(255,0,255) = -65281
		 * white = -1
		 * */
		public final static BufferedImage getTileByColor(int color) {
			
			switch (color) {
			
			case -16711916:{
				return Flag.getImageByNumber(2);
			}case -16711896:{
				return Flag.getImageByNumber(1);
			}case -16711876:{
				return Flag.getImageByNumber(0);
			}
			case -16777216: {//black
				return Brick ;
	
			}
			case-65536:{ // red
				return Castle;
			}
			case-16711936:{
				return Pipe;
			}
			case-16711681:{
				return PipeBody;
			}
			case-8441088:{ // brown
				return Ground;
			}
			default:
				System.out.println(color);
				throw new IllegalArgumentException("Unexpected value: " + color);
			}
		
			
			
			
			
		}
		
		
		
		

	
	
	
	
	
	
	
	
	
	
	
	
	

}
