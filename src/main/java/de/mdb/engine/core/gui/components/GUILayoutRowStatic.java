package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_static;

import org.lwjgl.nuklear.NkContext;

public class GUILayoutRowStatic extends GUIComponent{
	
	private float height;
	private int item_width;
	private int cols;
	
	public GUILayoutRowStatic(float height, int item_width, int cols) {
		super();
		this.height = height;
		this.item_width = item_width;
		this.cols = cols;
	}
	
	@Override
	public void layout(NkContext context) {
		nk_layout_row_static(context, height, item_width, cols);
		layoutComponents(context);
	}

}
