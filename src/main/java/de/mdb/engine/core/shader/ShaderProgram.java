package de.mdb.engine.core.shader;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

public class ShaderProgram {
	
	private int id;
	
	public ShaderProgram()
	{
		id = glCreateProgram();
	}
	
	public void use()
	{
		glUseProgram(id);
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setMat4(String name, Matrix4f value)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		buffer = value.get(buffer);
		glUniformMatrix4fv(glGetUniformLocation(id, name), false, buffer);
	}
	
	public void setVec3(String name, Vector3f value)
	{
		setVec3(name, value.x, value.y, value.z);
	}
	
	public void setVec3(String name, float v1, float v2, float v3)
	{
		glUniform3f(glGetUniformLocation(id, name), v1, v2, v3);
	}
	
	public void setBool(String name, boolean value)
	{
		int intvalue = value ? 1 : 0;
		glUniform1i(glGetUniformLocation(id, name), intvalue);
	}
	
	public void setInt(String name, int value)
	{
		glUniform1i(glGetUniformLocation(id, name), value);
	}
	
	public void setFloat(String name, float value)
	{
		glUniform1f(glGetUniformLocation(id, name), value);
	}
	
	public void setVec4(String name, Vector4f value)
	{
		setVec4(name, value.x, value.y, value.z, value.w);
	}
	
	public void setVec4(String name, float x, float y, float z, float w)
	{
		glUniform4f(glGetUniformLocation(id, name), x, y, z, w);
	}
	
	public void attachShader(Shader shader)
	{
		glAttachShader(id, shader.getID());
	}
	
	public void linkShader()
	{
		glLinkProgram(id);
	}
	
	public void delete()
	{
		glDeleteProgram(id);
	}
	
}
