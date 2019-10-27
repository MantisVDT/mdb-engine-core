package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;

import org.lwjgl.nuklear.NkContext;

public class GUILayoutRowDynamic extends GUIComponent{
	
	private float height;
	private int cols;
	
	public GUILayoutRowDynamic(float height, int cols) {
		super();
		this.height = height;
		this.cols = cols;
	}
	
	@Override
	public void layout(NkContext context) {
		nk_layout_row_dynamic(context, height, cols);
		layoutComponents(context);
	}

}
