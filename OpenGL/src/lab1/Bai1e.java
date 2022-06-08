package lab1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;

public class Bai1e extends GLCanvas {
	public void display() {
		float x, y, z, alpha, beta; // Storage for coordinates and angles
		float radius = 0.3f;
		int gradation = 10;
		myGL.glColor3f(1.0f, 0.0f, 1.0f);
		myGL.glTranslated(0.5, 0.5, 0);
		for (alpha = 0.0f; alpha < Math.PI; alpha += Math.PI / gradation) {
			myGL.glBegin(GL.GL_TRIANGLE_STRIP);
			for (beta = 0.0f; beta < 2.01 * Math.PI / 2; beta += Math.PI / gradation) {
				x = (float) (radius * Math.cos(beta) * Math.cos(alpha));
				y = (float) (radius * Math.sin(beta));
				z = (float) (radius * Math.cos(beta) * Math.sin(alpha));
				myGL.glVertex3f(x, y, z);
				x = (float) (radius * Math.cos(beta) * Math.cos(alpha + Math.PI / gradation));
				y = (float) (radius * Math.sin(beta));
				z = (float) (radius * Math.cos(beta) * Math.sin(alpha + Math.PI / gradation));
				myGL.glVertex3f(x, y, z);
			}
			myGL.glEnd();
		}
		myGL.glFlush();
	}

	private void myinit() {
		/* select clearing color */
		myGL.glClearColor(0.0f, 1.0f, 1.0f, 0.0f);

		/* initialize viewing values */
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f);
	}

	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutMainLoop();
	}

	public static void main(String args[]) throws IOException {
		Frame frame = new Frame();
		frame.setSize(500, 500);
		Bai1e cd1 = new Bai1e();
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
