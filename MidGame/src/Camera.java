import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public final class Camera extends JPanel implements KeyListener{
	 static Player player ;
	static int xScene  = 0 ,yScene = 0 ;
	
	public  Camera() {
		player = new Player( new SpriteImages(100, 100, "Images\\MarioSprite.png"));
		this.repaint();
		
		Timer timer = new Timer(4, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
		
		timer.start();
		
	}
	
	
	
	
	
	
	
	@Override
	protected final void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		//g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(Level.currentLevel.getBackground() , -xScene/10 % 1000,0, null);
		g.drawImage(Level.currentLevel.getBackground() , -xScene/10 % 1000 + 1000,0, null);
		if((player.getPos()[0] -xScene > 500 && player.getSensX() == 1 )||player.getPos()[0] - xScene < 00 &&player.getSensX() == -1)
			xScene += player.getSpeedX() /2 ; // Décalage ou non du cadre / champs de la caméra
		Level.currentLevel.Front(g , xScene);
			g.translate(-xScene, 0);
		
		Level.currentLevel.ShowEnnemies(g,xScene);
		
		
		//g.fillRect(player.getPos()[0] +player.hitbox.x,player.getPos()[1]+player.hitbox.y, player.hitbox.width, player.hitbox.height);
		//g.fillRect(Level.currentLevel.Ennemies.get(0).getPos()[0] +Level.currentLevel.Ennemies.get(0).hitbox.x,Level.currentLevel.Ennemies.get(0).getPos()[1]+Level.currentLevel.Ennemies.get(0).hitbox.y, Level.currentLevel.Ennemies.get(0).hitbox.width, Level.currentLevel.Ennemies.get(0).hitbox.height);
		//Affiche la hitbox
		g.drawImage(player.getCurrentImage(), player.getPos()[0], player.getPos()[1], null);
		g.dispose();
		
		
		
		
	}







	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}







	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		switch (e.getKeyCode()){
		case KeyEvent.VK_LEFT: {
			player.accelerateX(-1);
			
			break;
		}
		case KeyEvent.VK_RIGHT: {
			player.accelerateX(1);
			break;
		}
		case KeyEvent.VK_SPACE: {
			player.jump(true);;
			break;
		}
		case KeyEvent.VK_DOWN :{
			if(Tiles.isTopPipe(Level.currentLevel.CollisionMatrice[player.getcollisonPos(0) / 50][(player.getcollisonPos(1) + player.hitbox.height +1) / 50]))
				if(!Level.currentLevel.isSubLevel())
					new SubLevel("Images\\Sublevel_0.png", "Images\\Background_1.png", 0, Level.currentLevel, xScene);
				else
					Level.currentLevel.nextLevel();
			break;
		}
		default:
			break;
		}
		
		
	}







	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()){
		case KeyEvent.VK_LEFT: {
			player.accelerateX(0);
			break;
		}
		case KeyEvent.VK_RIGHT: {
			player.accelerateX(0);
			break;
		}
		default:
			break;
		}
		
	}
	
	
	
}
