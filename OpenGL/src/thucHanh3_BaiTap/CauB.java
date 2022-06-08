package thucHanh3_BaiTap;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class CauB extends GLCanvas {
	private float year = 0 , day = 1.5f;

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glPushMatrix();

		myGL.glColor3f(0.0f, 1.0f, 0.0f);
		myGL.glRotatef((float) year, 0, 0, year);
		myGL.glTranslatef(1.5f, 0f, 0.0f);
		myUT.glutWireSphere(0.3, 20, 15);
		
		myGL.glPopMatrix();
		
		myGL.glPushMatrix();
		myGL.glTranslatef(day, -1f, 0.0f);
		myGL.glScaled(1.5f, 0.5, 1);
		myUT.glutWireCube(1.0);
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(5.0f, -5.0f, 5.0f, -5.0f, 0.0f, 0.0f);
	}

	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutReshapeFunc("myReshape");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(60.0, (double) w / (double) h, 1.0, 20.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGLU.gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'x':
			System.exit(0);
		case 'p':
			year += 5 % 360;
			day -= 0.075;
			myUT.glutPostRedisplay();
			break;
		case 'P':
			year -= 5 % 360;
			day += 0.075;
			myUT.glutPostRedisplay();
			break;
		default:
			break;
		}
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		CauB mainCanvas = new CauB();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}
