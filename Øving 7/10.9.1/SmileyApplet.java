import javax.swing.*;
import java.awt.*;

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

public class SmileyApplet extends JApplet {
	private static final long serialVersionUID = 1L;
	public void init() {
		add(new Drawing());
	}
}