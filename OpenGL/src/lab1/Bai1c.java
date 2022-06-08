package lab1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jgl.GL;
import jgl.GLCanvas;

public class Bai1c extends GLCanvas {
	public static int a = 20;
	private double z = 0.01;
	private double b = 30;

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);
		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glLoadIdentity();
		myGL.glRotated(b, 1, 0, 0);
		myGL.glBegin(GL.GL_LINE_STRIP);
		double t = 0;
		for (int i = 0; i <= 1200; i++) {
			t += z;
			myGL.glVertex3d(Math.cos((Math.PI / 180) * i) * a, Math.sin((Math.PI / 180) * i) * a, t);
		}
		myGL.glEnd();
		myGL.glFlush();
	}

	private void myinit() {
		/* select clearing color */
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		/* initialize viewing values */
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();

		myGL.glOrtho(-100.0f, 100.0f, -100.0f, 100.0f, 0.0f, 0.0f);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
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

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 's':
			a++;
			myUT.glutPostRedisplay();
			break;
		case 'S':
			a--;
			myUT.glutPostRedisplay();
			break;
		case 'Z':
			z -= 0.01;
			myUT.glutPostRedisplay();
			break;
		case 'z':
			z += 0.01;
			myUT.glutPostRedisplay();
			break;
		case 'b':
			b += 1;
			myUT.glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		default:
			break;

		}
	}

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setSize(500, 500);
		Bai1c cd1 = new Bai1c();
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