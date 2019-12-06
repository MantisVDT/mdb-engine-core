package de.mdb.engine.test;

import static org.junit.Assert.assertEquals;

import org.joml.Vector3f;
import org.junit.Test;

import de.mdb.engine.core.camera.Camera;

public class CameraTest {
	
	@Test
	public void testDefaultValues()
	{
		Camera camera = new Camera();
		assertEquals(new Vector3f(0.0f, 0.0f, 0.0f), camera.getPosition());
		assertEquals(new Vector3f(0.0f, 0.0f, 0.0f), camera.getRotation());
	}
	
	@Test
	public void testCustomValues()
	{
		Camera camera = new Camera(new Vector3f(1.0f, 1.0f, 1.0f), new Vector3f(-1.0f, -1.0f, -1.0f));
		assertEquals(new Vector3f(1.0f, 1.0f, 1.0f), camera.getPosition());
		assertEquals(new Vector3f(-1.0f, -1.0f, -1.0f), camera.getRotation());
	}
	
	@Test
	public void testChangedValues()
	{
		Camera camera = new Camera(new Vector3f(1.0f, 1.0f, 1.0f), new Vector3f(2.0f, 2.0f, 2.0f));
		
		camera.setPosition(new Vector3f(-1.0f,-1.0f,-1.0f));
		camera.setRotation(new Vector3f(1.0f,1.0f,1.0f));
		
		assertEquals(new Vector3f(-1.0f, -1.0f, -1.0f), camera.getPosition());
		assertEquals(new Vector3f(1.0f, 1.0f, 1.0f), camera.getRotation());
	}
	
}
