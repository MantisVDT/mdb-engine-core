package de.mdb.engine.core.display;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_DEBUG_CONTEXT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetClipboardString;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.nglfwGetClipboardString;
import static org.lwjgl.opengl.GL11.glViewport;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.Platform;

import de.mdb.engine.core.display.event.FramebufferSizeEvent;
import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.input.Input;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.logger.Debug;

public class Display implements EventListener {

	private String mTitle;
	private int mWidth;
	private int mHeight;

	private boolean mvSync;

	private long mWindow = 0;

	public Display(int width, int height, String title, boolean vSync) {
		EventManager.registerListener(this);
		mTitle = title;
		mWidth = width;
		mHeight = height;
		mvSync = vSync;
	}

	@Event
	public void onKeyReleased(KeyReleasedEvent e) {
		if (e.getKeyCode() == GLFW.GLFW_KEY_ESCAPE) {
			glfwSetWindowShouldClose(mWindow, true);
		}
	}

	public void createWindow() {
		if (!glfwInit()) {
			Debug.severe("Failed to initialize GLFW!");
		}

		glfwSetErrorCallback(new GLFWErrorCallback() {
			public void invoke(int error, long description) {
				Debug.severe("Error: " + description);
			}
		});

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

		if (Platform.get() == Platform.MACOSX) {
			glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		}
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

		mWindow = glfwCreateWindow(mWidth, mHeight, mTitle, 0, 0);
		if (mWindow == 0) {
			Debug.severe("Failed to create the GLFW Window!");
		}

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// Center the window
		glfwSetWindowPos(mWindow, (vidmode.width() - mWidth) / 2, (vidmode.height() - mHeight) / 2);

		glfwMakeContextCurrent(mWindow);
		GL.createCapabilities();

		glfwSetFramebufferSizeCallback(mWindow, new GLFWFramebufferSizeCallbackI() {
			public void invoke(long window, int width, int height) {
				EventManager.executeEvent(new FramebufferSizeEvent(width, height));
				glViewport(0, 0, width, height);
			}
		});

		// Adds the listeners for CursorPos, MouseButton, KeyCallback, CharCallback and
		// ScrollCallback
		new Input(mWindow);

		if (mvSync)
			glfwSwapInterval(1);
	}

	public void setClipboardString(ByteBuffer str) {
		glfwSetClipboardString(mWindow, str);
	}

	public long getClipboardString() {
		return nglfwGetClipboardString(mWindow);
	}

	public void setCursorPosition(int x, int y) {
		glfwSetCursorPos(mWindow, x, y);
	}

	public void setInputMode(int mode, int value) {
		glfwSetInputMode(mWindow, mode, value);
	}

	public void setTitle(String title) {
		GLFW.glfwSetWindowTitle(mWindow, title);
	}

	public double getTime() {
		return glfwGetTime();
	}

	public void swapBuffers() {
		glfwSwapBuffers(mWindow);
	}

	public void cleanup() {
		glfwDestroyWindow(mWindow);
		glfwTerminate();
	}

	public boolean windowShouldClose() {
		return glfwWindowShouldClose(mWindow);
	}

	public long getWindow() {
		return mWindow;
	}

	public String getBaseTitle() {
		return mTitle;
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}
}
