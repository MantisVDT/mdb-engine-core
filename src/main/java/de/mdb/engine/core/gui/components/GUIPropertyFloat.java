package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_propertyf;

import org.lwjgl.nuklear.NkContext;

public class GUIPropertyFloat extends GUIComponent{

	private String name;
	private float min;
	private float val;
	private float max;
	private float step;
	private float inc_per_pixel;
	
	public GUIPropertyFloat(String name, float min, float val, float max, float step, float inc_per_pixel) {
		super();
		this.name = name;
		this.min = min;
		this.val = val;
		this.max = max;
		this.step = step;
		this.inc_per_pixel = inc_per_pixel;
	}
	
	@Override
	public void layout(NkContext context) {
		nk_propertyf(context, name, min, val, max, step, inc_per_pixel);
		layoutComponents(context);
	}
	
	public float getValue()
	{
		return val;
	}

}
