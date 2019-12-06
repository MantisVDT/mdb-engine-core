package de.mdb.engine.core.model;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import org.joml.Matrix4f;

import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Material;
import de.mdb.engine.core.textures.Texture;

public class Mesh {
	
	public final int VERTICES_COUNT;
	public final int INDICES_COUNT;
	
	private final int MESH_VAO;
	private final int VERTEX_VBO;
	private final int NORMAL_VBO;
	private final int TEXCOORD_VBO;
	private final int INDICES_EBO;
	
	private Material material;
	
	public Mesh(float[] vertices, float[] normals, float[] textureCoords, int[] indices)
	{
		VERTICES_COUNT = vertices.length;
		INDICES_COUNT = indices.length;
		
		MESH_VAO = glGenVertexArrays();
		VERTEX_VBO = glGenBuffers();
		NORMAL_VBO = glGenBuffers();
		TEXCOORD_VBO = glGenBuffers();
		INDICES_EBO = glGenBuffers();
		
		glBindVertexArray(MESH_VAO);
		
		glBindBuffer(GL_ARRAY_BUFFER, VERTEX_VBO);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, NORMAL_VBO);
		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, TEXCOORD_VBO);
		glBufferData(GL_ARRAY_BUFFER, textureCoords, GL_STATIC_DRAW);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, INDICES_EBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		
		glBindVertexArray(0);
	}
	
	public void render(ShaderProgram shader, Matrix4f modelMatrix)
	{
		shader.use();
		shader.setMat4("model", modelMatrix);
		
		assert(material != null);
		material.load(shader);
		
		Texture texture = material.getTexture();
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture != null ? texture.getID() : Texture.DEFAULT_TEXTURE.getID());
		
		if(material.hasNormalMap())
		{
			Texture normalMap = material.getNormalMap();
			glActiveTexture(GL_TEXTURE1);
			glBindTexture(GL_TEXTURE_2D, normalMap.getID());
		}
		
		glBindVertexArray(MESH_VAO);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glDrawElements(GL_TRIANGLES, INDICES_COUNT, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
	}
	
	public void setMaterial(Material material)
	{
		this.material = material;
	}
}
