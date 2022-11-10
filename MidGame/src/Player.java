
public class Player extends Sprite {
	
	

	public Player(SpriteImages anime) {
	super(anime ,400 ,500);
	this.flip();
	}
	
	
	public int getSpeedX() {
		return (int)this.vitesse[0];
	}
	
	
	
	
}
