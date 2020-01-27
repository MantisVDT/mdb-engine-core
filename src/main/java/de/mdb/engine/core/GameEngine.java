package de.mdb.engine.core;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Vector4f;

import de.mdb.engine.core.display.Display;
import de.mdb.engine.core.gui.GUIManager;
import de.mdb.engine.core.logger.Debug;
import de.mdb.engine.core.render.MasterRenderer;
import de.mdb.engine.core.render.Renderer;
import de.mdb.engine.core.util.Clock;

public class GameEngine implements Runnable{
	
	private final Thread gameLoopThread;
	
	private static Display display;
	private IGameLogic gameLogic;
	
	private static MasterRenderer masterRenderer;
	
	private static ArrayList<Renderer> renderers = new ArrayList<>();
	
	public static final Random RANDOM = new Random();
	
	public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic)
	{
		Debug.info("Initializing Engine");
		gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
		initDisplay(windowTitle, width, height, vSync);
		this.gameLogic = gameLogic;
	}
	
	private static synchronized void initDisplay(String windowTitle, int width, int height, boolean vSync)
	{
		display = new Display(width, height, windowTitle, vSync);
	}
	
	public static synchronized void init()
	{
		masterRenderer = new MasterRenderer();
		GameEngine.registerRenderer(masterRenderer);
	}
	
	private void gameLoop()
	{
		double secsPerUpdate = 1.0d / 30.0d;
		double previous = Clock.getTime();
		double steps = 0.0;
		
		Debug.info("Finished initializing Engine");
		
		while(!display.windowShouldClose()) {
			Clock.startFrame();
			GUIManager.startInputFrame();
			
			double current = Clock.getTime();
			double elapsed = current - previous;
			previous = current;
			steps += elapsed;
			
			input();
			
			while(steps >= secsPerUpdate) {
				update();
				steps -= secsPerUpdate;
			}
			
			GUIManager.endInputFrame();
			render();
			
			Clock.stopFrame();
			
			sync(current);
		}
		
		display.cleanup();
	}
	
	public static synchronized void stop()
	{
		glfwSetWindowShouldClose(display.getWindow(), true);
	}
	
	protected void input()
	{
		glfwPollEvents();
		gameLogic.input();
	}
	
	private synchronized void sync(double loopStartTime)
	{
		float loopSlot = 1f / 50.0f;
		double endTime = loopStartTime + loopSlot;
		while(Clock.getTime() < endTime) {
			try {
				wait(1);
			}catch(InterruptedException e) 
			{ 
				Debug.severe(e.getMessage());
				gameLoopThread.interrupt();
			}
		}
	}
	
	public synchronized void start()
	{
		if(System.getProperty("os.name").contains("Mac")) {
			gameLoopThread.run();
		}else {
			gameLoopThread.start();
		}
	}
	
	protected void update()
	{
		gameLogic.update();
	}
	
	protected void render()
	{
		gameLogic.render();
		
		for(Renderer rend : renderers)
		{
			rend.render();
		}
		
		GUIManager.render();
		display.swapBuffers();
	}
	
	public void run() {
		try {
			display.createWindow();
			init();
			gameLogic.init();
			gameLoop();
		}catch(Exception e)
		{
			Debug.severe(e);
		}finally {
			gameLogic.cleanup();
		}
	}
	
	public static void registerRenderer(Renderer renderer)
	{
		renderers.add(renderer);
	}
	
	public static void removeRenderer(Renderer renderer)
	{
		renderers.remove(renderer);
	}
	
	public static void setClearColor(Vector4f color)
	{
		masterRenderer.setClearColor(color);
	}
	
	public static void setClearColor(float color)
	{
		masterRenderer.setClearColor(color);
	}
	
	public static Display getDisplay()
	{
		if(display == null)
		{
			Debug.warning("The Game engine has not been initialized yet");
			return null;
		}else {
			return display;
		}
	}
}
