package de.mdb.engine.core.gui.components;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.nuklear.NkContext;

public abstract class GUIComponent {
	
	protected ArrayList<GUIComponent> components;
	
	protected GUIComponent()
	{
		components = new ArrayList<>();
	}
	
	public abstract void layout(NkContext context);
	
	public void layoutComponents(NkContext context)
	{
		for(GUIComponent component : components)
		{
			component.layout(context);
		}
	}
	
	public GUIComponent addComponent(GUIComponent component)
	{
		components.add(component);
		return this;
	}
	
	public void removeComponent(GUIComponent component)
	{
		components.remove(component);
	}
	
	public void removeComponent(int index)
	{
		components.remove(index);
	}
	
	public GUIComponent getComponent(int index)
	{
		return components.get(index);
	}
	
	public List<GUIComponent> getComponents()
	{
		return components;
	}
	
	public List<GUIComponent> getComponentsOfType(Class<? extends GUIComponent> c)
	{
		List<GUIComponent> componentsOfType = new ArrayList<>();
		
		for(GUIComponent component : components)
		{
			if(component.getClass().equals(c))
			{
				componentsOfType.add(component);
			}
		}
		
		return componentsOfType;
	}
	
}
