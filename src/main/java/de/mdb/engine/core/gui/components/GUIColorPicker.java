package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_color_picker;
import static org.lwjgl.nuklear.Nuklear.nk_combo_begin_color;
import static org.lwjgl.nuklear.Nuklear.nk_combo_end;
import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;
import static org.lwjgl.nuklear.Nuklear.nk_propertyf;
import static org.lwjgl.nuklear.Nuklear.nk_rgba_cf;

import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkVec2;

public class GUIColorPicker extends GUIComponent{
	
	private NkColorf color;
	private int colorFormat;

	private static final float MIN  = 0.0f;
	private static final float MAX  = 1.0f;
	private static final float STEP = 0.1f;
	private static final float INC  = 0.05f;
	
	private boolean open;
	private boolean hasSliders;
	
	public GUIColorPicker(NkColorf color, int colorFormat)
	{
		super();
		init(color, colorFormat);
	}
	
	public GUIColorPicker(int height, NkColorf color, int colorFormat)
	{
		super(height);
		init(color, colorFormat);
	}
	
	private void init(NkColorf color, int colorFormat)
	{
		this.color = color;
		this.colorFormat = colorFormat;
		open = false;
	}

	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		open = nk_combo_begin_color(context, nk_rgba_cf(color, NkColor.create()), NkVec2.create().set(250, 300));
		
		if(open)
		{
			nk_layout_row_dynamic(context, 150, cols);
			color = nk_color_picker(context, color, colorFormat);
			
			if(hasSliders)
			{
				nk_layout_row_dynamic(context, 30, cols);
				color.r(nk_propertyf(context, "#R", MIN, color.r(), MAX, STEP, INC));
				color.g(nk_propertyf(context, "#G", MIN, color.g(), MAX, STEP, INC));
				color.b(nk_propertyf(context, "#B", MIN, color.b(), MAX, STEP, INC));
				color.a(nk_propertyf(context, "#A", MIN, color.a(), MAX, STEP, INC));	
			}
		}
		
		nk_combo_end(context);
		
		layoutComponents(context);
	}
	
	public void setHasSliders(boolean flag)
	{
		hasSliders = flag;
	}
	
	public NkColorf getColor() {
		return color;
	}
	
	public void setColor(NkColorf color)
	{
		this.color = color;
	}

	public int getColorFormat() {
		return colorFormat;
	}
}
