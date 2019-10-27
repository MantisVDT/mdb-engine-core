package de.mdb.engine.core.util;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Clock {
	
	private static int iterations = 1;
	private static double time = glfwGetTime();
	private static double time_running = 0;
	
	private static double average_frame_time_rounded;
	private static double frameTime_ms;
	
	private Clock() {}
	
	public static void startFrame()
	{
		time = glfwGetTime();
	}
	
	public static void stopFrame()
	{
		// Calculates the time it took for the last frame in milliseconds
		frameTime_ms = (glfwGetTime() - time) * 1000.0f;
					
		//Don't let our numbers overflow
		if(iterations > 200)
		{
			iterations = 0;
			time_running = 0;
		}
					
		//Accumulates the frame time since program start to average it
		time_running += frameTime_ms;
		iterations++;
					
		average_frame_time_rounded = Math.round((time_running / iterations) * 100.0) / 100.0;
	}
	
	public static float getDeltaTime()
	{
		return (float)frameTime_ms / 1000.0f;
	}
	
	public static double getAverageFrameTimeRounded()
	{
		return average_frame_time_rounded;
	}
	
	public static int getAverageFPS()
	{
		return (int)(1000 / average_frame_time_rounded);
	}
	
	public static double getTime()
	{
		return glfwGetTime();
	}
	
}
