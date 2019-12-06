package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_combo_begin_color;
import static org.lwjgl.nuklear.Nuklear.nk_combo_end;

import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkVec2;

public class GUIComboColor extends GUIComponent{
	
	private NkColor color;
	private float width;
	private float height;
	
	private boolean open;
	
	public GUIComboColor(NkColor color, float width, float height) {
		super();
		this.color = color;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void layout(NkContext context) {
		open = nk_combo_begin_color(context, color, NkVec2.create().set(width, height));
		
		if(open)
		{
			layoutComponents(context);
		}
		
		nk_combo_end(context);
	}
	
	public void setColor(NkColor color)
	{
		this.color = color;
	}
	
	public NkColor getColor() {
		return color;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
