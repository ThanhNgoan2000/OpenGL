package thucHanh3_BaiTap;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class CauE extends GLCanvas {

	private float spin = 0;
	private int flag = 1;
	public double aL4 = 0;

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
//	myGL.glPushMatrix (); 
//	myGL.glRotatef (spin, 0.0f, 0.0f, 1.0f);
//	myGL.glColor3f (1.0f, 1.0f, 1.0f);
//	myGL.glRectf (-25.0f, -25.0f, 25.0f, 25.0f);
//	myGL.glPopMatrix ();
		myGL.glPushMatrix();
		myGL.glTranslated(0, -30, 0);
//	Line 1
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glVertex3f(0.0f, 25.0f, 0.0f);
		myGL.glVertex3f(0.0f, 0.0f, 0.0f);
		myGL.glEnd();
//	Line 2
		myGL.glTranslated(0, 25, 0);
		myGL.glRotated(aL4, 0, 0, 1);
		myGL.glTranslated(0, -25, 0);
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3f(0.0f, 0.0f, 1.0f);
		myGL.glVertex3f(0.0f, 25.0f, 0.0f);
		myGL.glVertex3f(25.0f, 25.0f, 0.0f);
		myGL.glEnd();
//	Line 3
		myGL.glTranslated(25, 25, 0);
		myGL.glRotated(aL4, 0, 0, 1);
		myGL.glTranslated(-25, -25, 0);
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3f(1.0f, 0.0f, 0.0f);
		myGL.glVertex3f(25.0f, 25.0f, 0.0f);
		myGL.glVertex3f(25.0f, 0.0f, 0.0f);
		myGL.glEnd();
//	Line 4
		myGL.glTranslated(25, 0, 0);
		myGL.glRotated(aL4, 0, 0, 1);
		myGL.glTranslated(-25, 0, 0);
		myGL.glBegin(GL.GL_LINES);
		myGL.glColor3f(1.0f, 0.0f, 1.0f);
		myGL.glVertex3f(25.0f, 0.0f, 0.0f);
		myGL.glVertex3f(0.0f, 0.0f, 0.0f);
		myGL.glEnd();

		myGL.glPopMatrix();

		myGL.glFlush();
	}

	public void spinDisplay(int value) {
//    	spin += 2.0f;
		if (spin >= 90.0f)
//	    spin = spin - 360.0f;
			flag = -1;
		if (spin <= 0)
			flag = 1;
		spin = spin + flag * 0.6f;
		aL4 = aL4 + flag * 0.6f;
		myUT.glutPostRedisplay();
		myUT.glutTimerFunc(20, "spinDisplay", 1);
	}

	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(-50.0f, 50.0f, -50.0f, 50.0f, -1.0f, 1.0f);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
	}

	/* ARGSUSED2 */
	public void mouse(int button, int state, int x, int y) {
		if (button == GLUT.GLUT_LEFT_BUTTON) {
			if (state == GLUT.GLUT_DOWN) {
//	    myUT.glutGet)
//		myUT.glutIdleFunc ("spinDisplay");
				myUT.glutTimerFunc(20, "spinDisplay", 1);
			}
		} else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
			if (state == GLUT.GLUT_DOWN) {
				myUT.glutIdleFunc(null);
			}
		}
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'x':
			System.exit(0);
		default:
			break;
		}
	}

	/*
	 * Request double buffer display mode. Register mouse input callback functions
	 */
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutReshapeFunc("myReshape");
		myUT.glutMouseFunc("mouse");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		CauE mainCanvas = new CauE();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}
