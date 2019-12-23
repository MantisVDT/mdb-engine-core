package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MINIMIZED;
import static org.lwjgl.nuklear.Nuklear.nk_begin;
import static org.lwjgl.nuklear.Nuklear.nk_end;
import static org.lwjgl.nuklear.Nuklear.nk_recti;

import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkRect;

public class GUIWindow extends GUIComponent{
	
	private String name;
	private int x;
	private int y;
	private int width;
	private int flags;
	private int additional_flags = 0;
	
	public GUIWindow(String name, int x, int y, int width, int height, int flags) {
		super(height);
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.flags = flags;
	}

	@Override
	public void layout(NkContext context) {
		if(nk_begin(context, name, nk_recti(x, y, width, height, NkRect.create()), flags | additional_flags))
		{
			setMinimized(false);
			layoutComponents(context);
		}
		nk_end(context);
	}
	
	public void setMinimized(boolean flag)
	{
		if(flag)
			additional_flags = NK_WINDOW_MINIMIZED;
		else
			additional_flags = 0;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
