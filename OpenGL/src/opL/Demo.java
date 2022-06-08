package opL;

//	Trình đơn chính
//	Đồ Hoạ Máy Tính_Học kỳ 2_2021-2022
//	Học kỳ 2_2021-2022
//	2. Giới thiệu - Thực hành
//	Công Song Nguyễn Đức
//	•
//	14:43
//	- Thư viện jgl
//	- Ví dụ mẫu
//
//	jgl.jar
//	Tệp không xác định
//
//	hello.java
//	Java
//	Nhận xét của lớp học
//
//	2. Giới thiệu - Thực hành
/*
 * hello.java
 * This is a simple, introductory OpenGL program.
 */

import java.awt.Frame;

import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLCanvas;

public class Demo extends GLCanvas {
	int a = 10;
	double X = 0.5, Y = 0.5;
	double R = 0.25;

	public void display() {
		/* clear all pixels */
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT);

		/*
		 * draw white polygon (rectangle) with corners at (0.25, 0.25, 0.0) and (0.75,
		 * 0.75, 0.0)
		 */
//		int a=100;a

		myGL.glColor3f(1.0f, 1.0f, 1.0f);
		myGL.glBegin(GL.GL_POLYGON);
		myGL.glColor3f(1.0f, 1.0f, 1.0f);

		for (double i = 0; i < 360; i = (Math.PI * 2 / 360) + i) {
			myGL.glVertex3d(X + R * Math.cos(i), Y + R * Math.sin(i), 0.0f);
			myGL.glColor3f(1.0f, 0.0f, 0.0f);
		}
		myGL.glBegin(GL.GL_POLYGON);
		myGL.glColor3f(1.0f, 1.0f, 1.0f);

		for (double i = 0; i < 360; i = (Math.PI * 2 / 360) + i) {
			myGL.glVertex3d(X + R*0.5 * Math.cos(i), Y + R*0.5 * Math.sin(i), 0.0f);
			myGL.glColor3f(1.0f, 1.0f, 0.0f);
		}

		myGL.glEnd();

		/*
		 * don't wait! start processing buffered OpenGL routines
		 */
		myGL.glFlush();
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'a':
			if (a < 100)
				a++;
			myUT.glutPostRedisplay();
			break;
		case 'A':
			if (a > 0)
				a--;
			myUT.glutPostRedisplay();
			break;
		case 'r':
			if (R < 1)
				R = R + 0.01;
			myUT.glutPostRedisplay();
			break;
		case 'R':
			if (R > 0.1)
				R = R - 0.01;
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
		myGL.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);

		/* initialize viewing values */
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGL.glOrtho(0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f);
	}

	/*
	 * Declare initial window size, position, and display mode (single buffer and
	 * RGBA). Open window with "hello" in its title bar. Call initialization
	 * routines. Register callback function to display graphics. Enter main loop and
	 * process events.
	 */
	public void init() {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	static public void main(String args[]) throws IOException {
		// Tao 1
		Frame mainFrame = new Frame();
		mainFrame.setSize(508, 524);
		Demo mainCanvas = new Demo();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}

//	hello.java
//	Đang hiển thị hello.java.
}
