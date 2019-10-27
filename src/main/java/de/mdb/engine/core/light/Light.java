package de.mdb.engine.core.light;

import org.joml.Vector3f;

import de.mdb.engine.core.shader.ShaderProgram;

public abstract class Light {
	
	public Vector3f position;
	public Vector3f ambient;
	public Vector3f diffuse;
	public Vector3f specular;
	
	public Light(Vector3f position, Vector3f ambient, Vector3f diffuse, Vector3f specular)
	{
		this.position = position;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
	}
	
	public abstract void load(ShaderProgram shader);
	
}
