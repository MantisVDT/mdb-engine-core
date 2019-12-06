package de.mdb.engine.core.logger;

import java.util.logging.Level;

/**
 * Handles all your static logging needs <br>
 * Takes in objects as parameters and will call toString on them <br>
 * Automatically handles exceptions and stacktraces
 * 
 * @author Mattis Boeckle
 *
 */
public class Debug {
	
	private static final MDBLogger logger = new MDBLogger("CORE", null);
	
	//TODO: Add Filelogger
	
	/**
	 * Logs a message at the specified Level
	 * 
	 * @param level The level the Message should be logged at
	 * @param message Object containing the Message
	 */
	public static void log(Level level, Object message)
	{
		logger.log(level, getMessage(message));
	}
	
	/**
	 * Logs the Message at Severe Log Level (Errors, ...)
	 * 
	 * @param message Object containing the Message
	 */
	public static void severe(Object message)
	{
		
		logger.severe(getMessage(message));
	}
	
	/**
	 * Logs the Message at Warning Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void warning(Object message)
	{
		logger.warning(getMessage(message));
	}
	
	/**
	 * Logs the Message at Info Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void info(Object message)
	{
		logger.info(getMessage(message));
	}
	
	/**
	 * Logs the Message at Config Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void config(Object message)
	{
		logger.config(getMessage(message));
	}
	
	/**
	 * Logs the Message at Fine Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void fine(Object message)
	{
		logger.fine(getMessage(message));
	}
	
	/**
	 * Logs the Message at Finer Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void finer(Object message)
	{
		logger.finer(getMessage(message));
	}
	
	/**
	 * Logs the Message at Finest Log Level
	 * 
	 * @param message Object containing the Message
	 */
	public static void finest(Object message)
	{
		logger.finest(getMessage(message));
	}
	
	/**
	 * Handles how a message Object is to be treated <br>
	 * If the Stacktrace should be printed or just an Object
	 * 
	 * @param from The Message Object
	 * @return The Message as a String extracted from the Object
	 */
	private static String getMessage(Object from)
	{
		StringBuilder msg;
		if(from instanceof Exception)
		{
			Exception e = (Exception) from;
			StackTraceElement[] stackTraceElements = e.getStackTrace();
			msg = new StringBuilder(e.getClass().getName() + " " + e.getMessage() + "\n");
			for(StackTraceElement elem : stackTraceElements)
			{
				String fileName = elem.getFileName();
				String location;
				if(fileName != null)
				{
					location = fileName + ":" + elem.getLineNumber();
				}else {
					location = "Unknown Source";
				}
				
				msg.append("	 at " + elem.getClassName() + "." + elem.getMethodName() + "(" + location + ")\n");
			}
		}else {
			msg = new StringBuilder(from.toString());
		}
		return msg.toString();
	}
	
}
