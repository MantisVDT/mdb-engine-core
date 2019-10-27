package de.mdb.engine.core.gui.components;

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
	private int height;
	private int flags;
	
	public GUIWindow(String name, int x, int y, int width, int height, int flags) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.flags = flags;
	}

	@Override
	public void layout(NkContext context) {
		if(nk_begin(context, name, nk_recti(x, y, width, height, NkRect.create()), flags))
		{
			layoutComponents(context);
		}
		nk_end(context);
	}

}
