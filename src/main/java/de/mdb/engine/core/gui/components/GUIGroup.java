package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;

import org.lwjgl.nuklear.NkContext;

public class GUIGroup extends GUIComponent{

	public GUIGroup(int height, int cols) {
		super(height);
		this.setCols(cols);
	}
	
	public GUIGroup() {
		super();
	}
	
	@Override
	public void layout(NkContext context) {
		nk_layout_row_dynamic(context, height, cols);
		layoutComponents(context);
	}
	
	@Override
	public GUIComponent addComponent(GUIComponent component)
	{
		component.grouped = true;
		components.add(component);
		return this;
	}
}
