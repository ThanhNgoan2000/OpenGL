package thucHanh3_BaiTap;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class CauA extends GLCanvas {
	private static double a = -2, b = -2;

	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(5.0f, -5.0f, 5.0f, -5.0f, 0.0f, 0.0f);
//		myGL.glShadeModel(GL.GL_FLAT);
	}

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glColor3f(0.0f, 1.0f, 0.0f);

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);

		myGL.glVertex3d(-1.0, -2.0, 0.0);
		myGL.glVertex3d(-4.0, -2.0, 0.0);

		myGL.glVertex3d(-4.0, -2.0, 0.0);
		myGL.glVertex3d(0.0, 2.0, 0.0);

		myGL.glVertex3d(0.0, 2.0, 0.0);
		myGL.glVertex3d(3.0, 2.0, 0.0);

		myGL.glVertex3d(3.0, 2.0, 0.0);
		myGL.glVertex3d(-1.0, -2.0, 0.0);

		myGL.glEnd();

		myGL.glTranslated(a, b, 0.0);
		myUT.glutWireSphere(0.3, 20, 15);
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	/* ARGSUSED2 */
	public void mouse(int button, int state, int x, int y) {

	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'p':
			if (a < 2) {
				a += 0.1;
				b += 0.1;
				myUT.glutPostRedisplay();
			}
			break;
		case 'P':
			if (a > -2) {
				a = a - 0.1;
				b = b - 0.1;
				myUT.glutPostRedisplay();
			}
			break;
		case 'x':
			System.exit(0);
		default:
			break;
		}
	}

	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
//		myUT.glutReshapeFunc("myReshape");
		myUT.glutMouseFunc("mouse");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(-50.0f, 50.0f, -50.0f, 50.0f, -1.0f, 1.0f);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		CauA mainCanvas = new CauA();
		mainCanvas.init();

		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}
