package de.mdb.engine.core.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * The default MDB Engine Logger <br>
 * Is created statically in Debug
 * 
 * @author Mattis Boeckle
 *
 */
public class MDBLogger extends Logger {
	
	//TODO: Filelogger
	public MDBLogger(String name, String resourceBundleName) {
		super(name, resourceBundleName);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		this.addHandler(handler);
	}

}
