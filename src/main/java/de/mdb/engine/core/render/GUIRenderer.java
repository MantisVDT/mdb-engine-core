package de.mdb.engine.core.render;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DELETE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_END;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_HOME;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_PAGE_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_PAGE_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_MIDDLE;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetFramebufferSize;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSetClipboardString;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.nglfwGetClipboardString;
import static org.lwjgl.nuklear.Nuklear.NK_ANTI_ALIASING_ON;
import static org.lwjgl.nuklear.Nuklear.NK_BUTTON_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_BUTTON_MIDDLE;
import static org.lwjgl.nuklear.Nuklear.NK_BUTTON_RIGHT;
import static org.lwjgl.nuklear.Nuklear.NK_FORMAT_COUNT;
import static org.lwjgl.nuklear.Nuklear.NK_FORMAT_FLOAT;
import static org.lwjgl.nuklear.Nuklear.NK_FORMAT_R8G8B8A8;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_BACKSPACE;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_COPY;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_CUT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_DEL;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_DOWN;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_ENTER;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_PASTE;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_RIGHT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_SCROLL_DOWN;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_SCROLL_END;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_SCROLL_START;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_SCROLL_UP;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_SHIFT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TAB;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_END;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_LINE_END;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_LINE_START;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_REDO;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_START;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_UNDO;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_WORD_LEFT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_TEXT_WORD_RIGHT;
import static org.lwjgl.nuklear.Nuklear.NK_KEY_UP;
import static org.lwjgl.nuklear.Nuklear.NK_UTF_INVALID;
import static org.lwjgl.nuklear.Nuklear.NK_VERTEX_ATTRIBUTE_COUNT;
import static org.lwjgl.nuklear.Nuklear.NK_VERTEX_COLOR;
import static org.lwjgl.nuklear.Nuklear.NK_VERTEX_POSITION;
import static org.lwjgl.nuklear.Nuklear.NK_VERTEX_TEXCOORD;
import static org.lwjgl.nuklear.Nuklear.nk__draw_begin;
import static org.lwjgl.nuklear.Nuklear.nk__draw_next;
import static org.lwjgl.nuklear.Nuklear.nk_buffer_init;
import static org.lwjgl.nuklear.Nuklear.nk_buffer_init_fixed;
import static org.lwjgl.nuklear.Nuklear.nk_clear;
import static org.lwjgl.nuklear.Nuklear.nk_convert;
import static org.lwjgl.nuklear.Nuklear.nk_init;
import static org.lwjgl.nuklear.Nuklear.nk_input_begin;
import static org.lwjgl.nuklear.Nuklear.nk_input_button;
import static org.lwjgl.nuklear.Nuklear.nk_input_end;
import static org.lwjgl.nuklear.Nuklear.nk_input_key;
import static org.lwjgl.nuklear.Nuklear.nk_input_motion;
import static org.lwjgl.nuklear.Nuklear.nk_input_scroll;
import static org.lwjgl.nuklear.Nuklear.nk_input_unicode;
import static org.lwjgl.nuklear.Nuklear.nk_style_set_font;
import static org.lwjgl.nuklear.Nuklear.nnk_strlen;
import static org.lwjgl.nuklear.Nuklear.nnk_textedit_paste;
import static org.lwjgl.nuklear.Nuklear.nnk_utf_decode;
import static org.lwjgl.opengl.GL11C.GL_BLEND;
import static org.lwjgl.opengl.GL11C.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;
import static org.lwjgl.opengl.GL11C.GL_LINEAR;
import static org.lwjgl.opengl.GL11C.GL_NEAREST;
import static org.lwjgl.opengl.GL11C.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.GL_RGBA;
import static org.lwjgl.opengl.GL11C.GL_RGBA8;
import static org.lwjgl.opengl.GL11C.GL_SCISSOR_TEST;
import static org.lwjgl.opengl.GL11C.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11C.GL_UNSIGNED_SHORT;
import static org.lwjgl.opengl.GL11C.glBindTexture;
import static org.lwjgl.opengl.GL11C.glBlendFunc;
import static org.lwjgl.opengl.GL11C.glDisable;
import static org.lwjgl.opengl.GL11C.glDrawElements;
import static org.lwjgl.opengl.GL11C.glEnable;
import static org.lwjgl.opengl.GL11C.glGenTextures;
import static org.lwjgl.opengl.GL11C.glScissor;
import static org.lwjgl.opengl.GL11C.glTexImage2D;
import static org.lwjgl.opengl.GL11C.glTexParameteri;
import static org.lwjgl.opengl.GL12C.GL_UNSIGNED_INT_8_8_8_8_REV;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;
import static org.lwjgl.opengl.GL14C.GL_FUNC_ADD;
import static org.lwjgl.opengl.GL14C.glBlendEquation;
import static org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15C.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL15C.GL_WRITE_ONLY;
import static org.lwjgl.opengl.GL15C.glBindBuffer;
import static org.lwjgl.opengl.GL15C.glBufferData;
import static org.lwjgl.opengl.GL15C.glGenBuffers;
import static org.lwjgl.opengl.GL15C.glMapBuffer;
import static org.lwjgl.opengl.GL15C.glUnmapBuffer;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glUseProgram;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;
import static org.lwjgl.stb.STBTruetype.stbtt_GetCodepointHMetrics;
import static org.lwjgl.stb.STBTruetype.stbtt_GetFontVMetrics;
import static org.lwjgl.stb.STBTruetype.stbtt_GetPackedQuad;
import static org.lwjgl.stb.STBTruetype.stbtt_InitFont;
import static org.lwjgl.stb.STBTruetype.stbtt_PackBegin;
import static org.lwjgl.stb.STBTruetype.stbtt_PackEnd;
import static org.lwjgl.stb.STBTruetype.stbtt_PackFontRange;
import static org.lwjgl.stb.STBTruetype.stbtt_PackSetOversampling;
import static org.lwjgl.stb.STBTruetype.stbtt_ScaleForPixelHeight;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAddress;
import static org.lwjgl.system.MemoryUtil.memAlloc;
import static org.lwjgl.system.MemoryUtil.memCopy;
import static org.lwjgl.system.MemoryUtil.memFree;
import static org.lwjgl.system.MemoryUtil.nmemAllocChecked;
import static org.lwjgl.system.MemoryUtil.nmemFree;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Objects;

import org.joml.Matrix4f;
import org.lwjgl.nuklear.NkAllocator;
import org.lwjgl.nuklear.NkBuffer;
import org.lwjgl.nuklear.NkContext;
import org.lwjgl.nuklear.NkConvertConfig;
import org.lwjgl.nuklear.NkDrawCommand;
import org.lwjgl.nuklear.NkDrawNullTexture;
import org.lwjgl.nuklear.NkDrawVertexLayoutElement;
import org.lwjgl.nuklear.NkUserFont;
import org.lwjgl.nuklear.NkUserFontGlyph;
import org.lwjgl.nuklear.NkVec2;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.gui.elements.GUIElement;
import de.mdb.engine.core.input.events.CharEvent;
import de.mdb.engine.core.input.events.KeyEvent;
import de.mdb.engine.core.input.events.MouseEvent;
import de.mdb.engine.core.input.events.MouseMovedEvent;
import de.mdb.engine.core.input.events.ScrollEvent;
import de.mdb.engine.core.shader.Shader;
import de.mdb.engine.core.shader.ShaderProgram;
import de.mdb.engine.core.util.Data;
import de.mdb.engine.core.util.IOUtil;

/**
 * Handles one NkContext with all of its elements and renders them
 * 
 * @author Mattis Böckle
 *
 */
public class GUIRenderer extends Renderer implements EventListener {

	private NkContext ctx;

	private ArrayList<GUIElement> elements;

	private static final int BUFFER_INITIAL_SIZE = 4 * 1024;

	private static final int MAX_VERTEX_BUFFER = 512 * 1024;
	private static final int MAX_ELEMENT_BUFFER = 128 * 1024;

	private int width;
	private int height;
	private int display_width;
	private int display_height;

	private static final NkAllocator ALLOCATOR;

	private static final NkDrawVertexLayoutElement.Buffer VERTEX_LAYOUT;

	private NkUserFont default_font = NkUserFont.create();

	private NkBuffer cmds = NkBuffer.create();
	private NkDrawNullTexture null_texture = NkDrawNullTexture.create();

	private int vbo, vao, ebo;

	private long window;

	static {
		ALLOCATOR = NkAllocator.create().alloc((handle, old, size) -> nmemAllocChecked(size))
				.mfree((handle, ptr) -> nmemFree(ptr));

		VERTEX_LAYOUT = NkDrawVertexLayoutElement.create(4).position(0).attribute(NK_VERTEX_POSITION)
				.format(NK_FORMAT_FLOAT).offset(0).position(1).attribute(NK_VERTEX_TEXCOORD).format(NK_FORMAT_FLOAT)
				.offset(8).position(2).attribute(NK_VERTEX_COLOR).format(NK_FORMAT_R8G8B8A8).offset(16).position(3)
				.attribute(NK_VERTEX_ATTRIBUTE_COUNT).format(NK_FORMAT_COUNT).offset(0).flip();
	}

	private final ByteBuffer ttf;

	public GUIRenderer(long window) {
		super(null);
		this.window = window;

		EventManager.registerListener(this);
		elements = new ArrayList<>();

		ctx = NkContext.create();
		nk_init(ctx, ALLOCATOR, null);

		try {
			this.ttf = IOUtil.ioResourceToByteBuffer(Data.RES_PATH + "fonts/FiraSans-Regular.ttf", 512 * 1024);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		setupDefaultFont();

		ctx = setupWindow(window);
	}

	private NkContext setupWindow(long window) {
		ctx.clip().copy((handle, text, len) -> {
			if (len == 0) {
				return;
			}

			try (MemoryStack stack = stackPush()) {
				ByteBuffer str = stack.malloc(len + 1);
				memCopy(text, memAddress(str), len);
				str.put(len, (byte) 0);

				glfwSetClipboardString(window, str);
			}
		}).paste((handle, edit) -> {
			long text = nglfwGetClipboardString(window);
			if (text != NULL) {
				nnk_textedit_paste(edit, text, nnk_strlen(text));
			}
		});

		setupContext();
		return ctx;
	}

	private void setupDefaultFont() {
		int BITMAP_W = 1024;
		int BITMAP_H = 1024;

		int FONT_HEIGHT = 18;
		int fontTexID = glGenTextures();

		STBTTFontinfo fontInfo = STBTTFontinfo.create();
		STBTTPackedchar.Buffer cdata = STBTTPackedchar.create(95);

		float scale;
		float descent;

		try (MemoryStack stack = stackPush()) {
			stbtt_InitFont(fontInfo, ttf);
			scale = stbtt_ScaleForPixelHeight(fontInfo, FONT_HEIGHT);

			IntBuffer d = stack.mallocInt(1);
			stbtt_GetFontVMetrics(fontInfo, null, d, null);
			descent = d.get(0) * scale;

			ByteBuffer bitmap = memAlloc(BITMAP_W * BITMAP_H);

			STBTTPackContext pc = STBTTPackContext.mallocStack(stack);
			stbtt_PackBegin(pc, bitmap, BITMAP_W, BITMAP_H, 0, 1, NULL);
			stbtt_PackSetOversampling(pc, 4, 4);
			stbtt_PackFontRange(pc, ttf, 0, FONT_HEIGHT, 32, cdata);
			stbtt_PackEnd(pc);

			// Convert R8 to RGBA8
			ByteBuffer texture = memAlloc(BITMAP_W * BITMAP_H * 4);
			for (int i = 0; i < bitmap.capacity(); i++) {
				texture.putInt((bitmap.get(i) << 24) | 0x00FFFFFF);
			}
			texture.flip();

			glBindTexture(GL_TEXTURE_2D, fontTexID);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, BITMAP_W, BITMAP_H, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV,
					texture);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

			memFree(texture);
			memFree(bitmap);
		}

		default_font.width((handle, h, text, len) -> {
			float text_width = 0;
			try (MemoryStack stack = stackPush()) {
				IntBuffer unicode = stack.mallocInt(1);

				int glyph_len = nnk_utf_decode(text, memAddress(unicode), len);
				int text_len = glyph_len;

				if (glyph_len == 0) {
					return 0;
				}

				IntBuffer advance = stack.mallocInt(1);
				while (text_len <= len && glyph_len != 0) {
					if (unicode.get(0) == NK_UTF_INVALID) {
						break;
					}

					/* query currently drawn glyph information */
					stbtt_GetCodepointHMetrics(fontInfo, unicode.get(0), advance, null);
					text_width += advance.get(0) * scale;

					/* offset next glyph */
					glyph_len = nnk_utf_decode(text + text_len, memAddress(unicode), len - text_len);
					text_len += glyph_len;
				}
			}
			return text_width;
		}).height(FONT_HEIGHT).query((handle, font_height, glyph, codepoint, next_codepoint) -> {
			try (MemoryStack stack = stackPush()) {
				FloatBuffer x = stack.floats(0.0f);
				FloatBuffer y = stack.floats(0.0f);

				STBTTAlignedQuad q = STBTTAlignedQuad.mallocStack(stack);
				IntBuffer advance = stack.mallocInt(1);

				stbtt_GetPackedQuad(cdata, BITMAP_W, BITMAP_H, codepoint - 32, x, y, q, false);
				stbtt_GetCodepointHMetrics(fontInfo, codepoint, advance, null);

				NkUserFontGlyph ufg = NkUserFontGlyph.create(glyph);

				ufg.width(q.x1() - q.x0());
				ufg.height(q.y1() - q.y0());
				ufg.offset().set(q.x0(), q.y0() + (FONT_HEIGHT + descent));
				ufg.xadvance(advance.get(0) * scale);
				ufg.uv(0).set(q.s0(), q.t0());
				ufg.uv(1).set(q.s1(), q.t1());
			}
		}).texture(it -> it.id(fontTexID));

		nk_style_set_font(ctx, default_font);
	}

	private void setupContext() {
		Shader guiVertex = new Shader(Data.RES_PATH + "shaders/guiVertexShader.vs", Shader.VERTEX_SHADER);
		Shader guiFragment = new Shader(Data.RES_PATH + "shaders/guiFragmentShader.fs", Shader.FRAGMENT_SHADER);
		
		shader = new ShaderProgram();
		shader.attachShader(guiVertex);
		shader.attachShader(guiFragment);
		shader.linkShader();
		
		nk_buffer_init(cmds, ALLOCATOR, BUFFER_INITIAL_SIZE);
		
		{
			// buffer setup
			vbo = glGenBuffers();
			ebo = glGenBuffers();
			vao = glGenVertexArrays();

			glBindVertexArray(vao);
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);

			glEnableVertexAttribArray(0);
			glEnableVertexAttribArray(1);
			glEnableVertexAttribArray(2);

			glVertexAttribPointer(0, 2, GL_FLOAT, false, 20, 0);
			glVertexAttribPointer(1, 2, GL_FLOAT, false, 20, 8);
			glVertexAttribPointer(2, 4, GL_UNSIGNED_BYTE, true, 20, 16);
		}

		{
			// null texture setup
			int nullTexID = glGenTextures();

			null_texture.texture().id(nullTexID);
			null_texture.uv().set(0.5f, 0.5f);

			glBindTexture(GL_TEXTURE_2D, nullTexID);
			try (MemoryStack stack = stackPush()) {
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 1, 1, 0, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV,
						stack.ints(0xFFFFFFFF));
			}
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}

		glBindTexture(GL_TEXTURE_2D, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public void addGUIElement(GUIElement e) {
		elements.add(e);
	}

	public void removeGUIElement(GUIElement e) {
		elements.remove(e);
	}

	public void render() {
		newFrame();

		for (GUIElement element : elements) {
			element.layout(ctx);
		}

		try (MemoryStack stack = stackPush()) {
			// setup global state
			glEnable(GL_BLEND);
			glBlendEquation(GL_FUNC_ADD);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glDisable(GL_CULL_FACE);
			glDisable(GL_DEPTH_TEST);
			glEnable(GL_SCISSOR_TEST);
			glActiveTexture(GL_TEXTURE0);

			// setup shader
			shader.use();
			shader.setInt("Texture", 0);
			shader.setMat4("ProjMtx", new Matrix4f(2.0f / width, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f / height,
					0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f));
		}

		drawToScreen(NK_ANTI_ALIASING_ON, MAX_VERTEX_BUFFER, MAX_ELEMENT_BUFFER);

		// return to default OpenGL state
		glUseProgram(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		glEnable(GL_CULL_FACE);
		glDisable(GL_BLEND);
		glDisable(GL_SCISSOR_TEST);

	}

	private void drawToScreen(int AA, int max_vertex_buffer, int max_element_buffer) {
		// convert from command queue into draw list and draw to screen

		// allocate vertex and element buffer
		glBindVertexArray(vao);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);

		glBufferData(GL_ARRAY_BUFFER, max_vertex_buffer, GL_STREAM_DRAW);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, max_element_buffer, GL_STREAM_DRAW);

		// load draw vertices & elements directly into vertex + element buffer
		ByteBuffer vertices = Objects
				.requireNonNull(glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY, max_vertex_buffer, null));
		ByteBuffer elements = Objects
				.requireNonNull(glMapBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_WRITE_ONLY, max_element_buffer, null));
		try (MemoryStack stack = stackPush()) {
			// fill convert configuration
			NkConvertConfig config = NkConvertConfig.callocStack(stack).vertex_layout(VERTEX_LAYOUT).vertex_size(20)
					.vertex_alignment(4).null_texture(null_texture).circle_segment_count(22).curve_segment_count(22)
					.arc_segment_count(22).global_alpha(1.0f).shape_AA(AA).line_AA(AA);

			// setup buffers to load vertices and elements
			NkBuffer vbuf = NkBuffer.mallocStack(stack);
			NkBuffer ebuf = NkBuffer.mallocStack(stack);

			nk_buffer_init_fixed(vbuf, vertices/* , max_vertex_buffer */);
			nk_buffer_init_fixed(ebuf, elements/* , max_element_buffer */);
			nk_convert(ctx, cmds, vbuf, ebuf, config);
		}
		glUnmapBuffer(GL_ELEMENT_ARRAY_BUFFER);
		glUnmapBuffer(GL_ARRAY_BUFFER);

		// iterate over and execute each draw command
		float fb_scale_x = (float) display_width / (float) width;
		float fb_scale_y = (float) display_height / (float) height;

		long offset = NULL;
		for (NkDrawCommand cmd = nk__draw_begin(ctx, cmds); cmd != null; cmd = nk__draw_next(cmd, cmds, ctx)) {
			if (cmd.elem_count() == 0) {
				continue;
			}
			glBindTexture(GL_TEXTURE_2D, cmd.texture().id());
			glScissor((int) (cmd.clip_rect().x() * fb_scale_x),
					(int) ((height - (int) (cmd.clip_rect().y() + cmd.clip_rect().h())) * fb_scale_y),
					(int) (cmd.clip_rect().w() * fb_scale_x), (int) (cmd.clip_rect().h() * fb_scale_y));
			glDrawElements(GL_TRIANGLES, cmd.elem_count(), GL_UNSIGNED_SHORT, offset);
			offset += cmd.elem_count() * 2;
		}
		nk_clear(ctx);
	}

	public void startInputFrame() {
		nk_input_begin(ctx);
	}

	public void endInputFrame() {
		nk_input_end(ctx);
	}

	private void newFrame() {
		try (MemoryStack stack = stackPush()) {
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);

			glfwGetWindowSize(window, w, h);
			width = w.get(0);
			height = h.get(0);

			glfwGetFramebufferSize(window, w, h);
			display_width = w.get(0);
			display_height = h.get(0);
		}
	}

	public NkContext getContext() {
		return ctx;
	}

	@Event
	public void onKeyEvent(KeyEvent e) {
		boolean press = e.isPressed();
		switch (e.getKeyCode()) {
		case GLFW_KEY_ESCAPE:
			glfwSetWindowShouldClose(window, true);
			break;
		case GLFW_KEY_DELETE:
			nk_input_key(ctx, NK_KEY_DEL, press);
			break;
		case GLFW_KEY_ENTER:
			nk_input_key(ctx, NK_KEY_ENTER, press);
			break;
		case GLFW_KEY_TAB:
			nk_input_key(ctx, NK_KEY_TAB, press);
			break;
		case GLFW_KEY_BACKSPACE:
			nk_input_key(ctx, NK_KEY_BACKSPACE, press);
			break;
		case GLFW_KEY_UP:
			nk_input_key(ctx, NK_KEY_UP, press);
			break;
		case GLFW_KEY_DOWN:
			nk_input_key(ctx, NK_KEY_DOWN, press);
			break;
		case GLFW_KEY_HOME:
			nk_input_key(ctx, NK_KEY_TEXT_START, press);
			nk_input_key(ctx, NK_KEY_SCROLL_START, press);
			break;
		case GLFW_KEY_END:
			nk_input_key(ctx, NK_KEY_TEXT_END, press);
			nk_input_key(ctx, NK_KEY_SCROLL_END, press);
			break;
		case GLFW_KEY_PAGE_DOWN:
			nk_input_key(ctx, NK_KEY_SCROLL_DOWN, press);
			break;
		case GLFW_KEY_PAGE_UP:
			nk_input_key(ctx, NK_KEY_SCROLL_UP, press);
			break;
		case GLFW_KEY_LEFT_SHIFT:
		case GLFW_KEY_RIGHT_SHIFT:
			nk_input_key(ctx, NK_KEY_SHIFT, press);
			break;
		case GLFW_KEY_LEFT_CONTROL:
		case GLFW_KEY_RIGHT_CONTROL:
			if (press) {
				nk_input_key(ctx, NK_KEY_COPY, glfwGetKey(window, GLFW_KEY_C) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_PASTE, glfwGetKey(window, GLFW_KEY_P) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_CUT, glfwGetKey(window, GLFW_KEY_X) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_UNDO, glfwGetKey(window, GLFW_KEY_Z) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_REDO, glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_WORD_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_WORD_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_LINE_START, glfwGetKey(window, GLFW_KEY_B) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_TEXT_LINE_END, glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS);
			} else {
				nk_input_key(ctx, NK_KEY_LEFT, glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_RIGHT, glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS);
				nk_input_key(ctx, NK_KEY_COPY, false);
				nk_input_key(ctx, NK_KEY_PASTE, false);
				nk_input_key(ctx, NK_KEY_CUT, false);
				nk_input_key(ctx, NK_KEY_SHIFT, false);
			}
			break;
		}
	}
	
	@Event
	public void onMouseButton(MouseEvent e) {
		try (MemoryStack stack = stackPush()) {
			DoubleBuffer cx = stack.mallocDouble(1);
			DoubleBuffer cy = stack.mallocDouble(1);

			glfwGetCursorPos(window, cx, cy);

			int x = (int) cx.get(0);
			int y = (int) cy.get(0);

			int nkButton;
			switch (e.getKeyCode()) {
			case GLFW_MOUSE_BUTTON_RIGHT:
				nkButton = NK_BUTTON_RIGHT;
				break;
			case GLFW_MOUSE_BUTTON_MIDDLE:
				nkButton = NK_BUTTON_MIDDLE;
				break;
			default:
				nkButton = NK_BUTTON_LEFT;
			}
			if (ctx != null) {
				nk_input_button(ctx, nkButton, x, y, e.isPressed());
			}
		}
	}
	
	@Event
	public void onCharEvent(CharEvent e)
	{
		nk_input_unicode(ctx, e.getCodepoint());
	}
	
	@Event
	public void onScrollEvent(ScrollEvent e)
	{
		try (MemoryStack stack = stackPush()) {
            NkVec2 scroll = NkVec2.mallocStack(stack)
                .x((float)e.getXOffset())
                .y((float)e.getYOffset());
            nk_input_scroll(ctx, scroll);
        }
	}
	
	@Event
	public void onMouseMove(MouseMovedEvent e)
	{
		nk_input_motion(ctx, (int)e.getX(), (int)e.getY());
	}	
}
