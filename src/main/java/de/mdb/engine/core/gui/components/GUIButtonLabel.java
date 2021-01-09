package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_button_label;
import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;

import org.lwjgl.nuklear.NkContext;

public class GUIButtonLabel extends GUIComponent{

	private String name;
	private boolean isPressed;
	
	public GUIButtonLabel(String name)
	{
		super();
		init(name);
	}
	
	public GUIButtonLabel(int height, String name) {
		super(height);
		init(name);
	}
	
	private void init(String name)
	{
		this.name = name;
		isPressed = false;
	}
	
	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		if(nk_button_label(context, name))
		{
			isPressed = true;
		}else {
			isPressed = false;
		}
		layoutComponents(context);
	}
	
	public boolean isPressed()
	{
		return isPressed;
	}
	
}
