package de.mdb.engine.core.light;

import org.joml.Vector3f;

import de.mdb.engine.core.shader.ShaderProgram;

public class PointLight extends Light{
	
	private final int ID;
	
	public float constant;
	public float linear;
	public float quadratic;
	
	public PointLight(Vector3f position, Vector3f ambient, Vector3f diffuse, Vector3f specular, float constant,
			float linear, float quadratic) {
		super(position, ambient, diffuse, specular);
		ID = PointLightManager.nextID();
		this.constant = constant;
		this.linear = linear;
		this.quadratic = quadratic;
		PointLightManager.addPointLight(this);
	}
	
	public void load(ShaderProgram shader)
	{
		String pointLightLocation = "pointLights["+ID+"].";
		shader.setVec3(pointLightLocation + "position", position);
		shader.setVec3(pointLightLocation + "ambient", ambient);
		shader.setVec3(pointLightLocation + "diffuse", diffuse);
		shader.setVec3(pointLightLocation + "specular", specular);
		shader.setFloat(pointLightLocation +"constant", constant);
		shader.setFloat(pointLightLocation +"linear", linear);
		shader.setFloat(pointLightLocation +"quadratic", quadratic);
	}
	
	public int getID()
	{
		return ID;
	}
	
}
