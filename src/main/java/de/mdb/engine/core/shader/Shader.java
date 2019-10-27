package de.mdb.engine.core.shader;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import de.mdb.engine.core.logger.Debug;

public class Shader {
	
	private int id;
	
	public Shader(String fileName, int type)
	{
		//Handle the creation of the shader
		try {
			createShader(fileName, type);
		} catch (ShaderException e) {
			Debug.severe(e.getMessage());
		}
	}
	
	public static final int VERTEX_SHADER = GL30.GL_VERTEX_SHADER;
	public static final int FRAGMENT_SHADER = GL30.GL_FRAGMENT_SHADER;
	public static final int GEOMETRY_SHADER = GL40.GL_GEOMETRY_SHADER;
	public static final int COMPUTE_SHADER = GL43.GL_COMPUTE_SHADER;
	
	public void delete()
	{
		glDeleteShader(id);
	}
	
	public int getID()
	{
		return id;
	}
	
	private void createShader(String path, int type) 
	{
		StringBuilder shaderSource = new StringBuilder();
		File f = new File(path);
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(f));
			
			if(!f.exists())
			{
				reader.close();
				throw new ShaderException("Couldn't find file!");
			}
			
			String line = reader.readLine();
			while(line != null)
			{
				shaderSource.append(line).append("\n");
				line = reader.readLine();
			}
			
			id = glCreateShader(type);
			glShaderSource(id, shaderSource);
			glCompileShader(id);
			if(glGetShaderi(id, GL_COMPILE_STATUS) == GL11.GL_FALSE) {
				reader.close();
				String info = glGetShaderInfoLog(id);
				throw new ShaderException("Couldn't compile shader: " + path + "\n" + info);
			}
			
			reader.close();
			
		}catch(IOException e) {
			Debug.severe(e.getMessage());
		}
		
	}
	
}
