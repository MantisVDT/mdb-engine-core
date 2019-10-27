package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.nk_rgba;

import org.lwjgl.nuklear.NkColor;

public class GUIBlueStyle extends GUIStyle {
	
	public GUIBlueStyle()
	{
		TEXT 					= nk_rgba(20, 20, 20, 255, NkColor.create());
		WINDOW 					= nk_rgba(202, 212, 214, 255, NkColor.create());
		HEADER 					= nk_rgba(137, 182, 224, 255, NkColor.create());
		BORDER 					= nk_rgba(140, 159, 173, 255, NkColor.create());
		BUTTON 					= nk_rgba(137, 182, 224, 255, NkColor.create());
		BUTTON_HOVER 			= nk_rgba(142, 187, 229, 255, NkColor.create());
		BUTTON_ACTIVE 			= nk_rgba(147, 192, 234, 255, NkColor.create());
		TOGGLE 					= nk_rgba(177, 210, 210, 255, NkColor.create());
		TOGGLE_HOVER 			= nk_rgba(182, 215, 215, 255, NkColor.create());
		TOGGLE_CURSOR 			= nk_rgba(137, 182, 224, 255, NkColor.create());
		SELECT 					= nk_rgba(177, 210, 210, 255, NkColor.create());
		SELECT_ACTIVE 			= nk_rgba(137, 182, 224, 255, NkColor.create());
		SLIDER 					= nk_rgba(177, 210, 210, 255, NkColor.create());
		SLIDER_CURSOR 			= nk_rgba(137, 182, 224, 255, NkColor.create());
		SLIDER_CURSOR_HOVER 	= nk_rgba(142, 188, 229, 255, NkColor.create());
		SLIDER_CURSOR_ACTIVE 	= nk_rgba(147, 193, 234, 255, NkColor.create());
		PROPERTY 				= nk_rgba(210, 210, 210, 255, NkColor.create());
		EDIT 					= nk_rgba(210, 210, 210, 255, NkColor.create());
		EDIT_CURSOR 			= nk_rgba(20, 20, 20, 255, NkColor.create());
		COMBO 					= nk_rgba(210, 210, 210, 255, NkColor.create());
		CHART 					= nk_rgba(210, 210, 210, 255, NkColor.create());
		CHART_COLOR 			= nk_rgba(137, 182, 244, 255, NkColor.create());
		CHAR_COLOR_HIGHLIGHT 	= nk_rgba(255, 0, 0, 255, NkColor.create());
		SCROLLBAR 				= nk_rgba(190, 200, 200, 255, NkColor.create());
		SCROLLBAR_CURSOR 		= nk_rgba(64, 84, 95, 255, NkColor.create());
		SCROLLBAR_CURSOR_HOVER 	= nk_rgba(70, 90, 100, 255, NkColor.create());
		SCROLLBAR_CURSOR_ACTIVE = nk_rgba(75, 95, 105, 255, NkColor.create());
		TAB_HEADER				= nk_rgba(156, 193, 220, 255, NkColor.create());
	}
	
}
