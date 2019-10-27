package de.mdb.engine.core.gui;

import java.util.ArrayList;

import de.mdb.engine.core.gui.elements.GUIElement;
import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.render.GUIRenderer;

public class GUIManager {
	
	private static ArrayList<GUIRenderer> renderers = new ArrayList<>();
	
	private GUIManager() {}
	
	public static void render()
	{
		for(GUIRenderer renderer : renderers)
		{
			renderer.render();
		}
	}
	
	public static void startInputFrame()
	{
		for(GUIRenderer renderer : renderers)
		{
			renderer.startInputFrame();
		}
	}
	
	public static void endInputFrame()
	{
		for(GUIRenderer renderer : renderers)
		{
			renderer.endInputFrame();
		}
	}
	
	public static void addGUIRenderer(GUIRenderer renderer)
	{
		renderers.add(renderer);
	}
	
	public static void removeGUIRenderer(GUIRenderer renderer)
	{
		renderers.remove(renderer);
	}
	
	public static void removeGUIRenderer(int index)
	{
		renderers.remove(index);
	}
	
	public static GUIRenderer getRenderer(int index)
	{
		return renderers.get(index);
	}
	
	public static void addGUIElement(int index, GUIElement element)
	{
		if(index >= renderers.size()) 
		{
			Debug.warning("Index is invalid");
			return;
		}
		renderers.get(index).addGUIElement(element);
	}
	
	public static void addGUIElement(GUIRenderer renderer, GUIElement element)
	{
		if(!renderers.contains(renderer)) 
		{
			Debug.warning("Invalid renderer");
			return;
		}
		renderer.addGUIElement(element);
	}
	
}
