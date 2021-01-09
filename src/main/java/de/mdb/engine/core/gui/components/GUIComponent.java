package de.mdb.engine.core.gui.components;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.nuklear.NkContext;

public abstract class GUIComponent {
	
	protected ArrayList<GUIComponent> components;
	
	protected int height;
	protected int cols;
	protected boolean grouped;
	
	public static final int DEFAULT_COLS = 1;
	public static final int DEFAULT_HEIGHT = 30;
	public static final boolean DEFAULT_GROUPED = false;
	
	/**
	 * Constructor for GUIComponent <br>
	 * If no height is specified the DEFAULT_HEIGHT constant will be used
	 */
	public GUIComponent()
	{
		init(DEFAULT_HEIGHT);
	}
	
	/**
	 * Constructor for GUIComponent <br>
	 * Initializes the Height of the Component
	 * 
	 * @param height The height of the Component
	 */
	public GUIComponent(int height)
	{
		init(height);
	}
	
	private void init(int height)
	{
		components = new ArrayList<>();
		this.height = height;
		this.cols = DEFAULT_COLS;
		this.grouped = DEFAULT_GROUPED;
	}
	
	public abstract void layout(NkContext context);
	
	public void layoutComponents(NkContext context)
	{
		components.forEach(comp -> comp.layout(context));
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
	
	public int height() {
		return height;
	}
	
	public void height(int height)
	{
		this.height = height;
	}
	
	public void setCols(int cols)
	{
		this.cols = cols;
	}
	
	/**
	 * Recursively fetches all components and subcomponents of this GUIComponent that are of type c
	 * 
	 * @param c The GUIComponent Type to filter all the Components by
	 * @return The List of GUIComponents of type c
	 */
	public List<GUIComponent> getComponentsOfType(Class<? extends GUIComponent> c)
	{
		return getComponentsOfType(this, c);
	}
	
	/**
	 * Recursively fetches all components and subcomponents of the comp GUIComponent that are of type c
	 * 
	 * @param comp The component to get the subcomponents from
	 * @param c The GUIComponent Type to filter all the Components by
	 * @return The List of GUIComponents of type c in comp
	 */
	public List<GUIComponent> getComponentsOfType(GUIComponent comp, Class<? extends GUIComponent> c)
	{
		List<GUIComponent> componentsOfType = new ArrayList<>();
		
		for(GUIComponent component : comp.components)
		{
			if(component.components.isEmpty())
			{
				if(component.getClass().equals(c))
				{
					componentsOfType.add(component);
				}
			}else {
				componentsOfType.addAll(getComponentsOfType(component, c));
			}	
		}
		
		return componentsOfType;
	}
}
