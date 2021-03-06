package lab2;

/*
 *  doublebuffer.java
 *  This is a simple double buffered program.
 *  Pressing the left mouse button rotates the rectangle.
 *  Pressing the middle mouse button stops the rotation.
 */

import java.awt.Frame;

import java.io.IOException;
import java.lang.String;
import java.lang.System;

import jgl.GL;
import jgl.GLUT;
import jgl.GLCanvas;

// "double" is a reserved word in Java
public class Cau2E extends GLCanvas {

    private float spin = 0;
    private int flag = 1;
    private int flag1 = 1;
    private int flag2 = 1;
    public double aL4=0;
    public double aL3=0;
    public double aL2=0;
    

    public void display () {
	myGL.glClear (GL.GL_COLOR_BUFFER_BIT);
//	myGL.glPushMatrix (); 
//	myGL.glRotatef (spin, 0.0f, 0.0f, 1.0f);
//	myGL.glColor3f (1.0f, 1.0f, 1.0f);
//	myGL.glRectf (-25.0f, -25.0f, 25.0f, 25.0f);
//	myGL.glPopMatrix ();
	myGL.glPushMatrix();
	myGL.glTranslated(0, -45, 0);
//	Line 1
	myGL.glBegin(GL.GL_LINES);
	myGL.glColor3f (1.0f, 1.0f, 1.0f);
    myGL.glVertex3f (0.0f, 0.0f, 0.0f);
    myGL.glVertex3f (0.0f, 25.0f, 0.0f);
	myGL.glEnd();
//	Line 2
	myGL.glTranslated(0, 25, 0);
	myGL.glRotated(aL2, 0, 0,1);
	myGL.glTranslated(0, -25, 0);
	myGL.glBegin(GL.GL_LINES);
	myGL.glColor3f (0.0f, 0.0f, 1.0f);
    myGL.glVertex3f (0.0f, 25.0f, 0.0f);
    myGL.glVertex3f (25.0f, 25.0f, 0.0f);
	myGL.glEnd();	
//	Line 3
	myGL.glTranslated(25, 25, 0);
	myGL.glRotated(aL3, 0, 0,1);
	myGL.glTranslated(-25, -25, 0);
	myGL.glBegin(GL.GL_LINES);
	myGL.glColor3f (1.0f, 0.0f, 0.0f);
    myGL.glVertex3f (25.0f, 25.0f, 0.0f);
    myGL.glVertex3f (25.0f, 0.0f, 0.0f);
	myGL.glEnd();	
//	Line 4
	myGL.glTranslated(25, 0, 0);
	myGL.glRotated(aL4, 0, 0 ,2);
	myGL.glTranslated(-25, 0, 0);
	myGL.glBegin(GL.GL_LINES);
	myGL.glColor3f (1.0f, 0.0f, 1.0f);
    myGL.glVertex3f (25.0f, 0.0f, 0.0f);
    myGL.glVertex3f (0.0f, 0.0f, 0.0f);
	myGL.glEnd();	

	myGL.glPopMatrix();

	myGL.glFlush ();
    }

    public void spinDisplay (int value) {
//    	spin += 2.0f;
//	if (spin >=90.0f) 
//	    spin = spin - 360.0f;
//		flag = -1;
//	if (spin <= 0)
//		flag = 1;
//	spin = spin + flag*0.2f;
//	aL2=aL3 + flag*0.2f;
//	aL3=aL4 + flag*0.2f;
//	aL4=aL4 + flag*0.2f;
	
	if (aL4 < 90.0f) 
		aL4=aL4 + flag*0.2f;
//    	aL4=90.0f;
	if (aL3 < 90.0f && aL4 >= 90.0f ) 
		aL3=aL3 + flag*0.2f;
//    	aL3=90.0f;
	if (aL2 < 90.0f&& aL4 >= 90.0f && aL3 >= 90.0f ) 
		aL2=aL2 + flag*0.2f;

	
	myUT.glutPostRedisplay ();
	myUT.glutTimerFunc(20, "spinDisplay", 1);
    }

    private void myinit () {
	myGL.glClearColor (0.0f, 0.0f, 0.0f, 0.0f);
	myGL.glShadeModel (GL.GL_FLAT);
    }

    public void myReshape (int w, int h) {
    	myGL.glViewport (0, 0, w, h);
	myGL.glMatrixMode (GL.GL_PROJECTION);
	myGL.glLoadIdentity ();
	myGL.glOrtho (-50.0f, 50.0f, -50.0f, 50.0f, -1.0f,  1.0f);
	myGL.glMatrixMode (GL.GL_MODELVIEW);
	myGL.glLoadIdentity ();
    }
 
    /* ARGSUSED2 */
    public void mouse (int button, int state, int x, int y) {
	if (button == GLUT.GLUT_LEFT_BUTTON) {
	    if (state == GLUT.GLUT_DOWN) {
//	    myUT.glutGet)
//		myUT.glutIdleFunc ("spinDisplay");
		myUT.glutTimerFunc(20, "spinDisplay", 1);
	    }
	} else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
	    if (state == GLUT.GLUT_DOWN) {
		myUT.glutIdleFunc (null);
	    }
	}
    }

    public void keyboard (char key, int x, int y) {
	switch (key) {
	    case 27:
		System.exit(0);
	    default:
		break;
	}
    }

    /* 
     *  Request double buffer display mode.
     *  Register mouse input callback functions
     */
    public void init () {
	myUT.glutInitWindowSize (500, 500);
	myUT.glutInitWindowPosition (0, 0);
	myUT.glutCreateWindow (this);
	myinit ();
	myUT.glutDisplayFunc ("display");
	myUT.glutReshapeFunc ("myReshape");
	myUT.glutMouseFunc ("mouse");
	myUT.glutKeyboardFunc ("keyboard");
	myUT.glutMainLoop ();
    }

    static public void main (String args[]) throws IOException {
	Frame mainFrame = new Frame ();
	mainFrame.setSize (508, 527);
	Cau2E mainCanvas = new Cau2E ();
	mainCanvas.init();
	mainFrame.add (mainCanvas);
	mainFrame.setVisible (true);
    }

}
