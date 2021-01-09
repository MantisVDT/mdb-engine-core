package de.mdb.engine.core.gui.elements;

import static org.lwjgl.nuklear.Nuklear.*;
import static org.lwjgl.system.MemoryStack.stackPush;

import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.GameEngine;
import de.mdb.engine.core.gui.components.*;
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
		
		
		window.addComponent(new GUILabel("Keybindings:", NK_TEXT_LEFT));	
		GUIGroup keyGroup = new GUIGroup(15, 2);
		keyGroup.addComponent(new GUILabel("R", NK_TEXT_LEFT));
		keyGroup.addComponent(new GUILabel("Fill", NK_TEXT_RIGHT));
		keyGroup.addComponent(new GUILabel("T", NK_TEXT_LEFT));
		keyGroup.addComponent(new GUILabel("Wireframe", NK_TEXT_RIGHT));
		keyGroup.addComponent(new GUILabel("Y/Z", NK_TEXT_LEFT));
		keyGroup.addComponent(new GUILabel("Point", NK_TEXT_RIGHT));
		window.addComponent(keyGroup);
		
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
