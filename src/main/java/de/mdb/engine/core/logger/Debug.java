package de.mdb.engine.core.logger;

import java.util.logging.Level;

public class Debug {
	
	private static final MDBLogger logger = new MDBLogger("CORE", null);
	
	//TODO: Add Filelogger
	
	public static void log(Level level, Object message)
	{
		logger.log(level, getMessage(message));
	}
	
	public static void severe(Object message)
	{
		
		logger.severe(getMessage(message));
	}
	
	public static void warning(Object message)
	{
		logger.warning(getMessage(message));
	}
	
	public static void info(Object message)
	{
		logger.info(getMessage(message));
	}
	
	public static void config(Object message)
	{
		logger.config(getMessage(message));
	}
	
	public static void fine(Object message)
	{
		logger.fine(getMessage(message));
	}
	
	public static void finer(Object message)
	{
		logger.finer(getMessage(message));
	}
	
	public static void finest(Object message)
	{
		logger.finest(getMessage(message));
	}
	
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
