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
		
		//dickbutt
		drawingSurface.setColor(Color.BLACK);
		drawingSurface.drawArc(700, 100, 20, 20, 180 , -180);
		drawingSurface.drawArc(700, 80, 20, 60, -180 , 90);
		drawingSurface.drawLine(720, 110, 725, 130);
		drawingSurface.drawArc(725, 120, 20, 20, 180 , -270);
		drawingSurface.drawLine(710, 140, 735, 140);
		drawingSurface.drawLine(725, 140, 722, 150);
		drawingSurface.drawLine(715, 140, 712, 150);
		drawingSurface.drawLine(712, 150, 710, 150);
		drawingSurface.drawLine(722, 150, 720, 150);
		drawingSurface.drawArc(702, 111, 11, 3, -180 , 160);
		drawingSurface.fillOval(703, 105, 3, 3);		
		drawingSurface.fillOval(707, 105, 3, 3);
		
	}
}

public class SmileyApplet extends JApplet {
	private static final long serialVersionUID = 1L;
	public void init() {
		add(new Drawing());
	}
}