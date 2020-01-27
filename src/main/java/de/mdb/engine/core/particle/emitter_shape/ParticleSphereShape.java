package de.mdb.engine.core.particle.emitter_shape;

import java.util.Random;

import org.joml.Vector3f;

import de.mdb.engine.core.GameEngine;

public class ParticleSphereShape implements ParticleEmitterShape{
	
	private static final Random random = GameEngine.RANDOM;
	
	@Override
	public Vector3f randomUnitVector() {
		float x = randomInRange(-1,1);
		float y = randomInRange(-1,1);
		float z = randomInRange(-1,1);
		Vector3f direction = new Vector3f(x, y, z);
		return direction.normalize();
	}
	
	private float randomInRange(float min, float max)
	{
		return min + (max - min) * random.nextFloat();
	}

}
