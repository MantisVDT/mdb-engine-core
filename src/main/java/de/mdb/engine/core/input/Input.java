package de.mdb.engine.core.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;
import static org.lwjgl.glfw.GLFW.glfwSetCharCallback;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.system.MemoryStack;

import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.input.events.CharEvent;
import de.mdb.engine.core.input.events.KeyEvent;
import de.mdb.engine.core.input.events.KeyPressedEvent;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.input.events.MouseDownEvent;
import de.mdb.engine.core.input.events.MouseEvent;
import de.mdb.engine.core.input.events.MouseMovedEvent;
import de.mdb.engine.core.input.events.MouseUpEvent;
import de.mdb.engine.core.input.events.ScrollEvent;

/**
 * Handles all of the GLFWInputCallbacks for one GLFWWindow and dispatches Events to the EventManager
 * 
 * @author Mattis Boeckle
 *
 */
public class Input implements EventListener{
	
	//Private Fields
	private float mouse_x = 0, mouse_y = 0;
	
	private static long mWindow;
	
	/**
	 * Constructor <br>
	 * Registers the GLFW Callbacks using the windowHandle
	 * 
	 * @param windowHandle The window Handle of the GLFWWindow
	 */
	public Input(long windowHandle)
	{
		mWindow = windowHandle;
		
		glfwSetCursorPosCallback(mWindow, GLFWCursorPosCallback.create((window, xpos, ypos) -> {
			EventManager.executeEvent(new MouseMovedEvent((float)xpos, (float)ypos, mouse_x-(float)xpos, mouse_y-(float)ypos));
			mouse_x = (float)xpos;
			mouse_y = (float)ypos;
		}));
		
		glfwSetMouseButtonCallback(mWindow, GLFWMouseButtonCallback.create((window, button, action, mods) -> {
			if(action == GLFW_PRESS) EventManager.executeEvent(new MouseDownEvent(mouse_x, mouse_y, button));
			if(action == GLFW_RELEASE) EventManager.executeEvent(new MouseUpEvent(mouse_x, mouse_y, button));
			EventManager.executeEvent(new MouseEvent(mouse_x, mouse_y, button, mods, action == GLFW_PRESS));
		}));
		
		glfwSetScrollCallback(mWindow, GLFWScrollCallback.create((window, xoffset, yoffset) -> {
			EventManager.executeEvent(new ScrollEvent(xoffset, yoffset));
		}));
		
		glfwSetKeyCallback(mWindow, GLFWKeyCallback.create((window, key, scancode, action, mods) -> {
			if(action == GLFW_PRESS) {
				EventManager.executeEvent(new KeyPressedEvent(key, mods));
			}
			if(action == GLFW_RELEASE) {
				EventManager.executeEvent(new KeyReleasedEvent(key, mods));
			}
			EventManager.executeEvent(new KeyEvent(key, mods, action == GLFW_PRESS));
		}));
		
		glfwSetCharCallback(mWindow, GLFWCharCallback.create((window, codepoint) -> {
			EventManager.executeEvent(new CharEvent(codepoint));
		}));
	}
	
	//TODO: Move this
	/**
	 * Returns whether a specific Key is currently pressed 
	 * 
	 * @param key The key Code of the Key
	 * @return Whether the key is pressed or not
	 */
	public static boolean isKeyDown(int key) {
		return glfwGetKey(mWindow, key) == GLFW_PRESS;
	}
	
	//TODO: Move this
	/**
	 * Returns whether a specific Mouse Button is currently pressed
	 * 
	 * @param button The key Code of the Button
	 * @return Whether the Button is pressed or not
	 */
	public static boolean isMouseButtonDown(int button)
	{
		return glfwGetMouseButton(mWindow, button) == GLFW_PRESS;
	}
	
	/**
	 * Returns the current Mouse Position as a 2D Vector
	 * 
	 * @return A Vector2f containing the current Mouse Position
	 */
	public static Vector2f getMousePosition()
	{
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			DoubleBuffer bufferX = stack.callocDouble(1);
			DoubleBuffer bufferY = stack.callocDouble(1);
			
			glfwGetCursorPos(mWindow, bufferX, bufferY);
			
			return new Vector2f((float)bufferX.get(0), (float)bufferX.get(0));
		}
	}
	
}
