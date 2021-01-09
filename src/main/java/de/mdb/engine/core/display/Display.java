package de.mdb.engine.core.display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.Platform;

import de.mdb.engine.core.display.event.FramebufferSizeEvent;
import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.input.Input;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.textures.TextureLoader;

/**
 * Represents a Window in the GameEngine
 * and handles GLFW
 * 
 * @author Mattis Boeckle
 *
 */
public class Display implements EventListener {

	//Private Fields
	private String mTitle;
	private int mWidth;
	private int mHeight;

	private boolean mvSync;

	private long mWindow = 0;

	/**
	 * The Constructor to create a Display <br>
	 * 
	 * <b>NOTE</b> vSync does not work correctly right now, it should be enabled at all times to
	 * avoid stuttering
	 * 
	 * @param width The width of the Display in pixels
	 * @param height The height of the Display in pixels
	 * @param title The Title of the Display
	 * @param vSync Whether vSync should be enabled (see note)
	 */
	public Display(int width, int height, String title, boolean vSync) {
		EventManager.registerListener(this);
		mTitle = title;
		mWidth = width;
		mHeight = height;
		mvSync = vSync;
	}

	/**
	 * The EventListener function for the KeyReleasedEvent <br>
	 * Will be called from EventManager
	 * 
	 * @param e The called KeyReleasedEvent
	 */
	@Event
	public void onKeyReleased(KeyReleasedEvent e) {
		if (e.getKeyCode() == GLFW.GLFW_KEY_ESCAPE) {
			glfwSetWindowShouldClose(mWindow, true);
		}
	}
	
	/**
	 * Handles GLFW and the creation of a new Window with Events and Input
	 */
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
		else
			glfwSwapInterval(0);
		
	}
	
	/**
	 * Setter for Image Icon <br>
	 * Wrapper for GLFW
	 * 
	 * @param fileName The file path of a File in res/
	 */
	public void setWindowIcon(String fileName)
	{
		try(MemoryStack stack = MemoryStack.stackPush())
		{
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
			ByteBuffer imageData = TextureLoader.loadImageData(fileName, w, h, comp);
			
			GLFWImage image = GLFWImage.malloc();
			GLFWImage.Buffer imagebf = GLFWImage.malloc(1);
			image.set(w.get(), h.get(), imageData);
			imagebf.put(0, image);
			
			glfwSetWindowIcon(mWindow, imagebf);
			
			image.free();
			imagebf.free();
		}
	}

	/**
	 * Setter for ClipboardString <br>
	 * Wrapper for GLFW
	 * 
	 * @param str The ByteBuffer to set the ClipboardString to
	 */
	public void setClipboardString(ByteBuffer str) {
		glfwSetClipboardString(mWindow, str);
	}

	/**
	 * Getter for ClipboardString <br>
	 * Wrapper for GLFW
	 * 
	 * @return The ClipboardString of the window as a long
	 */
	public long getClipboardString() {
		return nglfwGetClipboardString(mWindow);
	}

	/**
	 * Setter for CursorPosition <br>
	 * Wrapper for GLFW
	 * 
	 * @param x The new x position of the Cursor in pixels
	 * @param y The new y position of the Cursor in pixels
	 */
	public void setCursorPosition(int x, int y) {
		glfwSetCursorPos(mWindow, x, y);
	}

	/**
	 * Setter for InputMode <br>
	 * Wrapper for GLFW
	 * 
	 * @param mode The ID of the mode to be changed 
	 * @param value The new value of the mode
	 */
	public void setInputMode(int mode, int value) {
		glfwSetInputMode(mWindow, mode, value);
	}

	/**
	 * Setter for the Title <br>
	 * Will not change the Variable in the Display class. May be used to dynamically change the Title of the Window
	 * based on for Example FPS, UPS or Frame time <br><br>
	 * GLFW Wrapper
	 * 
	 * @param title The new Title of the Display
	 */
	public void setTitle(String title) {
		glfwSetWindowTitle(mWindow, title);
	}
	
	/**
	 * Getter for the Time since Display creation <br>
	 * GLFW Wrapper
	 * 
	 * @return The Time since display creation
	 */
	public double getTime() {
		return glfwGetTime();
	}

	/**
	 * Swaps the Buffer of the Window <br>
	 * Will be called every render tick <br>
	 * GLFW Wrapper
	 * 
	 */
	public void swapBuffers() {
		glfwSwapBuffers(mWindow);
	}
	
	/**
	 * Cleans up everything related to GLFW <br>
	 * Should be called when the window is closed or the program is ending
	 */
	public void cleanup() {
		glfwDestroyWindow(mWindow);
		glfwTerminate();
	}

	/**
	 * Getter for if the window should close <br>
	 * GLFW Wrapper
	 * 
	 * @return Whether the window should close
	 */
	public boolean windowShouldClose() {
		return glfwWindowShouldClose(mWindow);
	}

	/**
	 * Getter for the GLFW Window
	 * 
	 * @return The GLFW Window as long
	 */
	public long getWindow() {
		return mWindow;
	}

	/**
	 * Getter for the Title variable in Display, not the current value of Title in the Window
	 * 
	 * @return The Title variable stored in Display
	 */
	public String getBaseTitle() {
		return mTitle;
	}

	/**
	 * Getter for width
	 * 
	 * @return The width of the Display in pixels
	 */
	public int getWidth() {
		return mWidth;
	}

	/**
	 * Getter for height
	 * 
	 * @return The height of the Display in pixels
	 */
	public int getHeight() {
		return mHeight;
	}
}
