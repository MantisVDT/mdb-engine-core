package de.mdb.engine.core.textures;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {
	
	private int ID;
	
	private int w;
	private int h;
	
	public Texture(ByteBuffer data, int w, int h)
	{	
		this.w = w;
		this.h = h;
		
		ID = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, ID);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		glGenerateMipmap(GL_TEXTURE_2D);
	}
	
	public int getID()
	{
		return ID;
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	//TODO: Doesnt really work, should implement
	public static final Texture DEFAULT_TEXTURE = new Texture(ByteBuffer.wrap(new byte[0]), 1, 1);
	
}
