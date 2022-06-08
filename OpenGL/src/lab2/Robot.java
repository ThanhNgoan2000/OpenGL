package lab2;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Robot extends GLCanvas {
	private static int shoulderleft, elbowleft, shoulderright, elbowright,body = 0;
    private static int grainleft,kneeleft,grainright,kneeright =0;
	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
	}

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glPushMatrix();


//
//		// head
//		myGL.glPushMatrix();
//		myGL.glTranslatef(2.5f, 1.0f, 0.0f);
//		myGL.glScalef(1.0f, 1.1f, 1.0f);
//		myUT.glutWireCube(1.0);
//		myGL.glPopMatrix();
//		// neck
//		myGL.glPushMatrix();
//		myGL.glTranslatef(2.5f, 0.2f, 0.0f);
//		myGL.glScalef(0.3f, 0.7f, 1.0f);
//		myUT.glutWireCube(1.0);
//		myGL.glPopMatrix();
//
		// body
		myGL.glPushMatrix();
		myGL.glTranslatef(0f, 1.0f, 0.0f);
		myGL.glRotatef(body, 0, body, 1);
		myGL.glPushMatrix();
		myGL.glScalef(1.0f, 2.0f, 1.0f);
		myUT.glutWireCube(1.0);
		myGL.glPopMatrix();

			myGL.glPushMatrix();
	//		 left hand
			myGL.glTranslatef(-0.5f, 1.0f, 0.0f);
			myGL.glRotatef(shoulderleft, 0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(-0.75f, 0.0f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(1.5f, 0.4f, 0.7f);
			myUT.glutWireCube(1.0);
			myGL.glPopMatrix();

			myGL.glTranslatef(-0.5f, 0.0f, 0.0f);
			myGL.glRotatef(elbowleft, 0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(-1.0f, 0.0f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(1.5f, 0.4f, 0.7f);
			myUT.glutWireCube(1.0);
			myGL.glPopMatrix();
			myGL.glPopMatrix();



			myGL.glPushMatrix();
			// right hand
			myGL.glTranslatef(0.5f, 1.0f, 0.0f);
			myGL.glRotatef(shoulderright, 0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(0.75f, 0.0f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(1.5f, 0.4f, 0.7f);
			myUT.glutWireCube(1.0);
			myGL.glPopMatrix();

			myGL.glTranslatef(0.5f, 0.0f, 0.0f);
			myGL.glRotatef(elbowright, 0.0f, 0.0f, 1.0f);
			myGL.glTranslatef(1.0f, 0.0f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(1.5f, 0.4f, 0.7f);
			myUT.glutWireCube(1.0);
			myGL.glPopMatrix();
			myGL.glPopMatrix();

			myGL.glPopMatrix();

			myGL.glPushMatrix();
			// left leg

			myGL.glTranslatef(0.0f, -0.75f, 0.0f);
			myGL.glRotatef(grainleft,0.0f, 0.0f,0.0f);
			myGL.glTranslatef(-0.25f, 0.0f, 0.0f);
			myGL.glPushMatrix();
			myGL.glScalef(0.5f, 1.5f, 0.8f);
			myUT.glutWireCube(1.0);
			myGL.glPopMatrix();

//			myGL.glTranslatef(0.0f, -1.0f, 0.0f);
//			myGL.glRotatef(grainleft, 0.0f, 0.0f, 1.0f);
//			myGL.glTranslatef(-0.25f, 0.0f, 0.0f);
//			myGL.glPushMatrix();
//			myGL.glScalef(0.5f, 1.5f, 0.8f);
//			myUT.glutWireCube(1.0);
//			myGL.glPopMatrix();
//			myGL.glPopMatrix();


		myGL.glPopMatrix();
//// left leg
//		myGL.glPushMatrix();
//

//
//
//		myGL.glPopMatrix();
//
//		// right leg
//
//		myGL.glPushMatrix();
//
//		myGL.glPushMatrix();
//		myGL.glTranslatef(3.0f, -2.5f, 0.0f);
//		myGL.glScalef(0.3f, 1.5f, 0.8f);
//		myUT.glutWireCube(1.0);
//		myGL.glPopMatrix();
//
//		myGL.glPushMatrix();
//		myGL.glTranslatef(3.0f, -4.0f, 0.0f);
//		myGL.glScalef(0.3f, 1.5f, 0.8f);
//		myUT.glutWireCube(1.0);
//		myGL.glPopMatrix();
//
//		myGL.glPopMatrix();

		myGL.glFlush();
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(100.0, (double) w / (double) h, 1.0, 20.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGL.glTranslatef(0.0f, 0.0f, -5.0f);
	}

	/* ARGSUSED1 */
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 's':
			shoulderleft = (shoulderleft + 5) % 360;
			myUT.glutPostRedisplay();

			break;
		case 'r':
			shoulderright = (shoulderright + 5) % 360;
			myUT.glutPostRedisplay();

			break;
		case 'e':
			elbowleft = (elbowleft + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'w':
			elbowright = (elbowright + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 't':
			grainleft = (grainleft + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'b':
			body = (body + 3) % 360;
			myUT.glutPostRedisplay();
			break;

		case 0:
			System.exit(0);
		default:
			break;
		}
	}

	public void init() {
		myUT.glutInitWindowSize(400, 400);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutReshapeFunc("myReshape");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(600, 500);
		mainFrame.setLocationRelativeTo(null);
		Robot mainCanvas = new Robot();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}

}
