package de.mdb.engine.core.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;
import java.util.Iterator;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import de.mdb.engine.core.camera.FirstPersonCamera;
import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.particle.Particle;
import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.textures.Texture;
import de.mdb.engine.core.textures.TextureCache;

public class ParticleRenderer extends Renderer {
	
	private static final float[] VERTICES = {
			-0.5f, 0.5f,
			-0.5f, -0.5f,
			0.5f, 0.5f,
			0.5f, -0.5f
	};
	
	private static final float[] TEX_COORDS = {
		    0.0f, 0.0f,
		    0.0f, 1.0f,
		    1.0f, 0.0f,
		    1.0f, 1.0f 
	};
	
	private final int PARTICLE_VAO;
	private final int VERTICES_VBO;
	private final int TEXTURE_VBO;
	
	private static ArrayList<Particle> particles = new ArrayList<>();
	private FirstPersonCamera camera;
	private Texture texture;
	
	public ParticleRenderer(ShaderProgram program, Matrix4f projectionMatrix, FirstPersonCamera camera) {
		super(program);
		this.camera = camera;
		
		try {
			texture = TextureCache.getInstance().getTexture("textures/something.png");
		} catch (Exception e) {
			Debug.severe(e);
		}
		
		shader.use();
		shader.setMat4("projection", projectionMatrix);
		shader.setInt("texture2D", 0);
		
		PARTICLE_VAO = glGenVertexArrays();
		VERTICES_VBO = glGenBuffers();
		TEXTURE_VBO = glGenBuffers();
		
		glBindVertexArray(PARTICLE_VAO);
		
		glBindBuffer(GL_ARRAY_BUFFER, VERTICES_VBO);
		glBufferData(GL_ARRAY_BUFFER, VERTICES, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, TEXTURE_VBO);
		glBufferData(GL_ARRAY_BUFFER, TEX_COORDS, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	@Override
	public void render() {
		startRender();
		
		updateParticles();
		
		for(Particle particle : particles)
		{
			updateModelViewMatrix(particle.getPosition(), particle.getRotation(), particle.getScale());
			if(texture!=null) {
				glActiveTexture(GL_TEXTURE0);
				glBindTexture(GL_TEXTURE_2D, texture.getID());
			}
			glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
		}
		
		stopRender();
	}
	
	private void startRender()
	{
		glBindVertexArray(PARTICLE_VAO);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthMask(false);
	}
	
	private void stopRender()
	{
		glDepthMask(true);
		glDisable(GL_BLEND);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	private void updateParticles()
	{
		Iterator<Particle> iterator = particles.iterator();
		while(iterator.hasNext())
		{
			Particle p = iterator.next();
			boolean stillAlive = p.update();
			if(!stillAlive) iterator.remove();
		}
	}
	
	public static void addParticle(Particle particle)
	{
		particles.add(particle);
	}
	
	private void updateModelViewMatrix(Vector3f position, float rotation, float scale)
	{
		Matrix4f modelMatrix = new Matrix4f();
		Matrix4f viewMatrix = camera.getViewMatrix();
		modelMatrix.translate(position);
		modelMatrix.m00(viewMatrix.m00());
	    modelMatrix.m01(viewMatrix.m10());
	    modelMatrix.m02(viewMatrix.m20());
	    modelMatrix.m10(viewMatrix.m01());
	    modelMatrix.m11(viewMatrix.m11());
	    modelMatrix.m12(viewMatrix.m21());
	    modelMatrix.m20(viewMatrix.m02());
	    modelMatrix.m21(viewMatrix.m12());
	    modelMatrix.m22(viewMatrix.m22());
		modelMatrix.rotate((float)Math.toRadians(rotation), new Vector3f(0,0,1));
		modelMatrix.scale(scale);

		shader.use();
		shader.setMat4("modelViewMatrix", viewMatrix.mul(modelMatrix));
	}
	
}
