package de.mdb.engine.core.light;

import java.util.ArrayList;
import java.util.List;

import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.shader.ShaderProgram;

public class PointLightManager {
	
	public static final int MAX_POINT_LIGHTS = 4;
	
	private static List<PointLight> pointLights = new ArrayList<>();
	
	private PointLightManager() {}
	
	public static void addPointLight(PointLight pointLight)
	{
		if(pointLights.size() < MAX_POINT_LIGHTS && pointLight.getID() != -1) pointLights.add(pointLight);
		else Debug.warning("Tried to load more than the maximum of PointLights. Ignoring request");
	}
	
	public static void removePointLight(int index)
	{
		if(index >= 0 && index < MAX_POINT_LIGHTS) pointLights.remove(index);
	}
	
	public static void load(ShaderProgram shader) 
	{
		for(PointLight p : pointLights)
		{
			p.load(shader);
		}
	}
	
	public static int nextID()
	{
		if(pointLights.size() < MAX_POINT_LIGHTS)
			return pointLights.size();
		else
			return -1;
	}
	
}
