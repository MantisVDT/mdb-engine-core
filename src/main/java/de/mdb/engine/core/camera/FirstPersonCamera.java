package de.mdb.engine.core.camera;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import de.mdb.engine.core.display.Display;
import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.input.events.MouseMovedEvent;
import de.mdb.engine.core.util.Clock;

/**
 * Represents a FirstPersonCamera in 3D Space with a position and rotation <br>
 * Can be used to "move" in 3D and look at models
 * 
 * @author Mattis Boeckle
 *
 */
public class FirstPersonCamera extends Camera implements EventListener {
	
	// Private Fields
	private float flySpeed = 1.0f;
	private float rotationCap = 10.0f;
	
	/**
	 * Default empty Constructor <br>
	 * Initializes all fields and registers this class as an EventListener
	 */
	public FirstPersonCamera() {
		super();
		EventManager.registerListener(this);
	}

	/**
	 * Constructor to initialize the Fields to something else than the default values. <br>
	 * Initializes all fields given the variables and registers this class as an EventListener
	 * 
	 * @param position The position to initialize the Camera to
	 * @param rotation The rotation to initialize the Camera to
	 */
	public FirstPersonCamera(Vector3f position, Vector3f rotation) {
		super(position, rotation);
		EventManager.registerListener(this);
	}

	/**
	 * The EventListener function for the MouseMovedEvent <br>
	 * Will be called from EventManager
	 * 
	 * @param e The MouseMovedEvent to be handled
	 */
	@Event
	public void onMouseMove(MouseMovedEvent e) {
		float dX = e.getDX();
		float dY = e.getDY();
		if (dX > 100.0f) 	dX = 100.0f;
		if (dX < -100.0f) 	dX = -100.0f;
		if (dY > 100.0f)	dY = 100.0f;
		if (dY < -100.0f)	dY = -100.0f;

		moveRotation(dY * Clock.getDeltaTime() * -10.0f, dX * Clock.getDeltaTime() * -10.0f, 0);
	}
	
	/**
	 * Initializes the default settings in the Display <br>
	 * Sets the Cursor position to be in the center of the Screen
	 * then disables the Cursor.
	 * <br>
	 * <br>
	 * This is required for the camera to be free moving based on mouse motion.
	 * 
	 * @param d The Display for the Settings to be applied to
	 */
	public void setupSettings(Display d)
	{
		d.setCursorPosition(d.getWidth()/2, d.getHeight()/2);
		d.setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	}
	
	/**
	 * Getter for FlySpeed
	 * 
	 * @return The private field FlySpeed
	 */
	public float getFlySpeed()
	{
		return flySpeed;
	}
	
	/**
	 * Calculates the ViewMatrix of the Camera based on its position and rotation <br>
	 * Used for Shader calculations
	 * 
	 * @return The calculated Matrix4f
	 */
	public Matrix4f getViewMatrix() {
		Matrix4f view = new Matrix4f().identity();

		view = view.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0))
				.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
		view = view.translate(-position.x, -position.y, -position.z);

		return view;
	}

	/**
	 * Moves the camera position based on the given offsets
	 * 
	 * @param offsetX The x offset
	 * @param offsetY The y offset
	 * @param offsetZ The z offset
	 */
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
		if(movePosition)
		{
			
			if (offsetZ != 0) {
				position.x += ((float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ) * flySpeed;
				position.z += ((float) Math.cos(Math.toRadians(rotation.y)) * offsetZ) * flySpeed;
			}

			if (offsetX != 0) {
				position.x += ((float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX) * flySpeed;
				position.z += ((float) Math.cos(Math.toRadians(rotation.y - 90)) * offsetX) * flySpeed;
			}

			position.y += offsetY * flySpeed;
		}
		
	}

	/**
	 * Updates the Rotation based on the given offsets
	 * 
	 * @param offsetX The x offset in Degree
	 * @param offsetY The y offset in Degree
	 * @param offsetZ The z offset in Degree
	 */
	public void moveRotation(float offsetX, float offsetY, float offsetZ) {
		if(moveRotation)
		{
			offsetX = Math.max(-rotationCap, offsetX);
			offsetX = Math.min(rotationCap, offsetX);
			
			offsetY = Math.max(-rotationCap, offsetY);
			offsetY = Math.min(rotationCap, offsetY);
			
			offsetZ = Math.max(-rotationCap, offsetZ);
			offsetZ = Math.min(rotationCap, offsetZ);
			
			rotation.x += offsetX;
			rotation.y += offsetY;
			rotation.z += offsetZ;
		}
	}
	
	/**
	 * Setter for the private Field FlySpeed
	 * 
	 * @param speed The new FlySpeed as float
	 */
	public void setFlySpeed(float speed)
	{
		flySpeed = speed;
	}
}
