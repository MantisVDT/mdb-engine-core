package de.mdb.engine.core.particle.emitter_shape;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import de.mdb.engine.core.GameEngine;

public class ParticleConeShape implements ParticleEmitterShape{
	
	private Vector3f direction;
	private float angle;
	
	private static final Random random = GameEngine.RANDOM;
	
	public ParticleConeShape(Vector3f direction, float angle) {
		this.direction = direction;
		this.angle = angle;
	}
	
	@Override
	public Vector3f randomUnitVector() {
		float cosAngle = (float) Math.cos(angle);
        float theta = (float) (random.nextFloat() * 2f * Math.PI);
        float z = cosAngle + (random.nextFloat() * (1 - cosAngle));
        float rootOneMinusZSquared = (float) Math.sqrt(1 - z * z);
        float x = (float) (rootOneMinusZSquared * Math.cos(theta));
        float y = (float) (rootOneMinusZSquared * Math.sin(theta));
 
        Vector4f dir = new Vector4f(x, y, z, 1);
        if (direction.x != 0 || direction.y != 0 || (direction.z != 1 && direction.z != -1)) {
            Vector3f rotateAxis = new Vector3f();
            direction.cross(new Vector3f(0, 0, 1), rotateAxis);
            rotateAxis.normalize();
            float rotateAngle = (float) Math.acos(direction.dot(new Vector3f(0, 0, 1)));
            Matrix4f rotationMatrix = new Matrix4f();
            rotationMatrix.rotate(-rotateAngle, rotateAxis);
            rotationMatrix.transform(dir);
        } else if (direction.z == -1) {
        	dir.z *= -1;
        }
        return new Vector3f(dir.x, dir.y, dir.z);
	}

}
