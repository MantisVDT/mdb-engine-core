package de.mdb.engine.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joml.Vector3f;
import org.junit.Test;

import de.mdb.engine.core.camera.FirstPersonCamera;

public class FirstPersonCameraTest {
	
	@Test
	public void testDefaultValues()
	{
		FirstPersonCamera camera = new FirstPersonCamera();
		
		assertEquals(new Vector3f(0.0f, 0.0f, 0.0f), camera.getPosition());
		assertEquals(new Vector3f(0.0f, 0.0f, 0.0f), camera.getRotation());
		assertEquals(1.0f, camera.getFlySpeed());
	}
	
	@Test
	public void testCustomValues()
	{
		FirstPersonCamera camera = new FirstPersonCamera(new Vector3f(1.0f, 5.0f, -3.0f), new Vector3f(-5.0f, 3.0f, 1.0f));
		
		assertEquals(new Vector3f(1.0f, 5.0f, -3.0f), camera.getPosition());
		assertEquals(new Vector3f(-5.0f, 3.0f, 1.0f), camera.getRotation());
		assertEquals(1.0f, camera.getFlySpeed());
	}
	
	@Test
	public void testChangedValues()
	{
		FirstPersonCamera camera = new FirstPersonCamera(new Vector3f(1.0f, 5.0f, -3.0f), new Vector3f(-5.0f, 3.0f, 1.0f));
		
		camera.setPosition(new Vector3f(1.0f,1.0f,1.0f));
		camera.setRotation(new Vector3f(1.0f,1.0f,1.0f));
		camera.setFlySpeed(5.0f);
		
		assertEquals(new Vector3f(1.0f, 1.0f, 1.0f), camera.getPosition());
		assertEquals(new Vector3f(1.0f, 1.0f, 1.0f), camera.getRotation());
		assertEquals(5.0f, camera.getFlySpeed());
	}
	
}
