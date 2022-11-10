import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camera camera = new Camera();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(camera);
		frame.setSize(1000,1000);
		frame.setResizable(false);
		frame.addKeyListener(camera);
		
		
	}

}
