package lab1;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Bai1b extends GLCanvas{
	public void circle () {
		/* clear all pixels */
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);

		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glBegin(GL.GL_LINE_LOOP);
		for (int i = 0; i < 360; i++) {
			double angle = 2 * Math.PI * i / 360; // 6.2832 represents 2*PI
			float x = (float) (7 * Math.cos(angle));
			float y = (float) (7 * Math.sin(angle));
			myGL.glVertex2f(x, y);
		}
		myGL.glEnd();
		myGL.glBegin(GL.GL_LINE_LOOP);
		for (int i = 0; i < 360; i++) {
			double angle = 2 * Math.PI * i / 360; // 6.2832 represents 2*PI
			float x = (float) (4 * Math.cos(angle));
			float y = (float) (4 * Math.sin(angle));
			myGL.glVertex2f(x, y);
		}

		myGL.glEnd();
		myGL.glFlush();
	}
public void drawSin() {
	myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
	myGL.glColor3f(1.0f, 1.0f, 1.0f);
	myGL.glBegin(GL.GL_LINE_LOOP);
	for (int i = 0; i < 360; i++) {
		float x = (float)Math.sinh(i);
		float y = (float)Math.sin(x);
		myGL.glVertex2f(x,y);
		}
	myGL.glEnd();
	myGL.glFlush();
}
	

	public void keyboard(char key, int x, int y) {
		switch (key) {
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
		myGL.glOrtho(-10.0f, 10.0f, -10.0f, 10.0f, 0.0f, 0.0f);
	}

	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("circle");
//		myUT.glutDisplayFunc("drawSin");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 527);
		Bai1b mainCanvas = new Bai1b();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}

}
