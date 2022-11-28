import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class Tiles {
	
	public  static BufferedImage Brick,Pipe,Surprise, Castle, PipeBody,Ground ,Dirt;
	public static BufferedImage Unknow;
	public static  SpriteImages Flag;
	private static int[] Uncollided = new int[] {-16711916,-16711896,-16711876,-65536};
	private static int[] FlagColor = new int[] {-16711916,-16711896,-16711876};
	public Tiles() throws IOException {
		
			Unknow = ImageIO.read(new File("Images\\Unknow.png"));
			Brick = ImageIO.read(new File("Images\\Brick.png"));
			Castle = ImageIO.read(new File("Images\\Castle.png"));
			Pipe = ImageIO.read(new File("Images\\PipeEnd.png"));
			PipeBody = ImageIO.read(new File("Images\\PipeBody.png"));
			Ground = ImageIO.read(new File("Images\\Ground.png"));
			Dirt = ImageIO.read(new File("Images\\Dirt.png"));
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
		public final static boolean isNotCollide(int color) {
			for (int co : Uncollided ) 
				if(co == color)
					return true;
			return false;		
		}
	
		public final static boolean isTopPipe(int color) {
			return  color == -16711936;
		}
		public final static boolean isEnnemy(int color) {
			return color == -256;
		}
		public final static boolean isFlag(int color) {
			for (int co : FlagColor ) 
				if(co == color)
					return true;
			return false;		
		}
		public final boolean isSpawnPoint(int color) {
			return color == -65281 ;
		}
		
	
		public final static BufferedImage getTileByColor(int color) {
			
			switch (color) {
			
			case 16711916:{
				return Flag.getImageByNumber(2); // Haut du drapeau
			}case 16711896:{
				return Flag.getImageByNumber(1); // Drapeau
			}case 16711876:{
				return Flag.getImageByNumber(0); //bar du drapeau
			}
			case -16777216: {//black
				return Brick ;
	
			}
			case 65536:{ // red
				return Castle;
			}
			case-16711936:{ // Tuyau
				return Pipe;
			}
			case-16711681:{
				return PipeBody;
			}
			case-8441088:{ // brown
				
				return Ground;
			}
			case -1:{ // Air
				return null;
			}
			case 0:{ // Point de spawn
				return null;
			}
			case -16744448:{ // 0,0,128
				return Dirt;
			}
			default:
				System.out.println(color);
				return Unknow;
			}
		
			
			
			
			
		}
		
		
		
		

	
	
	
	
	
	
	
	
	
	
	
	
	

}
