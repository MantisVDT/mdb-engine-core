package de.mdb.engine.core.light;

import org.joml.Vector3f;

import de.mdb.engine.core.shader.ShaderProgram;

public class SpotLight extends Light {

	public Vector3f direction;
	
	public float constant;
	public float linear;
	public float quadratic;
	public float cutOff;
	public float outerCutOff;
	
	public SpotLight(Vector3f position, Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular, float constant,
			float linear, float quadratic, float cutOff, float outerCutOff) {
		super(position, ambient, diffuse, specular);
		this.direction = direction;
		this.constant = constant;
		this.linear = linear;
		this.quadratic = quadratic;
		this.cutOff = cutOff;
		this.outerCutOff = outerCutOff;
	}

	@Override
	public void load(ShaderProgram shader) {
		shader.setVec3("spotLight.position", position);
		shader.setVec3("spotLight.direction", direction);
		shader.setVec3("spotLight.ambient", ambient);
		shader.setVec3("spotLight.diffuse", diffuse);
		shader.setVec3("spotLight.specular", specular);
		shader.setFloat("spotLight.constant", constant);
		shader.setFloat("spotLight.linear", linear);
		shader.setFloat("spotLight.quadratic", quadratic);
		shader.setFloat("spotLight.cutOff", cutOff);
		shader.setFloat("spotLight.outerCutOff", outerCutOff);
	}
	
}
