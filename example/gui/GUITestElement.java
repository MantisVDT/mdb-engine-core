package gui;

import static org.lwjgl.nuklear.Nuklear.NK_RGBA;
import static org.lwjgl.nuklear.Nuklear.NK_TEXT_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_BORDER;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MINIMIZABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_MOVABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_SCALABLE;
import static org.lwjgl.nuklear.Nuklear.NK_WINDOW_TITLE;
import static org.lwjgl.nuklear.Nuklear.nk_begin;
import static org.lwjgl.nuklear.Nuklear.nk_button_label;
import static org.lwjgl.nuklear.Nuklear.nk_color_picker;
import static org.lwjgl.nuklear.Nuklear.nk_combo_begin_color;
import static org.lwjgl.nuklear.Nuklear.nk_combo_end;
import static org.lwjgl.nuklear.Nuklear.nk_end;
import static org.lwjgl.nuklear.Nuklear.nk_label;
import static org.lwjgl.nuklear.Nuklear.nk_layout_row_dynamic;
import static org.lwjgl.nuklear.Nuklear.nk_layout_row_static;
import static org.lwjgl.nuklear.Nuklear.nk_option_label;
import static org.lwjgl.nuklear.Nuklear.nk_property_int;
import static org.lwjgl.nuklear.Nuklear.nk_propertyf;
import static org.lwjgl.nuklear.Nuklear.nk_rect;
import static org.lwjgl.nuklear.Nuklear.nk_rgb_cf;
import static org.lwjgl.nuklear.Nuklear.nk_widget_width;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkColorf;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkRect;
import org.lwjgl.nuklear.NkVec2;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.gui.elements.GUIElement;
import de.mdb.engine.core.logger.Debug;

public class GUITestElement extends GUIElement{

	public GUITestElement(String name) {
		super(name);
	}

	private static final int EASY = 0;
	private static final int HARD = 1;

	NkColorf background = NkColorf.create().r(0.10f).g(0.18f).b(0.24f).a(1.0f);

	private int op = EASY;

	private IntBuffer compression = BufferUtils.createIntBuffer(1).put(0, 20);
	
	@Override
	public void layout(NkContext context) {
		try (MemoryStack stack = stackPush()) {
			NkRect rect = NkRect.mallocStack(stack);
			
			//Starting the GUIElement, setting flags
			if (nk_begin(context, name, nk_rect(x, y, 230, 250, rect), NK_WINDOW_BORDER | NK_WINDOW_MOVABLE
					| NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_TITLE)) {
				
				//Adding button with static layout
				nk_layout_row_static(context, 30, 80, 1);
				if (nk_button_label(context, "button")) {
					Debug.info("button pressed");
				}
				
				//Adding option buttons
				nk_layout_row_dynamic(context, 30, 2);
				if (nk_option_label(context, "easy", op == EASY)) {
					op = EASY;
				}
				if (nk_option_label(context, "hard", op == HARD)) {
					op = HARD;
				}
				
				//Adding compression slider
				nk_layout_row_dynamic(context, 25, 1);
				nk_property_int(context, "Compression:", 0, compression, 100, 10, 1);
				
				//Add the background label
				nk_layout_row_dynamic(context, 20, 1);
				nk_label(context, "background:", NK_TEXT_LEFT);
				
				//Add the color picker
				nk_layout_row_dynamic(context, 25, 1);
				if (nk_combo_begin_color(context, nk_rgb_cf(background, NkColor.mallocStack(stack)),
						NkVec2.mallocStack(stack).set(nk_widget_width(context), 400))) {
					nk_layout_row_dynamic(context, 120, 1);
					nk_color_picker(context, background, NK_RGBA);
					nk_layout_row_dynamic(context, 25, 1);
					background.r(nk_propertyf(context, "#R:", 0, background.r(), 1.0f, 0.01f, 0.005f))
							.g(nk_propertyf(context, "#G:", 0, background.g(), 1.0f, 0.01f, 0.005f))
							.b(nk_propertyf(context, "#B:", 0, background.b(), 1.0f, 0.01f, 0.005f))
							.a(nk_propertyf(context, "#A:", 0, background.a(), 1.0f, 0.01f, 0.005f));
					nk_combo_end(context);
				}
			}
			nk_end(context);
		}
	}
	
	public NkColorf getBackground()
	{
		return background;
	}

}
