package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.nk_rgba;

import org.lwjgl.nuklear.NkColor;

public class RedStyle extends GUIStyle {
	
	public RedStyle() {
		TEXT 					= nk_rgba(190, 190, 190, 255, NkColor.create());
		WINDOW 					= nk_rgba(30, 33, 40, 255, NkColor.create());
		HEADER 					= nk_rgba(181, 45, 69, 255, NkColor.create());
		BORDER 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		BUTTON 					= nk_rgba(181, 45, 69, 255, NkColor.create());
		BUTTON_HOVER 			= nk_rgba(190, 50, 70, 255, NkColor.create());
		BUTTON_ACTIVE 			= nk_rgba(195, 55, 75, 255, NkColor.create());
		TOGGLE 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		TOGGLE_HOVER 			= nk_rgba(45, 60, 60, 255, NkColor.create());
		TOGGLE_CURSOR 			= nk_rgba(181, 45, 69, 255, NkColor.create());
		SELECT 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		SELECT_ACTIVE 			= nk_rgba(181, 45, 69, 255, NkColor.create());
		SLIDER 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		SLIDER_CURSOR 			= nk_rgba(181, 45, 69, 255, NkColor.create());
		SLIDER_CURSOR_HOVER 	= nk_rgba(186, 50, 74, 255, NkColor.create());
		SLIDER_CURSOR_ACTIVE 	= nk_rgba(191, 55, 79, 255, NkColor.create());
		PROPERTY 				= nk_rgba(51, 55, 67, 255, NkColor.create());
		EDIT 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		EDIT_CURSOR 			= nk_rgba(190, 190, 190, 255, NkColor.create());
		COMBO 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		CHART 					= nk_rgba(51, 55, 67, 255, NkColor.create());
		CHART_COLOR 			= nk_rgba(170, 40, 60, 255, NkColor.create());
		CHAR_COLOR_HIGHLIGHT 	= nk_rgba(255, 0, 0, 255, NkColor.create());
		SCROLLBAR 				= nk_rgba(30, 33, 40, 255, NkColor.create());
		SCROLLBAR_CURSOR 		= nk_rgba(64, 84, 95, 255, NkColor.create());
		SCROLLBAR_CURSOR_HOVER 	= nk_rgba(70, 90, 100, 255, NkColor.create());
		SCROLLBAR_CURSOR_ACTIVE = nk_rgba(75, 95, 105, 255, NkColor.create());
		TAB_HEADER				= nk_rgba(181, 45, 69, 220, NkColor.create());
	}
}
