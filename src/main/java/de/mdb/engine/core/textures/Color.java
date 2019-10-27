package de.mdb.engine.core.textures;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Color {
	
	public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
	public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
	
	private float red;
	private float blue;
	private float green;
	private float alpha;
	
	public Color()
	{
		this(0f,0f,0f);
	}
	
	public Color(float r, float g, float b)
	{
		this(r,g,b,1.0f);
	}
	
	public Color(float r, float g, float b, float a)
	{
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		if(red > 1.0f) red = 1.0f;
		if(red < 0.0f) red = 0.0f;
		this.red = red;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		if(blue > 1.0f) blue = 1.0f;
		if(blue < 0.0f) blue = 0.0f;
		this.blue = blue;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		if(green > 1.0f) green = 1.0f;
		if(green < 0.0f) green = 0.0f;
		this.green = green;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		if(alpha > 1.0f) alpha = 1.0f;
		if(alpha < 0.0f) alpha = 0.0f;
		this.alpha = alpha;
	}
	
	public Vector3f toVetor3f()
	{
		return new Vector3f(red, green, blue);
	}
	
	public Vector4f toVector4f()
	{
		return new Vector4f(red, green, blue, alpha);
	}
	
}
