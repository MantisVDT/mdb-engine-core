package de.mdb.engine.core.model;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;

import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Material;
import de.mdb.engine.core.textures.Texture;

public class Mesh {
	
	public final int VERTICES_COUNT;
	public final int INDICES_COUNT;

	private VertexArray va;
	
	private Material material;
	
	public Mesh(float[] vertices, float[] normals, float[] textureCoords, int[] indices)
	{
		VERTICES_COUNT = vertices.length;
		INDICES_COUNT = indices.length;
		
		va = new VertexArray();
		
		va.bind();
		va.loadArray(vertices, 3);
		va.loadArray(normals, 3);
		va.loadArray(textureCoords, 2);
		va.loadElement(indices);
		va.unbind();
	}
	
	public void render(ShaderProgram shader, Matrix4f modelMatrix)
	{
		shader.use();
		shader.setMat4("model", modelMatrix);
		
		assert(material != null);
		material.load(shader);
		
		Texture texture = material.getTexture();
		texture.bind(0);
		
		if(material.hasNormalMap())
		{
			Texture normalMap = material.getNormalMap();
			normalMap.bind(1);
		}
		
		va.bind();
		va.enableBuffers();
		
		glDrawElements(GL_TRIANGLES, INDICES_COUNT, GL_UNSIGNED_INT, 0);
		
		va.disableBuffers();
		va.unbind();
	}
	
	public void setMaterial(Material material)
	{
		this.material = material;
	}
}
