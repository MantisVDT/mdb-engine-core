package de.mdb.engine.core.gui.elements;

import static org.lwjgl.nuklear.Nuklear.NK_RGBA;
import static org.lwjgl.nuklear.Nuklear.NK_TEXT_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_BORDER;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MINIMIZABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MOVABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_SCALABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_TITLE;
import static org.lwjgl.system.MemoryStack.stackPush;

import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.GameEngine;
import de.mdb.engine.core.gui.components.GUIButtonLabel;
import de.mdb.engine.core.gui.components.GUIColorPicker;
import de.mdb.engine.core.gui.components.GUILabel;
import de.mdb.engine.core.gui.components.GUIPropertyInt;
import de.mdb.engine.core.gui.components.GUIWindow;
import de.mdb.engine.core.util.Clock;

public class GUIDebugElement extends GUIElement {

	private NkColorf background = NkColorf.create();

	private GUIWindow window;
	private GUIButtonLabel exit;
	private GUIPropertyInt flySpeedSlider;
	
	private GUILabel fpsLabel;
	
	private GUIColorPicker colorPicker;
	
	public GUIDebugElement(String name, int x, int y) {
		super(name, x, y);
		
		//Initialize the background color
		 background.r(0.10f).g(0.18f).b(0.24f).a(1.0f);
		//Create the GUIWindow
		window = new GUIWindow(name, x, y, 220, 220,
				NK_WINDOW_BORDER | NK_WINDOW_MOVABLE | NK_WINDOW_SCALABLE |
				NK_WINDOW_MINIMIZABLE | NK_WINDOW_TITLE);
		components.add(window);
		
		window.setMinimized(true);
		
		fpsLabel = new GUILabel("FPS: ", NK_TEXT_LEFT);
		window.addComponent(fpsLabel);
		
		flySpeedSlider = new GUIPropertyInt("Fly Speed", 1, 2, 15, 1, 0.5f);
		window.addComponent(flySpeedSlider);
		
		window.addComponent(new GUILabel("Color:", NK_TEXT_LEFT));
		colorPicker = new GUIColorPicker(25, background, NK_RGBA);
		colorPicker.setHasSliders(true);
		window.addComponent(colorPicker);
		
		exit = new GUIButtonLabel(30, "Exit");
		window.addComponent(exit);
	}

	@Override
	public void layout(NkContext context) {
		
		style.applyStyle(context);
		
		try (MemoryStack stack = stackPush()) {
			window.layout(context);

			if (exit.isPressed()) {
				GameEngine.stop();
			}
			
			fpsLabel.setLabel("FPS: " + Clock.getAverageFPS());
		}
	}
	
	public int getFlySpeed()
	{
		return flySpeedSlider.getValue();
	}
	
	public NkColorf getBackground() {
		return background;
	}

}
