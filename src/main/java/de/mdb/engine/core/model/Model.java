package de.mdb.engine.core.model;

import java.util.List;

import org.joml.Matrix4f;

import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Material;

public class Model {
	
	private List<Mesh> meshes;
	private List<Material> materials;
	
	private Matrix4f modelMatrix;
	
	public Model(List<Mesh> meshes, List<Material> materials) {
		this.meshes = meshes;
		this.materials = materials;
		modelMatrix = new Matrix4f();
	}

	public void render(ShaderProgram shader)
	{
		for(Mesh m : meshes)
		{
			m.render(shader, modelMatrix);
		}
	}
	
	public void translate(float x, float y, float z)
	{
		modelMatrix.translate(x, y, z);
	}
	
	/**
	 * Rotates the Model with the angle on the specified Axis
	 * 
	 * @param angle The angle to rotate by
	 * @param rx The x component of the Rotation Axis
	 * @param ry The y component of the Rotation Axis
	 * @param rz The z component of the Rotation Axis
	 */
	public void rotate(float angle, float rx, float ry, float rz)
	{
		modelMatrix.rotate(angle, rx, ry, rz);
	}
	
	/**
	 * Scales the Model by the specified Factor
	 * 
	 * @param xyz The factor to scale by
	 */
	public void scale(float xyz)
	{
		modelMatrix.scale(xyz);
	}
	
	public List<Material> getMaterials()
	{
		return materials;
	}
}
