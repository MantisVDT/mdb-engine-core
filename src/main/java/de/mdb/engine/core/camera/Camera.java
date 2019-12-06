package de.mdb.engine.core.camera;

import org.joml.Vector3f;

/**
 * 
 * Represents a Camera Object with a position and rotation
 * 
 * @author Mattis Boeckle
 *
 */
public class Camera {
	
	//VARIABLES
	protected Vector3f position;
	protected Vector3f rotation;
	
	protected boolean movePosition = true;
	protected boolean moveRotation = true;
	
	/**
	 * Constructs a Camera with the given position and rotation
	 * 
	 * @param position The position of the Camera
	 * @param rotation The rotation of the Camera
	 */
	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	/**
	 * Constructs a Camera with a default location and rotation
	 */
	public Camera()
	{
		this.position = new Vector3f(0.0f, 0.0f, 0.0f);
		this.rotation = new Vector3f(0.0f, 0.0f, 0.0f);
	}
	
	/**
	 * Sets whether the Camera should update its position
	 * 
	 * @param flag Should the Camera move
	 */
	public void setMovePosition(boolean flag)
	{
		movePosition = flag;
	}
	
	/**
	 * Sets whether the Camera should update its rotation
	 * 
	 * @param flag Should the Camera rotate
	 */
	public void setMoveRotation(boolean flag)
	{
		moveRotation = flag;
	}
	
	/**
	 * Returns the current Position of the Camera
	 * 
	 * @return The position of the Camera
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * Sets the Camera Position to be position
	 * 
	 * @param position The new camera position
	 */
	public void setPosition(Vector3f position) {
		if(movePosition)
		{
			this.position = position;
		}
	}

	/**
	 * Returns the current Rotation of the Camera
	 * 
	 * @return The rotation of the Camera
	 */
	public Vector3f getRotation() {
		return rotation;
	}

	/**
	 * Sets the Camera Rotation to be rotation
	 * 
	 * @param rotation The new camera rotation
	 */
	public void setRotation(Vector3f rotation) {
		if(moveRotation)
		{
			this.rotation = rotation;
		}
		
	}
}
