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
import static org.lwjgl.nuklear.Nuklear.nk_rgba;

import org.lwjgl.nuklear.NkColor;

public class WhiteStyle extends GUIStyle {
	
	public WhiteStyle() {
		TEXT 					= nk_rgba(70, 70, 70, 255, NkColor.create());
		WINDOW 					= nk_rgba(175, 175, 175, 255, NkColor.create());
		HEADER 					= nk_rgba(175, 175, 175, 255, NkColor.create());
		BORDER 					= nk_rgba(0, 0, 0, 255, NkColor.create());
		BUTTON 					= nk_rgba(185, 185, 185, 255, NkColor.create());
		BUTTON_HOVER 			= nk_rgba(170, 170, 170, 255, NkColor.create());
		BUTTON_ACTIVE 			= nk_rgba(160, 160, 160, 255, NkColor.create());
		TOGGLE 					= nk_rgba(150, 150, 150, 255, NkColor.create());
		TOGGLE_HOVER 			= nk_rgba(120, 120, 120, 255, NkColor.create());
		TOGGLE_CURSOR 			= nk_rgba(175, 175, 175, 255, NkColor.create());
		SELECT 					= nk_rgba(190, 190, 190, 255, NkColor.create());
		SELECT_ACTIVE 			= nk_rgba(175, 175, 175, 255, NkColor.create());
		SLIDER 					= nk_rgba(190, 190, 190, 255, NkColor.create());
		SLIDER_CURSOR 			= nk_rgba(80, 80, 80, 255, NkColor.create());
		SLIDER_CURSOR_HOVER 	= nk_rgba(70, 70, 70, 255, NkColor.create());
		SLIDER_CURSOR_ACTIVE 	= nk_rgba(60, 60, 60, 255, NkColor.create());
		PROPERTY 				= nk_rgba(175, 175, 175, 255, NkColor.create());
		EDIT 					= nk_rgba(150, 150, 150, 255, NkColor.create());
		EDIT_CURSOR 			= nk_rgba(0, 0, 0, 255, NkColor.create());
		COMBO 					= nk_rgba(175, 175, 175, 255, NkColor.create());
		CHART 					= nk_rgba(160, 160, 160, 255, NkColor.create());
		CHART_COLOR 			= nk_rgba(45, 45, 45, 255, NkColor.create());
		CHAR_COLOR_HIGHLIGHT 	= nk_rgba(255, 0, 0, 255, NkColor.create());
		SCROLLBAR 				= nk_rgba(180, 180, 180, 255, NkColor.create());
		SCROLLBAR_CURSOR 		= nk_rgba(140, 140, 140, 255, NkColor.create());
		SCROLLBAR_CURSOR_HOVER 	= nk_rgba(150, 150, 150, 255, NkColor.create());
		SCROLLBAR_CURSOR_ACTIVE = nk_rgba(160, 160, 160, 255, NkColor.create());
		TAB_HEADER				= nk_rgba(180, 180, 180, 255, NkColor.create());
	}
	
	protected NkColor.Buffer styleBuffer() {
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
