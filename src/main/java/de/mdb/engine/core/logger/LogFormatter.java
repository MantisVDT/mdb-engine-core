package de.mdb.engine.core.logger;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return "<" + record.getLevel().getName() + "> " + new Date(record.getMillis()) + ": " + record.getMessage() + "\n";
	}
	
	
	
}
