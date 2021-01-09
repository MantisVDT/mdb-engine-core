package de.mdb.engine.core.render;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector4f;

public class MasterRenderer extends Renderer {
	
	private Vector4f clear_color;
	public RenderMode mode;
	
	public MasterRenderer()
	{
		super(null);
		
		clear_color = new Vector4f(0.0f);
		mode = RenderMode.FILL;
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
	}
	
	public void render()
	{
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		
		switch(mode) {
		case FILL:
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			break;
		case WIREFRAME:
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			break;
		case POINT:
			glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
			break;
		}
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(clear_color.x, clear_color.y, clear_color.z, clear_color.w);
	}
	
	public void setClearColor(float color)
	{
		clear_color = new Vector4f(color);
	}
	
	public void setClearColor(Vector4f color)
	{
		clear_color = color;
	}
	
}
