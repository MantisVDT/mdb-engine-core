package de.mdb.engine.core.light;

import org.joml.Vector3f;

import de.mdb.engine.core.shader.ShaderProgram;

public class DirectionalLight extends Light{
	
	public DirectionalLight(Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular)
	{
		super(direction, ambient, diffuse, specular);
	}
	
	public void load(ShaderProgram shader)
	{
		shader.setVec3("dirLight.direction", position);
		shader.setVec3("dirLight.ambient", ambient);
		shader.setVec3("dirLight.diffuse", diffuse);
		shader.setVec3("dirLight.specular", specular);
	}
	
}
