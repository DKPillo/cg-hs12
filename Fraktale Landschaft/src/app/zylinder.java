package app;
import jrtr.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.vecmath.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implements a simple application that opens a 3D rendering window and 
 * shows a rotating cube.
 */
public class zylinder
{	
	static RenderPanel renderPanel;
	static RenderContext renderContext;
	static SimpleSceneManager sceneManager;
	static Shape shape;
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
			// Update transformation
    		Matrix4f t = shape.getTransformation();
    		Matrix4f rotX = new Matrix4f();
    		rotX.rotX(angle);
    		Matrix4f rotY = new Matrix4f();
    		rotY.rotY(0*angle);
    		Matrix4f rotZ = new Matrix4f();
    		rotZ.rotZ(angle);
    		t.mul(rotX);
    		t.mul(rotY);
    		t.mul(rotZ);
    		shape.setTransformation(t);
    		
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
	public static void main(String[] args) {
		
		int parts = 360;
		float rad = 1;
		float angle = 360 / parts;
		angle = (float) Math.toRadians(angle);
		
		float front[] = new float[(parts + 1) * 3];
		front[0] = 0;
		front[1] = 0;
		front[2] = 5;
		for (int i = 1; i <= parts; i++) {
			int pos = 3*i;
			float localAngle = angle*(i-1);
			front[pos + 0] = (float) Math.cos(localAngle) * rad;
			front[pos + 1] = (float) Math.sin(localAngle) * rad;
			front[pos + 2] = 5;
		}

		float back[] = new float[(parts + 1) * 3];
		back[0] = 0;
		back[1] = 0;
		back[2] = -5;
		for (int i = 1; i <= parts; i++) {
			int pos = 3*i;
			float localAngle = angle*(i-1);
			back[pos + 0] = (float) Math.cos(localAngle) * rad;
			back[pos + 1] = (float) Math.sin(localAngle) * rad;
			back[pos + 2] = -5;
		}
		
		int pos0front = 0;
		int pos0back = (parts+1)*3;
		float v[] = new float[front.length + back.length];
		for (int i = 0; i < front.length; i++) {
			v[i] = front[i];
		}
		for (int i = 0; i < back.length; i++) {
			v[pos0back+i] = back[i];
		}

		//System.out.println("Array Begin");
		//for (int i = 0; i < v.length; i+=3) {
		//	System.out.println("[" + v[i] + "]-[" + v[i+1] + "]-[" + v[i+2] + "]");
		//}
		//System.out.println("Array End");
		
		float c[] = new float[v.length];
		for (int i = 0; i < v.length; i+=6) {
			c[i + 0] = 1;
			c[i + 1] = 0;
			c[i + 2] = 0;
			c[i + 3] = 0;
			c[i + 4] = 1;
			c[i + 5] = 0;
		}
		
		//System.out.println("Array Begin");
		//for (int i = 0; i < c.length; i+=3) {
		//	System.out.println("[" + c[i] + "]-[" + c[i+1] + "]-[" + c[i+2] + "]");
		//}
		//System.out.println("Array End");

		// Construct a data structure that stores the vertices, their
		// attributes, and the triangle mesh connectivity
		VertexData vertexData = new VertexData(parts*2 + 2);
		vertexData.addElement(c, VertexData.Semantic.COLOR, 3);
		vertexData.addElement(v, VertexData.Semantic.POSITION, 3);

		int indices[] = new int[parts * 4 * 3];
		
		int counter_front = 1;
		for (int i = 0*parts; i < 1*parts*3; i+=3) {
			indices[i] = pos0front;
			if (i == 1*parts*3 - 3) {
				indices[i + 1] = 1;
			} else {
				indices[i + 1] = pos0front + counter_front + 1;
			}
			indices[i + 2] = pos0front + counter_front;
			counter_front++;
		}

		int counter_center = 0;
		for (int i = 1*parts*3; i < 3*parts*3; i+=6) {
			indices[i + 0] = counter_center + 1;
			if (i == 3*parts*3 - 6) {indices[i + 1] = pos0back/3 + 1;} else {indices[i + 1] = counter_center + pos0back/3 + 2;}
			indices[i + 2] = counter_center + pos0back/3 + 1;
			
			if (i == 3*parts*3 - 6) {indices[i + 3] = pos0back/3 + 1;} else {indices[i + 3] = counter_center + pos0back/3 + 2;}
			indices[i + 4] = counter_center + 1;
			if (i == 3*parts*3 - 6) {indices[i + 5] = 1;} else {indices[i + 5] = counter_center + 2;}
			counter_center++;
		}
		
		int counter_back = 1;
		for (int i = 3*parts*3; i < 4*parts*3; i+=3) {
			indices[i] = pos0back/3;
			indices[i + 1] = pos0back/3 + counter_back + 0;
			if (i == 4*parts*3 - 3) {
				indices[i + 2] = pos0back/3 + 1;
			} else {
				indices[i + 2] = pos0back/3 + counter_back + 1;
			}
			counter_back++;
		}
		

		//System.out.println("Array Begin");
		//for (int i = 0; i <= indices.length-2; i+=3) {
		//	System.out.println("[" + indices[i] + "]-[" + indices[i+1] + "]-[" + indices[i+2] + "]");
		//}
		//System.out.println("Array End");

		vertexData.addIndices(indices);
				
		// Make a scene manager and add the object
		sceneManager = new SimpleSceneManager();
		shape = new Shape(vertexData);
		sceneManager.addShape(shape);

		// Make a render panel. The init function of the renderPanel
		// (see above) will be called back for initialization.
		renderPanel = new SimpleRenderPanel();
		
		// Make the main window of this application and add the renderer to it
		JFrame jframe = new JFrame("simple");
		jframe.setSize(800, 800);
		jframe.setLocationRelativeTo(null); // center of screen
		jframe.getContentPane().add(renderPanel.getCanvas());// put the canvas into a JFrame window

		// Add a mouse listener
	    jframe.addMouseListener(new SimpleMouseListener());
		   	    	    
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setVisible(true); // show window
	}
}
