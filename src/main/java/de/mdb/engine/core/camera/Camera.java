package de.mdb.engine.core.camera;

import org.joml.Vector3f;

public class Camera {
	
	protected Vector3f position;
	protected Vector3f rotation;
	
	protected boolean movePosition = true;
	protected boolean moveRotation = true;
	
	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	public Camera()
	{
		this.position = new Vector3f(0.0f, 0.0f, 3.0f);
		this.rotation = new Vector3f(0.0f, 0.0f, 0.0f);
	}
	
	public void init()
	{
		
	}
	
	public void setMovePosition(boolean flag)
	{
		movePosition = flag;
	}
	
	public void setMoveRotation(boolean flag)
	{
		moveRotation = flag;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		if(movePosition)
		{
			this.position = position;
		}
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		if(moveRotation)
		{
			this.rotation = rotation;
		}
		
	}
}
