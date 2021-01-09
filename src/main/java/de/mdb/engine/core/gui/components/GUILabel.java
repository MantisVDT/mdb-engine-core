package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_label;
import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;

import org.lwjgl.nuklear.NkContext;

public class GUILabel extends GUIComponent{
	
	private String label;
	private int align;
	
	/**
	 * Initializes a new GUILabel
	 * 
	 * @param height The Height of the Label
	 * @param label Text of the Label
	 * @param align The alignment
	 */
	public GUILabel(int height, String label, int align) {
		super(height);
		init(label, align);
	}
	
	/**
	 * Initializes a new GUILabel
	 * 
	 * @param label Text of the Label
	 * @param align The alignment
	 */
	public GUILabel(String label, int align) {
		super();
		init(label, align);
	}
	
	private void init(String label, int align)
	{
		this.label = label;
		this.align = align;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		nk_label(context, label, align);
		layoutComponents(context);
	}

}
