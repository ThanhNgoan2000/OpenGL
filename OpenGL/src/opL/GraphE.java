package opL;

import java.awt.Frame;
import java.io.IOException;

import jgl.GL;
import jgl.GLCanvas;
import jgl.GLUT;

public class GraphE extends GLCanvas{
	public double spinl4 = 0;
	public int flag = 1;
	 public void display () {
			myGL.glClear (GL.GL_COLOR_BUFFER_BIT);
			myGL.glPushMatrix ();
			
			// line 1
			myGL.glBegin(GL.GL_LINES);
			myGL.glColor3f (1.0f, 1.0f, 1.0f);
			myGL.glVertex3f(0.0f,0.0f,0.0f);
			myGL.glVertex3f(0.0f,25.0f,0.0f);
			myGL.glEnd();
			// line 2
			myGL.glBegin(GL.GL_LINES);
			myGL.glTranslatef(0.0f, 25.0f, 0.0f);
			myGL.glRotated(spinl4,0.0 ,0.0, 0.1);
			myGL.glTranslatef(0.0f, -25.0f, 0.0f);
			myGL.glColor3f (0.0f, 1.0f, 1.0f);
			myGL.glVertex3f(0.0f,25.0f,0.0f);
			myGL.glVertex3f(25.0f,25.0f,0.0f);
			myGL.glEnd();
			// line 3
			myGL.glBegin(GL.GL_LINES);
			myGL.glTranslatef(25.0f, 25.0f, 0.0f);
			myGL.glRotated(spinl4,0.0 ,0.0, 0.1);
			myGL.glTranslatef(-25.0f, -25.0f, 0.0f);
			myGL.glColor3f (0.0f, 1.0f, 1.0f);
			myGL.glVertex3f(25.0f,25.0f,0.0f);
			myGL.glVertex3f(25.0f,0.0f,0.0f);
			myGL.glEnd();
			// line 4
			myGL.glBegin(GL.GL_LINES);
			myGL.glTranslatef(25.0f, 0.0f, 0.0f);
			myGL.glRotated(spinl4,0.0 ,0.0, 0.1);
			myGL.glTranslatef(-25.0f, 0.0f, 0.0f);
			myGL.glColor3f (1.0f, 1.0f, 1.0f);
			myGL.glVertex3f(25.0f,0.0f,0.0f);
			myGL.glVertex3f(0.0f,0.0f,0.0f);
			myGL.glEnd();
			
			myGL.glPopMatrix ();
			myGL.glFlush ();
		    }
	 public void spindisplay(int value) {
		 if(spinl4<90.0f) {
			 spinl4 = spinl4+ flag*0.5;
		 }
		 myUT.glutPostRedisplay();
		 myUT.glutTimerFunc(15,"spindisplay", 1);
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

	 public void keyboard (char key, int x, int y) {
			switch (key) {
			    case 27:
				System.exit(0);
			    default:
				break;
			}
		    }
	  /* ARGSUSED2 */
	    public void mouse (int button, int state, int x, int y) {
		if (button == GLUT.GLUT_LEFT_BUTTON) {
		    if (state == GLUT.GLUT_DOWN) {
//		    myUT.glutGet)
//			myUT.glutIdleFunc ("spinDisplay");
			myUT.glutTimerFunc(20, "spindisplay", 1);
		    }
		} else if (button == GLUT.GLUT_MIDDLE_BUTTON) {
		    if (state == GLUT.GLUT_DOWN) {
			myUT.glutIdleFunc (null);
		    }
		}
	    }
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
			GraphE mainCanvas = new GraphE();
			mainCanvas.init();
			mainFrame.add (mainCanvas);
			mainFrame.setVisible (true);
		    }
	

}
