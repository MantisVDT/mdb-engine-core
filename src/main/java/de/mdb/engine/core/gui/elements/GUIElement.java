package de.mdb.engine.core.gui.elements;

import org.lwjgl.nuklear.NkContext;

import de.mdb.engine.core.gui.style.DefaultStyle;
import de.mdb.engine.core.gui.style.GUIStyle;

public abstract class GUIElement {
	
	protected int x;
	protected int y;
	
	protected String name;
	
	protected GUIStyle style;
	
	public GUIElement(String name)
	{
		this.name = name;
		this.style = new DefaultStyle();
	}
	
	public abstract void layout(NkContext context);
	
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
