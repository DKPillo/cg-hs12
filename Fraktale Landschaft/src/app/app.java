package app;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

import jrtr.GLRenderPanel;
import jrtr.RenderContext;
import jrtr.RenderPanel;
import jrtr.SWRenderPanel;
import jrtr.Shape;
import jrtr.SimpleSceneManager;
import jrtr.VertexData;
import lib.*;


/**
 * Implements a simple application that opens a 3D rendering window and 
 * shows a rotating cube.
 */
public class app
{	
	static RenderPanel renderPanel;
	static RenderContext renderContext;
	static SimpleSceneManager sceneManager;
	static Shape shape0;
	static Shape shapeA;
	static Shape shapeB;
	static Shape shapeC;
	static Shape shapeD;
	static Shape shapeE;
	static Shape shapeF;
	static Shape shapeG;
	static Shape shapeH;
	static float angle;

	/**
	 * An extension of {@link GLRenderPanel} or {@link SWRenderPanel} to 
	 * provide a call-back function for initialization. 
	 */ 
	public final static class SimpleRenderPanel extends GLRenderPanel
	{
		/**
		 * Initialization call-back. We initialize our renderer here.
		 * 
		 * @param r	the render context that is associated with this render panel
		 */
		public void init(RenderContext r)
		{
			renderContext = r;
			renderContext.setSceneManager(sceneManager);
	
			// Register a timer task
		    Timer timer = new Timer();
		    angle = 0.01f;
		    timer.scheduleAtFixedRate(new AnimationTask(), 0, 10);
		}
	}

	/**
	 * A timer task that generates an animation. This task triggers
	 * the redrawing of the 3D scene every time it is executed.
	 */
	public static class AnimationTask extends TimerTask
	{
		public void run()
		{
			
			//transformation of center
			Matrix4f a = shapeA.getTransformation();
    		Matrix4f rotX = new Matrix4f();
    		rotX.rotX(-2*angle);
    		Matrix4f rotY = new Matrix4f();
    		rotY.rotY(+2*angle);
    		Matrix4f rotZ = new Matrix4f();
    		rotZ.rotZ(+2*angle);
    		a.mul(rotX);
    		a.mul(rotY);
    		a.mul(rotZ);
    		shapeA.setTransformation(a);
			//transformation of center end
			
			//transformation of wheels
			Matrix4f r1B = shapeB.getTransformation();
			Matrix4f rotB = new Matrix4f();
			rotB.rotZ(angle*5);
			r1B.mul(rotB);
			Matrix4f oRotB = new Matrix4f();
			oRotB.rotZ(-angle);
			oRotB.mul(r1B);
			shapeB.setTransformation(oRotB);
			
			Matrix4f r1C = shapeC.getTransformation();
			Matrix4f rotC = new Matrix4f();
			rotC.rotZ(angle*5);
			r1C.mul(rotC);
			Matrix4f oRotC = new Matrix4f();
			oRotC.rotZ(-angle);
			oRotC.mul(r1C);
			shapeC.setTransformation(oRotC);
			
			Matrix4f r1D = shapeD.getTransformation();
			Matrix4f rotD = new Matrix4f();
			rotD.rotZ(angle*5);
			r1D.mul(rotD);
			Matrix4f oRotD = new Matrix4f();
			oRotD.rotZ(-angle);
			oRotD.mul(r1D);
			shapeD.setTransformation(oRotD);

			Matrix4f r1E = shapeE.getTransformation();
			Matrix4f rotE = new Matrix4f();
			rotE.rotZ(angle*5);
			r1E.mul(rotE);
			Matrix4f oRotE = new Matrix4f();
			oRotE.rotZ(-angle);
			oRotE.mul(r1E);
			shapeE.setTransformation(oRotE);
			//transformation of wheels ends
			
			//transformation of torso
			Matrix4f r1F = shapeF.getTransformation();
			Matrix4f oRotF = new Matrix4f();
			oRotF.rotZ(-angle);
			oRotF.mul(r1F);
			shapeF.setTransformation(oRotF);
			
			Matrix4f r1G = shapeG.getTransformation();
			Matrix4f oRotG = new Matrix4f();
			oRotG.rotZ(-angle);
			oRotG.mul(r1G);
			shapeG.setTransformation(oRotG);
			
			Matrix4f r1H = shapeH.getTransformation();
			Matrix4f rotH = new Matrix4f();
			rotH.rotZ(angle*5);
			r1H.mul(rotH);
			Matrix4f oRotH = new Matrix4f();
			oRotH.rotZ(-angle);
			oRotH.mul(r1H);
			shapeH.setTransformation(oRotH);
			//transformation of torso ends
    		
			
    		// Trigger redrawing of the render window
    		renderPanel.getCanvas().repaint(); 
		}
	}

	/**
	 * A mouse listener for the main window of this application. This can be
	 * used to process mouse events.
	 */
	public static class SimpleMouseListener implements MouseListener
	{
    	public void mousePressed(MouseEvent e) {}
    	public void mouseReleased(MouseEvent e) {}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}
    	public void mouseClicked(MouseEvent e) {}
	}
	
	/**
	 * The main function opens a 3D rendering window, constructs a simple 3D
	 * scene, and starts a timer task to generate an animation.
	 */
	public static void main(String[] args)
	{		
		//ground element
		float col[] = {1, 1, 0};
		IElement element0 = new Area(12, col);

		//center element
		IElement elementCenterA = new Torus(36, 1, 0.5);
		
		//wheels
		IElement elementWheelA = new Zylinder(36, 3, 0.5f);
		IElement elementWheelB = new Zylinder(36, 3, 0.5f);
		IElement elementWheelC = new Zylinder(36, 3, 0.5f);
		IElement elementWheelD = new Zylinder(36, 3, 0.5f);
		
		//torso
		IElement elementTorsoA = new Quad(3, 2, 1);
		IElement elementTorsoB = new Quad(1, 1, 1);
		IElement elementTorsoC = new Zylinder(36, 2, 0.5f);


		//******************************************************************************
		
		//creating the shapes
		shape0 = new Shape(createVertexData(element0));
		shape0 = setShapePos(shape0, 0, 0, -1);

		shapeA = new Shape(createVertexData(elementCenterA));

		shapeB = new Shape(createVertexData(elementWheelA));
		shapeB = setShapeOri(shapeB, 90, 0, 0);
		shapeB = setShapePos(shapeB, -1.25f, -4, -0.75f);

		shapeC = new Shape(createVertexData(elementWheelB));
		shapeC = setShapeOri(shapeC, 90, 0, 0);
		shapeC = setShapePos(shapeC, -0.5f, -4, -0.75f);

		shapeD = new Shape(createVertexData(elementWheelC));
		shapeD = setShapeOri(shapeD, 90, 0, 0);
		shapeD = setShapePos(shapeD, 0.5f, -4, -0.75f);

		shapeE = new Shape(createVertexData(elementWheelD));
		shapeE = setShapeOri(shapeE, 90, 0, 0);
		shapeE = setShapePos(shapeE, 1.25f, -4, -0.75f);

		shapeF = new Shape(createVertexData(elementTorsoA));
		shapeF = setShapePos(shapeF, 0, -4, 0);

		shapeG = new Shape(createVertexData(elementTorsoB));
		shapeG = setShapeOri(shapeG, 270, 90, 0);
		shapeG = setShapePos(shapeG, 0, -4, 0.75f);

		shapeH = new Shape(createVertexData(elementTorsoC));
		shapeH = setShapeOri(shapeH, 0, 90, 0);
		shapeH = setShapePos(shapeH, -1.5f, -4, 0.75f);
		

		//******************************************************************************
		
		//add all shapes to screen
		sceneManager = new SimpleSceneManager();
		sceneManager.addShape(shape0);
		sceneManager.addShape(shapeA);
		sceneManager.addShape(shapeB);
		sceneManager.addShape(shapeC);
		sceneManager.addShape(shapeD);
		sceneManager.addShape(shapeE);
		sceneManager.addShape(shapeF);
		sceneManager.addShape(shapeG);
		sceneManager.addShape(shapeH);

		// Make a render panel. The init function of the renderPanel
		// (see above) will be called back for initialization.
		renderPanel = new SimpleRenderPanel();
		
		// Make the main window of this application and add the renderer to it
		JFrame jframe = new JFrame("APP - DKPillo");
		jframe.setSize(800, 800);
		jframe.setLocationRelativeTo(null); // center of screen
		jframe.getContentPane().add(renderPanel.getCanvas());// put the canvas into a JFrame window

		// Add a mouse listener
	    jframe.addMouseListener(new SimpleMouseListener());
		   	    	    
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setVisible(true); // show window
	}
	
	/**
	 * 
	 * @param tempShape
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static Shape setShapePos(Shape origShape, float x, float y, float z){

		Matrix4f t = origShape.getTransformation();
		Matrix4f move = new Matrix4f();
		Vector3f goal = new Vector3f();
		goal.setX(x);
		goal.setY(y);
		goal.setZ(z);

		move.setTranslation(goal);
		t.add(move);
		
		origShape.setTransformation(t);
		
		return origShape;
	}
	
	/**
	 * 
	 * @param tempShape
	 * @param xAngle
	 * @param yAngle
	 * @param zAngle
	 * @return
	 */
	public static Shape setShapeOri(Shape origShape, float xAngle, float yAngle, float zAngle){
		
		Matrix4f t = origShape.getTransformation();
		Matrix4f rotX = new Matrix4f();
		rotX.rotX((float) Math.toRadians(xAngle));
		Matrix4f rotY = new Matrix4f();
		rotY.rotY((float) Math.toRadians(yAngle));
		Matrix4f rotZ = new Matrix4f();
		rotZ.rotZ((float) Math.toRadians(zAngle));
		
		t.mul(rotX);
		t.mul(rotY);
		t.mul(rotZ);
		origShape.setTransformation(t);
		
		return origShape;
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static VertexData createVertexData(IElement element) {
		VertexData ret_data = new VertexData(element.get_VertexDataSize());
		ret_data.addElement(element.get_c(), VertexData.Semantic.COLOR, 3);
		ret_data.addElement(element.get_v(), VertexData.Semantic.POSITION, 3);
		ret_data.addIndices(element.get_indices());
		return ret_data;
	}


}
