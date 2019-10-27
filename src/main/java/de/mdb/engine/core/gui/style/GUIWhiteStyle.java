package de.mdb.engine.core.gui.style;

import static org.lwjgl.nuklear.Nuklear.nk_rgba;

import org.lwjgl.nuklear.NkColor;

public class GUIWhiteStyle extends GUIStyle {
	
	public GUIWhiteStyle() {
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
}
