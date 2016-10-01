import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Window(String title) {
		setTitle(title);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Drawing drawing = new Drawing();
		add(drawing);
	}
}

class Drawing extends JPanel {
	private static final long serialVersionUID = 2L;
	
	public void paintComponent(Graphics drawingSurface) {
		super.paintComponent(drawingSurface);
		setBackground(Color.WHITE);
		drawingSurface.setColor(Color.YELLOW);
		drawingSurface.fillOval(200, 100, 400, 400);
		drawingSurface.setColor(Color.BLACK);
		drawingSurface.fillOval(300, 200, 40, 40);
		drawingSurface.fillOval(500, 200, 40, 40);
		drawingSurface.fillArc(250, 250, 300, 200, -180 , 180);
		drawingSurface.setColor(Color.RED);
		drawingSurface.fillArc(270, 280, 260, 160, -180 , 180);
	}
}

class Smiley {
	public static void main(String[] args) {
		Window windowOne = new Window("Smiley");
		windowOne.setVisible(true);
	}
}