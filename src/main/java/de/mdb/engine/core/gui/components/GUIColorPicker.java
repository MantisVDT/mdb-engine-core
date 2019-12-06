package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_color_picker;

import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;

public class GUIColorPicker extends GUIComponent{
	
	private NkColorf color;
	private int colorFormat;
	
	public GUIColorPicker(NkColorf color, int colorFormat)
	{
		super();
		this.color = color;
		this.colorFormat = colorFormat;
	}

	@Override
	public void layout(NkContext context) {
		nk_color_picker(context, color, colorFormat);
		layoutComponents(context);
	}

	public NkColorf getColor() {
		return color;
	}

	public int getColorFormat() {
		return colorFormat;
	}
}
