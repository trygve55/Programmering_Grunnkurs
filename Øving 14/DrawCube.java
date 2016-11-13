import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class CarvansOpenGL extends GLCanvas implements GLEventListener {
	private GLU glu;
	private int rotAngleX, rotAngleY, rotAngleZ, view;
	private float[] points;
	private float[] colors;
	
	public CarvansOpenGL() {
		this.addGLEventListener(this);
		this.addKeyListener(new RotateKeyListener());
		
		//lager punkter til kube
		points = new float[120];

		for (int i = 0; i < points.length;i++) points[i] = 1.0f;
		int dir = 0;
		for (int p = 0;p < 6; p++) {
			
			for (int i = 0;i < 3;i++) { 
				points[p * 18 + 3 + i] = points[p * 18 + i];
				points[p * 18 + 6 + i] = points[p * 18 + i];
				points[p * 18 + 9 + i] = points[p * 18 + 3 + i];
			}
			points[p * 18 + dir + 3] *= -1;
			points[p * 18 + dir + 7] *= -1;
			for (int i = 0;i < 3;i++) { 
				points[p * 18 + 12 + i] = points[p * 18 + 6 + i];
				points[p * 18 + 15 + i] = points[p * 18 + 9 + i];
				points[p * 18 + 18 + i] = points[p * 18 + 12 + i];
			}
			if (p == 3) {
				for (int i = 0;i < 18;i++) points[p*18+i] *= -1;
				
			}
			if (p == 4) {
				points[p*18+17] *= -1;
				points[p*18+16] *= -1;
				points[p*18+4] *= -1;
				points[p*18+5] *= -1;
				points[p*18+8] *= -1;
				points[p*18+1] *= -1;
			}
			if (p == 5) {
				points[p*18] *= -1;
				points[p*18+1] *= -1;
				points[p*18+2] *= -1;
				points[p*18+3] *= -1;
				points[p*18+7] *= -1;
				points[p*18+11] *= -1;
				points[p*18+10] *= -1;
				points[p*18+9] *= -1;
			}
			points[p * 18 + dir + 12] *= -1;
			points[p * 18 + dir + 15] *= -1;
			points[p * 18 + dir + 10] *= -1;
			dir++;
			if (dir == 2) dir = 0;
		}
		for (int i = 0; i < points.length/3;i++) {
			System.out.println(points[i*3] + " " + points[i*3+1] + " " + points[i*3+2] + "  " + (i%6 + 1) + " " + (i/6+1));
		}
		Random rand = new Random();
		colors = new float[120];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = rand.nextFloat();
		}
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
		
		switch (view) {
			case 0: glu.gluLookAt(0, 0, 1, 0, 0, 0, 0, 1 ,0); break;
			case 1: glu.gluLookAt(0, 2, 6, 0, 0, 0, 1, 1 ,0); break;
			case 2: glu.gluLookAt(18, 0, 9, 0, 0, 0, 0, 1 ,0); break;
			case 3: glu.gluLookAt(-18, 6, 9, 0, 0, 0, 0, 1 ,0); break;
		}
		
		gl.glTranslatef(0.0f, 0.0f, -5.0f);	
		gl.glRotated(rotAngleX, 1, 0, 0);
		gl.glRotated(rotAngleY, 0, 1, 0);
		gl.glRotated(rotAngleZ, 0, 0, 1);
	
		//tegner linjer
		
		for(int i = 0;i < points.length/18;i++) {
			gl.glBegin(GL2.GL_POLYGON);
			gl.glColor3fv(colors, i*3);
			gl.glVertex3fv(points, i*18);
			gl.glVertex3fv(points, i*18+3);
			gl.glVertex3fv(points, i*18+6);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3fv(points, i*18+9);
			gl.glVertex3fv(points, i*18+12);
			gl.glVertex3fv(points, i*18+15);
			gl.glEnd();
		}

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
				case KeyEvent.VK_SPACE: view += 1; if (view == 4) view = 0; break;
			}
            
            CarvansOpenGL.this.repaint();
        }
    }
}

class DrawCube {
	public static void main(String[] args) {
		String title = "Øving 14";
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