import java.awt.Graphics;

public class SubLevel extends Level {

	
	int DecalLevel;
	public final  Level RealLevel;
	public SubLevel(String COl, String Back, int nmb , Level level,int DecalLevel) {
		
		super(COl, Back, nmb);
		RealLevel = level;
		this.DecalLevel = DecalLevel;
		Camera.xScene = 0;
		Camera.player.pos[0] -= DecalLevel;
		
		
	}
	
	@Override
	public void nextLevel() {
		Camera.xScene += DecalLevel;
		Camera.player.pos[0] += DecalLevel;
		currentLevel = RealLevel;
		
	}
	
	
	@Override
	public void Front(Graphics g, int Xdecal) {
		//Xdecal -= this.DecalLevel;
		super.Front(g, Xdecal);
	}
	@Override
	public Boolean isSubLevel() {
		return true;
	}
	

}
