package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_property_int;

import org.lwjgl.nuklear.NkContext;

public class GUIPropertyInt extends GUIComponent{
	
	private String name;
	private int min;
	private int[] val;
	private int max;
	private int step;
	private float inc_per_pixel;
	
	public GUIPropertyInt(String name, int min, int val, int max, int step, float inc_per_pixel) {
		super();
		this.name = name;
		this.min = min;
		this.val = new int[]{val};
		this.max = max;
		this.step = step;
		this.inc_per_pixel = inc_per_pixel;
	}
	
	@Override
	public void layout(NkContext context) {
		nk_property_int(context, name, min, val, max, step, inc_per_pixel);
		layoutComponents(context);
	}
	
	public int getValue()
	{
		return val[0];
	}

}
