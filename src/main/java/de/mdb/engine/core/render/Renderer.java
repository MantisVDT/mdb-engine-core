package de.mdb.engine.core.render;

import de.mdb.engine.core.shader.ShaderProgram;

public abstract class Renderer {
	
	protected ShaderProgram shader;
	
	public Renderer(ShaderProgram program)
	{
		shader = program;
	}
	
	public abstract void render();
	
}
