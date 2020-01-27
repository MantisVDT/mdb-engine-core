package de.mdb.engine.core.render.shapes;

import org.joml.Matrix4f;

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
	
	public Rectangle(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		modelMatrix = new Matrix4f();
		modelMatrix.translate(x, y, 0);
		modelMatrix.scale(width, height, 1);
		shader = new ShaderProgram();
		shader.attachShader(new Shader("Shapeshaders/rectangleShader.vs", Shader.VERTEX_SHADER));
		shader.attachShader(new Shader("Shapeshaders/rectangleShader.fs", Shader.FRAGMENT_SHADER));
		shader.linkShader();
	}
	
	public void render(Matrix4f view, Matrix4f projection)
	{
		shader.use();
		shader.setMat4("model", modelMatrix);
		shader.setMat4("view", view);
		shader.setMat4("projection", projection);
		shader.setInt("Texture", 0);
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
