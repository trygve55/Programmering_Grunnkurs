import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.jogamp.opengl.util.gl2.GLUT;

class CarvansOpenGL extends GLCanvas implements GLEventListener {
	private GLU glu;
	private int rotAngleX, rotAngleY, rotAngleZ;
	private float scale = 1.0f;
	private float[] points;
	private boolean[] rederOrder = {
			true, false, false,
			false, true, false,
			false, false, true,
			true, false, false,
			false, true, false,
			false, false, true
		};
	
	public CarvansOpenGL() {
		this.addGLEventListener(this);
		this.addKeyListener(new RotateKeyListener());

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
		
		System.out.print("\nRender order: ");
		
		for (int i = 0;i < 3;i++) {
			if (rederOrder[i*3]) {
				gl.glTranslatef(0.0f, 0.0f, -5.0f);
				System.out.print("translate ");
			}
			
			if (rederOrder[i*3+1]) {
				gl.glScalef(scale ,scale ,scale);
				System.out.print("scale ");
			}
			
			if (rederOrder[i*3+2]) {
				gl.glRotated(rotAngleX, 1, 0, 0);
				gl.glRotated(rotAngleY, 0, 1, 0);
				gl.glRotated(rotAngleZ, 0, 0, 1);
				System.out.print("rotate ");
			}
		}
	
		GLUT glut = new GLUT();
		glut.glutWireCube(2.0f);
	}
	
	public void dispose(GLAutoDrawable drawable) {	
	}
	
	private class RotateKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();  
			switch(keyCode) {
				case KeyEvent.VK_UP:	rotAngleX -= 10; break;
				case KeyEvent.VK_DOWN:	rotAngleX += 10; break;
				case KeyEvent.VK_RIGHT:	rotAngleY += 10; break;
				case KeyEvent.VK_LEFT:	rotAngleY -= 10; break;
				case KeyEvent.VK_X:	rotAngleZ -= 10; break;
				case KeyEvent.VK_Z:	rotAngleZ += 10; break;
				case KeyEvent.VK_PLUS:	scale += 0.1f; break;
				case KeyEvent.VK_MINUS:	if (scale > 0.1f) scale -= 0.1f; break;
				case KeyEvent.VK_SPACE:	boolean[] newArray = new boolean[rederOrder.length];
										for (int i = 0; i < rederOrder.length;i++) {
											if (i + 3 < rederOrder.length) {
												newArray[i + 3] = rederOrder[i];
											} else {
												newArray[i - rederOrder.length + 3] = rederOrder[i];
											}
										}
										rederOrder = newArray;
										break;
			}
            
            CarvansOpenGL.this.repaint();
        }
    }
}

class DrawCube {
	public static void main(String[] args) {
		String title = "Øving 13 Oppg 3";
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