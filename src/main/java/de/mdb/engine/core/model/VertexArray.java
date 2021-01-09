package de.mdb.engine.core.model;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {
	
	private int id;
	private int currentBufferID;
	
	public VertexArray()
	{
		id = glGenVertexArrays();
		currentBufferID = 0;
	}
	
	public void loadArray(float[] buffer, int dataSize)
	{
		glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(currentBufferID, dataSize, GL_FLOAT, false, dataSize * Float.BYTES, 0);
		glEnableVertexAttribArray(currentBufferID);
		
		currentBufferID++;
	}
	
	public void loadElement(int[] element)
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, glGenBuffers());
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, element, GL_STATIC_DRAW);
	}
	
	public void enableBuffers()
	{
		for(int i=0;i<currentBufferID;i++)
		{
			glEnableVertexAttribArray(i);
		}
	}
	
	public void disableBuffers()
	{
		for(int i=0; i<currentBufferID;i++)
		{
			glDisableVertexAttribArray(i);
		}
	}
	
	public void bind()
	{
		glBindVertexArray(id);
	}
	
	public void unbind()
	{
		glBindVertexArray(0);
	}
	
}
