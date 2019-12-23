package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;
import static org.lwjgl.nuklear.Nuklear.nk_propertyf;

import org.lwjgl.nuklear.NkContext;

public class GUIPropertyFloat extends GUIComponent{

	private String name;
	private float min;
	private float val;
	private float max;
	private float step;
	private float inc_per_pixel;
	
	public GUIPropertyFloat(int height, String name, float min, float val, float max, float step, float inc_per_pixel) {
		super(height);
		init(name, min, val, max, step, inc_per_pixel);
	}
	
	public GUIPropertyFloat(String name, float min, float val, float max, float step, float inc) {
		super();
		init(name, min, val, max, step, inc);
	}
	
	private void init(String name, float min, float val, float max, float step, float inc)
	{
		this.name = name;
		this.min = min;
		this.val = val;
		this.max = max;
		this.step = step;
		this.inc_per_pixel = inc;
	}
	
	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		val = nk_propertyf(context, name, min, val, max, step, inc_per_pixel);
		layoutComponents(context);
	}
	
	public String getName()
	{
		return name;
	}
	
	public float getValue()
	{
		return val;
	}
	
	public void setValue(float value)
	{
		val = value;
	}

}
