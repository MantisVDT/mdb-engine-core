package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.nk_style_default;

import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkContext;

public class GUIDefaultStyle extends GUIStyle {
	
	public void applyStyle(NkContext context)
	{
		nk_style_default(context);
	}
	
	@Override
	protected NkColor.Buffer styleBuffer() {
		return null;
	}
	
}
