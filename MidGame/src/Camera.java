import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public final class Camera extends JPanel implements KeyListener{
	Player player ;
	int xScene  = 0 ,yScene = 0 ;
	
	public  Camera() {
		this.setVisible(true);
		player = new Player( new SpriteImages(100, 100, "Images\\MarioSprite.png"));
		this.repaint();
		System.out.println(Level.currentLevel.CollisionMap.getType());
		Timer timer = new Timer(16, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
		
		timer.start();
		
	}
	
	
	
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		
		g.drawImage(Level.currentLevel.getBackground() , 0,0, null);
		if((player.getPos()[0] -xScene > 800 && player.getSensX() == 1 )||player.getPos()[0] - xScene < 200 &&player.getSensX() == -1)
			xScene += player.getSpeedX()  ; // Décalage ou non du cadre / champs de la caméra
		
			g.translate(-xScene, 0);
		
		
		Level.currentLevel.Front(g);
		//g.fillRect(player.getPos()[0] +player.hitbox.x,player.getPos()[1]+player.hitbox.y, player.hitbox.width, player.hitbox.height);
		//Affiche la hitbox
		g.drawImage(player.getCurrentImage(), player.getPos()[0], player.getPos()[1], null);
		
		
		
		
		
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
			player.jump();;
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
