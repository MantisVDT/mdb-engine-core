package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_label;

import org.lwjgl.nuklear.NkContext;

public class GUILabel extends GUIComponent{
	
	private String label;
	private int align;
	
	public GUILabel(String label, int align) {
		super();
		this.label = label;
		this.align = align;
	}
	
	@Override
	public void layout(NkContext context) {
		nk_label(context, label, align);
		layoutComponents(context);
	}

}
