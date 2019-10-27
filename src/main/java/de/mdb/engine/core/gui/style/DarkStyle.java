package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.nk_rgba;

import org.lwjgl.nuklear.NkColor;

public class DarkStyle extends GUIStyle {
	
	public DarkStyle()
	{
		TEXT 					= nk_rgba(210, 210, 210, 255, NkColor.create());
		WINDOW 					= nk_rgba(57, 67, 71, 255, NkColor.create());
		HEADER 					= nk_rgba(51, 51, 17556, 255, NkColor.create());
		BORDER 					= nk_rgba(46, 46, 46, 255, NkColor.create());
		BUTTON 					= nk_rgba(48, 83, 111, 255, NkColor.create());
		BUTTON_HOVER 			= nk_rgba(58, 93, 121, 255, NkColor.create());
		BUTTON_ACTIVE 			= nk_rgba(63, 98, 126, 255, NkColor.create());
		TOGGLE 					= nk_rgba(50, 58, 61, 255, NkColor.create());
		TOGGLE_HOVER 			= nk_rgba(45, 53, 120, 255, NkColor.create());
		TOGGLE_CURSOR 			= nk_rgba(48, 83, 111, 255, NkColor.create());
		SELECT 					= nk_rgba(57, 67, 61, 255, NkColor.create());
		SELECT_ACTIVE 			= nk_rgba(48, 83, 111, 255, NkColor.create());
		SLIDER 					= nk_rgba(50, 58, 61, 255, NkColor.create());
		SLIDER_CURSOR 			= nk_rgba(48, 83, 111, 255, NkColor.create());
		SLIDER_CURSOR_HOVER 	= nk_rgba(53, 88, 116, 255, NkColor.create());
		SLIDER_CURSOR_ACTIVE 	= nk_rgba(58, 93, 121, 255, NkColor.create());
		PROPERTY 				= nk_rgba(50, 58, 61, 255, NkColor.create());
		EDIT 					= nk_rgba(50, 58, 61, 255, NkColor.create());
		EDIT_CURSOR 			= nk_rgba(210, 210, 210, 255, NkColor.create());
		COMBO 					= nk_rgba(50, 58, 61, 255, NkColor.create());
		CHART 					= nk_rgba(50, 58, 61, 255, NkColor.create());
		CHART_COLOR 			= nk_rgba(48, 83, 111, 255, NkColor.create());
		CHAR_COLOR_HIGHLIGHT 	= nk_rgba(255, 0, 0, 255, NkColor.create());
		SCROLLBAR 				= nk_rgba(50, 58, 61, 255, NkColor.create());
		SCROLLBAR_CURSOR 		= nk_rgba(48, 83, 111, 255, NkColor.create());
		SCROLLBAR_CURSOR_HOVER 	= nk_rgba(53, 88, 116, 255, NkColor.create());
		SCROLLBAR_CURSOR_ACTIVE = nk_rgba(58, 93, 121, 255, NkColor.create());
		TAB_HEADER				= nk_rgba(48, 83, 111, 255, NkColor.create());
	}
	
}
