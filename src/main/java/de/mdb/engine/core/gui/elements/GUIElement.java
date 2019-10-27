package de.mdb.engine.core.gui.elements;

import java.util.ArrayList;

import org.lwjgl.nuklear.NkContext;

import de.mdb.engine.core.gui.components.GUIComponent;
import de.mdb.engine.core.gui.style.GUIDefaultStyle;
import de.mdb.engine.core.gui.style.GUIStyle;

public abstract class GUIElement {
	
	protected int x;
	protected int y;
	
	protected String name;
	
	protected GUIStyle style;
	
	protected ArrayList<GUIComponent> components;
	
	public GUIElement(String name, int x, int y)
	{
		this.style = new GUIDefaultStyle();
		components = new ArrayList<>();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public abstract void layout(NkContext context);
	
	public void addComponent(GUIComponent component)
	{
		components.add(component);
	}
	
	public void removeComponent(GUIComponent component)
	{
		components.remove(component);
	}
	
	public void removeComponent(int index)
	{
		components.remove(index);
	}
	
	public void setGUIStyle(GUIStyle style)
	{
		this.style = style;
	}
	
	public GUIStyle getStyle()
	{
		return style;
	}
	
	public int x(){
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void y(int i)
	{
		y = i;
	}
	
	public void x(int i)
	{
		x = i;
	}
	
}
