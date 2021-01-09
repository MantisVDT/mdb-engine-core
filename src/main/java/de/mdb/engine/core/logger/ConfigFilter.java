package de.mdb.engine.core.logger;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Filters Logging messages to not contain the Config Level 
 * 
 * @author Mattis Boeckle
 *
 */
public class ConfigFilter implements Filter {

	public boolean isLoggable(LogRecord log) {
		return (log.getLevel() != Level.CONFIG);
	}

}
