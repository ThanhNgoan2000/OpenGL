package lab1;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Bai4 extends GLCanvas {
	public static double spin = 0;

	public void display() {
		/* clear all pixels */
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);

		myGL.glColor3f(1.0f, 1.0f, 1.0f);
//		myGL.glBegin(GL.GL_LINE_LOOP);
//		for (int i = 0; i < 360; i++) {
//			double angle = 2 * Math.PI * i / 360; // 6.2832 represents 2*PI
//			float x = (float) (7 * Math.cos(angle));
//			float y = (float) (7 * Math.sin(angle));
//			myGL.glVertex2f(x, y);
//		}

		myGL.glBegin(GL.GL_LINE_LOOP);
		myGL.glRotated(spin, 0, 1, 0);
		for (double i = -Math.PI; i <= Math.PI; i++) {
			double y = Math.sin(i);
			myGL.glVertex2d(i, y);
		}
		myGL.glEnd();

//		myGL.glBegin(GL.GL_LINES);
//		myGL.glTranslated(2.0, 0.0, 0.0);
//		myGL.glVertex3f(0.0f,0.0f, 0.0f);
//		myGL.glVertex3f(-3.0f,.0f, 0.0f);
//		myGL.glEnd();
//		myGL.glBegin(GL.GL_LINES);
//		myGL.glTranslated(0.0, -3.0, 0.0);
//		myGL.glVertex3f(0.0f, 0.0f, 0.0f);
//		myGL.glVertex3f(0.0f, -5.0f, 0.0f);
//		myGL.glEnd();

		myGL.glEnd();
		myGL.glFlush();
	}

    public void myReshape (int w, int h) {
	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	myGLU.gluPerspective (60.0, (double)w/(double)h, 1.0, 20.0);
	myGL.glMatrixMode (GL.GL_MODELVIEW);
	myGL.glLoadIdentity ();
	myGLU.gluLookAt (0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
    }

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'd':
			spin = (spin + 10) % 360;
			myUT.glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		default:
			break;
		}
	}

	private void myinit() {
		/* select clearing color */
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		/* initialize viewing values */
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(-5.0f, 5.0f, -5.0f, 5.0f, 0.0f, 0.0f);
	}

	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		Bai4 mainCanvas = new Bai4();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}

}
