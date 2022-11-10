import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Camera extends JPanel implements KeyListener{
	Player player ;
	
	public Camera() {
		this.setVisible(true);
		player = new Player( new SpriteImages(100, 100, "Images\\MarioSprite.png"));
		this.repaint();
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
		
		g.drawImage(player.getCurrentImage(), player.getPos()[0], player.getPos()[1], null);
		g.translate(player.getPos()[0], 0);
		
		
		
		
		
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
			System.out.println("TEST");
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
