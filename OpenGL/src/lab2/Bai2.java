package lab2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Bai2 extends GLCanvas {
	int xoay =0;
	float X =0, Y =0, Z =0;
	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
	}

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glColor3f(0f, 1f, 1f);


		myGL.glPushMatrix();
		myGL.glTranslatef(X, Y, Z);
		myGL.glTranslatef(-12f, -4f,0f);
		myGL.glRotatef(xoay, X, Y, Z+1);
		myGL.glTranslatef(12f, 4f, 0);
		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-12f, -4f);
		myGL.glVertex2f(12f, -4f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(12f, -4f);
		myGL.glVertex2f(24f, 0f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(24f, 0f);
		myGL.glVertex2f(20f, 0f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(20f, 0f);
		myGL.glVertex2f(16f, 4f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(16f, 4f);
		myGL.glVertex2f(-14f, 4f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-14f, 4f);
		myGL.glVertex2f(-16f, 10f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-16f, 10f);
		myGL.glVertex2f(-22f, 10f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-22f, 10f);
		myGL.glVertex2f(-22f, 4f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-22f, 4f);
		myGL.glVertex2f(-20f, 0f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-20f, 0f);
		myGL.glVertex2f(-12f, -4f);
		myGL.glEnd();
		myGL.glPopMatrix();
		//canh duoi
		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-4f, 0f);
		myGL.glVertex2f(-8f, -12f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-8f, -12f);
		myGL.glVertex2f(-4f, -12f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-4f, -12f);
		myGL.glVertex2f(6f, 0f);
		myGL.glEnd();
		myGL.glPopMatrix();
		//canh tren
		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-5f, 4f);
		myGL.glVertex2f(-8f, 12f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-8f, 12f);
		myGL.glVertex2f(-4f, 12f);
		myGL.glEnd();
		myGL.glPopMatrix();

		myGL.glPushMatrix();
		myGL.glBegin(GL.GL_LINES);
		myGL.glVertex2f(-4f, 12f);
		myGL.glVertex2f(3f, 4f);
		myGL.glEnd();
		myGL.glPopMatrix();


		myGL.glPopMatrix();
		myGL.glFlush ();
    }



	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(160.0, (double) w / (double) h, 1.0, 20.0);

		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGL.glTranslated(0, 0, -10);
	}
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'q':
			xoay = (xoay + 10) % 360;

			myUT.glutPostRedisplay();
			break;
		case 'a':

			X= X+0.2f ;
			Y=Y+0.2f;

			myUT.glutPostRedisplay();
			break;
		case 'z':
			X= X-0.2f ;
			Y=Y-0.2f;

			myUT.glutPostRedisplay();
			break;
		case 's':
			Y= Y+0.2f;

			myUT.glutPostRedisplay();
			break;

		case 'd':
			Z= Z+0.2f;

			myUT.glutPostRedisplay();
			break;
		case't':
			Z = Z -0.3f;
			myUT.glutPostRedisplay();
			break;

		case 27:
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
		myUT.glutReshapeFunc("myReshape");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	public static void main(String args[]) throws IOException {
		Frame frame = new Frame();
		frame.setSize(500, 500);
		Bai2 cd1 = new Bai2();
		cd1.init();
		frame.add(cd1);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				frame.dispose();
			}
		});
	}

}