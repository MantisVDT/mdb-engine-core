package de.mdb.engine.core.particle;

import de.mdb.engine.core.particle.emitter_shape.ParticleEmitterShape;

public abstract class ParticleSystem {
	
	public ParticleEmitterShape shape;
	
	public ParticleSystem(ParticleEmitterShape shape)
	{
		this.shape = shape;
	}
	
}
