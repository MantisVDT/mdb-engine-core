package de.mdb.engine.core.textures;

import java.util.HashMap;
import java.util.Map;

public class TextureCache {
	
	private static TextureCache INSTANCE;
	
	private Map<String, Texture> textureMap;
	
	private TextureCache()
	{
		textureMap = new HashMap<>();
	}
	
	public static TextureCache getInstance()
	{
		if(INSTANCE == null) INSTANCE = new TextureCache();
		return INSTANCE;
	}
	
	public Texture getTexture(String path) throws Exception {
		Texture texture = textureMap.get(path);
		
		if(texture == null)
		{
			texture = TextureLoader.loadTexture(path);
			textureMap.put(path, texture);
		}
		
		return texture;
	}
	
}
