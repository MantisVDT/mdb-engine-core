package de.mdb.engine.core.render.shapes;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glDrawElements;

import org.joml.Matrix4f;

import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.model.VertexArray;
import de.mdb.engine.core.shader.Shader;
import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Texture;

public class Rectangle {

	private int x;
	private int y;
	private int width;
	private int height;

	private Texture texture;

	private Matrix4f modelMatrix;
	private ShaderProgram shader;
	private VertexArray va;

	float VERTICES[] = {
			-0.5f, -0.5f, 0.0f, // top right
			0.5f, -0.5f, 0.0f, // bottom right
			0.5f, 0.5f, 0.0f, // bottom left
			-0.5f, 0.5f, 0.0f // top left
	};
	
	int INDICES[] = { // note that we start from 0!
			0, 1, 2, // first triangle
			2, 3, 0 // second triangle
	};

	private static final float TEX_COORDS[] = { 
			0.0f, 0.0f,
			1.0f, 0.0f,
			1.0f, 1.0f,
			0.0f, 1.0f
	};

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		modelMatrix = new Matrix4f().identity();
		modelMatrix.translate(x, y, 0);
		modelMatrix.scale(width, height, 1);
		shader = new ShaderProgram();
		shader.attachShader(new Shader("Shapeshaders/rectangleShader.vs", Shader.VERTEX_SHADER));
		shader.attachShader(new Shader("Shapeshaders/rectangleShader.fs", Shader.FRAGMENT_SHADER));
		shader.linkShader();

		shader.use();
		shader.setMat4("model", modelMatrix);
		
		va = new VertexArray();
		va.bind();
		va.loadArray(VERTICES, 3);
		va.loadElement(INDICES);
		va.unbind();
	}

	public void render() {
		shader.use();

		va.bind();

		glDrawElements(GL_TRIANGLES, INDICES.length, GL_UNSIGNED_INT, 0);

		va.unbind();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
