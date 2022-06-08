package lab2;

/*
 * hello.java
 * This is a simple, introductory OpenGL program.
 */

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class moveBall extends GLCanvas {
	int move = 0;
	int X,Y,Z=0;
	int v,t = 0;

	public void display() {
		/* clear all pixels */
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);

		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glBegin(GL.GL_POLYGON);
		myGL.glVertex3d(-20, -16, -1);
		myGL.glVertex3d(-6, -16, -1);
		myGL.glVertex3d(6, 0, 0);
		myGL.glVertex3d(-6, 0, 0);

		myGL.glEnd();
		myGL.glPushMatrix();
		myGL.glRotatef(move, 1.0f, 1.0f, 1.0f);
		myGL.glColor3f(1.0f, 0.0f, 1.0f);
		myUT.glutWireSphere(0.5, 20, 10);
		myGL.glPopMatrix();

		myGL.glFlush();
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'd':

			move = (move + 10)%360;

			myUT.glutPostRedisplay();
			break;
		case 'D':
			move = (move - 10)%360;
			X=X+2;
			Y=Y+2;
			Z=Z-1;
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
myGL.glShadeModel(GL.GL_FLAT);
	}
	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(120.0, (double)w/(double)h, 1.0,20.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGLU.gluLookAt(0, 0, 2, 0, 0, 0, 0, 1, 0);
		myGL.glTranslated(0, 0, -10);
	}
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutReshapeFunc ("myReshape");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		moveBall mainCanvas = new moveBall();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}

}