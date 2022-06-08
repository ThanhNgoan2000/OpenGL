package lab2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import jgl.GL;
import jgl.GLCanvas;

public class Robot2 extends GLCanvas {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/* Create checkerboard texture */
	private int width = 256;
	private int height = 256;

	private byte img1[][][] = new byte[width][height][4];
	private byte img2[][][] = new byte[width][height][4];
	private byte img3[][][] = new byte[width][height][4];
	private byte img4[][][] = new byte[width][height][4];
	private byte img5[][][] = new byte[width][height][4];

	private BufferedImage mimg1;
	private BufferedImage mimg2;
	private BufferedImage mimg3;
	private BufferedImage mimg4;
	private BufferedImage mimg5;

	private int texName[] = new int[5];

	// <!-- Thuyen -->
	private static final float ctrlpoints[][][] = {
			{ { -0f, 3f, 0f }, { -0f, 3f, 0f }, { 0f, 3f, 0f }, { 0f, 3f, 0f } },
			{ { -2f, 1.5f, 1f }, { -1f, 1.5f, 3f }, { 1f, 1.5f, 3f }, { 2f, 1.5f, 1f } },
			{ { -2f, -1.5f, 1f }, { -1f, -1.5f, 3f }, { 1f, -1.5f, 3f }, { 2f, -1.5f, 1f } },
			{ { -0f, -3f, 0f }, { -0f, -3f, 0f }, { 0f, -3f, 0f }, { 0f, -3f, 0f } } };

	// <!-- quay -->
	private static float xx = 0;
	private static float yy = 0;
	private static float zz = 0;

	// <!-- lock at-->
	private static float lx = 0;
	private static float ly = 3.2f;
	private static float lz = 10f;
	private static float lxx = 0;
	private static float lyy = 0;

	// <!-- near far khung-->
	private static float nfb = 0;

	// rotbot
	private float degRun = 0;
	private float haTay = 0;
	private float attack = 0;
	private float attack2 = 0;
	private float attack3 = 0;
	private float attack4 = 0;
	private boolean directionRun = false;
	private float test1 = 0;
	private float test2 = 0;
	private float test3 = 0;

	private float testQ1 = 100;
	private float testQ2 = 0;
	private float testQ3 = 0;

	// <3 biến để check màu>
	private float m1 = 0;
	private float m2 = 0;
	private float m3 = 0;

	private void makeimg() throws IOException {

		mimg1 = ImageIO.read(new File("image/RoadStripBottom.jpg"));
		mimg2 = ImageIO.read(new File("image/RoadStripCenter.jpg"));
		mimg3 = ImageIO.read(new File("image/RoadStripLeft.jpg"));
		mimg4 = ImageIO.read(new File("image/RoadStripRight.jpg"));
		mimg5 = ImageIO.read(new File("image/RoadStripTop.jpg"));

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color color1 = new Color(mimg1.getRGB(i, j));
				Color color2 = new Color(mimg2.getRGB(i, j));
				Color color3 = new Color(mimg3.getRGB(i, j));
				Color color4 = new Color(mimg4.getRGB(i, j));
				Color color5 = new Color(mimg5.getRGB(i, j));
				img1[i][j][0] = (byte) color1.getRed();
				img1[i][j][1] = (byte) color1.getGreen();
				img1[i][j][2] = (byte) color1.getBlue();
				img1[i][j][3] = (byte) 255;
				img2[i][j][0] = (byte) color2.getRed();
				img2[i][j][1] = (byte) color2.getGreen();
				img2[i][j][2] = (byte) color2.getBlue();
				img2[i][j][3] = (byte) 255;
				img3[i][j][0] = (byte) color3.getRed();
				img3[i][j][1] = (byte) color3.getGreen();
				img3[i][j][2] = (byte) color3.getBlue();
				img3[i][j][3] = (byte) 255;
				img4[i][j][0] = (byte) color4.getRed();
				img4[i][j][1] = (byte) color4.getGreen();
				img4[i][j][2] = (byte) color4.getBlue();
				img4[i][j][3] = (byte) 255;
				img5[i][j][0] = (byte) color5.getRed();
				img5[i][j][1] = (byte) color5.getGreen();
				img5[i][j][2] = (byte) color5.getBlue();
				img5[i][j][3] = (byte) 255;

			}
		}
	}

	private void myinit() throws IOException {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
		myGL.glEnable(GL.GL_DEPTH_TEST);
		myGL.glEnable(GL.GL_TEXTURE_2D);
		makeimg();
		myGL.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);

		myGL.glGenTextures(5, texName);
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img1);

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img2);

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img3);

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img4);

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		myGL.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		myGL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
		myGL.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, width, height, 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img5);
		myGL.glDisable(GL.GL_TEXTURE_2D);

		myGL.glEnable(GL.GL_TEXTURE_2D);
		myGL.glMap2f(GL.GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4, 0.0f, 1.0f, 12, 4, ctrlpoints);
		myGL.glEnable(GL.GL_MAP2_VERTEX_3);
		myGL.glEnable(GL.GL_AUTO_NORMAL);
		myGL.glEnable(GL.GL_NORMALIZE);
		myGL.glMapGrid2f(20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
		myGL.glDisable(GL.GL_TEXTURE_2D);

	}

	private void loadFrame() {
		myGL.glPushMatrix();
		// ------------
		float ambientt[] = { 5f, 5f, 5f, 1f };
		float positiont[] = { 0f, 0f, 1.5f, 1.0f };
		float mat_diffuset[] = { -0.2f, -0.2f, -0.2f, 1.0f };
		float mat_speculart[] = { 2.0f, 2.0f, 2.0f, 1.0f };
		float mat_shininesst[] = { 10.0f };

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambientt);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, positiont);

		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuset);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_speculart);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininesst);
		// -------------------------
		myGL.glEnable(GL.GL_TEXTURE_2D);
		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[0]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-5.0f, -5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(5f, -5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(5f, -5.0f, 5f - nfb);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-5.0f, -5.0f, 5f - nfb);
		myGL.glEnd();

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[1]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-5.0f, 5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(5f, 5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(5f, -5.0f, -15f - nfb);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-5.0f, -5.0f, -15f - nfb);
		myGL.glEnd();

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[2]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-5.0f, 5.0f, 5f - nfb);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(-5f, 5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(-5f, -5.0f, -15f - nfb);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-5.0f, -5.0f, 5f - nfb);
		myGL.glEnd();

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[3]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(5f, 5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(5.0f, 5.0f, 5f - nfb);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(5.0f, -5.0f, 5f - nfb);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(5f, -5.0f, -15f - nfb);
		myGL.glEnd();

		myGL.glBindTexture(GL.GL_TEXTURE_2D, texName[4]);
		myGL.glBegin(GL.GL_QUADS);
		myGL.glTexCoord2f(0.0f, 0.0f);
		myGL.glVertex3f(-5f, 5.0f, 5f - nfb);
		myGL.glTexCoord2f(0.0f, 1.0f);
		myGL.glVertex3f(-5f, 5.0f, -15f - nfb);
		myGL.glTexCoord2f(1.0f, 1.0f);
		myGL.glVertex3f(5.0f, 5.0f, -15.0f - nfb);
		myGL.glTexCoord2f(1.0f, 0.0f);
		myGL.glVertex3f(5.0f, 5.0f, 5f - nfb);
		myGL.glEnd();
		myGL.glDisable(GL.GL_TEXTURE_2D);
		myGL.glPopMatrix();
		// *****************************
	}

	public void display() {
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		myGL.glLoadIdentity();
		myGLU.gluLookAt(lx, ly, lz, lxx, lyy, 0, 0, 1, 0);

		// *****************************
		// *****************************

		loadFrame();
		// robot1
//		robot1();

		// robot2
		robot2();

		// locxay
//		locxoay();

		myGL.glFlush();
	}

	private void locxoay() {
		myGL.glPushMatrix();
		float ambienttt[] = { 1.5f, 1f, 1f, 1f };
		float positiontt[] = { 0f, 0f, 1.5f, 1.0f };
		float mat_diffusett[] = { -0.2f, -0.2f, -0.2f, 1.0f };
		float mat_speculartt[] = { 2.0f, 2.0f, 2.0f, 1.0f };
		float mat_shininesstt[] = { 10.0f };

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambienttt);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, positiontt);

		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffusett);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_speculartt);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininesstt);

		myGL.glScaled(0.2, 0.2, 0.2);
		myGL.glTranslated(0, -19, -19 + testQ1);
		myGL.glRotatef(90, 1f, 0f, 0f);

		myUT.glutSolidCone(2, 6, 20, 10);
		myGL.glPopMatrix();
	}

	public void robot1() {
		// <!-- main push-->
		myGL.glPushMatrix();

		// <!-- image and metar -->
		float ambienttt[] = { 1.5f, 1f, 1f, 1f };
		float positiontt[] = { 0f, 0f, 1.5f, 1.0f };
		float mat_diffusett[] = { -0.2f, -0.2f, -0.2f, 1.0f };
		float mat_speculartt[] = { 2.0f, 2.0f, 2.0f, 1.0f };
		float mat_shininesstt[] = { 10.0f };

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambienttt);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, positiontt);

		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffusett);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_speculartt);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininesstt);

		// <!-- Scaled and translate -->
		myGL.glScaled(0.1, 0.1, 0.1);

		myGL.glTranslated(0 + test1, -40 + test2, 35 + test3);

		myGL.glRotatef(xx, 1.0f, 0f, 0f);
		myGL.glRotatef(yy + 180, 0f, 1f, 0f);
		myGL.glRotatef(zz, 0f, 0f, 1f);

		// body
		myGL.glPushMatrix();

		// <!-- thân -->
		myGL.glPushMatrix();
		myGL.glTranslated(0, 1, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 2, 2, 2, 40, 1);
		myGL.glPopMatrix();

		// <!-- đầu -->
		myGL.glPushMatrix();
		myGL.glScaled(0.75, 1, 0.75);
		myGL.glTranslated(0, 1, 0);
		myUT.glutSolidSphere(2.6, 50, 50);
		myGL.glPopMatrix();

		// <!-- xương chậu -->
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.25, 1);
		myGL.glTranslated(0, -4.1, 0);
		myUT.glutSolidSphere(2, 20, 20);
		myGL.glPopMatrix();

		// <!-- mắc kính -->
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.75, 1);
		myGL.glTranslated(0, 1.25, 1.25);
		myUT.glutSolidSphere(1.25, 50, 50);
		myGL.glPopMatrix();
		// <!-- đuôi tóc -->
		myGL.glPushMatrix();
		myGL.glTranslated(0, 3.25, -1.5);
		myGL.glRotated(20, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.2, 0.25, 1, 10, 1);
		myGL.glPopMatrix();

		// <!-- tóc -->
		myGL.glPushMatrix();
		myGL.glScaled(1, 1, 0.5);
		myGL.glTranslated(0, 3.8, -4);
		myGL.glRotated(20, 1, 0, 0);
		myUT.glutSolidSphere(1.5, 20, 20);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(0, 4.2, -4);
		myGL.glRotated(10, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 1.5, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(0, 4.25, -4.95);
		myGL.glRotated(5, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.5, 1, 1, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(0, 4, -5.85);
		myGL.glRotated(-10, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0, 0.55, 1, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(1.1, 0.8, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(120, 0, 1, 0);
		myUT.glutSolidCone(1.6, 2.5, 20, 10);
		myGL.glPopMatrix();

		// <!-- end body>
		myGL.glPopMatrix();

		// <!-- chan trai>
		myGL.glPushMatrix();
		myGL.glRotated(degRun, 1, 0, 0);
		myGL.glPushMatrix();
		myGL.glTranslated(-1, -1.15, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 0.75, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.5, 1);
		myGL.glTranslated(-1, -6, 0);
		myUT.glutSolidSphere(0.75, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- chan phai -->
		myGL.glPushMatrix();
		myGL.glRotated(-degRun, 1, 0, 0);
		myGL.glPushMatrix();
		myGL.glTranslated(1, -1.15, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 0.75, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.5, 1);
		myGL.glTranslated(1, -6, 0);
		myUT.glutSolidSphere(0.75, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- tay phai -->
		myGL.glPushMatrix();

		myGL.glTranslated(1.75, 0.5, 0);
		myGL.glRotated(haTay, 0, 0, 1);
		myGL.glTranslated(-1.75, -0.5, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(1.75, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.75, 0.6, 1.25, 20, 1);
		myGL.glPopMatrix();

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(attack2, 0, 0, 1);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(attack3, 0, 1, 0);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(attack4, 1, 0, 0);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.6, 0.4, 1.25, 20, 1);
		myGL.glPopMatrix();

		myGL.glTranslated(4, 0.5, 0);
		myGL.glRotated(attack, 0, 0, 1);
		myGL.glTranslated(-4, -0.5, 0);

		// <!-- bàn tay cầm kiếm -->
		myGL.glPushMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4, 0.5, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		// <!-- kiem -->
		myGL.glPushMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, -0.75, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 1.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.2, 0.2, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.25, 0.25);
		myGL.glTranslated(4.1, 6.75, 0);
		myUT.glutSolidCube(2);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 5.9, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.55, 0.4, 4, 4, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 6.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0, 0.55, 0.5, 4, 10);
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- tay trai -->
		myGL.glPushMatrix();

		myGL.glTranslated(-1.5, 0.25, 0);
		myGL.glRotated(-haTay, 0, 0, 1);
		myGL.glTranslated(1.5, -0.25, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(-2.75, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.6, 0.75, 1.25, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(-4, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.4, 0.6, 1.25, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(-4, 0.5, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- main pop -- >
		myGL.glPopMatrix();
	}

	public void robot2() {
		// <!-- main push-->
		myGL.glPushMatrix();

		// <!-- image and metar -->
		float ambienttt[] = { 1.5f, 1f, 1f, 1f };
		float positiontt[] = { 0f, 0f, 1.5f, 1.0f };
		float mat_diffusett[] = { -0.2f, -0.2f, -0.2f, 1.0f };
		float mat_speculartt[] = { 2.0f, 2.0f, 2.0f, 1.0f };
		float mat_shininesstt[] = { 10.0f };

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambienttt);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, positiontt);

		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffusett);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_speculartt);
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininesstt);

//		// <!-- Scaled and translate -->
//		myGL.glScaled(0.1, 0.1, 0.1);
//
//		myGL.glTranslated(0, -40 + testQ2, -65);
//
//		myGL.glRotatef(testQ3, 1.0f, 0f, 0f);

		// <!-- Scaled and translate -->
		myGL.glScaled(0.1, 0.1, 0.1);

//		di chuyển
//		myGL.glTranslated(-5 + test1, -40 + test2, 30 + test3);
		myGL.glTranslated(0, -40 + testQ2, -65);
		myGL.glRotatef(testQ3, 1.0f, 0f, 0f);


		myGL.glRotatef(xx, 1.0f, 0f, 0f);
		myGL.glRotatef(yy + 180, 0f, 1f, 0f);
		myGL.glRotatef(zz, 0f, 0f, 1f);
		myGL.glRotatef(0, 2f, 0f, 1f);
//

		// body
		myGL.glPushMatrix();

		// <!-- thân -->
		myGL.glPushMatrix();
		myGL.glTranslated(0, 1, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 2, 2, 2, 40, 1);
		myGL.glPopMatrix();

		// <!-- đầu -->
		myGL.glPushMatrix();
		myGL.glScaled(0.75, 1, 0.75);
		myGL.glTranslated(0, 1, 0);
		myUT.glutSolidSphere(2.6, 50, 50);
		myGL.glPopMatrix();

		// <!-- xương chậu -->
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.25, 1);
		myGL.glTranslated(0, -4.1, 0);
		myUT.glutSolidSphere(2, 20, 20);
		myGL.glPopMatrix();

		// <!-- mắc kính -->
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.75, 1);
		myGL.glTranslated(0, 1.25, 1.25);
		myUT.glutSolidSphere(1.25, 50, 50);
		myGL.glPopMatrix();

//		// <!-- đuôi tóc -->
//		myGL.glPushMatrix();
//		myGL.glTranslated(0, 3.25, -1.5);
//		myGL.glRotated(20, 1, 0, 0);
//		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.2, 0.25, 1, 10, 1);
//		myGL.glPopMatrix();
//
//		// <!-- tóc -->
//		myGL.glPushMatrix();
//		myGL.glScaled(1, 1, 0.5);
//		myGL.glTranslated(0, 3.8, -4);
//		myGL.glRotated(20, 1, 0, 0);
//		myUT.glutSolidSphere(1.5, 20, 20);
//		myGL.glPopMatrix();
//		myGL.glPushMatrix();
//		myGL.glTranslated(0, 4.2, -4);
//		myGL.glRotated(10, 1, 0, 0);
//		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 1.5, 2, 20, 1);
//		myGL.glPopMatrix();
//		myGL.glPushMatrix();
//		myGL.glTranslated(0, 4.25, -4.95);
//		myGL.glRotated(5, 1, 0, 0);
//		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.5, 1, 1, 20, 1);
//		myGL.glPopMatrix();
//		myGL.glPushMatrix();
//		myGL.glTranslated(0, 4, -5.85);
//		myGL.glRotated(-10, 1, 0, 0);
//		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0, 0.55, 1, 20, 1);
//		myGL.glPopMatrix();
//		myGL.glPushMatrix();
//		myGL.glTranslated(1.1, 0.8, 0);
//		myGL.glRotated(90, 1, 0, 0);
//		myGL.glRotated(120, 0, 1, 0);
//		myUT.glutSolidCone(1.6, 2.5, 20, 10);
//		myGL.glPopMatrix();

		// <!-- end body>
		myGL.glPopMatrix();

		// <!-- chan trai>
		myGL.glPushMatrix();
		myGL.glRotated(0, 1, 0, 0);
		myGL.glPushMatrix();
		myGL.glTranslated(-1, -1.15, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 0.75, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.5, 1);
		myGL.glTranslated(-1, -6, 0);
		myUT.glutSolidSphere(0.75, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- chan phai -->
		myGL.glPushMatrix();
		myGL.glRotated(0, 1, 0, 0);
		myGL.glPushMatrix();
		myGL.glTranslated(1, -1.15, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 1, 0.75, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.5, 1);
		myGL.glTranslated(1, -6, 0);
		myUT.glutSolidSphere(0.75, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- tay phai -->
		myGL.glPushMatrix();

		myGL.glTranslated(1.75, 0.5, 0);
		myGL.glRotated(0, 0, 0, 1);
		myGL.glTranslated(-1.75, -0.5, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(1.75, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.75, 0.6, 1.25, 20, 1);
		myGL.glPopMatrix();

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(0, 0, 0, 1);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(0, 0, 1, 0);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(0, 1, 0, 0);
		myGL.glTranslated(-3, -0.5, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(3, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.6, 0.4, 1.25, 20, 1);
		myGL.glPopMatrix();

		myGL.glTranslated(4, 0.5, 0);
		myGL.glRotated(0, 0, 0, 1);
		myGL.glTranslated(-4, -0.5, 0);

		// <!-- bàn tay cầm kiếm -->
		myGL.glPushMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4, 0.5, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		// <!-- kiem -->
		myGL.glPushMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, -0.75, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 1.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.2, 0.2, 2, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glScaled(1, 0.25, 0.25);
		myGL.glTranslated(4.1, 6.75, 0);
		myUT.glutSolidCube(2);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 5.9, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.55, 0.4, 4, 4, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(4.1, 6.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0, 0.55, 0.5, 4, 10);
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- tay trai -->
		myGL.glPushMatrix();

		myGL.glTranslated(-1.5, 0.25, 0);
		myGL.glRotated(-0, 0, 0, 1);
		myGL.glTranslated(1.5, -0.25, 0);

		myGL.glPushMatrix();
		myGL.glTranslated(-2.75, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.6, 0.75, 1.25, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(-4, 0.5, 0);
		myGL.glRotated(90, 1, 0, 0);
		myGL.glRotated(90, 0, 1, 0);
		myGLU.gluCylinder(myGLU.gluNewQuadric(), 0.4, 0.6, 1.25, 20, 1);
		myGL.glPopMatrix();
		myGL.glPushMatrix();
		myGL.glTranslated(-4, 0.5, 0);
		myUT.glutSolidSphere(0.5, 25, 25);
		myGL.glPopMatrix();
		myGL.glPopMatrix();

		// <!-- main pop -- >
		myGL.glPopMatrix();
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(50.0, 1.0 * w / h, 1.0, 40.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGL.glTranslatef(0.0f, 0.0f, -4.6f);
	}

	public void keyboard(char key, int x, int y) {
		switch (key) {
		case 'r':
			if (!directionRun) {
				degRun += 5;
				if (degRun == 30)
					directionRun = true;
			} else {
				degRun -= 5;
				if (degRun == -30)
					directionRun = false;
			}
			myUT.glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		case 'a':
			lx += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'A':
			lx -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'b':
			ly += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'B':
			ly -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'c':
			lz += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'C':
			lz -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'd':
			lxx += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'D':
			lxx -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'e':
			lyy += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'E':
			lyy -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'f':
			nfb += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'F':
			nfb -= 0.1f;
			myUT.glutPostRedisplay();
			break;
		case 'g':
			xx = (xx + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'G':
			xx = (xx - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'h':
			yy = (yy + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'H':
			yy = (yy - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'i':
			zz = (zz + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'I':
			zz = (zz - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'v':
			test1 += 5f;
			myUT.glutPostRedisplay();
			break;
		case 'V':
			test1 -= 5f;
			myUT.glutPostRedisplay();
			break;

		case 'n':
			test2 += 5f;
			myUT.glutPostRedisplay();
			break;
		case 'N':
			test2 -= 5f;
			myUT.glutPostRedisplay();
			break;

		case 'm':
			test3 += 5f;
			myUT.glutPostRedisplay();
			break;
		case 'M':
			test3 -= 5f;
			myUT.glutPostRedisplay();
			break;

		case '!':
			attack = (attack + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '@':
			attack = (attack - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '1':
			haTay = (haTay + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '2':
			haTay = (haTay - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '3':
			attack2 = (attack2 + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '4':
			attack2 = (attack2 - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '5':
			attack3 = (attack3 + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '6':
			attack3 = (attack3 - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '7':
			attack4 = (attack4 + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case '8':
			attack4 = (attack4 - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case ',':
			testQ1 += 1f;
			myUT.glutPostRedisplay();
			break;
		case '<':
			testQ1 -= 1f;
			myUT.glutPostRedisplay();
			break;
		case '.':
			testQ2 += 1f;
			myUT.glutPostRedisplay();
			break;
		case '>':
			testQ2 -= 1f;
			myUT.glutPostRedisplay();
			break;
		case '/':
			testQ3 += 1f;
			myUT.glutPostRedisplay();
			break;
		case '?':
			testQ3 -= 1f;
			myUT.glutPostRedisplay();
			break;
		case '[':
			m1 += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case '{':
			m1 += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case ']':
			m2 += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case '}':
			m2 += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case '=':
			m3 += 0.1f;
			myUT.glutPostRedisplay();
			break;
		case '+':
			m3 += 0.1f;
			myUT.glutPostRedisplay();
			break;

		default:
			break;
		}
		System.out.println("xx: " + xx + ", yy: " + yy + ", zz: " + zz + ", test1: " + test1 + ", test2: " + test2
				+ ", test3: " + test3);
	}

	public void init() throws IOException {
		myUT.glutInitWindowSize(500, 500);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutReshapeFunc("myReshape");
		myUT.glutDisplayFunc("display");
//		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	public void animation() throws Exception {

		boolean chatay = false;
		boolean isChuanBiAttack = false;
		boolean isAttack = false;
		boolean huongKiem = false;
		boolean isOkeHatTung = false;
		boolean isLocXoay = false;
		boolean isHatTung = false;
		boolean isCompleteUntil = false;
		boolean isComplete1 = false;
		boolean isComplete2 = false;
		int count = 0;

		while (true) {

			if (test3 > -65 || ly > 0.6f || lz > 1.3f) {

				if (test3 > -65) {
					test3 -= 1;
					if (!chatay) {
						haTay += 15;
						if (haTay == 30) {
							chatay = true;
						}
					} else {
						haTay -= 15;
						if (haTay == -30) {
							chatay = false;
						}
					}

					if (!directionRun) {
						degRun += 10;
						if (degRun == 30)
							directionRun = true;
					} else {
						degRun -= 10;
						if (degRun == -30)
							directionRun = false;
					}
				} else {
					degRun = 0f;
					haTay = 0;
				}

				if (ly > 0.6f) {
					ly -= 0.1f;
				}

				if (lz > 1.3f) {
					lz -= 0.1f;
				}
			} else if (!isLocXoay) {
				if (!isChuanBiAttack) {
					if (attack > -90)
						attack -= 10;
					else if (haTay > -35)
						haTay -= 5;
					else
						isChuanBiAttack = true;
				} else if (!isAttack && !isOkeHatTung) {
					if (attack3 > -180 && !huongKiem) {
						attack3 -= 30;
						if (attack3 == -180)
							huongKiem = true;
					} else if (huongKiem) {
						attack3 += 30;
						if (attack3 == -0) {
							huongKiem = false;
							isAttack = true;
							isOkeHatTung = true;
						}
					}
				} else if (!isLocXoay) {
					if (testQ1 == 100) {
						testQ1 = 0;
					} else {
						if (testQ1 > -33) {
							testQ1 -= 1;
							if (testQ1 <= -14) {
								if (testQ2 < 15) {
									testQ2 += 1;
								} else {
									isHatTung = true;
									isAttack = false;
									testQ1 = 100;
									isLocXoay = true;
								}
							}
						} else {
						}
					}
				}
			}

			while (isHatTung && !isCompleteUntil) {
				if (!isAttack) {
					if (count == 0) {
						xx = 0;
						yy = 90;
						zz = 0;
						test1 = 5;
						test2 = 20;
						test3 = -100;
						count++;
						isAttack = true;
					} else if (count == 1) {
						xx = 0;
						yy = -135;
						zz = 0;
						test1 = -5;
						test2 = 20;
						test3 = -100;
						count++;
						isAttack = true;
					} else if (count == 2) {
						xx = 0;
						yy = -45;
						zz = 0;
						test1 = -5;
						test2 = 15;
						test3 = -95;
						count++;
						isAttack = true;
					} else if (count == 3) {
						xx = 0;
						yy = 90;
						zz = 0;
						test1 = 5;
						test2 = 20;
						test3 = -100;
						count++;
						isAttack = true;
					} else if (count == 4) {
						xx = 0;
						yy = -135;
						zz = 0;
						test1 = -5;
						test2 = 20;
						test3 = -100;
						count++;
						isAttack = true;
					} else if (count == 5) {
						xx = 0;
						yy = -45;
						zz = 0;
						test1 = -5;
						test2 = 15;
						test3 = -95;
						count++;
						isAttack = true;
					} else {
						xx = 0;
						yy = 0;
						zz = 0;
						test1 = 0;
						test2 = 0;
						test3 = -65;
						isCompleteUntil = true;
					}
				}
				else if (isAttack) {
					if (attack3 > -180 && !huongKiem) {
						attack3 -= 80;
						if (attack3 == -180)
							huongKiem = true;
					} else if (huongKiem) {
						attack3 += 80;
						if (attack3 == 0) {
							huongKiem = false;
							isAttack = false;
						}
					}
				}
				myUT.glutPostRedisplay();
				Thread.sleep(10);
			}

			while (isCompleteUntil) {
				if (testQ2 > 0)
					testQ2 -= 1;
				else {
					if (testQ3 > -90) {
						testQ3 -= 5;
					} else {
						isCompleteUntil = false;
						isComplete1 = true;
					}
				}
				myUT.glutPostRedisplay();
				Thread.sleep(10);
			}

			while (isComplete1) {

				if (test2 < 36) {
					test2 += 2;
				} else if (yy < 180) {
					yy += 10;
				} else if (xx < 50) {
					xx += 5;
				} else if (test3 < -10) {
					test3 += 2;
				} else {
					isComplete2 = true;
					isComplete1 = false;
				}

				myUT.glutPostRedisplay();
				Thread.sleep(10);
			}

			if (isComplete2)
				break;

			myUT.glutPostRedisplay();
			Thread.sleep(20);
		}
	}

	static public void main(String args[]) throws Exception {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(508, 527);
		Robot2 mainCanvas = new Robot2();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
		mainCanvas.animation();
	}

}
