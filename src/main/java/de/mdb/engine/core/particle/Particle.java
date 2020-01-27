package de.mdb.engine.core.particle;

import org.joml.Vector3f;

import de.mdb.engine.core.render.ParticleRenderer;
import de.mdb.engine.core.util.Clock;

public class Particle {
	
	public static final float GRAVITY = -30.0f;
	
	private Vector3f position;
	private Vector3f velocity;
	private float weight;
	private float life;
	private float rotation;
	private float scale;
	
	private float elapsedTime = 0;

	public Particle(Vector3f position, Vector3f velocity, float weight, float life, float rotation, float scale) {
		this.position = position;
		this.velocity = velocity;
		this.weight = weight;
		this.life = life;
		this.rotation = rotation;
		this.scale = scale;
		ParticleRenderer.addParticle(this);
	}
	
	public boolean update()
	{
		velocity.add(0, GRAVITY * weight * Clock.getDeltaTime(), 0);
		Vector3f change = new Vector3f(velocity);
		change.mul(Clock.getDeltaTime());
		position.add(change);
		elapsedTime += Clock.getDeltaTime();
		return elapsedTime < life;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public float getWeight() {
		return weight;
	}

	public float getLife() {
		return life;
	}

	public float getRotation() {
		return rotation;
	}

	public float getScale() {
		return scale;
	}

	public float getElapsedTime() {
		return elapsedTime;
	}
}
