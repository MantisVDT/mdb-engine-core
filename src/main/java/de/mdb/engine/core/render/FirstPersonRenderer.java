package de.mdb.engine.core.render;

import org.joml.Matrix4f;

import de.mdb.engine.core.camera.FirstPersonCamera;
import de.mdb.engine.core.display.event.FramebufferSizeEvent;
import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.shader.ShaderProgram;

public class FirstPersonRenderer extends Renderer implements EventListener{
	
	private FirstPersonCamera camera;
	
	public FirstPersonRenderer(ShaderProgram program, FirstPersonCamera camera, int width, int height) {
		super(program);
		EventManager.registerListener(this);
		
		this.camera = camera;
		
		shader.use();
		shader.setMat4("projection", new Matrix4f().perspective((float)Math.toRadians(90.0f), (float)width/height, 0.1f, 100.0f));
		shader.setMat4("model", new Matrix4f());
	}

	@Override
	public void render() {
		shader.use();
		shader.setMat4("view", camera.getViewMatrix());
	}
	
	@Event
	public void onSizeChanged(FramebufferSizeEvent e)
	{
		shader.use();
		shader.setMat4("projection", new Matrix4f().perspective((float)Math.toRadians(90.0f), (float)e.getWidth()/e.getHeight(), 0.1f, 100.0f));
	}
}
