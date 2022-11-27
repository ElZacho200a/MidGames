import java.io.IOException;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new Tiles();
		new Level("Images\\LEVEL_0.png", "Images\\BackGround_0.png", 0);
		Camera camera = new Camera();
		JFrame frame = new JFrame();
		
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(camera);
		camera.setBounds(0, 0, 1000, 1050);
		frame.setSize(1000,1050);
		frame.setResizable(false);
		frame.addKeyListener(camera);
		
		
	}

}
