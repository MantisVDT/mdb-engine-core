package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.NK_COLOR_BORDER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_BUTTON;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_BUTTON_ACTIVE;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_BUTTON_HOVER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_CHART;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_CHART_COLOR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_CHART_COLOR_HIGHLIGHT;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_COMBO;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_COUNT;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_EDIT;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_EDIT_CURSOR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_HEADER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_PROPERTY;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SCROLLBAR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SCROLLBAR_CURSOR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SCROLLBAR_CURSOR_ACTIVE;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SCROLLBAR_CURSOR_HOVER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SELECT;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SELECT_ACTIVE;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SLIDER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SLIDER_CURSOR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SLIDER_CURSOR_ACTIVE;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_SLIDER_CURSOR_HOVER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_TAB_HEADER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_TEXT;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_TOGGLE;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_TOGGLE_CURSOR;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_TOGGLE_HOVER;
import static org.lwjgl.nuklear.Nuklear.NK_COLOR_WINDOW;
import static org.lwjgl.nuklear.Nuklear.nk_style_default;
import static org.lwjgl.nuklear.Nuklear.nk_style_from_table;

import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkContext;

public abstract class GUIStyle {
	
	public  NkColor TEXT;
	public  NkColor WINDOW;
	public  NkColor HEADER;
	public  NkColor BORDER;
	public  NkColor BUTTON;
	public  NkColor BUTTON_HOVER;
	public  NkColor BUTTON_ACTIVE;
	public  NkColor TOGGLE;
	public  NkColor TOGGLE_HOVER;
	public  NkColor TOGGLE_CURSOR;
	public  NkColor SELECT;
	public  NkColor SELECT_ACTIVE;
	public  NkColor SLIDER;
	public  NkColor SLIDER_CURSOR;
	public  NkColor SLIDER_CURSOR_HOVER;
	public  NkColor SLIDER_CURSOR_ACTIVE;
	public  NkColor PROPERTY;
	public  NkColor EDIT;
	public  NkColor EDIT_CURSOR;
	public  NkColor COMBO;
	public  NkColor CHART;
	public  NkColor CHART_COLOR;
	public  NkColor CHAR_COLOR_HIGHLIGHT;
	public  NkColor SCROLLBAR;
	public  NkColor SCROLLBAR_CURSOR;
	public  NkColor SCROLLBAR_CURSOR_HOVER;
	public  NkColor SCROLLBAR_CURSOR_ACTIVE;
	public  NkColor TAB_HEADER;
	
	public void applyStyle(NkContext context)
	{
		NkColor.Buffer style = styleBuffer();
		if(style != null)
		{
			nk_style_from_table(context, style);
		}else {
			nk_style_default(context);
		}
	}
	
	protected NkColor.Buffer styleBuffer()
	{
		return NkColor.create(NK_COLOR_COUNT)
				.put(NK_COLOR_TEXT, TEXT)
				.put(NK_COLOR_WINDOW, WINDOW)
				.put(NK_COLOR_HEADER, HEADER)
				.put(NK_COLOR_BORDER, BORDER)
				.put(NK_COLOR_BUTTON, BUTTON)
				.put(NK_COLOR_BUTTON_HOVER, BUTTON_HOVER)
				.put(NK_COLOR_BUTTON_ACTIVE, BUTTON_ACTIVE)
				.put(NK_COLOR_TOGGLE, TOGGLE)
				.put(NK_COLOR_TOGGLE_HOVER, TOGGLE_HOVER)
				.put(NK_COLOR_TOGGLE_CURSOR, TOGGLE_CURSOR)
				.put(NK_COLOR_SELECT, SELECT)
				.put(NK_COLOR_SELECT_ACTIVE, SELECT_ACTIVE)
				.put(NK_COLOR_SLIDER, SLIDER)
				.put(NK_COLOR_SLIDER_CURSOR, SLIDER_CURSOR)
				.put(NK_COLOR_SLIDER_CURSOR_HOVER, SLIDER_CURSOR_HOVER)
				.put(NK_COLOR_SLIDER_CURSOR_ACTIVE, SLIDER_CURSOR_ACTIVE)
				.put(NK_COLOR_PROPERTY, PROPERTY)
				.put(NK_COLOR_EDIT, EDIT)
				.put(NK_COLOR_EDIT_CURSOR, EDIT_CURSOR)
				.put(NK_COLOR_COMBO, COMBO)
				.put(NK_COLOR_CHART, CHART)
				.put(NK_COLOR_CHART_COLOR, CHART_COLOR)
				.put(NK_COLOR_CHART_COLOR_HIGHLIGHT, CHAR_COLOR_HIGHLIGHT)
				.put(NK_COLOR_SCROLLBAR, SCROLLBAR)
				.put(NK_COLOR_SCROLLBAR_CURSOR, SCROLLBAR_CURSOR)
				.put(NK_COLOR_SCROLLBAR_CURSOR_HOVER, SCROLLBAR_CURSOR_HOVER)
				.put(NK_COLOR_SCROLLBAR_CURSOR_ACTIVE, SCROLLBAR_CURSOR_ACTIVE)
				.put(NK_COLOR_TAB_HEADER, TAB_HEADER);
	}
	
}
