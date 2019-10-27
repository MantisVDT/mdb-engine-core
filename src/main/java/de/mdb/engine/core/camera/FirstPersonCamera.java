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

public class FirstPersonCamera extends Camera implements EventListener {
	
	private float flySpeed = 1.0f;
	private float rotationCap = 10.0f;
	
	public FirstPersonCamera() {
		super();
		EventManager.registerListener(this);
	}

	public FirstPersonCamera(Vector3f position, Vector3f rotation) {
		super(position, rotation);
		EventManager.registerListener(this);
	}

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
	
	public void setupSettings(Display d)
	{
		d.setCursorPosition(d.getWidth()/2, d.getHeight()/2);
		d.setInputMode(GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	}
	
	public Matrix4f getViewMatrix() {
		Matrix4f view = new Matrix4f().identity();

		view = view.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0))
				.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
		view = view.translate(-position.x, -position.y, -position.z);

		return view;
	}

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
	
	public void setFlySpeed(float speed)
	{
		flySpeed = speed;
	}
}
