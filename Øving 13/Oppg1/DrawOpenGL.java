import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class CarvansOpenGL extends GLCanvas implements GLEventListener {
	private GLU glu;
	
	private float[] points = {
		0.0F, 2.0F, 0.0F,
		1.5F, 1.5F, 0.0F,
		2.0F, 0.0F, 0.0F,
		1.5F, -1.5F, 0.0F,
		0.0F, -2.0F, 0.0F,
		-1.5F, -1.5F, 0.0F,
		-2.0F, 0.0F, 0.0F,
		-1.5F, 1.5F, 0.0F,
	};
	
	public CarvansOpenGL() {
		this.addGLEventListener(this);
		
		float[] newArray = new float[points.length+9];
		for (int i = 0;i < newArray.length; i++) {
			if (i < points.length) {
				newArray[i] = points[i];
			} else {
				newArray[i] = points[i-points.length];
			}
		}
		points = newArray;
	}
	
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		
		if (height == 0) height = 1;
		float aspect = (float)width/height;
		
		gl.glViewport(0, 0, width,height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 0.1, 100);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		int drawMethod = 0;
		gl.glTranslatef(-6.0f, 4.0f, -16.0f);	
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 3; row++) {
				if (drawMethod < 9) {
					gl.glBegin(drawMethod);
					for(int i = 0;i < points.length-6;i+=3) {
						gl.glVertex3fv(points, i);
						gl.glVertex3fv(points, i+3);
						if(drawMethod == 4 || drawMethod == 5) gl.glVertex3fv(points, i+6);
					}
					gl.glEnd();
					drawMethod++;
				}
				gl.glTranslatef(0.0f, -4.2f, 0.0f);
			}
			gl.glTranslatef(4.2f, 12.6f, 0.0f);
		}
	}
	
	public void dispose(GLAutoDrawable drawable) {	
	}
}

class DrawOpenGL {
	public static void main(String[] args) {
		String title = "Øving 12 Oppg 1";
		int width = 800;
		int height = 600;
		
		GLCanvas carvans = new CarvansOpenGL();
		carvans.setPreferredSize(new Dimension(width, height));
		
		final JFrame frame = new JFrame();
		frame.getContentPane().add(carvans);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.pack();
		frame.setVisible(true);
		
	}
}