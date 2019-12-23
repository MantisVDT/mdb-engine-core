package gui;

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

import de.mdb.engine.core.gui.components.GUIButtonLabel;
import de.mdb.engine.core.gui.components.GUIColorPicker;
import de.mdb.engine.core.gui.components.GUIGroup;
import de.mdb.engine.core.gui.components.GUILabel;
import de.mdb.engine.core.gui.components.GUIOptionLabel;
import de.mdb.engine.core.gui.components.GUIPropertyInt;
import de.mdb.engine.core.gui.components.GUIWindow;
import de.mdb.engine.core.gui.elements.GUIElement;
import de.mdb.engine.core.logger.Debug;

public class GUITestElement extends GUIElement {

	private NkColorf background = NkColorf.create();

	//Creating the top GUIComponents we want to be able to access
	private GUIWindow window;
	private GUIButtonLabel button;
	
	private GUIOptionLabel easyOption;
	private GUIOptionLabel hardOption;
	private GUIOptionLabel mediumOption;
	
	private GUIPropertyInt propertyInt;
	
	private GUIColorPicker colorPicker;
	
	public GUITestElement(String name, int x, int y) {
		super(name, x, y);
		
		//Initialize the background color
		 background.r(0.10f).g(0.18f).b(0.24f).a(1.0f);
		
		//Create the GUIWindow
		window = new GUIWindow(name, x, y, 280, 250,
				NK_WINDOW_BORDER | NK_WINDOW_MOVABLE | NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_TITLE);
		components.add(window);
		
		//Add the GUIButton
		button = new GUIButtonLabel(25, "Button");
		window.addComponent(button);
		
		//Add the Option Buttons as a Group
		GUIGroup buttons = new GUIGroup(30, 3);
		easyOption   = new GUIOptionLabel("Easy", true);
		mediumOption = new GUIOptionLabel("Medium", false);
		hardOption   = new GUIOptionLabel("Hard", false);
		
		buttons.addComponent(easyOption);
		buttons.addComponent(mediumOption);
		buttons.addComponent(hardOption);
		
		window.addComponent(buttons);
		
		//Add an Integer slider
		propertyInt = new GUIPropertyInt(25, "Number:", 0, 50, 100, 10, 1);
		window.addComponent(propertyInt);
		
		//Add a Label
		window.addComponent(new GUILabel(25, "Color:", NK_TEXT_LEFT));
		
		//Create a ColorPicker and show its sliders
		colorPicker = new GUIColorPicker(25, background, NK_RGBA);
		colorPicker.setHasSliders(true);
		
		window.addComponent(colorPicker);
	}

	@Override
	public void layout(NkContext context) {
		
		style.applyStyle(context);
		
		try (MemoryStack stack = stackPush()) {

			// Starting the GUIElement
			window.layout(context);

			// Adding listener for button presses
			if (button.isPressed()) {
				Debug.info("Button pressed");
			}
			
			// Adding option buttons
			//If one of the buttons switched in the last Frame then the others get disabled
			if(easyOption.isSwitched())
			{
				hardOption.setActive(false);
				mediumOption.setActive(false);
			}
			
			if(mediumOption.isSwitched())
			{
				easyOption.setActive(false);
				hardOption.setActive(false);
			}
			
			if(hardOption.isSwitched())
			{
				easyOption.setActive(false);
				mediumOption.setActive(false);
			}
		}
	}

	public NkColorf getBackground() {
		return background;
	}

}
