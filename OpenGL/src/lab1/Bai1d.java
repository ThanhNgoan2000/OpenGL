package lab1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Bai1d extends GLCanvas {
	double rotation = 0;

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glPushMatrix();

		myGL.glRotated(rotation, 0, 0, 1);
		myGL.glBegin(GL.GL_LINES);

		myGL.glVertex2d(0, 0);
		myGL.glVertex2d(0, 3);
		myGL.glEnd();

		myGL.glTranslatef(0, 3, 0);
		myGL.glRotated(rotation, 0, 0, 1);
		myGL.glTranslated(0, -3, 0);
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2d(0, 3);
		myGL.glVertex2d(3, 3);
		myGL.glEnd();

		myGL.glTranslatef(3, 3, 0);
		myGL.glRotated(rotation, 0, 0, 1);
		myGL.glTranslated(-3, -3, 0);
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2d(3, 3);
		myGL.glVertex2d(3, 0);
		myGL.glEnd();

		myGL.glTranslatef(3, 0, 0);
		myGL.glRotated(rotation, 0, 0, 1);
		myGL.glTranslated(-3, 0, 0);
		myGL.glBegin(GL.GL_LINES);

		myGL.glVertex2d(3, 0);
		myGL.glVertex2d(0, 0);
		myGL.glEnd();
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void init() {

		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutReshapeFunc("myReshape");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
		myGL.glOrtho(1, 1, 0, 0, 0, 0);
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(70, (double) w / (double) h, 1.0, 20.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGL.glTranslatef(0f, 0f, -10.0f);
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'r':
			rotation += 5;
			myUT.glutPostRedisplay();
			break;
		case 'R':
			rotation -= 5;
			myUT.glutPostRedisplay();
			break;

		case 27:
			System.exit(0);
		default:
			break;
		}
	}

	public static void main(String args[]) throws IOException {
		Frame frame = new Frame();
		frame.setSize(500, 500);
		Bai1d cd1 = new Bai1d();
		cd1.init();
		frame.add(cd1);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				frame.dispose();
			}
		});
	}

}