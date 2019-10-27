package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_button_label;

import org.lwjgl.nuklear.NkContext;

public class GUIButtonLabel extends GUIComponent{

	private String name;
	private boolean isPressed;
	
	public GUIButtonLabel(String name) {
		super();
		this.name = name;
		isPressed = false;
	}
	
	@Override
	public void layout(NkContext context) {
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
