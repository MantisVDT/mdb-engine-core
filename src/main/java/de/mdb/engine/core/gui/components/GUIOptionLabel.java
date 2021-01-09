package de.mdb.engine.core.gui.components;

import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;
import static org.lwjgl.nuklear.Nuklear.nk_option_label;

import org.lwjgl.nuklear.NkContext;

public class GUIOptionLabel extends GUIComponent {
	
	private String label;
	private boolean active;
	private boolean switched = false;
	
	public GUIOptionLabel(String label, boolean active)
	{
		super();
		init(label, active);
	}
	
	public GUIOptionLabel(int height, String label, boolean active) {
		super(height);
		init(label, active);
	}
	
	private void init(String label, boolean active)
	{
		this.label = label;
		this.active = active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isSwitched() {
		return switched;
	}
	
	@Override
	public void layout(NkContext context) {
		if(!grouped) nk_layout_row_dynamic(context, height, cols);
		if(nk_option_label(context, label, active) != active)
		{
			switched = true;
			active = !active;
		}else {
			switched = false;
		}
		layoutComponents(context);
	}

}
