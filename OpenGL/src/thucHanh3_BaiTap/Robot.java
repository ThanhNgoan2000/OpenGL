package thucHanh3_BaiTap;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Robot extends GLCanvas {
	private static int shoulder = 0, elbow= 0;
	private static int moveLR, moveUD = 0;
	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
	}

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glPushMatrix(); // I

		myGL.glPushMatrix();
		myGL.glPushMatrix(); // body
		// body
		myGL.glTranslatef(0f, 1f, 0.0f);
		myGL.glRotated((float) moveLR, moveUD, 0.0f, 1.0f);
		myGL.glScalef(1.5f, 3.0f, 1.5f);
		myUT.glutWireCube(1.0);
		myGL.glTranslatef(-1.0f, 0.0f, 0.0f);	

//		handleft
		myGL.glTranslatef(-1f,0f, 0.0f);
		
		myGL.glPushMatrix(); //1
		myGL.glTranslatef(1f, 0.0f, 0.0f);
		myGL.glRotatef((float) shoulder, 0.0f, 0.0f, 1.0f);
		myGL.glTranslatef(0f, 0.0f, 0.0f);

		myGL.glPushMatrix(); //2	
		myGL.glScalef(1.5f, 0.2f, 1.0f);
		myUT.glutWireCube(1.0);
		myGL.glPopMatrix();	// 2

		myGL.glTranslatef(-0.75f, 0.0f, 0.0f);
		myGL.glRotatef((float) elbow, 0.0f, 0.0f, 1.0f);
		myGL.glTranslatef(-0.75f, 0.0f, 0.0f);
		
		myGL.glPushMatrix();//3
		myGL.glScalef(1.5f, 0.2f, 1.0f);
		myUT.glutWireCube(1.0);
		myGL.glPopMatrix();//3
		
		myGL.glPopMatrix(); //1


		myGL.glPopMatrix(); // body
		myGL.glPopMatrix(); // I
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
			shoulder = (shoulder + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'S':
			shoulder = (shoulder - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'e':
			elbow = (elbow + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'E':
			elbow = (elbow - 5) % 360;
			myUT.glutPostRedisplay();
			break;

		case 'x':
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
