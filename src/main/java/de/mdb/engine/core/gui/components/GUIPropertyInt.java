package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;
import static org.lwjgl.nuklear.Nuklear.nk_property_int;

import org.lwjgl.nuklear.NkContext;

public class GUIPropertyInt extends GUIComponent{
	
	private String name;
	private int min;
	private int[] val;
	private int max;
	private int step;
	private float inc_per_pixel;
	
	public GUIPropertyInt(int height, String name, int min, int value, int max, int step, float inc_per_pixel) {
		super(height);
		init(name, min, value, max, step, inc_per_pixel);
	}
	
	public GUIPropertyInt(String name, int min, int value, int max, int step, float inc)
	{
		super();
		init(name, min, value, max, step, inc);
	}
	
	private void init(String name, int min, int value, int max, int step, float inc)
	{
		this.name = name;
		this.min = min;
		this.val = new int[]{value};
		this.max = max;
		this.step = step;
		this.inc_per_pixel = inc;
	}
	
	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		nk_property_int(context, name, min, val, max, step, inc_per_pixel);
		layoutComponents(context);
	}
	
	public int getValue()
	{
		return val[0];
	}
	
	public void setValue(int value)
	{
		val[0] = value;
	}

	public String getName() {
		return name;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getStep() {
		return step;
	}

	public float getIncPerPixel() {
		return inc_per_pixel;
	}
	
	

}
