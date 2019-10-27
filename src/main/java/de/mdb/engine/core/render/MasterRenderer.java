package de.mdb.engine.core.render;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import org.joml.Vector4f;

public class MasterRenderer extends Renderer {
	
	private Vector4f clear_color;
	
	public MasterRenderer()
	{
		super(null);
		
		clear_color = new Vector4f(0.0f);
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
	}
	
	public void render()
	{
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
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
