package gui;

import static org.lwjgl.nuklear.Nuklear.NK_RGBA;
import static org.lwjgl.nuklear.Nuklear.NK_TEXT_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_BORDER;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MINIMIZABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MOVABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_SCALABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_TITLE;
import static org.lwjgl.nuklear.Nuklear.nk_rgb_cf;
import static org.lwjgl.system.MemoryStack.stackPush;

import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.gui.components.GUIButtonLabel;
import de.mdb.engine.core.gui.components.GUIColorPicker;
import de.mdb.engine.core.gui.components.GUIComboColor;
import de.mdb.engine.core.gui.components.GUILabel;
import de.mdb.engine.core.gui.components.GUILayoutRowDynamic;
import de.mdb.engine.core.gui.components.GUILayoutRowStatic;
import de.mdb.engine.core.gui.components.GUIOptionLabel;
import de.mdb.engine.core.gui.components.GUIPropertyFloat;
import de.mdb.engine.core.gui.components.GUIPropertyInt;
import de.mdb.engine.core.gui.components.GUIWindow;
import de.mdb.engine.core.gui.elements.GUIElement;
import de.mdb.engine.core.logger.Debug;

public class GUITestElement extends GUIElement {

	NkColorf background = NkColorf.create().r(0.10f).g(0.18f).b(0.24f).a(1.0f);

	//Creating the top GUIComponents we want to be able to access
	private GUIWindow window;
	private GUIButtonLabel button;
	
	private GUIOptionLabel easyOption;
	private GUIOptionLabel hardOption;
	private GUIOptionLabel mediumOption;
	
	private GUIPropertyInt propertyInt;
	
	private GUIComboColor colorCombo;
	private GUIColorPicker colorPicker;
	private GUIPropertyFloat red;
	private GUIPropertyFloat green;
	private GUIPropertyFloat blue;
	private GUIPropertyFloat alpha;
	
	public GUITestElement(String name, int x, int y) {
		super(name, x, y);
		
		//Create the GUIWindow
		window = new GUIWindow(name, x, y, 280, 250,
				NK_WINDOW_BORDER | NK_WINDOW_MOVABLE | NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_TITLE);
		components.add(window);
		
		//Add the GUIButton
		button = new GUIButtonLabel("Button");
		window.addComponent(new GUILayoutRowStatic(30, 80, 1).addComponent(button));
		
		//Add the Option Buttons
		easyOption = new GUIOptionLabel("Easy", true);
		mediumOption = new GUIOptionLabel("Medium", false);
		hardOption = new GUIOptionLabel("Hard", false);
		window.addComponent(new GUILayoutRowDynamic(30, 3)
				.addComponent(easyOption)
				.addComponent(mediumOption)
				.addComponent(hardOption));
		
		//Add the compression slider
		propertyInt = new GUIPropertyInt("Compression:", 0, 50, 100, 10, 1);
		window.addComponent(new GUILayoutRowDynamic(25, 1).addComponent(propertyInt));
		
		//Add the background label
		window.addComponent(new GUILayoutRowDynamic(20, 1).addComponent(new GUILabel("Color:", NK_TEXT_LEFT)));
		
//		nk_layout_row_dynamic(context, 25, 1);
//		if (nk_combo_begin_color(context, nk_rgb_cf(background, NkColor.mallocStack(stack)),
//				NkVec2.mallocStack(stack).set(nk_widget_width(context), 400))) {
		
		// Add the color picker
		colorCombo = new GUIComboColor(nk_rgb_cf(background, NkColor.create()), (float)window.getWidth(), 400.0f);
		window.addComponent(new GUILayoutRowDynamic(25, 1).addComponent(colorCombo));
		
		colorPicker = new GUIColorPicker(background, NK_RGBA);
		colorCombo.addComponent(new GUILayoutRowDynamic(120, 1).addComponent(colorPicker));

//		background.r(nk_propertyf(context, "#R:", 0, background.r(), 1.0f, 0.01f, 0.005f))
//		.g(nk_propertyf(context, "#G:", 0, background.g(), 1.0f, 0.01f, 0.005f))
//		.b(nk_propertyf(context, "#B:", 0, background.b(), 1.0f, 0.01f, 0.005f))
//		.a(nk_propertyf(context, "#A:", 0, background.a(), 1.0f, 0.01f, 0.005f));
		
		red 	= new GUIPropertyFloat("#R", 0, background.r(), 1.0f, 0.01f, 0.005f);
		green 	= new GUIPropertyFloat("#G", 0, background.g(), 1.0f, 0.01f, 0.005f);
		blue 	= new GUIPropertyFloat("#B", 0, background.b(), 1.0f, 0.01f, 0.005f);
		alpha 	= new GUIPropertyFloat("#A", 0, background.a(), 1.0f, 0.01f, 0.005f);
		
		colorCombo.addComponent(new GUILayoutRowDynamic(25, 1)
				.addComponent(red)
				.addComponent(green)
				.addComponent(blue)
				.addComponent(alpha));
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

			colorCombo.setColor(nk_rgb_cf(background, NkColor.create()));
			background.r(red.getValue());
			background.g(green.getValue());
			background.b(blue.getValue());
			background.a(alpha.getValue());
			
//			nk_layout_row_dynamic(context, 25, 1);
//			if (nk_combo_begin_color(context, nk_rgb_cf(background, NkColor.mallocStack(stack)),
//					NkVec2.mallocStack(stack).set(nk_widget_width(context), 400))) {
//				nk_layout_row_dynamic(context, 120, 1);
//				nk_color_picker(context, background, NK_RGBA);
//				nk_layout_row_dynamic(context, 25, 1);
//				nk_combo_end(context);
//			}
		}
	}

	public NkColorf getBackground() {
		return background;
	}

}
